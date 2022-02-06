const puppeteer = require('puppeteer');

const extensionPath = 'C:\\Users\\Admin\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Extensions\\admkpobhocmdideidcndkfaeffadipkc\\1.3.1_0'
const edgeOptions = {
  executablePath: 'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe',
  headless: true, // quyết định có mở browser lên cho thấy hay ko
  slowMo: 10,
  defaultViewport: null,
  args: [
    `--disable-extensions-except=${extensionPath}`,
    `--load-extension=${extensionPath}`,
  ]
};

(async () => {
  const browser = await puppeteer.launch(edgeOptions);
  // const browser = await puppeteer.launch();
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

  // pass captcha
  await page.waitForTimeout(3000);
  await page.waitForSelector("iframe");
  const captchaIframe = await (await page.$('iframe[src="https://www.google.com/recaptcha/api2/bframe?hl=en&v=1p3YWy80wlZ7Q8QFR1gjazwU&k=6Le-wvkSAAAAAPBMRTvw0Q4Muexq9bi0DJwx_mJ-"]')).contentFrame();
  await captchaIframe.click('div.help-button-holder');
  await page.waitForTimeout(10000);

  // submit
  let submit = await page.$('input#recaptcha-demo-submit');
  await submit.evaluate(i => i.click());
  await page.waitForNavigation();

  await page.screenshot({ path: new Date().getTime() + process.argv[2] + '.png' });
  await browser.close();
})();

