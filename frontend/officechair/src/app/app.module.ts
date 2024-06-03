import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { MenuComponent } from './components/menu/menu.component';
import { OrderComponent } from './components/order/order.component';
import{AuthconfigInterceptor} from './services/authconfig.interceptor';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewOrdersComponent } from './components/article/view-orders/view-orders.component';
import { DetailsOrderComponent } from './components/article/details-order/details-order.component';
import { AdminComponent } from './admin/admin.component';
import { MatCardModule } from '@angular/material/card';
import { ArticleDialogComponent } from './components/dialogs/article-dialog/article-dialog.component';
import { ArticleTypeDialogComponent } from './components/dialogs/article-type-dialog/article-type-dialog.component';
import { ArticleBrandDialogComponent } from './components/dialogs/article-brand-dialog/article-brand-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import {MatIconModule} from "@angular/material/icon";
import { provideFirebaseApp, getApp, initializeApp } from '@angular/fire/app';
import { getStorage, provideStorage } from '@angular/fire/storage';
import { environment } from 'src/environments/environment';
import {MatTableModule} from '@angular/material/table';
import { UserDialogComponent } from './components/dialogs/user-dialog/user-dialog.component';
import { CustomerOrderDetailComponent } from './components/customer-order-detail/customer-order-detail.component'
import { NgxStripeModule } from 'ngx-stripe';


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    FooterComponent,
    LoginComponent,
    SignupComponent,
    AboutComponent,
    NavbarComponent,
    GalleryComponent,
    MenuComponent,
    OrderComponent,
    UserProfileComponent,
    ViewOrdersComponent,
    DetailsOrderComponent,
    AdminComponent,
    ArticleDialogComponent,
    ArticleTypeDialogComponent,
    ArticleBrandDialogComponent,
    UserDialogComponent,
    CustomerOrderDetailComponent
  ],
  imports: [
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule,
    MatIconModule,
    provideFirebaseApp(() => initializeApp(environment.fireBaseConfig)),
    provideStorage(() => getStorage()),
    MatTableModule,
    NgxStripeModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthconfigInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
