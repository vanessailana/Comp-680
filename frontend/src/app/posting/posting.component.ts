import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import {Posting} from './posting.model';
import {Question} from './question.model';
import {PostingService} from './posting.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-posting',
  templateUrl: './posting.component.html',
  styleUrls: ['./posting.component.css'],

})
export class PostingComponent implements OnInit {
  question: Question;
  formGroup: FormGroup;
   submitSuccess: boolean;
  submitFail: boolean;
   test=localStorage.getItem('job_id');
  constructor(private postingService:PostingService,private fb: FormBuilder,private modalService: NgbModal) {
    this.submitSuccess = false;
    this.submitFail = false;

  }


  initQuestion() {
    // initialize our address
    return this.fb.group({
      question: ['', Validators.required],
      job_id: ['']
    });
  }

  addQuestion() {
    const control = <FormArray>this.formGroup.controls['questions'];
    control.push(this.initQuestion());
  }


done() {
  localStorage.removeItem("job_id");
}


   open(content) {
    this.modalService.open(content);
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
          console.log(this.formGroup.value.id);
    

          localStorage.setItem("id",this.formGroup.value.id)

          
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


save() {
   this.postingService.sumbitPost(this.formGroup.controls.job.value).subscribe(result => {
       console.log('HTTP Response', result.id);
       

     console.log(this.formGroup.controls.job);

    }, error => console.error(error));
   
     console.log(this.formGroup.controls.job.value.id);
  }


  saveQuest() {
   this.postingService.createQuestion(this.question).subscribe(result => {
       console.log('HTTP Response', result.id);
       

     console.log(this.formGroup.controls.job);

    }, error => console.error(error));
   
     console.log(this.formGroup.controls.job.value.id);
  }


get job_id(): any {
    return localStorage.getItem('job_id');
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
