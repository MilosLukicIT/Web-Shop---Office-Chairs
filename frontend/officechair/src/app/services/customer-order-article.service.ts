import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomerOrderArticleCreateDto } from '../models/customerOrderArticleDto/customerOrderArticleCreateDto';
import { CustomerOrderArticleUpdateDto } from '../models/customerOrderArticleDto/customerOrderArticleUpdateDto';


@Injectable({
  providedIn: 'root'
})
export class CustomerOrderArticleService {


  apiUrl = environment.apiCustomerOrderArticle;


  constructor(private http: HttpClient) { }


  getOrderArticles(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getOneOrderArticle(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  getOrderArticlesByOrder(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/order/${id}`);
  }

  addOrderArticle(newOrderArticle: CustomerOrderArticleCreateDto) {
    return this.http.post(this.apiUrl, newOrderArticle);
  }

  deleteOrderArticle(id: String): Observable<any> {
    return this.http.delete(this.apiUrl + id);
  }

  updateOrderArticle(updateOrderArticle: CustomerOrderArticleUpdateDto): Observable<any>{
    return this.http.put(this.apiUrl, updateOrderArticle);
  }
}
