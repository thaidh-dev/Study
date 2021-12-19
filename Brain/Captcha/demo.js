const puppeteer = require('puppeteer');

const edgeOptions = {
  executablePath: 'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe',
  headless: false,
  // slowMo: 10,
  defaultViewport: null
};

(async () => {
  // const browser = await puppeteer.launch(edgeOptions);
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto('https://www.google.com/recaptcha/api2/demo');

  // captcha
  await page.waitForSelector("iframe")
  let elementHandle = await page.$("div#recaptcha-demo iframe");
  let iframe = await elementHandle.contentFrame();
  // now iframe is like "page", and to click in the button you can do this
  // await iframe.click("#recaptcha-anchor > div.recaptcha-checkbox-border");
  //or
  await iframe.evaluate(() => {
    document.querySelector("#recaptcha-anchor > div.recaptcha-checkbox-border").click();
  });

  // submit
  let submit = await page.$('input#recaptcha-demo-submit');
  await submit.evaluate(i => i.click());
  await page.waitForNavigation();

  await page.screenshot({ path: new Date().getTime() + process.argv[2] + '.png' });
  await browser.close();
})();

