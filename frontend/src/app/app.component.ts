import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';

import { NgxPermissionsService } from 'ngx-permissions';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms' 
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FormBuilder]
})
export class AppComponent implements OnInit {
  profile:any;

  check= localStorage.getItem('roles');
  searchForm : FormGroup;

  isNavbarCollapsed : boolean = false;

  username : string;

  messageNotice : boolean;

  constructor(
    public auth: AuthService,
    private http: HttpClient, private permissionsService: NgxPermissionsService,
    private formBuilder: FormBuilder
  ) {

    
      this.auth.handleAuthentication();
      

  }
        

  ngOnInit(){

    
    if (this.auth.isAuthenticated()) {
      console.log("is authenticated");
      //this.auth.renewTokens();
      this.profile = JSON.parse(localStorage.getItem('profile'));
      let role =  this.profile['https://example.com/roles'];
      console.log("role"+ role);
      this.permissionsService.addPermission([role]);
      this.permissionsService.loadPermissions([role]);
  
    }else{
      this.permissionsService.addPermission(["guest"]);
      this.permissionsService.loadPermissions(["guest"]);
    }


    }

 


   
  }




  

