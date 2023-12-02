const { Builder, By, Key, until } = require("selenium-webdriver");
// import { Builder, By, Key, until } from "selenium-webdriver";

(async function example() {
  let driver = await new Builder().forBrowser("chrome").build();
  try {
    await driver.get(
      "http://192.168.56.102/geminiot/explore/?form_data=%7B%22slice_id%22%3A%202222%7D"
    );
    await driver
      .findElement(By.css("#controlSections-panel-query.css-x559s1"))
      .click();
    await driver.findElement(By.css("#rc_select_3")).click();
    await driver.findElement(By.xpath("(//div[text()='MAX'])[2]")).click();
    await driver
      .findElement(By.xpath("(//form[@id='metrics-edit-popover']//button)[3]"))
      .click();
    driver.sleep(1000);
  } finally {
    await driver.quit();
  }
})();
