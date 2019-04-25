import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
  };

@Injectable({
  providedIn: 'root'
})
export class MyMessageService {

  myMessageUrl = 'http://localhost:8080';  // URL to web api
  

  constructor(private http: HttpClient) { 

  }

  getFromMessage(fromUser:number): Observable<any> {

  	return this.http.get<any>(this.myMessageUrl+"/kafka/from/"+fromUser,httpOptions);
  }

  getToMessage(toUser:number): Observable<any> {

  	return this.http.get<any>(this.myMessageUrl+"/kafka/to/"+toUser,httpOptions);
  }

  getFromUser(from : Array<number>) : Observable<any>
  {
    return this.http.get<any>(this.myMessageUrl+"/kafka/fromUser/"+from,httpOptions);
  }  


}
