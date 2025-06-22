import { Routes } from '@angular/router';
import {HistoriqueComponent} from './pages/sender/historique/historique.component';
import {DemandeFormComponent} from './pages/sender/demande-form/demande-form.component';
import {AnnoncesComponent} from './pages/sender/annonces/annonces.component';
import {DashboardComponent} from './pages/sender/dashboard/dashboard.component';


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
    path: 'app-SenderDashboard',
    component: DashboardComponent,
    children: [
      { path: '', redirectTo: 'annonces', pathMatch: 'full' },
      { path: 'annonces', component: AnnoncesComponent },
      { path: 'demande', component: DemandeFormComponent },
      { path: 'historique', component: HistoriqueComponent },
    ]
  },
  {
    path: 'annonces',
    component: AnnoncesComponent
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
