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
 
  supportUrl = 'https://dry-coast-23307.herokuapp.com/createJob';  // URL to web api
  viewJobs = 'https://dry-coast-23307.herokuapp.com/all';  // URL to web api
  //deleteJobs = 'https://dry-coast-23307.herokuapp.com/jobs';  // URL to web api
  deleteJobs='https://localhost:8080/'
  quest= 'https://dry-coast-23307.herokuapp.com/createQuestion'; 
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
    return this.http.get('//localhost:8080/jobs/all/');
  }


  getMyJobs(id :any ): Observable<any>
  {
    return this.http.get("https://localhost:8080/recruiter/my_jobs/"+id,httpOptions);

  }

  createQuestion(question: any) :Observable<any> {
    return this.http.post<any>(this.quest,question,httpOptions);

  }





   updatePosting(posting:Posting){

   return this.http.put(this.supportUrl,posting).catch(this.errorHandler);

      }

  deletePosting(post) {
    return this.http.delete(this.deleteJobs +'recruiter/my_jobs/'+post.id+'/'+post.user_id);
  }



   errorHandler(error:Response){

     return Observable.throw(error||"SERVER ERROR");
  }

}
