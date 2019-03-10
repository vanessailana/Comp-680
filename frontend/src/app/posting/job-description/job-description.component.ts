import { Component, OnInit,Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
 

@Component({
  selector: 'app-job-description',
  templateUrl: './job-description.component.html',
  styleUrls: ['./job-description.component.css']
})
export class JobDescriptionComponent {

constructor(
    public dialogRef: MatDialogRef<JobDescriptionComponent>) {}



 onNoClick(): void {
    this.dialogRef.close();
  }


}
