import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-singup-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './singup-modal.component.html',
  styleUrls: ['./singup-modal.component.css']
})
export class SingupModalComponent {
  name = '';
  email = '';
  telefono = '';
  password = '';
  errorMessage = '';
  isLoading = false;
  acceptTerms = false;
  acceptData = false;

  constructor(
    public activeModal: NgbActiveModal,
    private authService: AuthService,
    private router: Router
  ) { }

  closeModal() {
    this.activeModal.dismiss();
  }

  ngAfterViewInit() {
    const modalContent = document.querySelector('.modal-content-custom') as HTMLElement;
    if (modalContent) {
      modalContent.style.backgroundColor = 'rgba(0,0,0)';
      modalContent.style.backdropFilter = 'blur(12px)';
    }
  }

  submitSingup() {
    // Validación de campos
    if (!this.name || !this.email || !this.telefono || !this.password) {
      this.errorMessage = 'Por favor, completa todos los campos obligatorios.';
      return;
    }

    if (!this.acceptTerms) {
      this.errorMessage = 'Debes aceptar los términos y condiciones.';
      return;
    }

    // Validación básica de email
    if (!this.email.includes('@') || !this.email.includes('.')) {
      this.errorMessage = 'Por favor, introduce un email válido.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const userData = {
      name: this.name,
      email: this.email,
      telefono: this.telefono,
      password: this.password,
    };

    this.authService.register(userData).subscribe({
      next: (registerResponse) => {
        // Después de registrar, hacemos login automático
        this.authService.loginAfterRegister(this.email, this.password).subscribe({
          next: (loginResponse) => {
            this.isLoading = false;
            
            if (loginResponse && loginResponse.token) {
              localStorage.setItem('token', loginResponse.token);
              this.activeModal.close();
              // Recargar para actualizar el estado de autenticación
              window.location.reload();
            } else {
              this.errorMessage = 'Registro completado, pero no se pudo iniciar sesión automáticamente. Por favor, inicia sesión manualmente.';
            }
          },
          error: (loginError) => {
            this.isLoading = false;
            this.errorMessage = 'Registro completado, pero no se pudo iniciar sesión automáticamente. Por favor, inicia sesión manualmente.';
            console.error('Error en login después de registro:', loginError);
          }
        });
      },
      error: (registerError) => {
        this.isLoading = false;
        console.error('Error en el registro:', registerError);
        
        if (registerError.status === 400) {
          if (registerError.error && registerError.error.message) {
            this.errorMessage = registerError.error.message;
          } else {
            this.errorMessage = 'Error en los datos proporcionados. Verifica la información.';
          }
        } else if (registerError.status === 409) {
          this.errorMessage = 'El email ya está registrado. ¿Quieres iniciar sesión?';
        } else if (registerError.status === 0) {
          this.errorMessage = 'No se pudo conectar con el servidor. Verifica tu conexión.';
        } else {
          this.errorMessage = 'Ocurrió un error inesperado. Por favor, inténtalo de nuevo.';
        }
      }
    });
  }
}