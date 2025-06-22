import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-annonces',
  templateUrl: './annonces.component.html',
  imports:[CommonModule],
  styleUrl: './annonces.component.css'
})
export class AnnoncesComponent {
  trajets = [
    {
      depart: 'Casablanca',
      destination: 'Rabat',
      date: '2025-07-01',
      typeColis: 'Fragile',
      capacite: '10kg'
    },
    {
      depart: 'Tanger',
      destination: 'Fès',
      date: '2025-07-03',
      typeColis: 'Lourd',
      capacite: '25kg'
    }
  ];
}
