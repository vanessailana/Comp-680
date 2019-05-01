import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthService } from './../auth/auth.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ProfileService } from './profile.service';

JSON = require('circular-json')
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
  editMode: string = "readonly"; 
  userForm : FormGroup;
  display : boolean = false;
  
  educations : any;
  experiences: any;
  projects : any;
  social : any;
  skills :any;

  success : boolean = false;

  constructor(public auth: AuthService, 
    private modalService: NgbModal,
    private profileService:ProfileService, 
    private fb: FormBuilder,
    private router: Router) {



     
      try{
      this.profile = JSON.parse(localStorage.getItem('profile'));

      }catch{

      }
 
        if(this.profile)
        {

          this.profileService.getUser(this.profile.email).subscribe(
            (res) => {this.user = res; console.log("user"+ JSON.stringify(this.user))
          
            this.userForm = this.fb.group({
              user: this.fb.group({id:[this.user.id],
              firstName:[this.user.firstName],
              lastName:[this.user.lastName],
              email:[this.profile.email],
              address:[this.user.address],
              city:[this.user.city],
              state:[this.user.state],
              zipCode:[this.user.zipCode],
              phoneNumber:[this.user.phoneNumber],
              objective:[this.user.objective],
              resume:[this.user.resume],
              image:[this.user.image]
              }),
              skills: this.initSkill(),
              experiences: this.initExperience(),
              projects:this.initProjects(),
              educations:this.initEducation(),
              social: this.initSocial()
              });

          },
            (err)=> console.log(err),
            ()=>{
             

                this.display = true; 

                //this.userForm.controls.skill.setValue(this.initSkill());
                //this.userForm.controls.educations.setValue(this.initEducation());
            }
          )

         
       
      }else{

          this.router.navigate(['/view_jobs']);
  
      }
     

    

   }


   initProjects(){
    var formArray = this.fb.array([]);
    this.profileService.getPro(this.user.id).subscribe(
      (res)=>{
        if(res)
        {
          this.projects = res;

          this.projects.forEach((e)=>{
            formArray.push(this.fb.group({
              id: [e.id],
              description:[e.schoolName],
              endDate:[e.endDate],
              startDate:[e.startDate],
              link: [e.link],
              techonologies:[e.techonologies]
              }))
            });

          }else{
            formArray.push(this.fb.group({
              description:[''],
              endDate:[''],
              startDate:[''],
              link: [''],
              techonologies:['']
            }))

          }
        })

     return formArray;

   }

   initSocial(){
     var control = null;

     this.profileService.getSocial(this.user.id).subscribe(
       (res)=>{
         if(res){
           this.social = res;
           control = this.fb.group({
            id : [this.social.id],
            facebook:[this.social.facebook],
            github:[this.social.github],
            linkedin:[this.social.linkedin],
            twitter:[this.social.twitter],
            website:[this.social.website]
            })
    
           
    

         }else{
          control = this.fb.group({
            facebook:[''],
            github:[''],
            linkedin:[''],
            twitter:[''],
            website:['']
            })
      
            
         }
       },
       (err)=>console.log(err),
       ()=>{
         return control
       }
     )


     
   }


   initSkill() {
    var formArray = this.fb.array([]);

    this.profileService.getSkill(this.user.id).subscribe(
      (res)=>{
        this.skills = res;

        this.skills.forEach((e)=>{
          formArray.push(this.fb.group({
            skill: [e.skill],
            level: [e.level]
          }))
        })
      }
    )

    formArray.push(this.fb.group({
      skill: [''],
      level: ['']
    }))
    

    return formArray;
  }


  addSkill()
  {
    const control = <FormArray>this.userForm.controls.skills;
    control.push(this.fb.group({
      skill: [''],
      level: ['']
    }))
  }

  removeSkill(i)
  {
    const control = <FormArray>this.userForm.controls.skills;
    control.removeAt(i);
  }

  initEducation() {
    var formArray = this.fb.array([]);
    


    this.profileService.getEdu(this.user.id).subscribe(
      (res)=>{
        this.educations = res;
        if(res){
        this.educations.forEach(
          (e)=>{
            formArray.push(this.fb.group({
              id : [e.id],
              schoolName:[e.schoolName],
              degree:[e.degree],
              major:[e.major],
              startDate:[e.startDate],
              endDate:[e.endDate],
              description:[e.description],
              inProgress:[e.inProgress]
              }))
          }
        )
      }else{
        formArray.push(this.fb.group({
          schoolName:[""],
          degree:[""],
          major:[""],
          startDate:[""],
          endDate:[""],
          description:[""],
          inProgress:[""]
          }))

      }
      }
    )
      
    return formArray;
      
  }




  initExperience() {
    var formArray = this.fb.array([]);

    this.profileService.getExp(this.user.id).subscribe(
      (res)=>{
        if(res)
        {
          this.experiences = res;
          this.experiences.forEach((e)=>{
            formArray.push(this.fb.group({
              id : [e.id],
              company:[e.company],
              current:[e.current],
              description:[e.description],
              endDate:[e.startDate],
              startDate:[e.endDate],
              title:[e.title]
              }))
            });
            return formArray
        }
      }
    )

    formArray.push(this.fb.group({
        company:[""],
        current:[""],
        description:[""],
        endDate:[""],
        startDate:[""],
        title:[""]
        }))
  
    return formArray;
  }


   enableEdit()
   {
     if(this.editMode=="")
     {
       this.editMode = "readonly";
     }else{
       this.editMode = "";
     }
     console.log("EditMode:"+this.editMode);

    
     
   }


  
  menuOption(str)
  {
    this.option = str;
    console.log(str);
    this.success = false;
     this.userForm = this.fb.group({
              user: this.fb.group({id:[this.user.id],
              firstName:[this.user.firstName],
              lastName:[this.user.lastName],
              email:[this.profile.email],
              address:[this.user.address],
              city:[this.user.city],
              state:[this.user.state],
              zipCode:[this.user.zipCode],
              phoneNumber:[this.user.phoneNumber],
              objective:[this.user.objective],
              resume:[this.user.resume],
              image:[this.user.image]
              }),
              skills: this.initSkill(),
              experiences: this.initExperience(),
              projects:this.initProjects(),
              educations:this.initEducation(),
              social: this.initSocial()
              });
    
  }


  addProject()
  {
   
    const control = <FormArray>this.userForm.controls.projects;
    control.push(this.fb.group({
      description:[''],
      endDate:[''],
      startDate:[''],
      link: [''],
      techonologies:['']
      ,
      user:[this.user]
    }))
  }


  removeProject(i)
  {
    const control = <FormArray>this.userForm.controls.projects;
    control.removeAt(i);
  }
  addExperience()
  {
   
    const control = <FormArray>this.userForm.controls.experiences;
    control.push(this.fb.group({
      company:[""],
      current:[""],
      description:[""],
      endDate:[""],
      startDate:[""],
      title:[""],
      user:[this.user]
      })
    )
  }


  removeExperience(i)
  {
    const control = <FormArray>this.userForm.controls.experiences;
    control.removeAt(i)
  }


  addEducation()
  {
    const control = <FormArray>this.userForm.controls.educations;
    
    control.push(this.fb.group({
      schoolName:[""],
      degree:[""],
      major:[""],
      startDate:[""],
      endDate:[""],
      description:[""],
      inProgress:[""],
      user:[this.user]
      }));

  }

  saveEdit()
  {
    if(this.option=='Profile'){
    this.profileService.patchUser(this.userForm.controls.user.value).subscribe(
      (res)=>this.user=res,
      (err)=>console.log(err),
      ()=>{
        console.log("sucess");
        this.success = true;
      }
    )

    
    }else if(this.option=='Project'){

      this.profileService.patchPro(this.userForm.controls.projects.value,this.user.id).subscribe(
        (res)=>this.projects=res
        
      )
    }
    else if(this.option=='Experience')
    {
      this.profileService.patchExp(this.userForm.controls.experiences.value,this.user.id).subscribe(
        (res)=>this.experiences=res
      )
    }else if(this.option=='Skill')
    {
    
      this.profileService.patchSkill(this.userForm.controls.skills.value,this.user.id).subscribe(
        (res)=> this.skills = res
      )
    }
    else if(this.option=='Social')
    {
      
      var value = this.userForm.controls.social.value;
      this.profileService.patchSocial(value,this.user.id).subscribe(
        (res)=> this.social = res
      )
    }else if(this.option=='Education')
    {
      this.profileService.patchEdu(this.userForm.controls.educations.value,this.user.id).subscribe(
        (res)=> this.educations = res
      )
    }


  }
  get diagnostic()
  {
    return JSON.stringify(this.userForm.value);
  }
  removeEducation(i)
  {
    const control = <FormArray>this.userForm.controls.educations;
    control.removeAt(i)
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
