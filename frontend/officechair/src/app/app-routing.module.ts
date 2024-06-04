import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { DetailsOrderComponent } from './components/article/details-order/details-order.component';
import { ViewOrdersComponent } from './components/article/view-orders/view-orders.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { OrderComponent } from './components/order/order.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { CustomerOrderDetailComponent } from './components/customer-order-detail/customer-order-detail.component';
import { CheckoutSuccessComponent } from './components/checkout-success/checkout-success.component';
import { CheckoutCancelComponent } from './components/checkout-cancel/checkout-cancel.component';

const routes: Routes = [
  {path: "", component: HomepageComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "about", component: AboutComponent},
  {path: "gallery", component: GalleryComponent},
  {path: "menu", component: MenuComponent},
  {path: "cart", component: OrderComponent},
  {path: "orders", component: ViewOrdersComponent},
  {path: "user-profile", component: UserProfileComponent},
  {path: "order-details/:id", component: DetailsOrderComponent},
  {path: "myOrders", component: CustomerOrderDetailComponent},
  {path: "successPayment", component: CheckoutSuccessComponent},
  {path: "cancelPayment", component: CheckoutCancelComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
