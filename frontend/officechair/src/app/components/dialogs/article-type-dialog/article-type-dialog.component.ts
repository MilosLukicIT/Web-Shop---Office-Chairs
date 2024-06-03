import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ArticleTypeCreateDto } from 'src/app/models/artcleTypeDto/articleTypeCreateDto';
import { ArticleTypeUpdateDto } from 'src/app/models/artcleTypeDto/articleTypeUpdateDto';
import { ArticleTypeViewDto } from 'src/app/models/artcleTypeDto/articleTypeViewDto';
import { ArticleTypeService } from 'src/app/services/article-type.service';

@Component({
  selector: 'app-article-type-dialog',
  templateUrl: './article-type-dialog.component.html',
  styleUrls: ['./article-type-dialog.component.css']
})
export class ArticleTypeDialogComponent implements OnInit {

  flag!: number;
  updateType: ArticleTypeUpdateDto = new ArticleTypeUpdateDto();
  createType: ArticleTypeCreateDto = new ArticleTypeCreateDto();


  constructor(public dialogRef: MatDialogRef<ArticleTypeViewDto>,
    @Inject (MAT_DIALOG_DATA) public data: ArticleTypeViewDto, private typeService: ArticleTypeService) { }

  ngOnInit(): void {
  }


  public update(): void {

    this.updateType.typeId = this.data.typeId;
    this.updateType.nameOfType = this.data.nameOfType;

    this.typeService.updateArticleType(this.updateType).subscribe();
  }

  public add(): void {
    this.createType.nameOfType = this.data.nameOfType;

    this.typeService.addArticleType(this.createType).subscribe();
  
  }

  public delete(): void {
    this.typeService.deleteArticleType(this.data.typeId).subscribe();
  }

  public cancel():void{
    this.dialogRef.close();
  }

}
