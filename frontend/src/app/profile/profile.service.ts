import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    
  })
};


@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  profileUrl = 'http://localhost:8080/profile';  // URL to web api
  constructor(private http: HttpClient) { 

  }

  getSocial(socialt: any): Observable<any> {
    //return this.http.post<any>(this.profileUrl+"/socials/?id="+,support,httpOptions);
    //Remove this and implement post
    return null;
  }


  getUser(email: any):Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/user",email,httpOptions);
  }

  patchSkills(user: any):Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/user/skills",user,httpOptions);
  }

  deleteSkill(id:number)
  {
    return this.http.delete<any>(this.profileUrl+"/user/skills/delete/"+id,httpOptions);
  }




}
