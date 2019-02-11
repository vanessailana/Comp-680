import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CallbackComponent } from './callback/callback.component';
import {HomeComponent } from './home/home.component';


export const ROUTES: Routes = [
  { path: '', component: AppComponent },
   { path: 'home', component: HomeComponent },
  { path: 'callback', component: CallbackComponent },
  { path: '**', redirectTo: '' }
];
