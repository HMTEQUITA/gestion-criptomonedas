import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@modules/auth/services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { CustomerComponent } from '../customer/customer.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  menu: Array<{name:string, path:string}> = [
    {
      name:'Customers',
      path:'/management/customer'
    },
    {
      name:'Cryptocurrencies',
      path:'/management/cryptocurrency'
    },
    {
      name:'Exchanges',
      path:'/management/exchange'
    }
  ]

  constructor(private authService:AuthService, private router:Router, private toastrService: ToastrService) { }

  ngOnInit(): void {
    
  }

  loadRoute(value:string):void{
    console.log(value)
  }

  logout(){
    if(this.authService.logout()){
      this.router.navigate(['/','auth', 'login'])
      this.toastrService.success("Sesión Cerrada", "Operación exitosa!", { closeButton: true });
    }
  }

}
