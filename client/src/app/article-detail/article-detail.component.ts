import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticlesService } from '../services/articles.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {
  article : any ;
  id:any;
  constructor(private authService:AuthenticationService, private articleService:ArticlesService,
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
    this.articleService.getArticleByID(this.id)
    .subscribe(res=>{
      this.article = res;
    });
  }

}
