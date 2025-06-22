import { Component } from '@angular/core';
import {AuthService} from '../../../core/auth.service';
import {Router} from '@angular/router';
import {FormsModule, NgForm} from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  firstname = '';
  lastname = '';
  email = '';
  password = '';
  role = 'SENDER';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister(form: NgForm) {
    if (form.invalid) return;

    const { firstname, lastname, email, password, role } = this;
    this.authService.register({ firstname, lastname, email, password, role }).subscribe({
      next: (res) => {
        console.log('Inscription réussie', res);
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.log('Erreur inscription', err);
      }
    });
  }
}
