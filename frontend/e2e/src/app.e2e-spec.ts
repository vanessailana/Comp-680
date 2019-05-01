import { AppPage } from './app.po';
import { browser, element,by,logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Welcome to frontend!');
  });
  it('we should be able to get the view jobs button', () => {
    return element(by.css('[routerLink="/view_jobs"]'));
    
   });
   it('If you are a recruiter you should be able to create a job', () => {
    return element(by.css('[routerLink="/posting"]'));
    
   });
  

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
