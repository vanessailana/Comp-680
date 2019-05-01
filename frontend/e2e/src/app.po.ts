import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  uniqueRecommendations() {
     return browser.get('/unique_recommendations');
  }

  getUniqueRecommendations() {
    return element.all(by.name('recommendations'));
  }
  getJobs() {
    return element.all(by.name('container-fluid'));
  }

  viewJobs() {
    return browser.get('/view_jobs');
 }

  getUniqueRecTitle() {
    return element.all(by.css('title'));
  }


  getTitleText() {
    return element(by.css('app-root h1')).getText() as Promise<string>;
  }
}
