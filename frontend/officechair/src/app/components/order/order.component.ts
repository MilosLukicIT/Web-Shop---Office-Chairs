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
import { loadStripe } from '@stripe/stripe-js';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

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
  listId = [new CustomerOrderArticleViewDto]

  currentUser!: User;
  stripePromise = loadStripe(environment.stripeKey);


  constructor(public fb: FormBuilder, public user: UserService, public cartService: CartService, 
    public orderService: OrderService, public auth: AuthenticationService, 
    public orderArticleService: CustomerOrderArticleService,
    private http: HttpClient) {
    
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

async checkout(){

  this.newOrder.customer = this.currentUser;
  this.newOrder.dateOfCreation = new Date();
  this.newOrder.totalBill = this.totalWithDiscount;

  this.orderService.addCustomerOrder(this.newOrder).subscribe(async res => {

    this.createdOrder = res;
    this.listId.pop();
    this.orderItems.forEach(e => {

      if(e != null) {
        e.customerOrderArticle = this.createdOrder;
        this.orderArticleService.addOrderArticle(e).subscribe(res => {
          console.log(res);
          this.listId.push(res);
        });
      }
    });
  
    const stripe = await this.stripePromise;

    this.http
      .post(`${environment.apiPayment}`, {
        currency: "eur",
        successUrl: `http://localhost:4200/successPayment`,
        cancelUrl: 'http://localhost:4200/cancelPayment',
        amount: this.totalWithDiscount,
        orderId: this.createdOrder.orderId
      })
      .subscribe((data: any) => {
        // I use stripe to redirect To Checkout page of Stripe platform
        stripe!.redirectToCheckout({
          sessionId: data.sessionId,
        });
      });
    
    
  })

}

}
