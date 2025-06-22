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
    this.authService.login({ email : this.email, password : this.password}).subscribe({

      next: (res) => {
        console.log('Réponse login:', res);
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role); // Stocker le rôle

        if (res.role === 'SENDER') {
          this.router.navigate(['/app-SenderDashboard']);
        } else if (res.role === 'DRIVER') {
          this.router.navigate(['/driver/dashboard']);
        } else if (res.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/']);
        }
      },
      error: (err) => {
        console.log('Erreur de login', err);
      }
    });

  }

}
