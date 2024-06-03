import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { UserViewDto } from 'src/app/models/userDto/userViewDto';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { UserDialogComponent } from '../dialogs/user-dialog/user-dialog.component';
import { ArticleBrandViewDto } from 'src/app/models/articleBrandDto/articleBrandViewDto';
import { ArticleTypeViewDto } from 'src/app/models/artcleTypeDto/articleTypeViewDto';
import { ArticleBrandService } from 'src/app/services/article-brand.service';
import { ArticleTypeService } from 'src/app/services/article-type.service';
import { ArticleTypeDialogComponent } from '../dialogs/article-type-dialog/article-type-dialog.component';
import { ArticleBrandDialogComponent } from '../dialogs/article-brand-dialog/article-brand-dialog.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {


  displayedColumns = ['name', 'surname', 'email', 'adress', 'role',  'actions'];
  displayedColumnsBrands = ['nameOfBrand', 'countryOfBrand', 'actions'];
  displayedColumnsTypes = ['nameOfType',  'actions'];
  dataSource!: MatTableDataSource<UserViewDto>;
  dataSourceBrands!: MatTableDataSource<ArticleBrandViewDto>;
  dataSourceTypes!: MatTableDataSource<ArticleTypeViewDto>;
  currentUser!: UserViewDto;
  constructor(public userService: UserService, private auth: AuthenticationService, public dialog: MatDialog, 
    private brandService: ArticleBrandService, private typeService: ArticleTypeService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.userService.getUserProfile(this.auth.decodeToken().id).subscribe(res => {
      this.currentUser = res;
    });

    this.userService.getAllUsers().subscribe(res => {
      this.dataSource = new MatTableDataSource(res);
    })

    this.brandService.getArticleBrands().subscribe(res => {
      this.dataSourceBrands = new MatTableDataSource(res);
    })

    this.typeService.getArticleTypes().subscribe(res => {
      this.dataSourceTypes = new MatTableDataSource(res);
    })
  }

  public isAdmin() {
    let tokenInfo = this.auth.decodeToken();
    return (tokenInfo.role == 'ADMIN');
  }


  public openDialog(flag: number,userId?: string, name?: string, surname?: string, email?: string, 
    adress?: string, username?: string, role?: string, contactNumber?: string){

    const dialogRef = this.dialog.open(UserDialogComponent, {data: {userId, name, surname, email
    , adress, username, role, contactNumber}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      result => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }



  public openDialogBrand(flag: number,brandId?: string, nameOfBrand?: string, countryOfBrand?: string){

    const dialogRef = this.dialog.open(ArticleBrandDialogComponent, {data: {brandId, nameOfBrand, countryOfBrand}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      result => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }

  public openDialogType(flag: number,typeId?: string, nameOfType?: string){

    const dialogRef = this.dialog.open(ArticleTypeDialogComponent, {data: {typeId, nameOfType}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      result => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }
}
