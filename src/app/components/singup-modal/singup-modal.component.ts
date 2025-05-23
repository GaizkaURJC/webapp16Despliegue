import { Component, AfterViewInit } from '@angular/core';
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
export class SingupModalComponent implements AfterViewInit {
  name = '';
  email = '';
  phone = '';
  password = '';
  acceptTerms = false;
  acceptData = false;

  errorMessage = '';
  isLoading = false;

  constructor(
    public activeModal: NgbActiveModal,
    private authService: AuthService,
    private router: Router
  ) {}

  ngAfterViewInit() {
    const modalContent = document.querySelector('.modal-content-custom') as HTMLElement;
    if (modalContent) {
      modalContent.style.backgroundColor = 'rgba(0,0,0)';
      modalContent.style.backdropFilter = 'blur(12px)';
    }
  }

  closeModal() {
    this.activeModal.dismiss();
  }

  submitSingup(): void {
    // Validar campos
    if (!this.name || !this.email || !this.phone || !this.password) {
      this.errorMessage = 'Por favor, completa todos los campos obligatorios.';
      return;
    }
    if (!this.acceptTerms) {
      this.errorMessage = 'Debes aceptar los términos y condiciones.';
      return;
    }
    if (!this.email.includes('@') || !this.email.includes('.')) {
      this.errorMessage = 'Por favor, introduce un email válido.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const userData = {
      name: this.name,
      email: this.email,
      phone: this.phone,
      password: this.password,
      roles: ['USER']
    };

    // 1) Registro
    this.authService.register(userData).subscribe({
      next: () => {
        // 2) Login automático tras registro
        this.authService.login(this.email, this.password).subscribe({
          next: () => {
            this.isLoading = false;
            this.activeModal.close();
            this.router.navigate(['/']);
          },
          error: () => {
            this.isLoading = false;
            this.errorMessage = 'Registro completado, pero no se pudo iniciar sesión automáticamente. Por favor, inicia sesión manualmente.';
          }
        });
      },
      error: registerError => {
        this.isLoading = false;
        if (registerError.status === 400 && registerError.error?.message) {
          this.errorMessage = registerError.error.message;
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