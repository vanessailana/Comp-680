import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl, AbstractControl } from '@angular/forms';
import {Posting} from './posting.model';
import {question} from './question.model';
import {PostingService} from './posting.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import{Router}  from '@angular/router';

const INIT:number = 0;
const SUCCESS:number = 1;
const FAIL:number = -1;

@Component({
  selector: 'app-posting',
  templateUrl: './posting.component.html',
  styleUrls: ['./posting.component.css'],

})
export class PostingComponent implements OnInit {
  jobMetadata: any;
  formGroup: FormGroup;
  submitted : number;

  constructor(
    private postingService:PostingService,
    private fb: FormBuilder,
    private modalService: NgbModal,
    private router: Router
    ) {
    
    this.submitted = INIT;

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
            (err)=>{this.submitted=FAIL; submitBtn.disabled = false;},
            () => {console.log("questions complete");this.submitted=SUCCESS;}
          );
        },
        (err)=>{this.submitted=FAIL; submitBtn.disabled = false;},
        ()=>{console.log("Complete");this.submitted=SUCCESS;});

    }
    else
    {

      console.log("invlaid input");
      submitBtn.disabled = false;
      this.submitted=FAIL;

    }

    

  }

  ngOnInit() {
  
   var role= localStorage.getItem('roles');
       
    if(role!=='admin') {

      this.router.navigateByUrl('/view_jobs');
    }

  }

}
