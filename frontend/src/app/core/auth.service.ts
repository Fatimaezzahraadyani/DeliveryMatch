import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private Url = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(data:any): Observable<any>{
    return this.http.post(`${this.Url}/login`,data);
  }

  register(data:any): Observable<any>{
    return this.http.post(`${this.Url}/register`, data);
  }

  saveToken(token:string){
    localStorage.setItem('jwt',token);
  }

  getToken(token: string){
    return localStorage.getItem('jwt');
  }

  logout(){
    localStorage.removeItem('jwt');
  }



}
