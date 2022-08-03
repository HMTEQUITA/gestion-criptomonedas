import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CryptoInterface } from '@core/models/crypto.interface';
import { CustomerInterface } from '@core/models/customer.interface';
import { AddcryptoService } from '@modules/management/services/addCrypto/addcrypto.service';
import { ToastrService } from 'ngx-toastr';

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

  constructor(private addCryptoService:AddcryptoService, private toastrService: ToastrService) { }

  ngOnInit(): void {

    this.newTask = new FormGroup(
      {
        crypto: new FormControl(null, [Validators.required]),
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
    this.addCryptoService.addCrypto(body.crypto.id).subscribe((response) => {
      if(response !== undefined){
        this.customer= response;
        this.toastrService.success("Crypto agregada correctamente", "Operación exitosa!", { closeButton: true });
      }
    });
  }

  removeCrypto(cryptoId:number):void{
    const body =  this.newTask.value
    this.addCryptoService.removeCrypto(cryptoId).subscribe((response) => {
      if(response !== undefined){
        this.customer= response;
        this.toastrService.success("Crypto eliminada correctamente", "Operación exitosa!", { closeButton: true });
      }
    });
  }

  cancel():void{
    this.addCryptoService.setShow(false)
  }
}
