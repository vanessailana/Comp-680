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

  constructor(public auth: AuthService, private modalService: NgbModal,private profileService:ProfileService, private formBuilder: FormBuilder) {

    if (this.auth.userProfile) {
      this.profile = this.auth.userProfile;
     
      console.log(this.profile.email);

      this.profileService.createOrUserInfo(this.profile.email).subscribe(
        res=>{ console.log(res); this.userInfo=res;},
        err=>console.log(err.message),
        () => console.log("Complete")
      );
      
     

    } else {
     
      this.auth.getProfile((err, profile) => {
        this.profile = profile;

        this.profileService.createOrUserInfo(this.profile.email).subscribe(
          res=> { console.log(res); this.userInfo=res;},
          err=>console.log(err),
          () => console.log("Complete")
        );
       
        
      });

      
    }

    

   }

  ngOnInit() {

    }

   

    onSubmit(submitBtn: HTMLButtonElement ) { 
      console.log("Button Name"+this.currentBtn.name);

      if(this.currentBtn.name=="edit_info")
      {
        this.profileService.postUser(this.dynamicForm.value).subscribe(
          res => {console.log(res),this.userInfo=res},
          err => console.log(err),
          () => console.log("complete")

        );

      }

    }
    get diagnostic() { return JSON.stringify(this.dynamicForm.value); }

    //This function is for job seeker modal opening up and the dynamic forms
    //Need to be able to switch html template based on user role 
    open(content : HTMLTemplateElement, btn: HTMLButtonElement) {

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
            linkedin:[""],
            twitter:[""],
            facebook:[""],
            github:[""],
            website:[""]
          });
        break;

        case "add_edu":
        this.dynamicForm = this.formBuilder.group({
          school:[""],
          degree:[""],
          major:[""],
          start_date:[""],
          end_date:[""],
          description:[""],
          in_progress:[""]
        });
      break;

      case "add_exp":
      this.dynamicForm = this.formBuilder.group({
        title:[""],
        company:[""],
        start_date:[""],
        end_date:[""],
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
