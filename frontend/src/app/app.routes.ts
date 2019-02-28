import { Routes } from '@angular/router';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';
<<<<<<< HEAD
import { SupportFormComponent } from './support-form/support-form.component'
=======

>>>>>>> Mail-Handler-backend

export const ROUTES: Routes = [
     { path: 'profile', component: ProfileComponent },
  { path: 'callback', component: CallbackComponent },
<<<<<<< HEAD
  { path: 'support', component: SupportFormComponent},
=======
>>>>>>> Mail-Handler-backend
  { path: '**', redirectTo: '' }
];
