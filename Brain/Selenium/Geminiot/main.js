import { Builder } from "selenium-webdriver";
import { TEST_CASES_FOLDER } from "./common/constants.js";
import { readdir, readFileSync, writeFile } from "fs";
import path from "path";
import chrome from "selenium-webdriver/chrome.js";
import yaml from "js-yaml";
import { removeAndRecreateEvidencesFolder } from "./events.js";
import { execTestCase } from "./execTestCase.js";

export let driver;

const openWindow = async () => {
  const options = new chrome.Options();
  options.addArguments("--start-maximized"); // This argument opens Chrome in full-screen mode.
  driver = await new Builder()
    .forBrowser("chrome")
    .setChromeOptions(options)
    .build();
  await driver.manage().setTimeouts({ implicit: 10000 });
};

const main = async (yamlData) => {
  const { testCaseName, evidencesFolder, testCases } = yamlData;
  const namesOfTestCases = Object.keys(testCases);
  const totalNumTestCases = namesOfTestCases.length;
  const testCasesFailed = [];

  removeAndRecreateEvidencesFolder(evidencesFolder);

  iterateOverTestCases: for (let i = 0; i < totalNumTestCases; i++) {
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
    for (const file of files) {
      const filePath = path.join(TEST_CASES_FOLDER, file);

      // Check if the file has a .yaml or .yml extension
      if ([".yaml", ".yml"].includes(path.extname(file))) {
        try {
          const yamlData = readFileSync(filePath, {
            encoding: "utf8",
            flag: "r",
          });
          const parsed = yaml.load(yamlData); // Sử dụng js-yaml để parse
          statistics.push(await main(parsed));
        } catch (err) {
          console.error(`Error parsing YAML file ${file}:`, err);
        }
      }
    }

    await driver.quit();
    exportStatisticsFile(statistics);
  });
};

try {
  readTestCases();
} catch (error) {
  driver.quit();
}
