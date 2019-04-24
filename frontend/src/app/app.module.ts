import { NgModule } from '@angular/core';
import {JobDescriptionComponent} from './posting/job-description/job-description.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Ng2SearchPipeModule } from 'node_modules/ng2-search-filter';
import { NgxPermissionsModule } from 'ngx-permissions';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { NgbModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './auth/auth.service';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { CallbackComponent } from './callback/callback.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './profile/profile.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { SupportFormComponent } from './support-form/support-form.component';
import { HttpErrorHandler } from './http-error-handler.service';
import { MessageService } from './message.service';
import { PostingService } from './posting/posting.service';
 import { PostingComponent } from './posting/posting.component';
import {MatDialogModule,MatDialogRef} from "@angular/material";
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { ViewpostingComponent } from './posting/viewposting/viewposting.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { MyJobsComponent } from './my-jobs/my-jobs.component'; 
import { AppliedComponent } from './applied/applied.component'; 
import { AppliedService } from './applied/applied.service';
import {BrowserModule} from '@angular/platform-browser';
 
import { ViewApplicantsComponent } from './view-applicants/view-applicants.component';
import { ApplicantsService} from './view-applicants/applicants.service';

import { OrderModule } from 'ngx-order-pipe';

@NgModule({
  declarations: [
    AppComponent,
    CallbackComponent,
    ProfileComponent,
    ChatbotComponent,
    SupportFormComponent,
    PostingComponent,
    ViewpostingComponent,
    JobDescriptionComponent,
    MyJobsComponent,
    AppliedComponent,
    ViewApplicantsComponent

  ],
  imports: [OrderModule,
NgbModule, MatDialogModule, NgxPaginationModule,
    BrowserModule,Ng2SearchPipeModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    ReactiveFormsModule,
    NgbModule,
    NgxPermissionsModule.forRoot(),
    FormsModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  entryComponents:[ViewpostingComponent,
    JobDescriptionComponent],
  providers: [ 
    ApplicantsService,
    AuthService, PostingService, AppliedService,
    HttpErrorHandler, JobDescriptionComponent,
    MessageService, 
    NgbModal, 
    {provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}},

  
    FormsModule

  ],
  exports:[
    NgxPermissionsModule,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
