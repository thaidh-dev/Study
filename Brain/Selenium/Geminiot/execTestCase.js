import { Key } from "selenium-webdriver";
import {
  findElementByXPath,
  onClick,
  highlightElementWithoutPadding,
  highlightElementWithPadding,
  takeScreenshot,
} from "./events.js";
import { driver } from "./main.js";

export const execTestCase = async (
  event,
  evidencesFolder,
  loginActs,
  commonActs,
  testCase
) => {
  switch (typeof event) {
    case "string":
      await onClick(event);
      break;

    case "object":
      if (event.hasOwnProperty("useCommonAct")) {
        for (const act of commonActs[event.useCommonAct]) {
          await execTestCase(
            act,
            evidencesFolder,
            loginActs,
            commonActs,
            testCase
          );
        }
      }

      if (event.hasOwnProperty("useLogin")) {
        for (const action of loginActs[event.useLogin]) {
          await execTestCase(
            action,
            evidencesFolder,
            loginActs,
            commonActs,
            testCase
          );
        }
        await driver.sleep(1000);
      }

      if (event.hasOwnProperty("highlightElementWithoutPadding")) {
        for (const e of event.highlightElementWithoutPadding) {
          const elementToHighlight = await findElementByXPath(e);
          await highlightElementWithoutPadding(elementToHighlight);
        }
      }

      if (event.hasOwnProperty("highlightElementWithPadding")) {
        for (const e of event.highlightElementWithPadding) {
          const elementToHighlight = await findElementByXPath(e);
          await highlightElementWithPadding(elementToHighlight);
        }
      }

      if (event.hasOwnProperty("takeScreenshot")) {
        await takeScreenshot(
          event.takeScreenshot,
          `${evidencesFolder}\\${testCase}`,
          `${Date.now().toString()}.png`
        );
      }

      if (event.hasOwnProperty("select")) {
        await findElementByXPath(event.select.xpath).sendKeys(
          event.select.text,
          Key.ENTER
        );
      }

      if (event.hasOwnProperty("input")) {
        const input = await findElementByXPath(event.input.xpath);
        input.clear();
        input.sendKeys(event.input.text);
      }

      if (event.hasOwnProperty("url")) {
        await driver.get(event.url);
      }

      if (event.hasOwnProperty("scrollTo")) {
        const element = await findElementByXPath(event.scrollTo.element);
        switch (event.scrollTo.position) {
          case "top":
            await driver.executeScript("arguments[0].scrollTop = 25;", element);
            break;

          case "bottom":
            await driver.executeScript(
              "arguments[0].scrollTop = arguments[0].scrollHeight / 3;",
              element
            );
            break;

          default:
            break;
        }
      }

      if (event.hasOwnProperty("sleep")) {
        await driver.sleep(event.sleep);
      }

      break;
    default:
      break;
  }
};
