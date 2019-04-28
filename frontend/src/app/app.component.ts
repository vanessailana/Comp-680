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

    try{
    this.auth.getProfile((err, profile) => {
      this.profile = profile;
      this.auth.userProfile = this.profile;
      localStorage.setItem("user", JSON.stringify(this.profile));


      var check = localStorage.getItem('roles');
      const role= [check];
      console.log(role);
      this.permissionsService.loadPermissions(role);
      console.log(this.permissionsService.getPermissions());
    


    });

    }catch{
      console.log("caught");
    }
    this.searchForm = this.formBuilder.group({
      keyword: ["",Validators.required],
      location: [""]
    });

    if(localStorage.getItem('isLoggedIn')) {
      this.auth.renewTokens();
    }
  }

  ngOnInit(){
  
  }

 

  onSubmit(btn:HTMLButtonElement)
  {

  }

  
}

