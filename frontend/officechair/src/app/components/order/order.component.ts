import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CartService } from '../../services/cart.service';
import { OrderService } from '../../services/order.service';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { UserViewDto } from 'src/app/models/userDto/userViewDto';
import { CustomerOrderArticleViewDto } from 'src/app/models/customerOrderArticleDto/customerOrderArticleViewDto';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { CustomerOrderCreateDto } from 'src/app/models/customerOrderDto/customerOrderCreateDto';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  submitted = false;
  emailError = false;
  emailErrorMsg = 'Već ste kreirali porudžbinu. Nije moguće imati više od dve porudžbine'
  orderItems =  [new CustomerOrderArticleViewDto];
  ordersFromBase!: CustomerOrderViewDto[];
  total = 0;
  newOrder = new CustomerOrderCreateDto();
  orderForm!: FormGroup;
  userInfo = {
    name: "",
    email: ""
  }
  currentUser!: UserViewDto;
  
  constructor(public fb: FormBuilder, public user: UserService, public cartService: CartService, public orderService: OrderService, public auth: AuthenticationService) {
    
   }

  ngOnInit(): void {
    // this.loadData();
    this.orderItems = this.cartService.getCart();
    this.getUserInfo();
    this.countTotal();
    this.buildFormControls();
  }

  private getUserInfo(){
    if(this.auth.isLoggedIn){
      this.user.getUserProfile(this.auth.decodeToken().id).subscribe(res => {
        this.currentUser = res;
        this.orderForm = this.fb.group({
          contact: [this.currentUser.contactNumber],
          address: [this.currentUser.adress]
        });
    })
    }
  }

  private loadData() {
    this.orderService.getCustomerOrders().subscribe(data => {
      this.ordersFromBase = data;
    },(error: any) => {
      console.log(error)
    })

    
  }

  get address() {
    return this.orderForm.get('address');
  }

  get contact() {
    return this.orderForm.get('contact');
  }

  removeItem(item: String){
    this.cartService.removeItem(item);
    this.orderItems = [];
    this.orderItems = this.cartService.getCart();
    this.countTotal();

  }

  handleSubmit() {
    this.buildOrder();
    if (!this.isInvalidEmail()) {
      this.orderService.addCustomerOrder(this.newOrder).subscribe(data => {
        this.submitted = true;
        this.emailError = false;
        this.cartService.clearCart();
      })
    }
  }

  isInvalidEmail() {
    let tokenInfo = this.auth.decodeToken();
    if(this.ordersFromBase){
      if (this.ordersFromBase.filter(el => el.customer.email == tokenInfo.sub).length > 0) {
        this.emailError = true;
        return true;
      } else
        return false
    } else return false;
    
  }


  countTotal() {
    this.total = 0;
    this.orderItems.forEach(element => {
      this.total = this.total + (element.article.priceOfArticle * element.amountOfArticle);
    }); 
  }

  public buildOrder(){
    // let p = this.orderForm.value;
    // this.newOrder.first_name = this.userInfo.name;
    // this.newOrder.email = this.userInfo.email;
    // this.newOrder.address = p.address;
    // this.newOrder.contact = p.contact;
    
    for(var i = 0; i<this.orderItems.length; i++)
    {
      // this.newOrder.items[i] = this.orderItems[i];
    }
  }

  buildFormControls(){
    this.orderForm = new FormGroup({
      address: new FormControl('', [Validators.required]),
      contact: new FormControl('', [Validators.required])
    })
  }



}
