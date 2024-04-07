import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "../register/register.component";
import {authGuard} from "./services/auth/auth.guard";
import {TfaSetupComponent} from "./pages/tfa-setup/tfa-setup.component";
import {PersonalComponent} from "./pages/personal/personal.component";
import {TestComponent} from "./pages/test/test.component";


const routes: Routes = [
  { path: '', redirectTo: '/main-page', pathMatch: 'full' },
  { path: 'main-page',
    component: MainPageComponent,
    canActivate: [authGuard]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'tfa-setup', component: TfaSetupComponent},
  {path: 'personal', component: PersonalComponent},
  {path: 'test', component: TestComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
