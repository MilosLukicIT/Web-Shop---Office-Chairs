import { HttpClient } from '@angular/common/http';
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
import { FirebaseStorageServiceService } from 'src/app/services/firebase-storage-service.service';

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

  selectedFile!: File;

  constructor(public dialogRef: MatDialogRef<ArticleViewDto>, 
              @Inject (MAT_DIALOG_DATA) public data: ArticleViewDto,
              public articleService: ArticleService, private authService: AuthenticationService, private articleBrandService: ArticleBrandService,
              private articleTypeService: ArticleTypeService, private http: HttpClient, private firebase: FirebaseStorageServiceService) { }

  ngOnInit(): void {
    this.articleBrandService.getArticleBrands().subscribe(res => {
      this.articleBrands = res;
    });

    this.articleTypeService.getArticleTypes().subscribe(res => {
      this.articleTypes = res;
    });
  }

  public compare(a:any, b:any){
    return a.brandId == b.brandId;
  }

  public compare1(a:any, b:any){
    return a.typeId == b.typeId;
  }

  public update(): void {

    this.updateArticle.articleId = this.data.articleId;
    this.updateArticle.nameOfArticle = this.data.nameOfArticle;
    this.updateArticle.priceOfArticle = this.data.priceOfArticle;
    this.updateArticle.manufacturerOfArticle = this.data.manufacturerOfArticle;
    this.updateArticle.carryingCapacity = this.data.carryingCapacity;
    this.updateArticle.colorOfArticle = this.data.colorOfArticle;
    this.updateArticle.availableAmountOfArticle = this.data.availableAmountOfArticle;
    this.updateArticle.warrantyLength = this.data.warrantyLength;
    this.updateArticle.heightOfArticle = this.data.heightOfArticle;
    this.updateArticle.widthOfArticle = this.data.widthOfArticle;
    this.updateArticle.lengthOfArticle = this.data.lengthOfArticle;
    this.updateArticle.descriptionOfArticle = this.data.descriptionOfArticle;
    this.updateArticle.discount = this.data.discount;
    this.updateArticle.imageUrl = this.data.imageUrl;
    this.updateArticle.articleBrand = this.data.articleBrand;
    this.updateArticle.articleType = this.data.articleType;

    this.articleService.updateArticle(this.updateArticle).subscribe();
  }

  public add(): void {
    
    this.createArticle.nameOfArticle = this.data.nameOfArticle;
    this.createArticle.priceOfArticle = this.data.priceOfArticle;
    this.createArticle.manufacturerOfArticle = this.data.manufacturerOfArticle;
    this.createArticle.carryingCapacity = this.data.carryingCapacity;
    this.createArticle.colorOfArticle = this.data.colorOfArticle;
    this.createArticle.availableAmountOfArticle = this.data.availableAmountOfArticle;
    this.createArticle.warrantyLength = this.data.warrantyLength;
    this.createArticle.heightOfArticle = this.data.heightOfArticle;
    this.createArticle.widthOfArticle = this.data.widthOfArticle;
    this.createArticle.lengthOfArticle = this.data.lengthOfArticle;
    this.createArticle.descriptionOfArticle = this.data.descriptionOfArticle;
    this.createArticle.discount = this.data.discount;
    this.createArticle.imageUrl = this.data.imageUrl;
    this.createArticle.articleBrand = this.data.articleBrand;
    this.createArticle.articleType = this.data.articleType;

    this.articleService.addArticle(this.createArticle).subscribe(res => {

    });

    console.log(this.data.imageUrl)
    
  }


  public onFileSelected(event: any){
    this.selectedFile = <File>event.target.files[0];
  }

  public async upload() {
    if(this.selectedFile){
      const filePath = `uploads/${this.selectedFile.name}`;
      try {
        const downloadUrl = await this.firebase.uploadImg(filePath, this.selectedFile);
        this.data.imageUrl = downloadUrl;
        window.alert("Image uploaded!!!")
      } catch (error) {
        console.log(error);
      }
    }



  }

  public cancel():void{
    this.dialogRef.close();
  }


  isAdmin(){
      return (this.authService.decodeToken().role == 'ADMIN');
  }

}
