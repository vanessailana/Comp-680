import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import{Router}  from '@angular/router';


@Component({
  selector: 'app-unique-recommendations',
  templateUrl: './unique-recommendations.component.html',
  styleUrls: ['./unique-recommendations.component.css']
})
export class UniqueRecommendationsComponent implements OnInit {
jobs:any;
p: number = 1;
recommendedJobs:any;
 user = JSON.parse(localStorage.getItem('user'));

  constructor(private http:HttpClient,private router: Router) { }

  ngOnInit() 
  
  {

    
    var role= localStorage.getItem('roles');
   
    
    if(role!=='user') {

    
  
    this.http.get('https://agile-scrubland-47616.herokuapp.com/stackrec').
    subscribe(data => {

      this.jobs=data;
      console.log(this.jobs);
    })


     
    this.http.get('https://agile-scrubland-47616.herokuapp.com/recommendations/'+this.user.id).
    subscribe(data => {

      this.recommendedJobs=data;
      console.log(this.recommendedJobs);
    })

  } else {
    this.router.navigateByUrl('/view_jobs');
  }
}
}


