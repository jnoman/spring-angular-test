import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../model/Order';
import { AuthenticationService } from '../services/authentication.service';
import { OrdersService } from '../services/orders.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orderList : any ;
  order:Order={};
  constructor(private authService:AuthenticationService, private orderService:OrdersService,
    private router:Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if(!this.authService.authenticated){
      this.router.navigateByUrl("/login");
    } 
    this.orderService.getAllOrders()
    .subscribe(res=>{
      this.orderList = res;
    });
  }

  addOrder(){
    this.orderService.addOrder(this.order)
    .subscribe(res=>{
      this.orderService.getAllOrders()
      .subscribe(res=>{
        this.orderList = res;
      });
    });
  }

}
