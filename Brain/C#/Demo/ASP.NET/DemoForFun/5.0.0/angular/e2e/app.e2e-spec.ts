import { DemoForFunTemplatePage } from './app.po';

describe('DemoForFun App', function() {
  let page: DemoForFunTemplatePage;

  beforeEach(() => {
    page = new DemoForFunTemplatePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
