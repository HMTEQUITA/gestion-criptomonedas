import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
      path:'customer'
    },
    {
      name:'Cryptocurrencies',
      path:'cryptocurrency'
    },
    {
      name:'Exchanges',
      path:'exchange'
    }
  ]

  constructor(private router:Router) { }

  ngOnInit(): void {
    console.log('header init')
  }

  loadRoute(value:string):void{
    console.log(value)
  }

}
