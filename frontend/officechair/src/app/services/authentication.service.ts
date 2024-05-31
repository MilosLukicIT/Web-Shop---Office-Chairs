import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
import { AuthCredentials } from '../models/auth';
import { environment } from 'src/environments/environment';
import { Token } from '../models/token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  apiUserLogin = environment.apiUserLogin;
  constructor(private http: HttpClient, private router: Router) { }

  singIn(credentials: AuthCredentials) {
    console.log(credentials);
    return this.http.post(`${this.apiUserLogin}`, credentials)
      .subscribe((res: any) => {
        localStorage.setItem('access-token', res.token);
        this.router.navigate(['user-profile']);
      });
  };
  getToken() {
    return localStorage.getItem('access-token');
  }

  decodeToken():Token{
    let token = this.getToken();
    try {
      return jwt_decode(token!)
    } catch(Error) {
      return new Token();
    }
  }

  get isLoggedIn(){
    let authToken = localStorage.getItem('access-token');
    return authToken !== null ? true : false;
  }

  doLogout(){
    let removeToken = localStorage.removeItem('access-token');
    if (removeToken == null) {
      this.router.navigate(['login']);
    }
  }
}
