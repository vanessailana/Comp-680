import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-posting',
  templateUrl: './posting.component.html',
  styleUrls: ['./posting.component.css'],

})
export class PostingComponent implements OnInit {

  formGroup: FormGroup;

  constructor(private fb: FormBuilder) { }


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

  get diagnostic() { return JSON.stringify(this.formGroup.value); }


  onSubmit(btn: HTMLButtonElement){

    console.log(this.diagnostic);

  }

  ngOnInit() {

    this.formGroup = this.fb.group({
        job : this.fb.group({
          title: [""],
          description: [""],
          location: [""],
          compensation: [""],
          employment_type: [""]
    
      }),
    questions : this.fb.array([this.initQuestion()])
    })

  }

}
