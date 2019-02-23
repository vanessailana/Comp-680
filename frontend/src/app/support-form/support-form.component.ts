import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms' 
import { SupportService } from './support.service';
import { AuthService } from './../auth/auth.service';
@Component({
  selector: 'app-support-form',
  templateUrl: './support-form.component.html',
  providers: [SupportService, FormBuilder],
  styleUrls: ['./support-form.component.css']
})
export class SupportFormComponent implements OnInit {
  supportForm : FormGroup;


  
  constructor(public auth: AuthService,private supportService: SupportService, private formBuilder: FormBuilder) 
  { 
  
  }

  onSubmit() { 

    var sub=this.supportService.sumbitSupportRequest(this.supportForm.value);
    sub.subscribe((data) => console.log(data),
                  (error: Response) => console.log(error));
    //console.log(this.supportForm.value);
  }

  get diagnostic() { return JSON.stringify(this.supportForm.value); }

  ngOnInit() {

    if(this.auth.userProfile)
    {
      this.supportForm = this.formBuilder.group({
        name: [this.auth.userProfile.nickname,Validators.required],
        email: [this.auth.userProfile.email,Validators.email],
        content: ['',[Validators.required,Validators.minLength(10)]]
      });

    }else{
      this.supportForm = this.formBuilder.group({
        name: ['',Validators.required],
        email: ['',Validators.email],
        content: ['',[Validators.required,Validators.minLength(10)]]
      });
  }
  }

}
