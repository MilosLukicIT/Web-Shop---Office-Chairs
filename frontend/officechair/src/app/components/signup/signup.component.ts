import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserCreateDto } from 'src/app/models/userDto/userCreationDto';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm!: FormGroup;
  userCreate!: UserCreateDto;
  constructor(public fb: FormBuilder, public userService: UserService, public router: Router, public authService: AuthenticationService) { 
    this.signUpForm = this.fb.group({
      name: [''],
      surname: [''],
      email: [''],
      password: [''],
      role: [''],
      adress: [''],
      username: [''],
      contactNumber: ['']

    })
    this.buildFormControls();
  }

  ngOnInit(): void {
  }

  registerUser() {

    if(!this.authService.isLoggedIn){
      this.signUpForm.value.role = 'CUSTOMER'
    }


    this.userCreate = this.signUpForm.value;
    this.userService.signUp(this.userCreate).subscribe((res) => {
      
      if(this.authService.isLoggedIn)
      {
        this.signUpForm.reset();
      }
      else
      this.router.navigate(['login']);
    });
  }

  get email() {
    return this.signUpForm.get('email');
  }

  get password() {
    return this.signUpForm.get('password');
  }

  get surname() {
    return this.signUpForm.get('surname');
  }

  get adress() {
    return this.signUpForm.get('adress');
  }

  get name() {
    return this.signUpForm.get('name');
  }

  get username() {
    return this.signUpForm.get('username');
  }

  get contactNumber() {
    return this.signUpForm.get('contactNumber');
  }


  buildFormControls(){
    this.signUpForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      surname: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.email, Validators.required]),
      role: new FormControl(''),
      password: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      adress: new FormControl('', [Validators.required]),
      contactNumber: new FormControl('', [Validators.required])
    })
  }

  public isAdmin() {
    let tokenInfo = this.authService.decodeToken();
    return (tokenInfo.role == 'Admin');
  }
}
