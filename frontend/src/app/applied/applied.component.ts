import { Component, OnInit } from '@angular/core';

import { RouterModule, Router, Routes } from '@angular/router';
import { AppliedService } from './applied.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../auth/auth.service';
import { ProfileService } from '../profile/profile.service';
@Component({
  selector: 'app-applied',
  templateUrl: './applied.component.html',
  styleUrls: ['./applied.component.css']
})
export class AppliedComponent implements OnInit {

  jobs : any;
  closeResult : any;
  profile : any;
  user : any;
  constructor(private router: Router, private appliedService:AppliedService,
    private modalService:NgbModal, private auth:AuthService,
    private profileService:ProfileService) { 


      this.auth.getProfile((err, profile) => {
        this.profile = profile;
        console.log(this.profile);

        this.profileService.getUser(this.profile.email).subscribe(
          (res) => {
            
            this.user = res
            
           
          
          },
          (err)=> console.log(err)
          ,
          ()=>{
            this.appliedService.getAppliedJobs(this.user.id).subscribe(
              (res)=> {this.jobs = res},
            );
          
          }
        )
        

    });
  }



  ngOnInit() {
    
   var role= localStorage.getItem('roles');

   if(role=="admin") {
     this.router.navigateByUrl('/posting');
   } 
   if(role=="user") {
    this.router.navigateByUrl('/applied');
  } 

  if(role!="user" && role!="admin") {
    this.router.navigateByUrl('/view_jobs');
  } 
 
  }

  open(content : HTMLTemplateElement) {

    

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
