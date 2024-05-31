import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerOrderViewDto } from 'src/app/models/customerOrderDto/customerOrderViewDto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-details-order',
  templateUrl: './details-order.component.html',
  styleUrls: ['./details-order.component.css']
})
export class DetailsOrderComponent implements OnInit {

  id: String = "";
  order = new CustomerOrderViewDto();
  total = 0;
  constructor(private actRoute: ActivatedRoute, private orderService: OrderService, private auth: AuthenticationService) { }

  ngOnInit(): void {
    this.id = this.auth.decodeToken().id;
    this.loadData(this.id);
  }

  loadData(id: String) {
    this.orderService.getCustomerOrder(id).subscribe(data => {
      this.order = data;
      this.total = this.order.totalBill;

    }, (error: any) => {
      console.log(error)
    })
  }

}
