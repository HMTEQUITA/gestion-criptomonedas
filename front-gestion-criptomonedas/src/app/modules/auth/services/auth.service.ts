import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie';
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private URL = environment.api;

  constructor(private httpClient:HttpClient, private cookieService:CookieService) { }

  submitLogin(credentials:{email:string, password:string}):Observable<any> {
    return this.httpClient.post(
      `${this.URL}/auth/signin`,
      credentials)
      .pipe(
        tap((stream:any) => {
         const {token} = stream;
         this.cookieService.put('token_session',token,{
           path:'/'
         })
        }),
        catchError(() => {
         console.log('Algo ocurrio?? fijate')
         return of([])
       })
      )
   }
}
