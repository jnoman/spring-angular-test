import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'client';
  constructor(private authService:AuthenticationService, private router:Router) {}
  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      this.authService.authenticated=true;
    }
    if(!this.authService.authenticated){
      this.router.navigateByUrl("/login");
    } else {
      this.router.navigateByUrl("/articles");
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
