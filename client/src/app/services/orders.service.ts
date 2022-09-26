import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/Order';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  hostOrder:String="http://localhost:8080";

  constructor(private http:HttpClient) { }

  getAllOrders(){
    let headres= new HttpHeaders({'Authorization':localStorage.getItem("token")+""});
    return this.http.get<Order[]>(this.hostOrder+"/orders",{headers:headres});
  }

  getOrderByID(id:any){
    let headres= new HttpHeaders({'Authorization':localStorage.getItem("token")+""});
    return this.http.get<Order>(this.hostOrder+"/orders/"+id,{headers:headres});
  }
}
