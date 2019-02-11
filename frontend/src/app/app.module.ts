import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { CallbackComponent } from './callback/callback.component';
import { RouterModule } from '@angular/router';
import {HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    CallbackComponent, HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [ AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
