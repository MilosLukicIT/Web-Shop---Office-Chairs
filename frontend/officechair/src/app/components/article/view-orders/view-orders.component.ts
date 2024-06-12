import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CustomerOrderUpdateDto } from 'src/app/models/customerOrderDto/customerOrderUpdateDto';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {

  displayedColumns = ['dateOfCreation', 'customer', 'employee', 'totalBill', 'paid', 'actions'];
  displayedColumnsDone = ['dateOfCreation', 'customer', 'employee', 'totalBill', 'paid', 'actions'];
  notDoneOrders = [new CustomerOrderViewDto()];
  orders = [new CustomerOrderViewDto()];
  doneOrder = [new CustomerOrderViewDto()];
  dataSource!: MatTableDataSource<CustomerOrderViewDto>;
  dataSourceDone!: MatTableDataSource<CustomerOrderViewDto>;
  updateOrderEmployee!: CustomerOrderUpdateDto;
  currentEmployee!: User;

  constructor(public user: UserService, public orderService: OrderService, 
    public router: Router, public auth: AuthenticationService) { }

  ngOnInit(): void {
    this.loadData();

    this.user.getUserProfile(this.auth.decodeToken().id).subscribe(res => {
      this.currentEmployee = res;
    })
  }

  public isAdmin() {
    let tokenInfo = this.auth.decodeToken();
    return (tokenInfo.role == 'ADMIN');
  }

  async loadData() {

    this.orderService.getCustomerOrders().subscribe(data => {
      this.orders = data;
      this.doneOrder.pop();
      this.notDoneOrders.pop();
      this.orders.forEach(item => {
        if(item.sent){
          this.doneOrder.push(item);
        }
        else {
          this.notDoneOrders.push(item);
        }
      })
      this.dataSourceDone = new MatTableDataSource(this.doneOrder);
      this.dataSource = new MatTableDataSource(this.notDoneOrders);
      
    }, (error: any) => {
      console.log(error);
    });



    
  }

  public delete( _id: string) {
    if(confirm("Are you sure to delete this?")) {
      this.orderService.deleteCustomerOrder(_id).subscribe(res =>{
        this.loadData();

      }, error => console.log(error));
    }
  }

  openDialog(flag: number){

  }

  takeOrder(row: any){

    this.updateOrderEmployee = row;

    this.updateOrderEmployee.employee = this.currentEmployee;

    this.orderService.updateCustomerOrder(this.updateOrderEmployee).subscribe(res => {

      this.orders = [];
      this.doneOrder = [];
      this.notDoneOrders = [];
      this.loadData();
    });
    
  }

  viewOrder(row: any){


  }

}
