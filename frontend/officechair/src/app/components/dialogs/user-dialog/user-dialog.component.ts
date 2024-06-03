import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/models/user';
import { UserCreateDto } from 'src/app/models/userDto/userCreationDto';
import { UserUpdateDto } from 'src/app/models/userDto/userUpdateDto';
import { UserViewDto } from 'src/app/models/userDto/userViewDto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.css']
})
export class UserDialogComponent implements OnInit {


  flag!: number;
  updateUser: UserUpdateDto = new UserUpdateDto();
  createUser: UserCreateDto = new UserCreateDto();

  roles = ['WORKER', 'ADMIN']

  constructor(public dialogRef: MatDialogRef<UserViewDto>, 
    @Inject (MAT_DIALOG_DATA) public data: User,

   public userService: UserService, private authService: AuthenticationService) { }

  ngOnInit(): void {

  }

  compare(a:any, b:any) {
    return a == b;
  }

  public update(): void {

    this.updateUser.userId = this.data.userId;
    this.updateUser.name = this.data.name;
    this.updateUser.surname = this.data.surname;
    this.updateUser.email = this.data.email;
    this.updateUser.adress = this.data.adress;
    this.updateUser.username = this.data.role;
    this.updateUser.role = this.data.role;
    this.updateUser.contactNumber = this.data.contactNumber;
 
    this.userService.updateUserProfile(this.updateUser).subscribe();
  }

  public add(): void {
    this.createUser.name = this.data.name;
    this.createUser.surname = this.data.surname;
    this.createUser.email = this.data.email;
    this.createUser.adress = this.data.adress;
    this.createUser.username = this.data.username;
    this.createUser.role = this.data.role;
    this.createUser.contactNumber = this.data.contactNumber;
    this.createUser.password = this.data.password;

    this.userService.createEmployee(this.createUser).subscribe();
  
  }

  public delete(): void {
    this.userService.deleteUserProfile(this.data.userId).subscribe();
  }

  public cancel():void{
    this.dialogRef.close();
  }


  isAdmin(){
      return (this.authService.decodeToken().role == 'ADMIN');
  }

}
