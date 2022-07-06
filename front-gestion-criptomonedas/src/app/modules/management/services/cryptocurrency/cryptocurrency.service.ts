import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CryptoInterface } from '@core/models/crypto.interface';
import { catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CryptocurrencyService {

  private URL = environment.api;

  constructor(private httpClient:HttpClient) {   }

  getCryptos(): Observable<CryptoInterface[]> {
    return this.httpClient.get<CryptoInterface[]>( `${this.URL}/cryptocurrencies`)
      .pipe(
        catchError(this.handleError<CryptoInterface[]>('getCrypto', []))
      );
  }

  getCryptosByCountry(idCountry:number): Observable<CryptoInterface[]> {
    return this.httpClient.get<CryptoInterface[]>( `${this.URL}/cryptocurrencies/country/${idCountry}`)
      .pipe(
        catchError(this.handleError<CryptoInterface[]>('getCrypto', []))
      );
  }

  getCryptosByCustomer(idCustomer:number): Observable<CryptoInterface[]> {
    return this.httpClient.get<CryptoInterface[]>( `${this.URL}/cryptocurrencies/customer/${idCustomer}`)
      .pipe(
        catchError(this.handleError<CryptoInterface[]>('getCrypto', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}
