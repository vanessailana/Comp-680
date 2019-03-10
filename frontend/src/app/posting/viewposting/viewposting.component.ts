import { Component, OnInit } from '@angular/core';
import {PostingService } from '../posting.service';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';

import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-viewposting',
  templateUrl: './viewposting.component.html',
  styleUrls: ['./viewposting.component.css']
})
export class ViewpostingComponent implements OnInit {

 jobs: Array<any>;
  constructor(private postingService: PostingService,private modalService: NgbModal) { }

  ngOnInit() {

  this.postingService.getAll().subscribe(data => {
      this.jobs= data;
      console.log(this.jobs);
    });
  }

  open(content) {
    this.modalService.open(content);
  }



}
