import { Routes } from '@angular/router';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';
import { SupportFormComponent } from './support-form/support-form.component'

export const ROUTES: Routes = [
     { path: 'profile', component: ProfileComponent },
  { path: 'callback', component: CallbackComponent },
  { path: 'support', component: SupportFormComponent},
  { path: '**', redirectTo: '' }
];
