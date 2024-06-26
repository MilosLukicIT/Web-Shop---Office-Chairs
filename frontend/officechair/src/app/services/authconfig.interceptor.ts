import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor
} from '@angular/common/http';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class AuthconfigInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    if(this.authService.isLoggedIn){
      const authToken = this.authService.getToken();
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + authToken
        }
      });
    }
   
    return next.handle(request);
  };
}
