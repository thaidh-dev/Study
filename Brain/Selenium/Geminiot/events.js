import { By, until, Key } from "selenium-webdriver";
import screenshot from "screenshot-desktop";
import { existsSync, mkdirSync, writeFileSync } from "fs";
import { driver } from "./main.js";

/*
Do not use until.elementIsVisible() or element.isDisplayed() for:
  opacity: 0
  visibility: hidden
  pointer-events: none
  elements styled out of layout
*/
export const findElementByXPath = async (xpath, index) => {
  if (index) {
    xpath = xpath.replace("${i}", index);
  }
  let element = await driver.findElement(By.xpath(xpath));
  return await driver.wait(until.elementIsVisible(element));
};

export const click = async (element) => {
  await element.click();
};

export const select = async (element, option) => {
  await element.sendKeys(option, Key.ENTER);
};

export const inputTypeContent = async (input, text) => {
  await input.clear();
  await input.sendKeys(text);
};

export const goToUrl = async (url) => {
  await driver.get(url);
};

export const scrollTo = async (element, position) => {
  switch (position) {
    case "top":
      await driver.executeScript("arguments[0].scrollTop = 0;", element);
      break;

    case "bottom":
      await driver.executeScript(
        "arguments[0].scrollTop = arguments[0].scrollHeight;",
        element
      );
      break;

    default:
      await driver.executeScript(
        `arguments[0].scrollTop = ${position};`,
        element
      );
      break;
  }
};

export const sleep = async (duration) => {
  await driver.sleep(duration);
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

export const checkbox = async (element, checked) => {
  let isSelected = await element.isSelected();
  if (isSelected !== checked) {
    await element.click();
  }
};
