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

questionUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {


   }


  getQuestions(job_id: number)
  {
    return this.http.get<any>(this.questionUrl+"/questions/"+job_id);
  }
  sumbitAnswers(quest: any): Observable<any> {
  	return this.http.post<any>(this.questionUrl+"/answers",quest);
  }
  
}
