import { Component, OnInit } from '@angular/core';
import { PostingService } from '../posting/posting.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-my-jobs',
  templateUrl: './my-jobs.component.html',
  styleUrls: ['./my-jobs.component.css']
})
export class MyJobsComponent implements OnInit {

  jobs:any;
  p: number = 1;
  showButton: boolean;

  user : any;
  constructor(private postingService: PostingService,private modalService: NgbModal) { 

    this.showButton = false;
 
    let user = JSON.parse(localStorage.getItem('user'));

    console.log(user);


   

  }

  ngOnInit() {

     if(this.jobs!=null) {

     
    
    this.postingService.getMyJobs().subscribe(
      (res)=> {this.jobs = res; 
        console.log("this works");
      })
    }
     else {
    this.showButton=true;
     }


    
   
  
  }

}

