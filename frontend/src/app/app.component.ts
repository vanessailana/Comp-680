import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { NgxPermissionsService } from 'ngx-permissions';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

profile:any;
  constructor(public auth: AuthService,private permissionsService: NgxPermissionsService,
               private http: HttpClient) {

    auth.handleAuthentication();



  }


  ngOnInit(){
  const perm = localStorage.getItem('roles');
  console.log(perm);
 var arr = [perm];

 console.log(arr);


     
    this.permissionsService.loadPermissions(arr);
    
 
  
    if (localStorage.getItem('isLoggedIn')) {

 this.auth.renewTokens();
    }

 

      
  

}
}

