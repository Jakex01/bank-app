import {Component, OnInit} from '@angular/core';
import {VerificationRequest} from "../../models/verification-request";
import {Router} from "@angular/router";
import {AuthenticationResponse} from "../../models/authentication-response";
import {AuthenticationService} from "../../services/auth-service/authentication.service";
import {NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-tfa-setup',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './tfa-setup.component.html',
  styleUrl: './tfa-setup.component.css'
})


export class TfaSetupComponent implements OnInit{
  authResponse: AuthenticationResponse = {};
  message = '';
  otpCode = '';
  email = '';
  constructor(private router: Router,
              private authService: AuthenticationService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras.state) {
      this.authResponse = navigation.extras.state['authResponse'];
      this.email = navigation.extras.state['email'];
    }
  }

  ngOnInit(): void {
    if (!this.authResponse || !this.email) {
      this.router.navigate(['/login']);
    }
    }

  verifyTfa() {
    this.message = '';
    const verifyRequest: VerificationRequest = {
      email: this.email,
      code: this.otpCode
    };

    console.log(verifyRequest);
    this.authService.verifyCode(verifyRequest)
        .subscribe({
          next: (response) => {
            this.message = 'Account created successfully\nYou will be redirected to the main page in 3 seconds';
            setTimeout(() => {
              localStorage.setItem('token', response.accessToken as string);
              this.router.navigate(['main-page']);
            }, 3000);
          }
        });
  }

}
