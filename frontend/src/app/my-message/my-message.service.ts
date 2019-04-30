import { Injectable } from '@angular/core';
import { Observable, interval } from 'rxjs';
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

  getMessages(userId:number): Observable<any> {
  	return interval(300).flatMap(()=>{
      return this.http.get<any>(this.myMessageUrl+"/kafka/messages/"+userId,httpOptions)
    });
  }


  getFromUser(from : Array<number>) : Observable<any>
  {
    return this.http.get<any>(this.myMessageUrl+"/kafka/fromUser/"+from,httpOptions);
  }

  postMessage(message : any)
  {
    return this.http.post<any>(this.myMessageUrl+"/kafka/publish",message,httpOptions);
  }


}
