import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from '../model/Article';
import { ArticlesService } from '../services/articles.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {
  article:Article={
    id:0,
    name:"",
    price:0,
    picture:null
  };
  articlePicture!: File | any;
  msgerror!: String;
  constructor(private authService:AuthenticationService, private articleService:ArticlesService,
    private router:Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if(!this.authService.authenticated){
      this.router.navigateByUrl("/login");
    } 
  }

  onAddArticle(){
    const formData=new FormData();
    formData.append(
      'article',
      new Blob([JSON.stringify(this.article)],{type:'application/json'})
    );
    formData.append(
      "picture", this.articlePicture
    );
    this.articleService.addArticle(formData)
    .subscribe(res=>{
      let c =alert("articel add successfully");
      this.article.name="";
      this.article.price=0;
      this.articlePicture=null;
    },err=>{
      this.msgerror ="problem to add the article";
    });
  }

  onFileSelect(event:any){
    if(event.target.files){
     this.articlePicture = event.target.files[0];
    }
 }

}
