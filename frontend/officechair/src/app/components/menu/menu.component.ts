import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ArticleService } from '../../services/article.service'
import * as _ from 'underscore';
import { CartService } from '../../services/cart.service';
import { AuthenticationService } from '../../services/authentication.service';
import { ArticleViewDto } from 'src/app/models/articleDto/articleViewDto';
import { CustomerOrderArticleViewDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleViewDto';
import { MatDialog } from '@angular/material/dialog';
import { ArticleDialogComponent } from '../dialogs/article-dialog/article-dialog.component';
import { ArticleBrandViewDto } from 'src/app/models/articleBrandDto/articleBrandViewDto';
import { ArticleTypeViewDto } from 'src/app/models/artcleTypeDto/articleTypeViewDto';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  
  products = [new ArticleViewDto()];
  sortedProduct: any;
  count = 0;
  pageNumber = 1;
  numberPerPage = new FormControl(3);
  sortValue = new FormControl('nameOfArticle');
  sortDirection = new FormControl(1);
  items = 3;
  sortV = 'nameOfArticle';
  sortDirec = 1;



  constructor(public user: UserService, private articleService: ArticleService,
              private cartService: CartService, private auth: AuthenticationService, private dialog: MatDialog) {
                this.numberPerPage.valueChanges.subscribe(val => {
                  if(val != null)
                    {
                      this.items = val;
                      this.loadData();
                    }
                    
                })

                this.sortValue.valueChanges.subscribe(val => {
                  if(val != null)
                    {
                      this.sortV = val;
                      this.loadData();
                    }
                    
                })

                this.sortDirection.valueChanges.subscribe(val => {
                  if(val != null)
                    {
                      this.sortDirec = val;
                      this.loadData();
                    }
                    
                })
               }

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
    this.articleService.getArticles(this.pageNumber-1, this.items, this.sortDirec, this.sortV).subscribe(data => {
      this.products = data;
      this.sortedProduct = this.products;
    },(error: any) => {
      console.log(error)
    })

  }

  public deleteArticle(nameOfArticle: String, id: string) {
    if(confirm("Da li ste sigurni da želite da izbrišete "+ nameOfArticle + " ?")) {

      this.articleService.deleteArticle(id).subscribe(res =>{
        this.loadData();
      });
      
      
    }
    
  }


  public prevPage(){

    if(this.pageNumber>1)
    this.pageNumber--;
    this.loadData();   
  }

  public nextPage(){
    console.log(this.sortedProduct.length == this.items)
    if(this.sortedProduct.length == this.items){
      this.pageNumber++;
      this.loadData();
    }
    
  }

  public openDialog(flag: number, articleId?: String, nameOfArticle?: String, priceOfArticle?: number, manufacturerOfArticle?: String, 
    carryingCapacity?: Number, colorOfArticle?: String, availableAmountOfArticle?: Number, 
    warrantyLength?: String, heightOfArticle?: Number, widthOfArticle?: Number, lengthOfArticle?: Number, 
    descriptionOfArticle?: String, discount?: number, imageUrl?: String,
    articleBrand?: ArticleBrandViewDto, articleType?: ArticleTypeViewDto) {

    const dialogRef = this.dialog.open(ArticleDialogComponent, {height: '600px',
    width: '100%', data: {articleId, nameOfArticle, priceOfArticle, manufacturerOfArticle, carryingCapacity, colorOfArticle,availableAmountOfArticle,
      warrantyLength, heightOfArticle, widthOfArticle, lengthOfArticle, descriptionOfArticle, discount, imageUrl, articleBrand, articleType}});
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


