import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Respuesta } from '../modelo/Respuesta';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };
  url:string = 'http://localhost:8082/';
  respuesta: Respuesta;
  isAuthenticated : boolean;

  constructor(private http:HttpClient) {
  }


  async login(inicioUser: String){

   let data = await this.http.post<Respuesta>(this.url+"infoUser", inicioUser, this.httpOptions).toPromise();

    this.respuesta = data;

    if(this.respuesta.resultCode == 0){
      this.isAuthenticated = true
    }else{
      this.isAuthenticated = false
    }

    return this.isAuthenticated;

  }
  
}
