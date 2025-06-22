import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable} from 'rxjs';
import {Router} from '@angular/router';


  interface authResponse{
    token : string ;
    role : string ;
  }
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private Url = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router : Router) { }

  login(data:any): Observable<any>{
    return this.http.post(`${this.Url}/login`,data);
  }


  register(data: {
    firstname: string;
    lastname: string;
    email: string;
    password: string;
    role: string;
  }): Observable<any> {
    console.log("📤 Données envoyées :", data);
    return this.http.post('http://localhost:8080/api/auth/register', data, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }



  saveToken(token:string){
    localStorage.setItem('jwt',token);
  }

  getToken(token: string){
    return localStorage.getItem('jwt');
  }

  getUserRole() : string | null {
    return localStorage.getItem('userRole')
  }

  private redirectToDashboard(role : string) : void{
    switch (role){
      case 'SENDER' :
        this.router.navigate(['/sender/dashboard']);
        break;
      case 'DRIVER' :
        this.router.navigate(['/driver/dashboard']);
        break;
      case 'ADMIN' :
        this.router.navigate(['/admin/dashboard']);
        break;
      default :
        this.router.navigate(['/home']);
        break;
    }
  }

  logout(){
    localStorage.removeItem('jwt');
    localStorage.removeItem('userRole');
    this.router.navigate(['/login']);
  }



}
