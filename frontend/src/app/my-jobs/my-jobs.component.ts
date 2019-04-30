import { Component, OnInit } from '@angular/core';
import { PostingService } from '../posting/posting.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {Posting} from '../posting/posting.model';
import {MatDialog, MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import { RouterModule, Router, Routes } from '@angular/router';
import { NgxPermissionsService } from 'ngx-permissions';
import {JobDescriptionComponent} from '../posting/job-description/job-description.component';
import { AuthService } from '../auth/auth.service';
import { ProfileService } from '../profile/profile.service';
@Component({
  selector: 'app-my-jobs',
  templateUrl: './my-jobs.component.html',
  styleUrls: ['./my-jobs.component.css']
})
export class MyJobsComponent implements OnInit {

  userApplied: boolean;
  posting:Posting[];
  jobs: any;
  p: number = 1;
  showButton: boolean;
  user : any;
  profile : any;
  edit_job: any; // job user edits

  constructor(private postingService: PostingService,private _rotuer:Router,
    private modalService: NgbModal,private router: Router,public dialog: MatDialog,
    private auth: AuthService, private profileService: ProfileService) { 

    this.showButton = false;
 

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
     (res)=>{this.user = res;},
     (err)=>console.log(err),
     ()=>{
      this.postingService.getMyJobs(this.user.id).subscribe(
        (res)=> {this.jobs = res; 
        
          console.log("this works");
        })
     });
    }
   
}
  open(content) {
    this.modalService.open(content);
  }

  edit(contente, job) {
    this.modalService.open(contente);
    console.log(job);
    this.edit_job = job;
  }

  saveJob(job) {
    console.log(job);
    console.log(this.edit_job);
  }

  delete(contentd) {
    this.modalService.open(contentd);
  }

  deleteJob(job: Posting){
    this.postingService.deletePosting(job)
      .subscribe( data => {
        this.jobs = this.jobs.filter(u => u !== job);
        location.reload();
      });
    location.reload();
  };

  onNoClick(): void {
     this.dialog.closeAll();
  }

   onCloseCancel() {
     this._rotuer.navigate(['view_jobs']);
  }

 openDialog(): void {
    const dialogRef = this.dialog.open(JobDescriptionComponent, {
      width: '250px'
    });

  dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.userApplied = false;
    });
}


  ngOnInit() {
    
  
  }
    
}
  
   
  


