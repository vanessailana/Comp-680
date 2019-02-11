import { Routes } from '@angular/router';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';


export const ROUTES: Routes = [
     { path: 'profile', component: ProfileComponent },
  { path: 'callback', component: CallbackComponent },
  { path: '**', redirectTo: '' }
];
