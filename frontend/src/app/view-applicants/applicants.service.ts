import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class ApplicantsService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    let user = JSON.parse(localStorage.getItem('user'))
    return this.http.get('//localhost:8080/recruiter/my_jobs/'+user.id);
  }

}