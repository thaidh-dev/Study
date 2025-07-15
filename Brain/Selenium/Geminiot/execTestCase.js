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
      const [key] = Object.keys(event);

      switch (key) {
        case "common":
          for (const act of event.common) {
            await execTestCase(act, evidencesFolder, testCase);
          }
          break;

        case "highlightElementWithoutPadding":
          for (const e of event.highlightElementWithoutPadding) {
            const elementToHighlight = await findElementByXPath(e);
            await highlightElementWithoutPadding(elementToHighlight);
          }
          break;

        case "highlightElementWithPadding":
          for (const e of event.highlightElementWithPadding) {
            const elementToHighlight = await findElementByXPath(e);
            await highlightElementWithPadding(elementToHighlight);
          }
          break;

        case "takeScreenshot":
          await takeScreenshot(
            event.takeScreenshot,
            `${evidencesFolder}\\${testCase}`,
            `${Date.now().toString()}.png`
          );
          break;

        case "select":
          const elementToSelect = await findElementByXPath(event.select.xpath);
          await select(elementToSelect, event.select.text);
          break;

        case "input":
          const input = await findElementByXPath(event.input.xpath);
          await inputTypeContent(input, event.input.text);
          break;

        case "url":
          await goToUrl(event.url);
          break;

        case "scrollTo":
          const element = await findElementByXPath(event.scrollTo.element);
          await scrollTo(element, event.scrollTo.position);
          break;

        case "sleep":
          await sleep(event.sleep);
          break;

        default:
          break;
      }

      break;
    default:
      break;
  }
};
