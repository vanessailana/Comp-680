import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { HttpErrorHandler, HandleError } from '../http-error-handler.service';
import { Posting} from './posting.model';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
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

  postingUrl = 'http://localhost:8080';  // URL to web api
  
  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) { 

  }

  sumbitPost(post: any): Observable<any> {
  	return this.http.post<any>(this.postingUrl+"/createJob",post,httpOptions);
  }

  getAll(): Observable<any> {
    return this.http.get(this.postingUrl+"/jobs/all");
  }

  getMyJobs(): Observable<any>
  {
    let user = JSON.parse(localStorage.getItem('user'));
    return this.http.get(this.postingUrl+"/recruiter/my_jobs/"+user.id);
  }

  createQuestion(question: any) :Observable<any> {
    return this.http.post<any>(this.postingUrl+"/createQuestion",question,httpOptions);
  }

  updatePosting(posting: any){
    return this.http.put(this.postingUrl,posting);
  }

  deletePosting(post : any) {
    return this.http.delete(this.postingUrl +'/recruiter/my_jobs/'+post.id+'/'+post.user_id);
  }





}
