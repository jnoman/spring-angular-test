import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Utilisateur } from '../model/Utilisateur';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  utilsateur: Utilisateur = {
    username: "",
    email: "",
    password: ""
  }
  msgerror!: String;

  constructor(private authService: AuthenticationService, private router:Router) { }

  ngOnInit(): void {

  }

  onInscription(){
    this.authService.inscription(this.utilsateur)
    .subscribe(res=>{
      let c =alert("user add successfully");
      this.router.navigateByUrl("/login");
    },err=>{
      this.msgerror ="user already exists";
    })
  }

}
