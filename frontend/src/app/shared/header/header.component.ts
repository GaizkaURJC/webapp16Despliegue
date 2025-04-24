import { Component } from '@angular/core';

@Component({
  standalone: true,
  
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  items = [
    { name: 'Home', icon: 'home', link: '/' },
    { name: 'Clubbing', icon: 'information-circle', link: '/clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/concerts' },
    { name: 'Eventos', icon: 'mail', link: '/events' },
    { name: 'Contactanos', icon: 'party', link: '/contact' },
    { name: 'Login', icon: 'people', link: '/modalLogin' }
  ];
}
