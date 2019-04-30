import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthService } from './../auth/auth.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: any;
  levels = ['Novice', 'Intermediate', 'Expert'];

  user : any;
  option : string = 'Personal';

  editMode: boolean = false; 

  userForm : FormGroup;


  constructor(public auth: AuthService, 
    private modalService: NgbModal,
    private profileService:ProfileService, 
    private fb: FormBuilder,
    private router: Router) {



  
   
     
      this.profile = JSON.parse(localStorage.getItem('profile'));

 
        if(this.profile)
        {

          this.profileService.getUser(this.profile.email).subscribe(
            (res) => {this.user = res},
            (err)=> console.log(err)
          )

          this.userForm = this.fb.group({
          firstName:[""],
          lastName:[""],
          email:[""],
          address:[""],
          city:[""],
          zipCode:[""],
          phoneNumber:[""],
          objective:[""],
          resume:[""],
          image:[""],
          skills: this.initSkill(),
          });
       
      }else{

          this.router.navigate(['/view_jobs']);
  
      }
     
    

   }


   initSkill() {
    var formArray = this.fb.array([]);
    formArray.push(this.fb.group({
        skill: [''],
        level: ['Novice']
      }))
    return formArray;
  }

  initExperience() {
    var formArray = this.fb.array([]);
    formArray.push(this.fb.group({
        skill: [''],
        level: ['Novice']
      }))
    return formArray;
  }


   enableEdit()
   {
     this.editMode = true;
     console.log("EditMode:"+this.editMode);
   }
  
  menuOption(str)
  {
    this.option = str;
    this.editMode = false;
    console.log(str);
  }

  ngOnInit() {

   
    var role= localStorage.getItem('roles');

   
    
    if(role="user") 
    {
        this.router.navigateByUrl("/profile");
    }
    else if(role="admin") 
    {
        this.router.navigateByUrl("/posting");
    }else
    {
        this.router.navigateByUrl("/view_jobs");
    }
     
  }
    

}
