import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  standalone: true,
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  imports: [CommonModule, LoginModalComponent]
})
export class HeaderComponent {
  items = [
    { name: 'Home', icon: 'home', link: '/' },
    { name: 'Clubbing', icon: 'information-circle', link: '/clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/concerts' },
    { name: 'Eventos', icon: 'mail', link: '/events' },
    { name: 'Contactanos', icon: 'party', link: '/contact' },
    { name: 'Login', icon: 'people', link: '' }
  ];

  constructor(private modalService: NgbModal) {}

  openLoginModal(event: Event) {
    event.preventDefault(); // Previene la recarga de la página
    this.modalService.open(LoginModalComponent, { centered: true });
  }
}