import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Añade esto
import { AuthService } from '../../services/login.service'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-modal',
  standalone: true,
  imports: [FormsModule, CommonModule], // Añade CommonModule aquí
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {
  emailLogin: string = '';
  passwordLogin: string = '';
  errorMessage: string = '';
  isLoading: boolean = false; // Añadimos un estado de carga

  constructor(
    public activeModal: NgbActiveModal,
    private authService: AuthService,
    private router: Router
  ) {}

  closeModal() {
    this.activeModal.dismiss();
  }

  submitLogin() {
    if (!this.emailLogin || !this.passwordLogin) {
      this.errorMessage = 'Por favor, completa todos los campos.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login(this.emailLogin, this.passwordLogin).subscribe({
      next: (response) => {
        this.isLoading = false;
        
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          this.activeModal.close();
          
          // Recarga la página para actualizar el estado de autenticación
          window.location.reload();
        } else {
          this.errorMessage = 'Respuesta inesperada del servidor';
        }
      },
      error: (error) => {
        this.isLoading = false;
        console.error('Error en el login:', error);
        
        if (error.status === 401) {
          this.errorMessage = 'Credenciales incorrectas. Por favor, inténtalo de nuevo.';
        } else if (error.status === 0) {
          this.errorMessage = 'No se pudo conectar con el servidor. Verifica tu conexión.';
        } else {
          this.errorMessage = 'Error en el servidor. Por favor, inténtalo más tarde.';
        }
      }
    });
  }
}