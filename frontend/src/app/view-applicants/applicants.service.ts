import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class ApplicantsService {

  constructor(private http: HttpClient) {
  }

  getAll(user : number): Observable<any> {
    return this.http.get('http://localhost:8080/view_applicants/'+user);
  }

}