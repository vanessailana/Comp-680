import { AppPage } from './app.po';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import{Router}  from '@angular/router';
import { browser, by, logging, element } from 'protractor';
import { Component, OnInit } from '@angular/core';

import { NgxPermissionsService } from 'ngx-permissions';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms' 
import { HttpClient } from '@angular/common/http';
import { UniqueRecommendationsComponent } from 
'../../src/app/unique-recommendations/unique-recommendations.component';
describe('workspace-project App', () => {
  let page: AppPage;
  let UniqueRecommendationsComponent : UniqueRecommendationsComponent;

  beforeEach(() => {
    page = new AppPage();
    
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Welcome to frontend!');
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getUniqueRecTitle().toEqual('Personalized Recommendations!'));
  });


  //if we want to send a message to support we should get the chat bot
  //or email 

  it('Retrieving all jobs', () => {
    page.viewJobs();
    expect(page.getJobs()), 100000;
  });





  it('Retrieving unique recommendations', () => {
    page.uniqueRecommendations();
    expect(page.getUniqueRecommendations().count()).toBe(50), 6000;
  });

  
 
  });

  it('we should be able to get the view jobs button', () => {


    return element(by.css('[routerLink="/view_jobs"]'));
    
   });

   it('we should be able to get the support page', () => {


    return element(by.css('[routerLink="/support"]'));
    
   });
   

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.ALL
    }));
  });

