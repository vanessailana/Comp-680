import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl, AbstractControl } from '@angular/forms';
import {Posting} from './posting.model';
import {question} from './question.model';
import {PostingService} from './posting.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-posting',
  templateUrl: './posting.component.html',
  styleUrls: ['./posting.component.css'],

})
export class PostingComponent implements OnInit {
  jobMetadata: any;
  formGroup: FormGroup;
  submitted : boolean;
  constructor(private postingService:PostingService,private fb: FormBuilder,private modalService: NgbModal) {
    

    this.formGroup = this.fb.group({
      job : this.fb.group({
        title: ["",[Validators.required]],
        description: ["",[Validators.required]],
        location: ["",[Validators.required]],
        startCompensation: ["",[Validators.required]],
        endCompensation: [""],
        employmentType: [""]
  
    }),
    
    questions: this.initQuestion()

    })

  }

  initQuestion() {
    var formArray = this.fb.array([]);
    formArray.push(this.fb.group({
        question: ['', [Validators.required]],
      }))
    return formArray;
  }

  addQuestion() {
    const controls = <FormArray>this.formGroup.controls['questions'];
    let formGroup = this.fb.group({
      question: ['', [Validators.required]]
    });
    controls.push(formGroup);
  }
   
get getQuestions(): FormArray { return this.formGroup.get('questions') as FormArray }

done() {
  localStorage.removeItem("job_id");
}


   open(content) {
    this.modalService.open(content);
  }

  removeQuestion(i: number) {
    this.getQuestions.removeAt(i);
  }

  get diagnostic() { return JSON.stringify(this.formGroup.controls.job.value); }


  get d2() { return JSON.stringify(this.formGroup.controls.questions.value); }



  onSubmit(submitBtn: HTMLButtonElement){

    
    submitBtn.disabled = true;
  
    if(this.formGroup.valid)
    {
    
      this.jobMetadata = this.formGroup.controls.job.value;
      this.jobMetadata['user'] = JSON.parse(localStorage.getItem('user'));
      var sub = this.postingService.sumbitPost(this.jobMetadata);
      sub.subscribe(
        (res) => 
        {
          this.jobMetadata = res; 
          console.log("Meta DAta"+JSON.stringify(this.jobMetadata));
          console.log('HTTP Response', res);
          console.log(this.formGroup.value.id);
          let questions = this.formGroup.controls.questions.value;
          let job = this.jobMetadata;
          questions.forEach(q => {
              q['job'] = job;           
          });
          this.postingService.createQuestion(questions).subscribe(
            (res)=>console.log(res),
            (err)=>console.log(err),
            () => console.log("questions complete")
          );
        },
        (err)=>console.log(err),
        ()=>{console.log("Complete"); this.submitted=true;});

    }
    else
    {

      console.log("invlaid input");
     

    }

    

  }

  ngOnInit() {
  


  }

}
