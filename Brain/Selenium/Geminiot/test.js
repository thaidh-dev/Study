import { Builder, By, Key, until } from "selenium-webdriver";
import { USERNAME, PASSWORD, TEST_CASES_FOLDER } from "./common/constants.js";
import {
  existsSync,
  mkdirSync,
  readdir,
  readFileSync,
  rmSync,
  writeFile,
  writeFileSync,
} from "fs";
import path from "path";
import chrome from "selenium-webdriver/chrome.js";

let driver;

const openWindow = async () => {
  const options = new chrome.Options();
  options.addArguments("--start-maximized"); // This argument opens Chrome in full-screen mode.
  driver = await new Builder()
    .forBrowser("chrome")
    .setChromeOptions(options)
    .build();
  await driver.manage().setTimeouts({ implicit: 10000 });
};

const onClick = async (xpath) => {
  // Assuming 'driver' is your WebDriver instance and 'element' is the element you want to click
  const element = await driver.wait(
    until.elementIsVisible(driver.findElement(By.xpath(xpath)))
  );
  await element.click();
};

const highlightElement = async (driver, element) => {
  const script = "arguments[0].style.border='3px solid red';";
  await driver.executeScript(script, element);
};

const takeScreenshot = async (driver, folderPath, filename) => {
  await driver.sleep(1000);
  const screenshot = await driver.takeScreenshot();

  if (!existsSync(folderPath)) {
    mkdirSync(folderPath, { recursive: true });
  }
  writeFileSync(`${folderPath}/${filename}`, screenshot, "base64");
};

const removeAndRecreateEvidencesFolder = (evidencesFolder) => {
  if (existsSync(evidencesFolder)) {
    rmSync(evidencesFolder, { recursive: true, force: true });
    mkdirSync(evidencesFolder, { recursive: true });
  }
};

const login = async () => {
  await driver.findElement(By.xpath('//*[@id="username"]')).sendKeys(USERNAME);
  await driver.findElement(By.xpath('//*[@id="password"]')).sendKeys(PASSWORD);
  await onClick('//*[@id="loginbox"]/div/div[2]/form/div[3]/div/div/input');
};

const execTestCase = async (event, evidencesFolder, testCase) => {
  switch (typeof event) {
    case "string":
      await onClick(event);
      break;
    case "object":
      if (event.hasOwnProperty("highlightElements")) {
        for (const e of event.highlightElements) {
          const elementToHighlight = await driver.findElement(By.xpath(e));
          await highlightElement(driver, elementToHighlight);
        }
        await takeScreenshot(driver, evidencesFolder, `${testCase}.png`);
      }
      if (event.hasOwnProperty("select")) {
        await driver
          .findElement(By.xpath(event.select.xpath))
          .sendKeys(event.select.text, Key.ENTER);
      }
      if (event.hasOwnProperty("input")) {
        const input = await driver.findElement(By.xpath(event.input.xpath));
        input.clear();
        input.sendKeys(event.input.text);
      }
      break;
    default:
      break;
  }
};

const main = async (jsonData, needLogin) => {
  const { url, testCaseName, evidencesFolder, testCases } = jsonData;
  const namesOfTestCases = Object.keys(testCases);
  const totalNumTestCases = namesOfTestCases.length;
  const testCasesFailed = [];

  removeAndRecreateEvidencesFolder(evidencesFolder);

  iterateOverTestCases: for (let i = 0; i < totalNumTestCases; i++) {
    await driver.get(url);
    if (needLogin && i === 0) {
      await login();
    }
    await driver.sleep(1000);

    const testCase = namesOfTestCases[i];
    for (const event of testCases[testCase]) {
      try {
        await execTestCase(event, evidencesFolder, testCase);
      } catch (error) {
        testCasesFailed.push(testCase);
        continue iterateOverTestCases;
      }
    }
  }

  return {
    testCaseName,
    totalNumTestCases,
    testCasesFailed,
  };
};

const exportStatisticsFile = (statistics) => {
  let content = "";

  statistics.forEach((statistic) => {
    content += `${statistic.testCaseName}:
     Total number of test cases: ${statistic.totalNumTestCases}
     Number of cases passed: ${
       statistic.totalNumTestCases - statistic.testCasesFailed.length
     }
     Number of cases failed: ${statistic.testCasesFailed.length}
     Test cases failed: ${statistic.testCasesFailed.toString()}
  \n`;
  });

  // Write the content to a file named 'test_results.txt'
  writeFile("./test_results.txt", content, (err) => {
    if (err) {
      console.error(err);
      return;
    }
    console.log("File created successfully!");
  });
};

const readTestCases = () => {
  readdir(TEST_CASES_FOLDER, async (err, files) => {
    if (err) {
      console.error("Error reading folder:", err);
      return;
    }

    await openWindow();

    const statistics = [];
    let needLogin = true;
    for (const file of files) {
      const filePath = path.join(TEST_CASES_FOLDER, file);

      // Check if the file has a .json extension
      if (path.extname(file) === ".json") {
        // Read and parse JSON files
        const jsonData = readFileSync(filePath, {
          encoding: "utf8",
          flag: "r",
        });
        statistics.push(await main(JSON.parse(jsonData), needLogin));
      }
      needLogin = false;
    }

    await driver.quit();
    exportStatisticsFile(statistics);
  });
};

readTestCases();
