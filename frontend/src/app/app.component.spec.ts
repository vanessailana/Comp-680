import { TestBed, async,inject } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { AppModule } from './app.module';
import { AuthService } from './auth/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';

describe('AuthService', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,HttpClientModule,AppModule
      ],
      declarations: [
       
      ],
    }).compileComponents();
  }));

 it('should be created 2', inject([AuthService], (service: AuthService) => {
    expect(service).toBeTruthy();
  }));



});
