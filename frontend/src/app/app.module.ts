import { NgModule } from '@angular/core';
import { NgxPermissionsModule } from 'ngx-permissions';

import { BrowserModule } from '@angular/platform-browser';
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

import { PostingComponent } from './posting/posting.component';

import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';


@NgModule({
  declarations: [
    AppComponent,
    CallbackComponent,
    ProfileComponent,
    ChatbotComponent,
    SupportFormComponent,
    PostingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    ReactiveFormsModule,
    NgbModule,NgxPermissionsModule.forRoot(),

    FormsModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [ 
    AuthService, 
    HttpErrorHandler, 
    MessageService, 
    NgbModal, 
    {provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}},

  
    FormsModule

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
