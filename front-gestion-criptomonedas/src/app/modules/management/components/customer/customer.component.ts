import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, TitleStrategy } from '@angular/router';
import { CountryInterface } from '@core/models/country.interface';
import { CustomerInterface } from '@core/models/customer.interface';
import { AddcryptoService } from '@modules/management/services/addCrypto/addcrypto.service';
import { CustomerService } from '@modules/management/services/customer/customer.service';
import { CountryListService } from '@shared/services/country-list.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customerForm:FormGroup = new FormGroup({});
  countries:CountryInterface[] = [{id:0, name:'Todos'}];
  selectedCountry: Object = {};
  customers:CustomerInterface[] = [];

  constructor(private countryService:CountryListService, private customerService:CustomerService,
    private addCryptoService:AddcryptoService) { }
  
    ngOnInit(): void {
      this.countryService.getCountries().subscribe((response) =>{
        response.forEach(element => {
          this.countries.push(element);
        });
      });

      this.customerForm = new FormGroup({
        country: new FormControl(this.countries[0]),
      })
    }

  search():void{
    const body = this.customerForm.value;

    if(body.country.name === 'Todos'){
      this.customerService.getCustomers().subscribe((response) => {
        this.customers = response;
      })
    }else{
      this.customerService.getCryptosByCountry(body.country.id).subscribe((response) => {
        this.customers = response;
      })
    }
  }

  addCrypto(idConsumer:number){   
    this.addCryptoService.setShow(true);
    this.addCryptoService.setCustomer(idConsumer);
    this.addCryptoService.setCryptos();
  }
}