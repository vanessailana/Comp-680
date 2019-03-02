import { Routes } from '@angular/router';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';
import { ChatbotComponent } from './chatbot/chatbot.component';


export const ROUTES: Routes = [
  { path: 'profile', component: ProfileComponent },
    { path: 'support', component: ChatbotComponent },
  { path: 'callback', component: CallbackComponent },
  { path: '**', redirectTo: '' }
];
