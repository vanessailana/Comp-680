import { Injectable } from '@angular/core';
import { HandleError, HttpErrorHandler } from '../http-error-handler.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AppliedService {

  private handleError: HandleError;

  url_apply = 'http://localhost:8080/apply';  // URL to web api

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) { 
    this.handleError = httpErrorHandler.createHandleError('PostingService');
  }

  apply(applicant) {
    return this.http.post<any>(this.url_apply, applicant, httpOptions);
  }
}
