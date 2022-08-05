import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagementRoutingModule } from './management-routing.module';
import { ManagementPageComponent } from './page/management-page.component';
import { HeaderComponent } from './components/header/header.component';
import { CustomerComponent } from './components/customer/customer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ExchangeComponent } from './components/exchange/exchange.component';
import { CryptocurrencyComponent } from './components/cryptocurrency/cryptocurrency.component';
import { AddCryptoComponent } from './components/add-crypto/add-crypto.component';
import { HomeComponent } from './components/home/home/home.component';
import { SharedModule } from '@shared/shared.module';


@NgModule({
  declarations: [
    ManagementPageComponent,
    HeaderComponent,
    CustomerComponent,
    ExchangeComponent,
    CryptocurrencyComponent,
    AddCryptoComponent,
    HomeComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ManagementRoutingModule,
    FormsModule,
    SharedModule
  ]
})
export class ManagementModule { }
