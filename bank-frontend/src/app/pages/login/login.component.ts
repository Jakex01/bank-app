import {Component} from '@angular/core';

import {RegisterRequest} from "../../models/register-request";
import {AuthenticationResponse} from "../../models/authentication-response";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/auth-service/authentication.service";
import {VerificationRequest} from "../../models/verification-request";
import {AuthenticationRequest} from "../../models/authentication-request";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  isActive = false; // Controls which form is active
  registerRequest: RegisterRequest = {};
  authResponse: AuthenticationResponse = {};
  authRequest: AuthenticationRequest = {
    email: '',
    password: '',
  };

  showMfaSetup: boolean = false;
  message = '';
  otpCode = '';
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) { }



  registerUser() {
    this.message = '';
    this.registerRequest.role = "USER";
    this.authService.register(this.registerRequest)
      .subscribe({
        next: (response) => {
          if (response) {
            this.authResponse = response;
            this.router.navigate(['/tfa-setup'], { state: { authResponse: response, email: this.registerRequest.email } });
          } else {
            // inform the user
            this.message = 'Account created successfully\nYou will be redirected to the Login page in 3 seconds';
            setTimeout(() => {
              this.router.navigate(['login']);
            }, 3000)
          }
        }
      });

  }

  authenticate() {
    console.log(this.authRequest.email + ": " + this.authRequest.password);
    this.authService.login(this.authRequest)
        .subscribe({
          next: (response) => {
            this.authResponse = response;
            if (!this.authResponse.mfaEnabled) {
              localStorage.setItem('token', response.accessToken as string);
              this.router.navigate(['personal']);
            }
          }
        });
  }

  // verifyTfa() {
  //   this.message = '';
  //   const verifyRequest: VerificationRequest = {
  //     email: this.registerRequest.email,
  //     code: this.otpCode
  //   };
  //   this.authService.verifyCode(verifyRequest)
  //     .subscribe({
  //       next: (response) => {
  //         this.message = 'Account created successfully\nYou will be redirected to the main page in 3 seconds';
  //         setTimeout(() => {
  //           localStorage.setItem('token', response.accessToken as string);
  //           this.router.navigate(['main-page']);
  //         }, 3000);
  //       }
  //     });
  // }
  toggleActive(isActive: boolean): void {
    this.isActive = isActive;
  }
  verifyCode() {
    const verifyRequest: VerificationRequest = {
      email: this.authRequest.email,
      code: this.otpCode
    };
    this.authService.verifyCode(verifyRequest)
        .subscribe({
          next: (response) => {
            localStorage.setItem('token', response.accessToken as string);
            this.router.navigate(['welcome']);
          }
        });
  }

}
