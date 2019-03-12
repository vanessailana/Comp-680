import { Component, OnInit } from '@angular/core';
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
  closeResult: string;
  profile: any;

  currentBtn : HTMLButtonElement;
  dynamicForm: FormGroup;

  levels = ['Novice', 'Intermediate', 'Expert'];

  userInfo:any;
  socialInfo:any;
  eduInfo:any;
  expInfo:any;

  eduId:number;
  expId: number;

  constructor(public auth: AuthService, private modalService: NgbModal,private profileService:ProfileService, private formBuilder: FormBuilder) {

    if (this.auth.userProfile) {
      this.profile = this.auth.userProfile;

      this.profileService.createOrUserInfo(this.profile.email).subscribe(
        res=> 
        { 
          console.log(res); 
          this.userInfo=res;
          this.profileService.createOrSocialInfo(this.userInfo.id).subscribe(
            res => {console.log(res); this.socialInfo = res;},
            err => console.log(err.message),
            ()=> console.log("Complete")
          );

          this.profileService.getEdu(this.userInfo.id).subscribe(
            res => {console.log(res); this.eduInfo = res;},
            err => console.log(err.message),
            ()=> console.log("Complete")
          )

          this.profileService.getExp(this.userInfo.id).subscribe(
            res => {console.log(res); this.expInfo = res;},
            err => console.log(err.message),
            ()=> console.log("Complete")
          )
        },
        err=>console.log(err),
        () => console.log("Complete")
      );
     
      console.log(this.profile.email);
    
    } else {
     
      this.auth.getProfile((err, profile) => {
        this.profile = profile;
        this.profileService.createOrUserInfo(this.profile.email).subscribe(
          res=> 
          { 
            console.log(res); 
            this.userInfo=res;
            this.profileService.createOrSocialInfo(this.userInfo.id).subscribe(
              res => {console.log(res); this.socialInfo = res;},
              err => console.log(err.message),
              ()=> console.log("Complete")
            );

            this.profileService.getEdu(this.userInfo.id).subscribe(
              res => {console.log(res); this.eduInfo = res;},
              err => console.log(err.message),
              ()=> console.log("Complete")
            )

            this.profileService.getExp(this.userInfo.id).subscribe(
              res => {console.log(res); this.expInfo = res;},
              err => console.log(err.message),
              ()=> console.log("Complete")
            )
          },
          err=>console.log(err),
          () => console.log("Complete")
        );

        
        
      });
      
    }

   
   
    

   }




  ngOnInit() {

    

   
    }

   

    onDelete(btn:HTMLButtonElement, id?:number)
    {
        if(btn.name=='delete_edu')
        {
          
          this.profileService.deleteEdu(this.userInfo.id,this.eduInfo[id].id).subscribe(
            res => {console.log(res), this.eduInfo = res;},
            err => console.log(err),
            () => console.log("Complete")
          );
        }else if(btn.name=='delete_exp')
        {
          this.profileService.deleteExp(this.userInfo.id, this.expInfo[id].id).subscribe(
            res => {console.log(res), this.expInfo = res;},
            err => console.log(err),
            () => console.log("Complete")
          );
        }
    }

    onSubmit(submitBtn: HTMLButtonElement , id?:number) { 
      console.log("Button Name"+this.currentBtn.name);

      if(this.currentBtn.name=="edit_info")
      {
        this.profileService.postUser(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.userInfo=res},
          err => console.log(err),
          () => console.log("complete")

        );

      }
      else if(this.currentBtn.name=="add_social")
      {
        this.dynamicForm.value['user']=this.userInfo;
        //this.dynamicForm.value['users_id']=this.userInfo.id;
        console.log(this.dynamicForm.value);
        this.profileService.patchSocial(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.socialInfo=res},
          err => console.log(err),
          () => console.log("complete")

        )
      }
      else if(this.currentBtn.name=="add_edu")
      {
        this.dynamicForm.value['user']=this.userInfo;
        console.log(this.dynamicForm.value);
        this.profileService.postEdu(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.eduInfo=res},
          err => console.log(err),
          () => console.log("complete")

        )
      }
      else if(this.currentBtn.name=="edit_edu")
      {
        this.dynamicForm.value['user']=this.userInfo;
        this.dynamicForm.value['id'] = this.eduId;
        console.log(this.dynamicForm.value);
        this.profileService.patchEdu(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.eduInfo=res},
          err => console.log("Error: "+err),
          () => console.log("complete")

        )
      }
      else if (this.currentBtn.name=="add_exp")
      {
        this.dynamicForm.value['user']=this.userInfo;
        this.dynamicForm.value['id'] = this.expId;
        console.log(this.dynamicForm.value);
        this.profileService.postExp(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.expInfo=res},
          err => console.log(err),
          () => console.log("complete")

        )

      }
      else if(this.currentBtn.name=="edit_exp")
      {
        this.dynamicForm.value['user']=this.userInfo;
        this.dynamicForm.value['id'] = this.expId;
        console.log(this.dynamicForm.value);
        this.profileService.patchExp(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.expInfo=res},
          err => console.log(err),
          () => console.log("complete")

        )
      }

    }
    get diagnostic() { return JSON.stringify(this.dynamicForm.value); }

    //This function is for job seeker modal opening up and the dynamic forms
    //Need to be able to switch html template based on user role 
    open(content : HTMLTemplateElement, btn: HTMLButtonElement, id?: number) {

      this.currentBtn = btn;
      switch(btn.name)
      {
        case "edit_info":
          this.dynamicForm = this.formBuilder.group({
            firstName:[this.userInfo.firstName],
            lastName: [this.userInfo.lastName],
            email: [this.userInfo.email],
            address:[this.userInfo.address],
            city:[this.userInfo.city],
            state:[this.userInfo.state],
            zipCode:[this.userInfo.zipCode],
            number:[this.userInfo.number],
            objective:[this.userInfo.objective],
            resume:[this.userInfo.resume],
            image:[this.userInfo.image]
          });
        break;
        case "add_skill":
          
          this.dynamicForm = this.formBuilder.group({
            skill:["",Validators.required],
            level: ["Novice",Validators.required],
          });
        break;
        case "add_project":
          
          this.dynamicForm = this.formBuilder.group({
            name:["",Validators.required],
            description: ["",Validators.required],
            technologies: [""],
            link: [""],
            start_date: [""],
            end_date: [""]
          });
        break;
        case "add_social":
          
          this.dynamicForm = this.formBuilder.group({
            linkedin:[this.socialInfo.linkedin],
            twitter:[this.socialInfo.twitter],
            facebook:[this.socialInfo.facebook],
            github:[this.socialInfo.github],
            website:[this.socialInfo.website]
          });
        break;

        case "add_edu":
        this.dynamicForm = this.formBuilder.group({
          schoolName:[""],
          degree:[""],
          major:[""],
          startDate:[""],
          endDate:[""],
          description:[""],
          inProgress:[""]
        });
      break;

      case "add_exp":
      this.dynamicForm = this.formBuilder.group({
        title:[""],
        company:[""],
        startDate:[""],
        endDate:[""],
        description:[""],
        current:[""]
      });
    break;

    case "edit_info":
        this.dynamicForm = this.formBuilder.group({
        first_name:[""],
        last_name:[""],
        email:[""],
        address:[""],
        city:[""],
        zip_code:[""],
        phone_number:[""],
        objective:[""],
        resume:[""],
        profile_image:[""]
      });

      break;

      case "edit_edu":
      this.dynamicForm = this.formBuilder.group({
        schoolName:[this.eduInfo[id].schoolName],
        degree:[this.eduInfo[id].degree],
        major:[this.eduInfo[id].major],
        startDate:[this.eduInfo[id].startDate],
        endDate:[this.eduInfo[id].endDate],
        description:[this.eduInfo[id].description],
        inProgress:[this.eduInfo[id].inProgress]
      });
      this.eduId = this.eduInfo[id].id;
      break;
      case "edit_exp":
      this.dynamicForm = this.formBuilder.group({
        title:[this.expInfo[id].title],
        company:[this.expInfo[id].company],
        startDate:[this.expInfo[id].startDate],
        endDate:[this.expInfo[id].endDate],
        description:[this.expInfo[id].description],
        current:[this.expInfo[id].current]
      });
      this.expId = this.expInfo[id].id;
      break;

      }

      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    }
  
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
    }
}
