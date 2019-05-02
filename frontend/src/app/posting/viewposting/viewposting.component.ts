import { Component, OnInit } from '@angular/core';
import {PostingService } from '../posting.service';
import {MatDialog, MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import {Posting} from '../posting.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import{Router}  from '@angular/router';
import {JobDescriptionComponent} from '../job-description/job-description.component';
import { AppliedService } from 'src/app/applied/applied.service';
import { NgxPermissionsService } from 'ngx-permissions';
import { QuestionsService } from '../questions.service';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';

import { AuthService } from '../../auth/auth.service';
import { ProfileService } from 'src/app/profile/profile.service';

@Component({
  selector: 'app-viewposting',
  templateUrl: './viewposting.component.html',
  styleUrls: ['./viewposting.component.css']
})
export class ViewpostingComponent  implements OnInit {
  posting:Posting[];
  p: number = 1;
  totalRec : number;
  jobs: Array<any>;
  searchText:any;
  appliedJobs : any;
  userApplied: boolean;
  questions: any;
  question : any;
  formGroup : FormGroup;
  alreadyApplied : boolean;

  displayLogin : boolean;

 loading: boolean;
  user: any;
  profile : any;

  applied : Array<any>;



  constructor(public dialog: MatDialog,
    private questionService: QuestionsService,
    private appliedService: AppliedService, 
    private permissionsService: NgxPermissionsService,
    private postingService: PostingService,
    private modalService: NgbModal,private _rotuer:Router,
    private formBuilder: FormBuilder,
    private auth: AuthService, private profileService:ProfileService,
    private permissionService: NgxPermissionsService) { 

   
      var permuser = permissionService.getPermission('user');
      var permadmin = permissionService.getPermission('admin');


      if(permuser){
        permissionService.loadPermissions([permuser.name])
      }
      if(permadmin)
      {
        permissionService.loadPermissions([permadmin.name])
      }
  
    }

 

  ngOnInit() {

    this.alreadyApplied = false;

      this.formGroup = this.formBuilder.group({
        answers : this.initAnswers()
      });

      this.postingService.getAll().subscribe((res) => {
        this.jobs = res;
        console.log(res);
        },
        (err)=>(console.log(err)),
        ()=>{
         
        }
      );


      this.profile = localStorage.getItem('profile');

      this.profile = JSON.parse(localStorage.getItem('profile'));
      if(this.profile)
      {
        this.profileService.getUser(this.profile.email).subscribe((res)=>{
          this.user=res
          this.appliedService.getAppliedJobs(this.user.id).subscribe((res)=>{
            this.applied = res;
          })
        });
      }
        
       




   const role= [localStorage.getItem('roles')];
 
    this.permissionsService.loadPermissions(role);

    this.loading = true;
   this.postingService.getAll().subscribe(data => {
      this.jobs= data;
      this.loading = false;
    });

  }

  initAnswers() {
    var formArray = this.formBuilder.array([]);
    return formArray;
  }
 
  initAnswer() {
    const controls = <FormArray>this.formGroup.controls['answers'];
    let formGroup = this.formBuilder.group({
      answer: ['', [Validators.required]]
    });
    controls.push(formGroup);
  }
   
  open(i,content) {

    this.modalService.open(content);
    this.formGroup = this.formBuilder.group({
      answers : this.initAnswers()
    })

    this.questionService.getQuestions(i).subscribe((res)=>{
      this.question = res
      this.question.forEach((e) => this.initAnswer())
    });




    if(this.user==null) {  
      this.displayLogin = true;
    }else{
      this.displayLogin = false;
      if(this.applied.find((e)=>e.id==i))
      {
        this.alreadyApplied = true;
      }else{
        this.alreadyApplied = false;
      }
      
    }

  }



  delete(contentd) {
    this.modalService.open(contentd);
  }

    deleteJob(job: Posting){
    this.postingService.deletePosting(job)
      .subscribe( data => {
        this.jobs = this.jobs.filter(u => u !== job);
location.reload();
        
  
      })
 location.reload();

  };

  onNoClick(): void {
     this.dialog.closeAll();

  }

   onCloseCancel() {
     this._rotuer.navigate(['view_jobs']);
     
  }

 openDialog(): void {
    const dialogRef = this.dialog.open(JobDescriptionComponent, {
      width: '250px'
    });

   




  dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.userApplied = false;
    });
}

close():void
{
  this.alreadyApplied = false;
  this.userApplied = false;
  this.formGroup = null;
  this.question = [];
}

  applyToJob(job,btn:HTMLButtonElement) {
    console.log(job);
    console.log(this.user);

    if(this.user){
    let applicant = {'job': job, 'user': this.user};

    var answers = this.formGroup.controls['answers'].value;

    console.log(answers);
    this.question = [];
   
    this.appliedService.apply(applicant).subscribe(
    res => {
      console.log("res: " + JSON.stringify(res));
      btn.disabled = true;
      this.userApplied = true;

      var i = 0 ;
  
      answers.forEach(element=>{
        element['applicant'] = applicant;
        element['question'] = this.questions[i];
        
        i+=1;
      })
     
      if(answers.length > 0){
       this.questionService.sumbitAnswers(answers).subscribe(
        (res)=>console.log(res),
        (err)=>console.log(err),
        ()=>console.log("complete")
      )
       }
      
      
    },
    err => {
      console.log("err: " + JSON.stringify(err));
    },
    ()=>{
      this.appliedService.getAppliedJobs(this.user.id).subscribe((res)=>{
        this.applied = res;
      })
    });
  }


  }

}




