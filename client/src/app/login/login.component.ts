import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../model/Utilisateur';
import { NgForm } from '@angular/forms'
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  utilsateur: Utilisateur = {
    username: "",
    email: "",
    password: ""
  }
  msgerror!: String;
  constructor(private authService: AuthenticationService, private router:Router) { }

  ngOnInit(): void {
    
  }

  onLogin(){
    this.authService.login(JSON.stringify(this.utilsateur))
    .subscribe(res=>{
      let jwt = res.headers.get('Authorization');
      this.authService.saveToken(jwt);
      this.router.navigateByUrl("/articles");
    },err=>{
      this.msgerror ="invalid login or password";
    })
  }

}
