import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomerOrderCreateDto } from '../models/customerOrderDto/customerOrderCreateDto';
import { CustomerOrderUpdateDto } from '../models/customerOrderDto/customerOrderUpdateDto';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  apiUrl = environment.apiCustomerOrder;

  constructor(private http: HttpClient) { }


  getCustomerOrders(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getCustomerOrderForCustomer(idCustomer: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/customer/${idCustomer}`);
  }

  getOneOrder(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  addCustomerOrder(newOrder: CustomerOrderCreateDto): Observable<any> {
    return this.http.post(this.apiUrl, newOrder);
  }

  deleteCustomerOrder(id: String): Observable<any> {
    return this.http.delete(this.apiUrl +"/"+ id);
  }

  updateCustomerOrder(updateOrder: CustomerOrderUpdateDto): Observable<any>{
    return this.http.put(this.apiUrl, updateOrder);
  }

}


