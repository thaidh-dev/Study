import { By, until } from "selenium-webdriver";
import screenshot from "screenshot-desktop";
import { existsSync, mkdirSync, rmSync, writeFileSync } from "fs";
import { driver } from "./main.js";

export const findElementByXPath = async (xpath) => {
  let element = await driver.findElement(By.xpath(xpath));
  return await driver.wait(until.elementIsVisible(element));
};

export const onClick = async (xpath) => {
  // Assuming 'driver' is your WebDriver instance and 'element' is the element you want to click
  const element = await findElementByXPath(xpath);
  await element.click();
};

export const highlightElementWithoutPadding = async (element) => {
  const script = "arguments[0].style.border='3px solid red';";
  await driver.executeScript(script, element);
};

export const highlightElementWithPadding = async (element) => {
  const script = `
    arguments[0].style.boxShadow = '0 0 0 5px white, 0 0 0 8px red';
  `;
  await driver.executeScript(script, element);
};

export const takeScreenshot = async (screenshotType, folderPath, filename) => {
  let img;
  await driver.sleep(1000);

  switch (screenshotType) {
    case "page":
      img = await driver.takeScreenshot();
      break;

    case "desktop":
      img = await screenshot();
      break;

    default:
      break;
  }

  if (!existsSync(folderPath)) {
    mkdirSync(folderPath, { recursive: true });
  }
  writeFileSync(`${folderPath}/${filename}`, img, "base64");
};

export const removeAndRecreateEvidencesFolder = (evidencesFolder) => {
  if (existsSync(evidencesFolder)) {
    rmSync(evidencesFolder, { recursive: true, force: true });
    mkdirSync(evidencesFolder, { recursive: true });
  }
};
