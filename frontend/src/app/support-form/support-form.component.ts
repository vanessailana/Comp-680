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
  }

  onSubmit(submitBtn: HTMLButtonElement ) { 

    submitBtn.disabled = true;

    if(this.supportForm.valid)
    {
      var sub = this.supportService.sumbitSupportRequest(this.supportForm.value);
      sub.subscribe((data) => {
                                console.log(data); 
                                this.supportForm.reset(); 
                                submitBtn.disabled = false; 
                                this.submitSuccess = true;
                                this.submitFail = false;
                              },
                  (error: Response) => {
                                        console.log(error);
                                        this.submitFail=true; 
                                        this.submitSuccess = false;
                                        }
                                        );
        
    }else{
      console.log("invlaid input");
      submitBtn.disabled = false;
      this.submitFail=true; 
      this.submitSuccess = false;
    }

    console.log(this.submitFail);
    console.log(this.submitSuccess);
    
    
    
  }

  get diagnostic() { return JSON.stringify(this.supportForm.value); }

  ngOnInit() {

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

}
