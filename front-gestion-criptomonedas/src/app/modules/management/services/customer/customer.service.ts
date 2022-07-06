import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerInterface } from '@core/models/customer.interface';
import { catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private URL = environment.api;

  constructor(private httpClient:HttpClient) {   }


  getCustomers(): Observable<CustomerInterface[]> {
    return this.httpClient.get<CustomerInterface[]>( `${this.URL}/customers`)
      .pipe(
        catchError(this.handleError<CustomerInterface[]>('getCrypto', []))
      );
  }

  getCustomersById(idCustomer:number): Observable<CustomerInterface> {
    return this.httpClient.get<CustomerInterface>( `${this.URL}/customers/${idCustomer}`)
      .pipe(
        catchError(this.handleError<CustomerInterface>('getCrypto', ))
      );
  }

  getCryptosByCountry(idCountry:number): Observable<CustomerInterface[]> {
    return this.httpClient.get<CustomerInterface[]>( `${this.URL}/customers/country/${idCountry}`)
      .pipe(
        catchError(this.handleError<CustomerInterface[]>('getCrypto', []))
      );
  }


  addCrypto(idCustomer:number, idCrypto:number): Observable<CustomerInterface> {
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
