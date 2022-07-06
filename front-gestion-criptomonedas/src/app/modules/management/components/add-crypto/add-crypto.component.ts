import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { CryptoInterface } from '@core/models/crypto.interface';
import { CustomerInterface } from '@core/models/customer.interface';
import { AddcryptoService } from '@modules/management/services/addCrypto/addcrypto.service';
import { CryptocurrencyService } from '@modules/management/services/cryptocurrency/cryptocurrency.service';
import { catchError, of, pipe, tap } from 'rxjs';

@Component({
  selector: 'app-add-crypto',
  templateUrl: './add-crypto.component.html',
  styleUrls: ['./add-crypto.component.css']
})
export class AddCryptoComponent implements OnInit {
  newTask:FormGroup = new FormGroup({})
  showModal$ = this.addCryptoService.showModal$;
  
  customer$ = this.addCryptoService.customer$;
  customer:CustomerInterface = {id:0, name:'', country:'', surname:'', cryptocurrencies:[]};

  cryptos$ = this.addCryptoService.cryptos$;
  cryptos:CryptoInterface[] = [{id:0, name:'Seleccione', symbol:'', exchangeRate:''}];
  cryptoSelected:Object = {};

  constructor(private addCryptoService:AddcryptoService) { }

  ngOnInit(): void {

    this.newTask = new FormGroup(
      {
        cryptoId: new FormControl(0)
      });

      this.customer$.subscribe((response:CustomerInterface) => {
        this.customer= response;
      });

      this.cryptos$.subscribe((response:CryptoInterface[]) => {
        this.cryptos= response;
      });

  
  }

  addCrypto():void{
    const body =  this.newTask.value
    this.addCryptoService.addCrypto(body.cryptoId)
    
    .subscribe((response) => {
      console.log('add', response)
      this.addCryptoService.setShow(false)
      console.log('La tarea se guardo!!!')
    });
  }

  cancel():void{
    this.addCryptoService.setShow(false)
  }
}
