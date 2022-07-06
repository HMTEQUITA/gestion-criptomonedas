import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
@Component({
  selector: 'app-login-page',
  templateUrl:'./login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm:FormGroup = new FormGroup({});

  constructor(private authService:AuthService, private router:Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required /*Validators.email*/]),
      password: new FormControl('', [Validators.required])
    })
  }

  sendCredentials():void{
    const body = this.loginForm.value;
    this.authService.submitLogin(body)
    .subscribe((response) => {
      this.router.navigate(['/','management'])
    })
    console.log(body)
  }

  

}
