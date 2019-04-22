import { Component, OnInit } from '@angular/core';
import { PostingService } from '../posting/posting.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {Posting} from '../posting/posting.model';
import {MatDialog, MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import { RouterModule, Router, Routes } from '@angular/router';
import { NgxPermissionsService } from 'ngx-permissions';
import {JobDescriptionComponent} from '../posting/job-description/job-description.component';
@Component({
  selector: 'app-my-jobs',
  templateUrl: './my-jobs.component.html',
  styleUrls: ['./my-jobs.component.css']
})
export class MyJobsComponent implements OnInit {

  userApplied: boolean;
  posting:Posting[];
  jobs:any;
  p: number = 1;
  showButton: boolean;

  user : any;
  constructor(private postingService: PostingService,private _rotuer:Router,
    private modalService: NgbModal,private router: Router,public dialog: MatDialog) { 

    this.showButton = false;
 
    let user = JSON.parse(localStorage.getItem('user'));

    console.log(user);


   

  }
  open(content) {
    this.modalService.open(content);
  }


  delete(contentd) {
    this.modalService.open(contentd);
  }

    deleteJob(job: Posting){
    this.postingService.deletePosting(job)
      .subscribe( data => {
        this.jobs = this.jobs.filter(u => u !== job);
location.reload();
        
  
      })
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
    
   var role= localStorage.getItem('roles');

 
   

   
  
   if(role=='admin') {

     if(this.jobs!==null && role=='admin') {

     
     
    
    this.postingService.getMyJobs().subscribe(
      (res)=> {this.jobs = res; 
      
        console.log("this works");
      })
    }else {
      this.showButton=true;
    }
    
    
    } else {
      this.router.navigateByUrl('/view_jobs');
    }
  }
    
}
  
   
  


