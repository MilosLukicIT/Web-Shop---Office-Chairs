import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ArticleViewDto } from 'src/app/models/articleDto/articleViewDto';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  
  submitted = false;
  nameError = false;
  nameErrorMsg = 'Ovo jelo već postoji!!'
  products!: ArticleViewDto[];
  menuProduct = new ArticleViewDto();
  productForm!: FormGroup;
  
  constructor(private product: ArticleService, public fb: FormBuilder) {
    this.productForm = this.fb.group({
      name: [''],
      type: [''],
      price: 0,
      description: ['']

    });
   }

  ngOnInit(): void {
    this.loadData();
  }




  private loadData() {
    this.product.getArticles().subscribe(data => {
      this.products = data;
    },(error: any) => {
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
    // if (!this.isInvalidName()) {
    //   this.product.addItem(this.menuProduct).subscribe(data => {
    //     this.submitted = true;
    //     this.nameError = false;
    //   })
    // }
  }

  //Check for duplicate email

  isInvalidName() {
    // let name = this.productForm.get('name')?.value;
    // let type = this.productForm.get('type')?.value;
    // if (this.products.filter(el => el.name == name && el.type == type).length > 0) {
    //   this.nameError = true;
    //   return true;
    // } else
    //   return false
  }

  public buildProduct(){
    let p = this.productForm.value;
    this.menuProduct.nameOfArticle = p.name;
    // this.menuProduct.type = p.type;
    // this.menuProduct.description = p.description;
    // this.menuProduct.price = parseInt(p.price);
  }


}

