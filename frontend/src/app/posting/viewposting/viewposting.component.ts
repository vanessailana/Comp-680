import { Component, OnInit } from '@angular/core';
import {PostingService } from '../posting.service';
import {MatDialog, MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import {Posting} from '../posting.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import{Router}  from '@angular/router';
import {JobDescriptionComponent} from '../job-description/job-description.component';

@Component({
  selector: 'app-viewposting',
  templateUrl: './viewposting.component.html',
  styleUrls: ['./viewposting.component.css']
})
export class ViewpostingComponent implements OnInit {
 posting:Posting[];
  p: number = 1;
  totalRec : number;
 jobs: Array<any>;
  constructor(private postingService: PostingService,private modalService: NgbModal,private _rotuer:Router, public dialogRef: MatDialogRef<JobDescriptionComponent>,public dialog: MatDialog) { }

  ngOnInit() {



  this.postingService.getAll().subscribe(data => {
      this.jobs= data;
      console.log(this.jobs);
    });
  }

  open(content) {
    this.modalService.open(content);
  }

  delete(content) {
    this.modalService.open(content);
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
    
    });


}

}


