import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {

orders = [new CustomerOrderViewDto()];
doneOrder = new CustomerOrderViewDto();

  constructor(public user: UserService, public orderService: OrderService, public router: Router, public auth: AuthenticationService) { }

  ngOnInit(): void {
    this.loadData();
  }

  public isAdmin() {
    let tokenInfo = this.auth.decodeToken();
    return (tokenInfo.role == 'Admin');
  }

  loadData() {
    this.orderService.getCustomerOrders().subscribe(data => {
      this.orders = data;
    },(error: any) => {
      console.log(error)
    })
  }

  public deleteOrder( _id: string) {
    if(confirm("Are you sure to delete this?")) {
      this.orderService.deleteCustomerOrder(_id).subscribe(res =>{
        var orderName = res.first_name;
        window.alert("You deleted this persons order: " + orderName);
        window.location.reload();

      }, error => console.log(error));
    }
  }

  noOrder(){
    return this.orders.length < 1;
  }

//Done
  isDone(id: string){
    this.doneOrder = this.orders[this.orders.findIndex(o => o.orderId == id)];
    this.doneOrder.sent = true
    setTimeout(() => {
      console.log(this.doneOrder)
      this.orderService.updateCustomerOrder(this.doneOrder).subscribe(data =>{
      });
    }, 1000);
  }
}
