import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerInterface } from '@core/models/customer.interface';
import { BehaviorSubject, catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CryptocurrencyService } from '../cryptocurrency/cryptocurrency.service';
import { CustomerService } from '../customer/customer.service';

@Injectable({
  providedIn: 'root'
})
export class AddcryptoService {

  private URL = environment.api;

  
  public payload:number = 0;
  
  private _showModal$ = new BehaviorSubject<boolean>(false);
  public showModal$ = this._showModal$.asObservable();

  private _customer$ = new BehaviorSubject<any>(null);
  public customer$ = this._customer$.asObservable()

  private _cryptos$ = new BehaviorSubject<any>(null);
  public cryptos$ = this._cryptos$.asObservable()

  constructor(private httpClient:HttpClient, private customerService:CustomerService, private cryptoService:CryptocurrencyService) { }

  public setShow(flag:boolean){
    this._showModal$.next(flag)
  }

  public setCustomer(id:number):void{
    this.payload = id;
    this.customerService.getCustomersById(id).subscribe((response) => {
      this._customer$.next(response);
    } );
  }

  public setCryptos():void{
    this.cryptoService.getCryptos().subscribe((response) => {
      this._cryptos$.next(response);
    } );
  }

  public addCrypto(cryptoId:number):Observable<any>{
   // console.log('save', this.payload)
   // return this.customerService.addCrypto(this.payload, cryptoId);
    
    return this.httpClient.get<CustomerInterface>( `${this.URL}/customers/${this.payload}/cryptocurrency/${cryptoId}`)
    .pipe(
      catchError(this.handleError<any>('getCrypto',))
    );
  }

  public addCrypto2(idCustomer:number, idCrypto:number): Observable<CustomerInterface> {
    return this.httpClient.get<CustomerInterface>( `${this.URL}/customers/${idCustomer}/cryptocurrency/${idCrypto}`)
      .pipe(
        catchError(this.handleError<CustomerInterface>('getCrypto',))
      );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}
