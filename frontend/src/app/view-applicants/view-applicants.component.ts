import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {ApplicantsService} from './applicants.service';
import{Router}  from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MyMessageService } from '../my-message/my-message.service';
import { ProfileService } from '../profile/profile.service';

@Component({
  selector: 'app-view-applicants',
  templateUrl: './view-applicants.component.html',
  styleUrls: ['./view-applicants.component.css']
})
export class ViewApplicantsComponent implements OnInit {


  applicants: Array<any>;
  applied: Array<any>;
  applicant: Array <any>;
  p: number = 1;
  order: string = 'createdAt';
  searchText;
  messageForm : FormGroup;

  myId : number;
  profile : any;
  user : any;
  toUserId : number;
  constructor(private applicantService: ApplicantsService,private router: Router,
              private fb: FormBuilder, private messageService:MyMessageService,
              private profileService: ProfileService) {


    
              
                
    this.messageForm = this.fb.group({
      toUser : ["",Validators.required],
      fromUser : ["",Validators.required],
      message : ["", Validators.required],
      sentAtDate: ["",Validators.required]
    })

    this.profile = JSON.parse(localStorage.getItem('profile'));

    if(this.profile)
    {
      this.getUser();
    }
 
    
   

   }

 
 private getUser()
 {
   if(this.profile.email)
   {
   this.profileService.getUser(this.profile.email).subscribe(
     (res)=>{
       this.user = res;
       this.toUserId = -1;
       this.myId = this.user.id;
       this.messageForm = this.fb.group({
         toUser : ["",Validators.required],
         fromUser : ["",Validators.required],
         message : ["", Validators.required],
         sentAtDate: ["",Validators.required]
       })
       this.applicantService.getAll(this.myId).subscribe(data => {

        console.log(data);
        this.applicants=data;
  
     
     
      });
     }
   )
  }
 }

  ngOnInit() {


   
 
  }

  onSubmit(toId)
  {
    this.messageForm.controls['toUser'].setValue(toId);
    this.messageForm.controls['fromUser'].setValue(this.myId);
    this.messageForm.controls['sentAtDate'].setValue(Date.now());

    this.messageService.postMessage(this.messageForm.value).subscribe(
      (res)=>{ console.log(res) },
      (err)=>{},
      ()=>{},
    )
  }

}
