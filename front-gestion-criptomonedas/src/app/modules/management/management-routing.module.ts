import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CryptocurrencyComponent } from './components/cryptocurrency/cryptocurrency.component';
import { CustomerComponent } from './components/customer/customer.component';
import { ExchangeComponent } from './components/exchange/exchange.component';
import { ManagementPageComponent } from './page/management-page.component';

const routes: Routes = [
  {
    path:'',
    component:ManagementPageComponent
  },
  {
    path:'customer',
    component:CustomerComponent
  },
  {
    path:'exchange',
    component:ExchangeComponent
  },
  {
    path:'cryptocurrency',
    component:CryptocurrencyComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagementRoutingModule { }
