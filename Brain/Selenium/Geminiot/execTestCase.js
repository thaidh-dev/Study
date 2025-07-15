import {
  findElementByXPath,
  click,
  select,
  inputTypeContent,
  goToUrl,
  scrollTo,
  sleep,
  highlightElementWithoutPadding,
  highlightElementWithPadding,
  takeScreenshot,
} from "./events.js";

export const execTestCase = async (event, evidencesFolder, testCase) => {
  switch (typeof event) {
    case "string":
      await click(event);
      break;

    case "object":
      if (event.hasOwnProperty("common")) {
        for (const act of event.common) {
          await execTestCase(act, evidencesFolder, testCase);
        }
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
        const elementToSelect = await findElementByXPath(event.select.xpath);
        await select(elementToSelect, event.select.text);
      }

      if (event.hasOwnProperty("input")) {
        const input = await findElementByXPath(event.input.xpath);
        await inputTypeContent(input, event.input.text);
      }

      if (event.hasOwnProperty("url")) {
        await goToUrl(event.url);
      }

      if (event.hasOwnProperty("scrollTo")) {
        const element = await findElementByXPath(event.scrollTo.element);
        await scrollTo(element, event.scrollTo.position);
      }

      if (event.hasOwnProperty("sleep")) {
        await sleep(event.sleep);
      }

      break;
    default:
      break;
  }
};
