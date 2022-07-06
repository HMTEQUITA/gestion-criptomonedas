import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CountryInterface } from '@core/models/country.interface';
import { catchError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CountryListService {
  private URL = environment.api;

  constructor(private httpClient:HttpClient) {   }

  getCountries(): Observable<CountryInterface[]> {
    return this.httpClient.get<CountryInterface[]>( `${this.URL}/countries`)
      .pipe(
        catchError(this.handleError<CountryInterface[]>('getHeroes', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}