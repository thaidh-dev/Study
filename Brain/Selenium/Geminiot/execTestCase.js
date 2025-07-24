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
  checkbox,
} from "./events.js";

export const execTestCase = async (event, evidencesFolder, testCase, index) => {
  switch (typeof event) {
    case "string":
      const elementToClick = await findElementByXPath(event, index);
      await click(elementToClick);
      break;

    case "object":
      const [key] = Object.keys(event);

      switch (key) {
        case "common":
          for (const act of event.common) {
            await execTestCase(act, evidencesFolder, testCase, index);
          }
          break;

        case "highlightElementWithoutPadding":
          for (const e of event.highlightElementWithoutPadding) {
            const elementToHighlight = await findElementByXPath(e, index);
            await highlightElementWithoutPadding(elementToHighlight);
          }
          break;

        case "highlightElementWithPadding":
          for (const e of event.highlightElementWithPadding) {
            const elementToHighlight = await findElementByXPath(e, index);
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
          const elementToSelect = await findElementByXPath(
            event.select.xpath,
            index
          );
          await select(elementToSelect, event.select.text);
          break;

        case "input":
          const input = await findElementByXPath(event.input.xpath, index);
          await inputTypeContent(input, event.input.text);
          break;

        case "url":
          await goToUrl(event.url);
          break;

        case "scrollTo":
          const elementToScroll = await findElementByXPath(
            event.scrollTo.element,
            index
          );
          await scrollTo(elementToScroll, event.scrollTo.position);
          break;

        case "sleep":
          await sleep(event.sleep);
          break;

        case "checkbox":
          const elementToCheck = await findElementByXPath(
            event.checkbox.xpath,
            index
          );
          await checkbox(elementToCheck, event.checkbox.checked);
          break;

        case "foreach":
          const [array, from, to, doActions] = [
            event.foreach.array,
            event.foreach.from,
            event.foreach.to,
            event.foreach.doActions,
          ];

          if (array) {
            for (const item of array) {
              for (const action of doActions) {
                await execTestCase(action, evidencesFolder, testCase, item);
              }
            }
          } else {
            for (let i = from; i <= to; i++) {
              for (const action of doActions) {
                await execTestCase(action, evidencesFolder, testCase, i);
              }
            }
          }

          break;

        default:
          break;
      }

      break;
    default:
      break;
  }
};
