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

  profileUrl = 'https://dry-coast-23307.herokuapp.com/profile';  // URL to web api
  constructor(private http: HttpClient) { 

  }

  getSocial(socialt: any): Observable<any> {
    //return this.http.post<any>(this.profileUrl+"/socials/?id="+,support,httpOptions);
    //Remove this and implement post
    return null;
  }


  createOrUserInfo(email: any):Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/user",email,httpOptions);
  }

  postUser(user: any): Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/create",user,httpOptions);
  }

  createOrSocialInfo(userId : any): Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/social",userId, httpOptions)
  }

  patchSocial(social : any ): Observable<any>
  {
    return this.http.post<any>(this.profileUrl+"/social/patch",social,httpOptions);
  }


  getEdu(id : any):Observable<any>
  {
    return this.http.get<any>(this.profileUrl+"/edu/"+id,httpOptions);
  }

  postEdu(edu: any)
  {
    return this.http.post<any>(this.profileUrl+"/edu/post",edu,httpOptions);
  }


  patchEdu(edu: any)
  {
    return this.http.post<any>(this.profileUrl+"/patchEdu",edu,httpOptions);
  }

  deleteEdu(users_id: any, edu_id: any)
  {
    
    return this.http.delete<any>(this.profileUrl+"/edu/delete/"+users_id+"/"+edu_id, httpOptions);
  }


  getExp(id : any):Observable<any>
  {
    return this.http.get<any>(this.profileUrl+"/experience/"+id,httpOptions);
  }

  postExp(exp: any)
  {
    return this.http.post<any>(this.profileUrl+"/experience/post",exp,httpOptions);
  }

  deleteExp(users_id: any, exp_id: any)
  {
    
    return this.http.delete<any>(this.profileUrl+"/experience/delete/"+users_id+"/"+exp_id, httpOptions);
  }

  patchExp(exp: any)
  {
    return this.http.post<any>(this.profileUrl+"/patchExp",exp,httpOptions);
  }


  getSkill(id : any):Observable<any>
  {
    return this.http.get<any>(this.profileUrl+"/skill/"+id,httpOptions);
  }

  postSkill(exp: any)
  {
    return this.http.post<any>(this.profileUrl+"/skill/post",exp,httpOptions);
  }

  deleteSkill(users_id: any, skill_id: any)
  {
    
    return this.http.delete<any>(this.profileUrl+"/skill/delete/"+users_id+"/"+skill_id, httpOptions);
  }

  patchSkill(exp: any)
  {
    return this.http.post<any>(this.profileUrl+"/skill/patch",exp,httpOptions);
  }


  getProject(id : any):Observable<any>
  {
    return this.http.get<any>(this.profileUrl+"/project/"+id,httpOptions);
  }

  postProject(project: any)
  {
    return this.http.post<any>(this.profileUrl+"/project/post",project,httpOptions);
  }

  deleteProject(users_id: any, project_id: any)
  {
    
    return this.http.delete<any>(this.profileUrl+"/project/delete/"+users_id+"/"+project_id, httpOptions);
  }

  patchProject(project: any)
  {
    return this.http.post<any>(this.profileUrl+"/project/patch",project,httpOptions);
  }





}
