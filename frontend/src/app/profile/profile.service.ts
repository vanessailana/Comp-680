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

  profileUrl = 'http://localhost:8080';  // URL to web api
  constructor(private http: HttpClient) { 

  }


  getUser(email: any):Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/profile/user",email,httpOptions);
  }



  patchUser(user : any)
  {
    return this.http.post(this.profileUrl+"/profile/patch",user,httpOptions);
  }

  getEdu(user_id: number)
  {
    return this.http.get(this.profileUrl+"/profile/edu/"+user_id,httpOptions);
  }

  getExp(user_id: number)
  {
    return this.http.get(this.profileUrl+"/profile/exp/"+user_id,httpOptions);
  }

  getPro(user_id: number)
  {
    return this.http.get(this.profileUrl+"/profile/pro/"+user_id,httpOptions);
  }



  getSkill(user_id: number)
  {
    return this.http.get(this.profileUrl+"/profile/skill/"+user_id,httpOptions);
  }

  getSocial(user_id: number)
  {
      return this.http.get(this.profileUrl+"/profile/social/"+user_id).toPromise();
  }


  patchPro(list:any , user_id : number)
  {
    return this.http.post(this.profileUrl+"/profile/pro/"+user_id,list,httpOptions);
  }
  patchExp(list, user_id: number)
  {
    return this.http.post(this.profileUrl+"/profile/exp/"+user_id,list,httpOptions);
  }

  patchSkill(list, user_id: number)
  {
    return this.http.post(this.profileUrl+"/profile/skill/"+user_id , list ,httpOptions);
  }

  patchEdu(list, user_id: number)
  {
    return this.http.post(this.profileUrl+"/profile/edu/"+user_id,list,httpOptions);
  }

  patchSocial(social: any, user_id: number)
  {
    return this.http.post(this.profileUrl+"/profile/social/"+user_id,social,httpOptions);
  }





}
