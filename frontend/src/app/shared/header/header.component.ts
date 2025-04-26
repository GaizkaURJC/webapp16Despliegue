// header.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../../services/login.service';
import { AuthStateService } from '../../services/auth-state.service';
import { Router } from '@angular/router';

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
    { name: 'Clubbing', icon: 'information-circle', link: '/spa/clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/spa/concerts' },
    { name: 'Eventos', icon: 'mail', link: '/events' },
    { name: 'Contactanos', icon: 'party', link: '/contact' },
    { name: 'Login', icon: 'people', link: '' }
  ];

  isAuthenticated = false;

  constructor(
    private modalService: NgbModal,
    private authService: AuthService,
    private authState: AuthStateService,
    private router: Router
  ) {
    this.authState.isAuthenticated$.subscribe(auth => {
      this.isAuthenticated = auth;
      // Actualiza el menú según el estado de autenticación
      this.updateMenuItems();
    });
  }

  private updateMenuItems(): void {
    if (this.isAuthenticated) {
      this.items = this.items.filter(item => item.name !== 'Login');
      // Puedes añadir más lógica aquí para actualizar el menú
    } else {
      if (!this.items.some(item => item.name === 'Login')) {
        this.items.push({ name: 'Login', icon: 'people', link: '' });
      }
    }
  }

  openLoginModal(event: Event) {
    event.preventDefault();
    this.modalService.open(LoginModalComponent, { centered: true });
  }

  logout(event: Event) {
    event.preventDefault();
    this.authService.logout();
    this.authState.setAuthenticated(false);
    this.router.navigate(['/']);
  }
}