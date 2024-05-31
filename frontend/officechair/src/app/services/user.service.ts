import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { UserCreateDto } from '../models/userDto/userCreationDto';
import { UserUpdateDto } from '../models/userDto/userUpdateDto';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUser = environment.apiUserUri;
  headers = new HttpHeaders().set('Content-type', 'application/json');
  constructor(private http: HttpClient, public router: Router) { }

  signUp(user: UserCreateDto): Observable<any> {
    let api = `${this.apiUser}`;
    return this.http.post(api, user, {responseType: 'text'}).pipe(catchError(this.handleError));
  }


  getAllUsers(): Observable<any> {
    return this.http.get(`${this.apiUser}`).pipe(catchError(this.handleError));
  }
  
  getUserProfile(id: any): Observable<any> {
    let api = `${this.apiUser}/${id}`;
    return this.http.get(api, { headers: this.headers }).pipe(
      map((res) => {
        return res || {};
      }),
      catchError(this.handleError)
    );
  }

deleteUserProfile(id :any): Observable<any>{
  let api = `${this.apiUser}/${id}`;
  return this.http.delete(api);
}
  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }

  updateUserProfile(user: UserUpdateDto): Observable<any> {
    return this.http.put(`${this.apiUser}`, user).pipe(catchError(this.handleError));
  }
}
