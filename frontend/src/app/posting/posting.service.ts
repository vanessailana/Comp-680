import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { HttpErrorHandler, HandleError } from '../http-error-handler.service';

import {Observable, throwError} from 'rxjs'
import { catchError, retry } from 'rxjs/operators';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
  };

@Injectable({
  providedIn: 'root'
})
export class PostingService {

  supportUrl = 'http://localhost:8080/posting';  // URL to web api
  private handleError: HandleError;

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) { 

    this.handleError = httpErrorHandler.createHandleError('PostingService');

  }

  sumbitPost(post: any): Observable<any> {
  	return this.http.post<any>(this.supportUrl,post,httpOptions);
  }

}
