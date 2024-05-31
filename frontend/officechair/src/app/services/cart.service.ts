import { Injectable } from '@angular/core';
import { CustomerOrderArticleCreateDto } from '../models/customerOrderArticleDto/customerOrderArticleCreateDto';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  items: any;
  constructor() { }

  addToCart(addedItem: CustomerOrderArticleCreateDto) {

    if (this.cartExists){
      this.items = this.getCart();
    }
    var index = this.items.findIndex((o: { article: { articleId: String; }; }) => o.article.articleId === addedItem.article.articleId);
    if (index > -1) {
      this.items[index].amountOfArticle++;
    }

    else {
      this.items.push(addedItem);
    }
    this.saveCart();
  }

  getCart() {
    return JSON.parse(localStorage.getItem('cart')!) ;
  }

  clearCart() {
    this.items = [];
    this.saveCart();
  }

  saveCart(){
    localStorage.setItem('cart', JSON.stringify(this.items));
  }

  removeItem(item: String) {
    this.items = this.getCart();
    var index = this.items.findIndex((o: { article: { articleId: String; }; }) => o.article.articleId === item);
    if (index > -1) {
      if (this.items[index].amountOfArticle > 1) {
        this.items[index].amountOfArticle = this.items[index].amountOfArticle - 1;
      }
      else {
        this.items.splice(index, 1);
      }
      this.saveCart();
    }
    else {
      window.alert('Item does not exist');
    }
  }

  itemsCount() {
    this.items = this.getCart()
    var numberOfItems = 0;
    for (var i = 0; i < this.items.length; i++) {
      numberOfItems = numberOfItems + this.items[i].amountOfArticle;
    }

    return numberOfItems;
  }

  get cartExists() {
    let cart = localStorage.getItem('cart');
    return cart != null ? true : false;
  }
}
