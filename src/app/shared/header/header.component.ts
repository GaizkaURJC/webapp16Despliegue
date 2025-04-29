// header.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../../services/login.service';
import { AuthStateService } from '../../services/auth-state.service';
import { Router } from '@angular/router';
import { UserDTO } from '../../dtos/user.dto';

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
    { name: 'Clubbing', icon: 'information-circle', link: './clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: './concerts' },
    { name: 'Eventos', icon: 'mail', link: './events' },
    { name: 'Contactanos', icon: 'party', link: './contact' }
  ];

  isAuthenticated = false;
  userName: string = '';

  constructor(
    private modalService: NgbModal,
    private authService: AuthService,
    private authState: AuthStateService,
    private router: Router
  ) {
    this.authState.isAuthenticated$.subscribe(auth => {
      this.isAuthenticated = auth;
      if (auth) {
        const userData = localStorage.getItem('currentUser');
        if (userData) {
          const user: UserDTO = JSON.parse(userData);
          this.userName = user.name || user.email;
        }
      } else {
        this.userName = '';
      }
    });
  }

  private loadUserData(): void {
    this.authState.getAuthenticatedUser().subscribe({
      next: (user: UserDTO | null) => {
        if (user){ 
          this.userName = user.name || user.name;
        }else{
          console.error('No se encontró el usuario autenticado.');
        }
      },
      error: (err) => {
        console.error('Error loading user data:', err);
      }
    });
  }

  openLoginModal(event: Event) {
    event.preventDefault();
    this.modalService.open(LoginModalComponent, { centered: true });
  }

  goToProfile(event: Event) {
    event.preventDefault();
    this.router.navigate(['/profile']);
  }

  logout(event: Event) {
    event.preventDefault();
    this.authService.logout();
    this.authState.setAuthenticated(false);
    this.router.navigate(['/']);
  }

  
}