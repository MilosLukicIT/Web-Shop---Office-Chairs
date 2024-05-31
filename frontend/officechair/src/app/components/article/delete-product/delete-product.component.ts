import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-delete-product',
  templateUrl: './delete-product.component.html',
  styleUrls: ['./delete-product.component.css']
})
export class DeleteProductComponent implements OnInit {


  id: string = "";
  productName: string = "";
  isDeleted = false;
  constructor(private product: ArticleService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.deleteRecord();
  }

  private deleteRecord() {
    this.product.deleteArticle(this.id).subscribe(data => {
      this.productName = data.name;
      this.isDeleted = true;
    }, error => console.log(error))

  }

}
