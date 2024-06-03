import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CartService } from '../../services/cart.service';
import { OrderService } from '../../services/order.service';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { UserViewDto } from 'src/app/models/userDto/userViewDto';
import { CustomerOrderArticleViewDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleViewDto';
import { CustomerOrderCreateDto } from 'src/app/models/customerOrderDto/customerOrderCreateDto';
import { User } from 'src/app/models/user';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { CustomerOrderArticleService } from 'src/app/services/customer-order-article.service';
import { CustomerOrderArticleCreateDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleCreateDto';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orderItems =  [new CustomerOrderArticleCreateDto];
  total = 0;
  totalWithDiscount = 0;
  newOrder = new CustomerOrderCreateDto();
  createdOrder = new CustomerOrderViewDto(); 

  currentUser!: User;
  
  constructor(public fb: FormBuilder, public user: UserService, public cartService: CartService, 
    public orderService: OrderService, public auth: AuthenticationService, public orderArticleService: CustomerOrderArticleService) {
    
   }

  ngOnInit(): void {
    this.loadData();
    this.orderItems = this.cartService.getCart();
    this.getUserInfo();
    this.countTotal();
  }

  private getUserInfo(){
    if(this.auth.isLoggedIn){
      this.user.getUserProfile(this.auth.decodeToken().id).subscribe(res => {
        this.currentUser = res;
    })
    }
  }

  private loadData() {
    this.orderService.getCustomerOrders().subscribe(data => {
    },(error: any) => {
      console.log(error)
    })

    
  }

  removeItem(item: String){
    this.cartService.removeItem(item);
    this.orderItems = [];
    this.orderItems = this.cartService.getCart();
    this.countTotal();

  }


  countTotal() {
    this.total = 0;
    this.orderItems.forEach(element => {
      this.total = this.total + (element.article.priceOfArticle * element.amountOfArticle);
    }); 

    this.totalWithDiscount = 0;
    this.orderItems.forEach(element => {
      this.totalWithDiscount = this.totalWithDiscount + ((element.article.priceOfArticle - 
        (element.article.priceOfArticle * element.article.discount/100))
         * element.amountOfArticle);
    }); 
  }


  isLoggedIn() {
    return this.auth.isLoggedIn;
  }

checkout(){

  this.newOrder.customer = this.currentUser;
  this.newOrder.dateOfCreation = new Date();
  this.newOrder.totalBill = this.totalWithDiscount;

  this.orderService.addCustomerOrder(this.newOrder).subscribe(res => {

    this.createdOrder = res;

    this.orderItems.forEach(e => {

      if(e != null) {
        e.customerOrderArticle = this.createdOrder;
        this.orderArticleService.addOrderArticle(e).subscribe();
      }
    });

    this.cartService.clearCart();
    this.loadData();
    
    
  })

}

}
