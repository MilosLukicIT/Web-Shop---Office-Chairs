import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ArticleTypeViewDto } from 'src/app/models/artcleTypeDto/articleTypeViewDto';
import { ArticleBrandViewDto } from 'src/app/models/articleBrandDto/articleBrandViewDto';
import { ArticleCreateDto } from 'src/app/models/articleDto/articleCreateDto';
import { ArticleUpdateDto } from 'src/app/models/articleDto/articleUpdateDto';
import { ArticleViewDto } from 'src/app/models/articleDto/articleViewDto';
import { ArticleBrandService } from 'src/app/services/article-brand.service';
import { ArticleTypeService } from 'src/app/services/article-type.service';
import { ArticleService } from 'src/app/services/article.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-article-dialog',
  templateUrl: './article-dialog.component.html',
  styleUrls: ['./article-dialog.component.css']
})
export class ArticleDialogComponent implements OnInit {


  flag!: number;
  updateArticle: ArticleUpdateDto = new ArticleUpdateDto();
  createArticle: ArticleCreateDto = new ArticleCreateDto();
  articleBrands!: ArticleBrandViewDto[];
  articleTypes!: ArticleTypeViewDto[];

  constructor(public dialogRef: MatDialogRef<ArticleViewDto>, 
              @Inject (MAT_DIALOG_DATA) public data: ArticleViewDto,
              public articleService: ArticleService, private authService: AuthenticationService, private articleBrandService: ArticleBrandService,
              private articleTypeService: ArticleTypeService) { }

  ngOnInit(): void {
    this.articleBrandService.getArticleBrands().subscribe(res => {
      this.articleBrands = res;
    });

    this.articleTypeService.getArticleTypes().subscribe(res => {
      this.articleTypes = res;
    })
  }

  public compare(a:any, b:any){
    return a.brandId == b.brandId;
  }

  public update(): void {

  }

  public add(): void {
  
    this.createArticle.nameOfArticle = this.data.nameOfArticle;
    
  }

  public delete(): void {


    this.articleService.deleteArticle(this.data.articleId).subscribe();
  }

  public cancel():void{
    this.dialogRef.close();
  }


  isAdmin(){
      return (this.authService.decodeToken().role == 'ADMIN');
  }

}
