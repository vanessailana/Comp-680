import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {ApplicantsService} from './applicants.service';
import{Router}  from '@angular/router';

@Component({
  selector: 'app-view-applicants',
  templateUrl: './view-applicants.component.html',
  styleUrls: ['./view-applicants.component.css']
})
export class ViewApplicantsComponent implements OnInit {

  constructor(private applicantService: ApplicantsService,private router: Router) { }
   applicants: Array<any>;
   applied: Array<any>;
   applicant: Array <any>;
   p: number = 1;
   order: string = 'createdAt';
 searchText;

  ngOnInit() {

    var role= localStorage.getItem('roles');
   
    
    if(role!=='admin') {

    

    

      this.router.navigateByUrl('/view_jobs');
    }

    this.applicantService.getAll().subscribe(data => {

      this.applicants=data;

   
   
    });
 
  }

}
