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
    return element.all(by.className('container-fluid'));
  }

  viewJobs() {
    return browser.get('/view_jobs');
 }

  getUniqueRecTitle() {
    return element.all(by.css('title'));
  }

  getAppliedJobs() {
     return browser.get('/applied');
  }
  myAppliedJobs() {
    return element.all(by.className('table table-bordered'));
  }


  viewMyJobs() {
    return browser.get('/my_jobs');
  }

  myJobs(){
    return element.all(by.className('table table-bordered'));
  }

  viewApplicants() {
    return browser.get('/applicants');
  }

  myApplicants(){
    return element.all(by.className('table table-bordered'));
  }

  applicants(){
    return browser.get('/applicants');
  }

  applyJob() {
    return browser.get('/applied');
  }

 

  apply(){
    return element.all(by.className('my-btn-primary btn-lg'));
  }

  applyLink(){
    return browser.get('/view_jobs');
  }


  getTitleText() {
    return element(by.css('app-root h1')).getText() as Promise<string>;
  }
}
