import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms' 
import { NgxPermissionsService } from 'ngx-permissions';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FormBuilder]
})
export class AppComponent implements OnInit {
  profile:any;
  searchForm : FormGroup;

  constructor(
    public auth: AuthService,
    private permissionsService: NgxPermissionsService,
    private http: HttpClient,
    private formBuilder: FormBuilder
  ) {
    auth.handleAuthentication();
  }

  ngOnInit(){
    const perm = localStorage.getItem('roles');
    console.log(perm);
    var arr = [perm];
    
    this.permissionsService.loadPermissions(arr);
    console.log(arr);

    this.searchForm = this.formBuilder.group({
      keyword: ["",Validators.required],
      location: [""]
    });

    if(localStorage.getItem('isLoggedIn')) {
      this.auth.renewTokens();
    }
  }
}

