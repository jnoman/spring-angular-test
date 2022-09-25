import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utilisateur } from '../model/Utilisateur';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  hostLogin:String="http://localhost:8080";
  jwt!: string;
  authenticated:boolean=false;

  constructor(private http:HttpClient) { }

  login(data:String){
    return this.http.post(this.hostLogin+"/login", data, {observe:'response'});
  }

  inscription(data:Utilisateur){
    return this.http.post<Utilisateur>(this.hostLogin+"/utilisateurs", data, {observe:'response'});
  }
  
  saveToken(jwt: string | null) {
    localStorage.setItem('token',"Bearer "+jwt);
    this.authenticated=true;
  }
}
