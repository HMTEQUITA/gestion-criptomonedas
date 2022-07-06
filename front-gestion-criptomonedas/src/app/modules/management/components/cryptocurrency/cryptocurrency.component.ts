import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CountryInterface } from '@core/models/country.interface';
import { CryptoInterface } from '@core/models/crypto.interface';
import { CryptocurrencyService } from '@modules/management/services/cryptocurrency/cryptocurrency.service';
import { CountryListService } from '@shared/services/country-list.service';

@Component({
  selector: 'app-cryptocurrency',
  templateUrl: './cryptocurrency.component.html',
  styleUrls: ['./cryptocurrency.component.css']
})
export class CryptocurrencyComponent implements OnInit {
 
  cryptoForm:FormGroup = new FormGroup({});
  countries:CountryInterface[] = [{id:0, name:'Todos'}];
  selectedCountry: Object = {};
  cryptos:CryptoInterface[] = [];

  constructor(private countryService:CountryListService, private cryptoService:CryptocurrencyService) { }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe((response) =>{
      response.forEach(element => {
        this.countries.push(element);
      });
    });

    this.cryptoForm = new FormGroup({
      country: new FormControl(this.countries[0]),
    })
  }

  
  search():void{
    const body = this.cryptoForm.value;

    if(body.country.name === 'Todos'){
      this.cryptoService.getCryptos().subscribe((response) => {
        this.cryptos = response;
      })
    }else{
      this.cryptoService.getCryptosByCountry(body.country.id).subscribe((response) => {
        this.cryptos = response;
      })
    }

  
    console.log(body)
  }

}
