import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { UserService } from '../../services/user.service'; 
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(public user: UserService, public cartService: CartService, public authService: AuthenticationService) { 
  }

  ngOnInit(): void {
    
  }

  public getUserLink(){
    let tokenInfo = this.authService.decodeToken();
    return "/user-profile";
  }

  public isAdmin(){
    let tokenInfo = this.authService.decodeToken();
    return (tokenInfo.role == 'ADMIN' || tokenInfo.role == 'WORKER');
  }

  itemsCount(){
    return this.cartService.itemsCount();
  }

  logoutAndClearCart(){
    this.authService.doLogout();
  }

}
