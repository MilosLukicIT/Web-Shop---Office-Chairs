import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ArticleService } from '../../services/article.service'
import { Router } from '@angular/router';
import * as _ from 'underscore';
import { CartService } from '../../services/cart.service';
import { AuthenticationService } from '../../services/authentication.service';
import { ArticleViewDto } from 'src/app/models/articleDto/articleViewDto';
import { CustomerOrderArticleViewDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleViewDto';
import { MatDialog } from '@angular/material/dialog';
import { ArticleDialogComponent } from '../dialogs/article-dialog/article-dialog.component';
import { ArticleBrandViewDto } from 'src/app/models/articleBrandDto/articleBrandViewDto';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  
  products = [new ArticleViewDto()];
  sortedProduct: any;
  count = 0;


  constructor(public user: UserService, public articleService: ArticleService, public router: Router, 
              public cartService: CartService, public auth: AuthenticationService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }

  addToCart(article: ArticleViewDto){
    let addItem = new CustomerOrderArticleViewDto();
    addItem.article = article
    addItem.amountOfArticle = 1;
    this.cartService.addToCart(addItem);
  }

  clearCart(){
    this.cartService.clearCart();
  }

  
  public isAdmin() {
    let tokenInfo = this.auth.decodeToken();
    return (tokenInfo.role == 'ADMIN');
  }

  loadData() {
    this.articleService.getArticles().subscribe(data => {
      this.products = data;
      this.sortedProduct = this.products;
    },(error: any) => {
      console.log(error)
    })

  }

  public clickMethod(name: string, _id: string) {
    if(confirm("Are you sure to delete "+ name + " ?")) {
      this.router.navigate(['/product/delete/'+ _id]);
      
    }
  }

  public updateItem(id: string){
    this.router.navigate(['/product/update/'+ id]);
  }


  public openDialog(flag: number, articleId?: String, nameOfArticle?: String, manufacturerOfArticle?: String, priceOfArticle?: Number, colorOfArticle?: String, carryingCapacity?: Number, articleBrand?: ArticleBrandViewDto) {

    const dialogRef = this.dialog.open(ArticleDialogComponent, {height: '600px',
    width: '500px', data: {articleId, nameOfArticle, manufacturerOfArticle, priceOfArticle, colorOfArticle, carryingCapacity, articleBrand}});
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


