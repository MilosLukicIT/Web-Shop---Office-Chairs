import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ArticleBrandCreateDto } from 'src/app/models/articleBrandDto/articleBrandCreateDto';
import { ArticleBrandUpdateDto } from 'src/app/models/articleBrandDto/articleBrandUpdateDto';
import { ArticleBrandViewDto } from 'src/app/models/articleBrandDto/articleBrandViewDto';
import { ArticleBrandService } from 'src/app/services/article-brand.service';

@Component({
  selector: 'app-article-brand-dialog',
  templateUrl: './article-brand-dialog.component.html',
  styleUrls: ['./article-brand-dialog.component.css']
})
export class ArticleBrandDialogComponent implements OnInit {


  flag!: number;
  updateBrand: ArticleBrandUpdateDto = new ArticleBrandUpdateDto();
  createBrand: ArticleBrandCreateDto = new ArticleBrandCreateDto();

  constructor(public dialogRef: MatDialogRef<ArticleBrandViewDto>,
    @Inject (MAT_DIALOG_DATA) public data: ArticleBrandViewDto, private brandService: ArticleBrandService
  ) { }

  ngOnInit(): void {
  }

  public update(): void {

    this.updateBrand.brandId = this.data.brandId;
    this.updateBrand.nameOfBrand = this.data.nameOfBrand;
    this.updateBrand.countryOfBrand = this.data.countryOfBrand;

    this.brandService.updateArticleBrand(this.updateBrand).subscribe();
  }

  public add(): void {
    this.createBrand.nameOfBrand = this.data.nameOfBrand;
    this.createBrand.countryOfBrand = this.data.countryOfBrand;

    this.brandService.addArticleBrand(this.createBrand).subscribe();
  
  }

  public delete(): void {
    this.brandService.deleteArticleBrand(this.data.brandId).subscribe();
  }

  public cancel():void{
    this.dialogRef.close();
  }



}
