import { Component, OnInit,Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
 import {Posting}  from '../posting.model';
import {PostingService} from '../posting.service';
@Component({
  selector: 'app-job-description',
  templateUrl: './job-description.component.html',
  styleUrls: ['./job-description.component.css']
})
export class JobDescriptionComponent {
posting: Posting[];

constructor(public dialogRef: MatDialogRef<JobDescriptionComponent>,private postingService:  PostingService,) {}



 onNoClick(): void {
    this.dialogRef.close();
  }




}
