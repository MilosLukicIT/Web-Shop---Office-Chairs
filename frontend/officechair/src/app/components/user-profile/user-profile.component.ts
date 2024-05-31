import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { UserViewDto } from 'src/app/models/userDto/userViewDto';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser!: UserViewDto;
  orders = [new CustomerOrderViewDto()];
  userOrder = [new CustomerOrderViewDto()];
  total = 0;
  orderExist = false;
  constructor(public userService: UserService, private actRoute: ActivatedRoute, private router: Router, private auth: AuthenticationService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  public deleteProfile(_id: String) {
    if (confirm("Are you sure to delete your profile?")) {
      this.userService.deleteUserProfile(_id).subscribe(res => {
        var userName = res.first_name;
        window.alert("Good bye " + userName + ":(");
        this.auth.doLogout();
        this.router.navigate(['login']);

      }, error => console.log(error));
    }
  }
  loadData() {
    this.userService.getUserProfile(this.auth.decodeToken().id).subscribe(res => {
      this.currentUser = res;
    });
  }

  public isAdmin() {
    let tokenInfo = this.auth.decodeToken();
    return (tokenInfo.role == 'ADMIN');
  }

  public createNewAdmin(){
    this.router.navigate(['signup'])
  }

}
