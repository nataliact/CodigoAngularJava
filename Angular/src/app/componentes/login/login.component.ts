import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/servicios/auth.service';
import { Respuesta } from '../../modelo/Respuesta';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  public loginInvalid = false;
  private formSubmitAttempt = false;
  private returnUrl: string;
  isAuthenticated: boolean;

  respuesta: Respuesta;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {

    this.form = this.fb.group({
      user: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.isAuthenticated = authService.isAuthenticated;
    if((localStorage.getItem("fuller") != null)){
      this.router.navigate(["/inicio"]);
    }
  }

  async ngOnInit(): Promise<void> {
    
  }

  async inicio() {

    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        
        if(await this.authService.login(JSON.stringify(this.form.value))){
          this.router.navigate(["/inicio"]);
          this.loginInvalid = false;
          var enAuth = CryptoJS.AES.encrypt(JSON.stringify(this.form.value), "4rr0zC0-l3Ch333").toString();
          localStorage.setItem('fuller', enAuth.toString());
        }else{
          this.router.navigate(["/invalido"]);
          this.loginInvalid = true;
        }
      } catch (err) {
        this.loginInvalid = true;
        console.log(err);
      }
    } else {
      this.formSubmitAttempt = true;
      console.log(4);
    }
    console.log(5);
  }
  
}
