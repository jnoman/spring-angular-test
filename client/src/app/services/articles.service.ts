import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../model/Article';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {
  hostArticles:String="http://localhost:8080";
  constructor(private http:HttpClient) { }

  getAllArticles(){
    let headres= new HttpHeaders({'Authorization':localStorage.getItem("token")+""});
    return this.http.get<Article[]>(this.hostArticles+"/articles",{headers:headres});
  }

  getArticleByID(id:any){
    let headres= new HttpHeaders({'Authorization':localStorage.getItem("token")+""});
    return this.http.get<Article[]>(this.hostArticles+"/articles/"+id,{headers:headres});
  }
}
