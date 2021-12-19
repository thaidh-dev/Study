const puppeteer = require('puppeteer');

const edgeOptions = {
  executablePath: 'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe',
  headless: false,
  slowMo: 50,
  defaultViewport: null
};

(async () => {
  // const browser = await puppeteer.launch(edgeOptions);
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto('https://dtsvn.com/form-check-in-check-out/');

  // input
  await (await page.$('[name="ma-nhan-vien"]')).type('DFT20');
  await (await page.$('[name="ho-va-ten"]')).type('Dương Hồng Thái');
  await (await page.$('[name="Email"]')).type('duonghongthai07@gmail.com');
  await (await page.$('[name="bo-phan"]')).select('DEV');
  let now = new Date();
  let date = now.getDate();
  let month = now.getMonth() + 1;
  let year = now.getFullYear();
  await (await page.$('[name="ngay"]')).type(date + '/' + month + '/' + year);
  await (await page.$('[name="cham-cong"]')).select('Check ' + process.argv[2]);

  // captcha
  await page.waitForSelector("iframe")
  let elementHandle = await page.$("div.wpcf7-form-control-wrap iframe");
  let iframe = await elementHandle.contentFrame();
  // now iframe is like "page", and to click in the button you can do this
  await iframe.evaluate(() => {
    document.querySelector("#recaptcha-anchor > div.recaptcha-checkbox-border").click();
  });

  // submit
  let submit = await page.$('input.wpcf7-form-control.wpcf7-submit');
  await submit.evaluate(i => i.click());
  // await page.waitForNavigation();

  // await page.screenshot({ path: new Date().getTime() + '.png' });
  await browser.close();
})();

