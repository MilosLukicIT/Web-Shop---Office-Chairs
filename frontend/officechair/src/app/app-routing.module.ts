import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { AddProductComponent } from './components/article/add-product/add-product.component';
import { DeleteProductComponent } from './components/article/delete-product/delete-product.component';
import { DetailsOrderComponent } from './components/article/details-order/details-order.component';
import { UpdateProductComponent } from './components/article/update-product/update-product.component';
import { ViewOrdersComponent } from './components/article/view-orders/view-orders.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { OrderComponent } from './components/order/order.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

const routes: Routes = [
  {path: "", component: HomepageComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "about", component: AboutComponent},
  {path: "gallery", component: GalleryComponent},
  {path: "menu", component: MenuComponent},
  {path: "order", component: OrderComponent},
  {path: "orders", component: ViewOrdersComponent},
  {path: "user-profile", component: UserProfileComponent},
  {path: "orders/details/:id", component: DetailsOrderComponent},
  {path: "products/add-product", component: AddProductComponent},
  {path: "product/delete/:id", component: DeleteProductComponent},
  {path: "product/update/:id", component: UpdateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
