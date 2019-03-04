import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms' 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FormBuilder]
})
export class AppComponent implements OnInit {

profile:any;
searchForm : FormGroup;
  constructor(public auth: AuthService, private formBuilder: FormBuilder) {

    auth.handleAuthentication();

  }


  ngOnInit() {

    this.searchForm = this.formBuilder.group({
      keyword: ["",Validators.required],
      location: [""]
    });

    if (localStorage.getItem('isLoggedIn')) {
 this.auth.renewTokens();
    }


     if (this.auth.userProfile) {
      this.profile = this.auth.userProfile;
    } else {
      this.auth.getProfile((err, profile) => {
        this.profile = profile;
      });
  }

}
}

