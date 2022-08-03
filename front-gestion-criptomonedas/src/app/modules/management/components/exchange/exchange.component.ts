import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CountryInterface } from '@core/models/country.interface';
import { ExchangeInterface } from '@core/models/exchange.interface';
import { ExchangeService } from '@modules/management/services/exchange/exchange.service';
import { CountryListService } from '@shared/services/country-list.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-exchange',
  templateUrl: './exchange.component.html',
  styleUrls: ['./exchange.component.css']
})
export class ExchangeComponent implements OnInit {
 
  exchangeForm:FormGroup = new FormGroup({});
  countries:CountryInterface[] = [{id:0, name:'Todos'}];
  selectedCountry: Object = {};
  exchanges:ExchangeInterface[] = [];


  constructor(private countryService:CountryListService, private exchangeService:ExchangeService) { }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe((response) =>{
      response.forEach(element => {
        this.countries.push(element);
      });
    });

    this.exchangeForm = new FormGroup({
      country: new FormControl(null, [Validators.required]),
    })
  }

  search():void{
    const body = this.exchangeForm.value;

    if(body.country.name === 'Todos'){
      this.exchangeService.getExchanges().subscribe((response) => {
        this.exchanges = response;
      })
    }else{
      this.exchangeService.getExchangesByCountry(body.country.id).subscribe((response) => {
        this.exchanges = response;
      })
    }

  
    console.log(body)
  }



}
