import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import {Posting} from './posting.model';
import {PostingService} from './posting.service';

@Component({
  selector: 'app-posting',
  templateUrl: './posting.component.html',
  styleUrls: ['./posting.component.css'],

})
export class PostingComponent implements OnInit {

  formGroup: FormGroup;

  submitSuccess: boolean;
  submitFail: boolean;

  constructor(private postingService:PostingService,private fb: FormBuilder) {
    this.submitSuccess = false;
    this.submitFail = false;

  }


  initQuestion() {
    // initialize our address
    return this.fb.group({
      question: ['', Validators.required]
    });
  }

  addQuestion() {
    const control = <FormArray>this.formGroup.controls['questions'];
    control.push(this.initQuestion());
  }

  removeQuestion(i: number) {
    const control = <FormArray>this.formGroup.controls['questions'];
    control.removeAt(i);
  }

  get diagnostic() { return JSON.stringify(this.formGroup.controls.job.value); }


  onSubmit(submitBtn: HTMLButtonElement){

    
    submitBtn.disabled = true;
  
    if(this.formGroup.valid)
    {
    console.log(this.formGroup);
      var sub = this.postingService.sumbitPost(this.formGroup.controls.job.value);

      sub.subscribe(
        res => 
        {

          this.submitFail=true; 
          this.submitSuccess = false;
          console.log('HTTP Response', res);
          this.formGroup.reset();
          
        },
        err => 
        { 
          this.submitFail=true; 
          this.submitSuccess = false;
          console.log('HTTP Error', err)
          submitBtn.disabled = false;
        },
        () => 
        {
          console.log('HTTP request completed.');
          submitBtn.disabled = false;
          console.log(sub);
        }

      )        
    }
    else
    {

      console.log("invlaid input");
      submitBtn.disabled = false;
      this.submitFail=true; 
      this.submitSuccess = false;

    }

    console.log(this.submitFail);
    console.log(this.submitSuccess);

    

  }


  processForm() {


  }



  ngOnInit() {

    this.formGroup = this.fb.group({
        job : this.fb.group({
          title: ["",[Validators.required]],
          description: ["",[Validators.required]],
          location: ["",[Validators.required]],
          start_compensation: ["",[Validators.required]],
          end_compensation: [""],
          employment_type: [""]
    
      }),
      questions : this.fb.array([
        this.initQuestion()

      ])

     
    })

  }

}
