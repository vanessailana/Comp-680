<<<<<<< HEAD
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { NgbModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';




=======
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
>>>>>>> Mail-Handler-backend
import { AuthService } from './auth/auth.service';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { CallbackComponent } from './callback/callback.component';
import { RouterModule } from '@angular/router';
<<<<<<< HEAD
import { HttpClientModule } from '@angular/common/http';

import { ProfileComponent } from './profile/profile.component';
import { SupportFormComponent } from './support-form/support-form.component';
import { HttpErrorHandler } from './http-error-handler.service';
import { MessageService } from './message.service';


=======
import { ProfileComponent } from './profile/profile.component';
>>>>>>> Mail-Handler-backend

@NgModule({
  declarations: [
    AppComponent,
    CallbackComponent,
<<<<<<< HEAD
    ProfileComponent,
    SupportFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    ReactiveFormsModule,
    NgbModule,
    FormsModule
  ],
  providers: [ AuthService, HttpErrorHandler, MessageService, NgbModal],
=======
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [ AuthService],
>>>>>>> Mail-Handler-backend
  bootstrap: [AppComponent]
})
export class AppModule { }
