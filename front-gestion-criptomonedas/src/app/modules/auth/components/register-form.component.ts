import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { CountryListService } from '@shared/services/country-list.service';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  
  registerForm:FormGroup = new FormGroup({});
  
  constructor(countryListService:CountryListService) { }

  ngOnInit(): void {
    
    this.registerForm = new FormGroup(
      {
        email: new FormControl('',[Validators.required, Validators.email]),
        name: new FormControl('',[Validators.required, Validators.minLength(3)]),
        surname: new FormControl('',[Validators.required, Validators.minLength(3)]),
        country: new FormControl('',[Validators.required]),
      },
      {
        validators:passwordMatchValidator
      }
    )

  }
}

function passwordMatchValidator(formCurrent:AbstractControl | FormGroup):any {
  const valuePassword = formCurrent.get('password')?.value;
  const valuePasswordConfirm = formCurrent.get('passwordConfirm')?.value;
  return (valuePassword === valuePasswordConfirm) ? null : {'mismatch': true};
}
