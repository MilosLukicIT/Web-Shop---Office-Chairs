import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerOrderArticleViewDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleViewDto';
import { CustomerOrderUpdateDto } from 'src/app/models/customerOrderDto/customerOrderUpdateDto';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CustomerOrderArticleService } from 'src/app/services/customer-order-article.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-details-order',
  templateUrl: './details-order.component.html',
  styleUrls: ['./details-order.component.css']
})
export class DetailsOrderComponent implements OnInit {

  order = new CustomerOrderViewDto();
  orderArticles = [new CustomerOrderArticleViewDto()];
  total = 0;
  numberOfArticle = 0;
  orderUpdate = new CustomerOrderUpdateDto();



  constructor(private actRoute: ActivatedRoute, private orderService: OrderService, private auth: AuthenticationService, 
        private orderArticleService: CustomerOrderArticleService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {

    this.orderArticles.pop();
    this.orderService.getOneOrder(this.actRoute.snapshot.paramMap.get('id')!).subscribe(res => {
      this.order = res;
      this.orderUpdate = this.order;
      this.total = this.order.totalBill;
    });

    this.orderArticleService.getOrderArticlesByOrder(this.actRoute.snapshot.paramMap.get('id')!).subscribe(res => {
      this.orderArticles = res;

      this.orderArticles.forEach(e => {
        this.numberOfArticle = this.numberOfArticle + e.amountOfArticle;

      })
    })
  }


  public send(){

    this.orderUpdate.sent = true;
    this.orderUpdate.timeWhenSent = new Date();
    this.orderService.updateCustomerOrder(this.orderUpdate).subscribe(res =>{
      
      this.loadData()
    }
    );  
  }

  isAdmin(){
    return this.auth.decodeToken().role == "ADMIN" || this.auth.decodeToken().role == "WORKER"
  }

}
