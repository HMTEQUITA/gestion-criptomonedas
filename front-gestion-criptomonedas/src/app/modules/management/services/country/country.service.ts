import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  private URL = environment.api;

  constructor(private httpClient:HttpClient) { }

  getCountries():Observable<any>{
    return this.httpClient.get(`$(this.URL)/countries`)
  }
}
