import { Component, OnInit } from '@angular/core';

import { RouterModule, Router, Routes } from '@angular/router';
@Component({
  selector: 'app-applied',
  templateUrl: './applied.component.html',
  styleUrls: ['./applied.component.css']
})
export class AppliedComponent implements OnInit {

  constructor(private router: Router) { }

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

}
