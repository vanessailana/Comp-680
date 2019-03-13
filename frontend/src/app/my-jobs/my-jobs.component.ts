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
  constructor(private postingService: PostingService,private modalService: NgbModal) { 


 

  }

  ngOnInit() {

    let user = JSON.parse(localStorage.getItem('user'));
    this.postingService.getMyJobs(user.id).subscribe(
      (res)=> {this.jobs = res; console.log(res);},
      (err)=> console.log(err),
      ()=>console.log("Completed")
    );

  }

}
