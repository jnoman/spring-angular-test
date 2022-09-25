import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'client';
  constructor(private authService:AuthenticationService, private router:Router, private Location:Location) {}
  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if("" === this.Location.path()){
      if(this.authService.authenticated){
        this.router.navigateByUrl("/articles");
      } else {
        this.router.navigateByUrl("/login");
      }
    }
  }

  isAuthenticated(){
    return this.authService.authenticated;
  }

  logout(){
    this.authService.authenticated=false;
    localStorage.removeItem('token');
    this.router.navigateByUrl("/login");
  }
}
