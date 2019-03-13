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

  disabled: boolean;

  submitSuccess: boolean;
  submitFail: boolean;

  constructor(public auth: AuthService,private supportService: SupportService, private formBuilder: FormBuilder) 
  { 
      this.submitSuccess = false;
      this.submitFail = false;

    if(this.auth.userProfile)
    {
      this.supportForm = this.formBuilder.group({
        name: [this.auth.userProfile.nickname,Validators.required],
        email: [this.auth.userProfile.email,Validators.email],
        message: ['',[Validators.required,Validators.minLength(10)]]
      });

    }else{
      this.supportForm = this.formBuilder.group({
        name: ['',Validators.required],
        email: ['',Validators.email],
        message: ['',[Validators.required,Validators.minLength(10)]]
      });
  }
  }

  onSubmit(submitBtn: HTMLButtonElement ) { 

    submitBtn.disabled = true;
  
    if(this.supportForm.valid)
    {
      var sub = this.supportService.sumbitSupportRequest(this.supportForm.value);
      sub.subscribe(
        res => 
        {

          this.submitSuccess = true;
          console.log('HTTP Response', res);
          this.supportForm.reset(); 
          
        },
        err => 
        { 
          this.submitFail=true; 
          
          console.log('HTTP Error', err);
          submitBtn.disabled = false;
        },
        () => 
        {
          
          console.log('HTTP request completed.');
          submitBtn.disabled = false;
        }

      )        
    }
    else
    {

      submitBtn.disabled = false;
      


    }



    
    
  }

  get diagnostic() { return JSON.stringify(this.supportForm.value); }

  ngOnInit() {

    this.submitSuccess = false;
    this.submitFail = false;
    
  }

}
