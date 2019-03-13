import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { HttpErrorHandler, HandleError } from '../http-error-handler.service';
import { Posting} from './posting.model';
import 'rxjs/add/operator/map'
import {Observable, throwError} from 'rxjs'
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

questionUrl = 'https://dry-coast-23307.herokuapp.com';

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) {


   }


 sumbitQuestion(quest: any): Observable<any> {
  	return this.http.post<any>(this.questionUrl,quest).catch(this.errorHandler);
  }


    errorHandler(error:Response){

     return Observable.throw(error||"SERVER ERROR");
  }
}
