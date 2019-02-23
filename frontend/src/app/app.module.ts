import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AuthService } from './auth/auth.service';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { CallbackComponent } from './callback/callback.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { ProfileComponent } from './profile/profile.component';
import { SupportFormComponent } from './support-form/support-form.component';
import { HttpErrorHandler } from './http-error-handler.service';
import { MessageService } from './message.service';


@NgModule({
  declarations: [
    AppComponent,
    CallbackComponent,
    ProfileComponent,
    SupportFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    ReactiveFormsModule
  ],
  providers: [ AuthService, HttpErrorHandler, MessageService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
