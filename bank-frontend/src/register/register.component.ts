import { Component } from '@angular/core';
import {RegisterRequest} from "../app/models/register-request";
import {AuthenticationResponse} from "../app/models/authentication-response";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerRequest: RegisterRequest = {};
  authResponse: AuthenticationResponse = {};

  message = '';
  otpCode = '';


}
