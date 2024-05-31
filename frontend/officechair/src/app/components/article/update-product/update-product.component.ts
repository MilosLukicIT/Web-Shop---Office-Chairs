import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ArticleUpdateDto } from 'src/app/models/articleDto/articleUpdateDto';
import {  ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id = '';
  submitted = false;
  products = [new ArticleUpdateDto()];
  menuProduct = new ArticleUpdateDto();
  productForm!: FormGroup;

  constructor(private product: ArticleService, public fb: FormBuilder, public actRoute: ActivatedRoute) {
    this.productForm = this.fb.group({
      name: [''],
      type: [''],
      price: 0,
      description: ['']
    });
  }

  ngOnInit(): void {
    this.buildFormControls();
    this.id = this.actRoute.snapshot.paramMap.get('id')!;
    this.loadData();
    // setTimeout(() => {
    //   this.menuProduct = this.products[this.products.findIndex(o => o._id == this.id)];
    // }, 2000);
    setTimeout(() => {
      this.productForm.patchValue(this.menuProduct);
    }, 2000);
  }




  private loadData() {
    this.product.getArticles().subscribe(data => {
      this.products = data;
    }, (error: any) => {
      console.log(error)
    })
  }

  get name() {
    return this.productForm.get('name');
  }

  get price() {
    return this.productForm.get('price');
  }

  get type() {
    return this.productForm.get('type');
  }

  get description() {
    return this.productForm.get('description');
  }


  handleSubmit() {
    // console.log(this.productForm.value);
    // this.buildProduct();
    // this.product.updateItem(this.id, this.menuProduct).subscribe(data => {
    //   this.submitted = true;
    // })
  }


  public buildProduct() {
    // let p = this.productForm.value;
    // this.menuProduct.name = p.name;
    // this.menuProduct.type = p.type;
    // this.menuProduct.description = p.description;
    // this.menuProduct.price = parseInt(p.price);
  }

  buildFormControls() {
    this.productForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required]),
      type: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    }
    )
  }

}
