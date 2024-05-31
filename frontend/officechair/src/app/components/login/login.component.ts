import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  signinForm!: FormGroup;
  constructor(public fb: FormBuilder, public userService: UserService, router: Router, private auth: AuthenticationService) {
    this.signinForm = this.fb.group({
      email: [''],
      password: ['']
    });
   }

  ngOnInit(): void {
    this.buildFormControls();
  }

  get email() {
    return this.signinForm.get('email');
  }

  get password() {
    return this.signinForm.get('password');
  }

  loginUser(){
    this.auth.singIn(this.signinForm.value);
  }

  buildFormControls(){
    this.signinForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    })
  }

}
