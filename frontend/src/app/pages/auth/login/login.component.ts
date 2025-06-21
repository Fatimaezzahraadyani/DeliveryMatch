import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../core/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  password = '';

  constructor(private authService : AuthService, private router: Router) {
  }

  onLogin(){
    this.authService.login({ email : this.email, password : this.password}).subscribe(
      {
        next: res =>{
          this.authService.saveToken(res.token);
          this.router.navigate(["/"])
        },
        error: err => console.error(err)
      }
    );
  }

}
