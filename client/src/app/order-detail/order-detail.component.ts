import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Article } from '../model/Article';
import { OrderDetail } from '../model/orderDertail';
import { ArticlesService } from '../services/articles.service';
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
  orderDetail : OrderDetail={};
  articleList!: Article[];
  constructor(private authService:AuthenticationService, private orderService:OrdersService,
    private router:Router, private route: ActivatedRoute, private articleService:ArticlesService) { }

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
    this.ArticleNotInOrder();
  }

  removeArticleFromOrder(idArticle:any){
    this.orderDetail.articleID=idArticle;
    this.orderDetail.Statu="remove";
    this.orderService.updateOrder(this.id,this.orderDetail).subscribe(res=>{
      this.orderService.getOrderByID(this.id)
      .subscribe(res=>{
        this.order = res;
        this.ArticleNotInOrder();
      });
    });
    
  }
  ArticleNotInOrder(){
    this.articleService.getAllArticles()
    .subscribe(res=>{
      this.articleList = res;
      for (let i = 0; i < this.order.articles.length; i++) {
        if (this.articleList.find(x => x.id === this.order.articles[i].id)) {
          this.articleList.splice(this.articleList.findIndex(x => x.id === this.order.articles[i].id), 1);
        }
      }
    });
  }

  addArticletoOrder(idArticle:any){
    this.orderDetail.articleID=idArticle;
    this.orderDetail.Statu="add";
    this.orderService.updateOrder(this.id,this.orderDetail).subscribe(res=>{
      this.orderService.getOrderByID(this.id)
      .subscribe(res=>{
        this.order = res;
        this.ArticleNotInOrder();
      });
    });
  }
  

}
