import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExchangeInterface } from '@core/models/exchange.interface';
import { catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  private URL = environment.api;

  constructor(private httpClient:HttpClient) {   }

  getExchanges(): Observable<ExchangeInterface[]> {
    return this.httpClient.get<ExchangeInterface[]>( `${this.URL}/exchanges`)
      .pipe(
        catchError(this.handleError<ExchangeInterface[]>('getEchange', []))
      );
  }

  
  getExchangesByCountry(idCountry:number): Observable<ExchangeInterface[]> {
    return this.httpClient.get<ExchangeInterface[]>( `${this.URL}/exchanges/country/${idCountry}`)
      .pipe(
        catchError(this.handleError<ExchangeInterface[]>('getExchange', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}
