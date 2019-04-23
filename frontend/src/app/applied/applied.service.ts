import { Injectable } from '@angular/core';
import { HandleError, HttpErrorHandler } from '../http-error-handler.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  url_apply = 'http://localhost:8080';  // URL to web api

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) { 
    this.handleError = httpErrorHandler.createHandleError('PostingService');
  }

  apply(applicant) {
    return this.http.post<any>(this.url_apply+"/apply", applicant, httpOptions);
  }

  hasApplied(job_id:number, user_id: number)
  {
    return this.http.get<any>(this.url_apply+"/answers/"+job_id+"/"+user_id, httpOptions);

  }

  getAppliedJobs(user_id: number)
  {
    return this.http.get<any>(this.url_apply+"/applied/"+user_id,httpOptions);
  }
}
