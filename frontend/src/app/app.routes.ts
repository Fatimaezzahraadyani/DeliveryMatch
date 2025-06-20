import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./pages/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'sender',
    loadChildren: () =>
      import('./pages/sender/sender.module').then(m => m.SenderModule)
  },
  {
    path: 'driver',
    loadChildren: () =>
      import('./pages/driver/driver.module').then(m => m.DriverModule)
  },
  {
    path: '**',
    redirectTo: 'auth/login'
  }
];
