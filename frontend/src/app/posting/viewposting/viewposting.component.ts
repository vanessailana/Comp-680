import { Component, OnInit } from '@angular/core';
import {PostingService } from '../posting.service';
import {MatDialog, MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import {Posting} from '../posting.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import{Router}  from '@angular/router';
import {JobDescriptionComponent} from '../job-description/job-description.component';
import { AppliedService } from 'src/app/applied/applied.service';
import { NgxPermissionsService } from 'ngx-permissions';
@Component({
  selector: 'app-viewposting',
  templateUrl: './viewposting.component.html',
  styleUrls: ['./viewposting.component.css']
})
export class ViewpostingComponent  implements OnInit {
 posting:Posting[];
   p: number = 1;
  totalRec : number;
 jobs: Array<any>;
searchText;

 appliedJobs : any;


 userApplied: boolean;


  constructor(public dialog: MatDialog, 
    private appliedService: AppliedService, private permissionsService: NgxPermissionsService,
    private postingService: PostingService,
    private modalService: NgbModal,private _rotuer:Router) { }

  ngOnInit() {

   const role= [localStorage.getItem('roles')];
 
    this.permissionsService.loadPermissions(role);

 
   this.postingService.getAll().subscribe(data => {
      this.jobs= data;
    
    });

  
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

  applyToJob(job,btn:HTMLButtonElement) {
    let user = JSON.parse(localStorage.getItem('user'));
    console.log(job);
    console.log(user);
    let applicant = {'job': job, 'user': user};

    this.appliedService.apply(applicant).subscribe(
    res => {
      console.log("res: " + JSON.stringify(res));
      btn.disabled = true;
      this.userApplied = true;
    },
    err => {
      console.log("err: " + JSON.stringify(err));
    });
  }

}




