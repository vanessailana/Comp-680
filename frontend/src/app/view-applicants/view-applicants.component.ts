import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {ApplicantsService} from './applicants.service';
@Component({
  selector: 'app-view-applicants',
  templateUrl: './view-applicants.component.html',
  styleUrls: ['./view-applicants.component.css']
})
export class ViewApplicantsComponent implements OnInit {

  constructor(private applicantService: ApplicantsService) { }
   applicants: Array<any>;
  ngOnInit() {

    this.applicantService.getAll().subscribe(data => {
      this.applicants = data;
      console.log(this.applicants);
    });
 
  }

}
