import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../model/Article';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {
  hostArticles:String="http://localhost:8080";
  constructor(private http:HttpClient) { }

  getAllArticles(){
    return this.http.get<Article[]>(this.hostArticles+"/articles");
  }
}
