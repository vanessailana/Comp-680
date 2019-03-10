import { Routes } from '@angular/router';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { SupportFormComponent } from './support-form/support-form.component';
import { PostingComponent } from './posting/posting.component';
import { ViewpostingComponent } from './posting/viewposting/viewposting.component';

export const ROUTES: Routes = [
  { path: 'profile', component: ProfileComponent },
  { path: 'chat', component: ChatbotComponent },
  { path: 'posting', component: PostingComponent },
  { path: 'callback', component: CallbackComponent },
  { path: 'support', component: SupportFormComponent},
  { path: 'view_jobs', component: ViewpostingComponent},
  { path: '**', redirectTo: '' }
];
