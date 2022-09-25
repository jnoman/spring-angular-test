import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticlesService } from '../services/articles.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  public articleList : any ;
  thumbnail: any;

  constructor(private authService:AuthenticationService, private articleService:ArticlesService,
     private router:Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if(!this.authService.authenticated){
      this.router.navigateByUrl("/login");
    } 
    this.articleService.getAllArticles()
    .subscribe(res=>{
      this.articleList = res;
    });
  }

}
