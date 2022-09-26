import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDetail } from '../model/orderDertail';
import { AuthenticationService } from '../services/authentication.service';
import { OrdersService } from '../services/orders.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  order : any ;
  id:any;
  orderDetail : OrderDetail={}
  constructor(private authService:AuthenticationService, private orderService:OrdersService,
    private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if(!this.authService.authenticated){
      this.router.navigateByUrl("/login");
    } 
    this.route.params.subscribe(params => {
      this.id=params['id'];
    });
    this.orderService.getOrderByID(this.id)
    .subscribe(res=>{
      this.order = res;
    });
  }

  removeArticleFromOrder(idArticle:any){
    this.orderDetail.articleID=idArticle;
    this.orderDetail.Statu="remove";
    this.orderService.updateOrder(this.id,this.orderDetail).subscribe(res=>{
      console.log(res);
      this.orderService.getOrderByID(this.id)
      .subscribe(res=>{
        this.order = res;
      });
    });
    
  }

}
