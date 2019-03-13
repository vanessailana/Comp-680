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
 private posting = new Posting();
 
  supportUrl = 'http://localhost:8080/createJob';  // URL to web api
  viewJobs = 'http://localhost:8080/all';  // URL to web api
  deleteJobs = 'http://localhost:8080/jobs';  // URL to web api
  quest='http://localhost:8080/createQuestion'; 
  private handleError: HandleError;

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) { 

    this.handleError = httpErrorHandler.createHandleError('PostingService');

  }

  setter(posting:Posting){
     this.posting=posting;
   }

  getter(){
    return this.posting;
  }

  sumbitPost(post: any): Observable<any> {
  	return this.http.post<any>(this.supportUrl,post,httpOptions);
  }

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/jobs/all',httpOptions);
  }


  getMyJobs(id :any ): Observable<any>
  {
    return this.http.get("http://localhost:8080/recruiter/my_jobs/"+id,httpOptions);

  }

  createQuestion(question: any) :Observable<any> {
    return this.http.post<any>(this.quest,question,httpOptions);

  }





   updatePosting(posting:Posting){

   return this.http.put(this.supportUrl,posting).catch(this.errorHandler);

      }

  deletePosting(post) {
    return this.http.delete(this.deleteJobs + "/"+ post.id);
  }



   errorHandler(error:Response){

     return Observable.throw(error||"SERVER ERROR");
  }

}
