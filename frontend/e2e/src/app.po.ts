import { browser, by, promise,element, ElementFinder } from 'protractor';

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

     
  getAddJob():ElementFinder {
    return element(by.css('[routerLink="/posting"]'));
}

getMockPaste(): any {
  let paste: any = { location:"new mx",title: "Something  here", job: "Ruby", startCompensation:0, employment_type:"full time" ,status:1}
  return paste;
}

getInputTitle():ElementFinder {
  return element(by.name("title"));
}

getInputLocation(): ElementFinder {
  return element(by.name("location"));
}

getInputDescription(): ElementFinder {
  return element(by.name("description"));

}

getStartComp(): ElementFinder {
  return element(by.name("startCompensation"));

}

getEmploymentType(): ElementFinder {
  return element(by.name("employmentType"));

}

getCreateButton(): ElementFinder {
  return this.addNewJob().element(by.buttonText("Submit"));
}

isCreateButtonPresent() : promise.Promise<boolean> {
  return this.getCreateButton().isPresent();
}

clickCreateButton(): promise.Promise<void> {
  return this.getCreateButton().click();
}
getSaveButton(): ElementFinder {
  return this.addNewJob().element(by.buttonText("Save"));
}

clickSaveButton():promise.Promise<void> {
  return this.getSaveButton().click();
}

getInputPasteValues(): Promise<string[]> {
  let inputTitle, inputLocation, inputstartComp,RTCSessionDescription, 
  employType;

  // Return the input values after the promise is resolved
  // Note that this.getInputTitle().getText doesn't work
  // Use getAttribute('value') instead
  return Promise.all([this.getInputTitle().getAttribute("value"), 
  this.getEmploymentType().getAttribute("value"), 
  this.getInputDescription().getAttribute("value"), 
  this.getStartComp().getAttribute("value"), 
  this.getStartComp().getAttribute("value")])
  
  .then( (values) => {
      return values;
  });
}

getAddPaste():ElementFinder {
  return element(by.tagName('app-add-paste'));
}
getCreatePasteModal(): ElementFinder {
  return this.getAddPaste().element(by.className("alert alert-success"));
}

isCreatePasteModalPresent() : promise.Promise<boolean> {
  return this.getCreatePasteModal().isPresent();
}
getCloseButton(): ElementFinder {
  return this.getAddPaste().element(by.buttonText("Close"));
}

clickCloseButton():promise.Promise<void> {
  return this.getCloseButton().click();
}


addNewJob(): any {
  let newPaste: any = this.getMockPaste();
 
        //Send input values
        this.getInputTitle().sendKeys(newPaste.title);
        this.getInputLocation().sendKeys(newPaste.location);
        this.getStartComp().sendKeys(newPaste.startCompensation);
        this.getInputDescription().sendKeys(newPaste.description);
        this.getEmploymentType().sendKeys(newPaste.employmentType);
      
        //Convert the paste object into an array
        return Object.keys(newPaste).map(key => newPaste[key]);
}

//get inout values 

}