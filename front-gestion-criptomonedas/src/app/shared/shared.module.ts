import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReplaceSpacePipe } from './pipes/replace-space.pipe';
import { OrderByPipe } from './pipes/order-by.pipe';



@NgModule({
  declarations: [
    ReplaceSpacePipe,
    OrderByPipe
  ],
  imports: [
    CommonModule
  ],
  exports:[
    OrderByPipe
  ]
})
export class SharedModule { }
