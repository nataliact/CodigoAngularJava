import { Component, OnInit } from '@angular/core';
import { Respuesta } from '../../modelo/Respuesta';
import { AuthService } from '../../servicios/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  respuesta: Respuesta;
  isAuthenticated: boolean;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { 
    
  }

  ngOnInit() {
    this.isAuthenticad();
  }

  async isAuthenticad(){
    try {
      
      if ((localStorage.getItem("fuller") === null)) {
        this.router.navigate(["/login"]);
        this.isAuthenticated = false;
      }else{

        //var enAuth = localStorage.getItem('fuller');
        //var user = CryptoJS.AES.decrypt(enAuth, "4rr0zC0-l3Ch333"); 

        const enAuth = CryptoJS.AES.decrypt(localStorage.getItem('fuller'), '4rr0zC0-l3Ch333');
        const user = enAuth.toString(CryptoJS.enc.Utf8);
        
                
        if(await this.authService.login(user)){
          this.respuesta = this.authService.respuesta;
          this.isAuthenticated = true;
        }else{
          this.router.navigate(["/invalido"]);
          this.isAuthenticated = false;
        }

      }
    } catch (err) {

    }
    
  }

  logout() {
    localStorage.removeItem("fuller");
    this.router.navigate(["/login"]);
    this.isAuthenticated = false;
  }

}
