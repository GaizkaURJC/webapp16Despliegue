import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {
  emailLogin: string = '';
  passwordLogin: string = '';

  constructor(public activeModal: NgbActiveModal) {}

  closeModal() {
    this.activeModal.dismiss(); // Cierra el modal
  }

  submitLogin() {
    if (!this.emailLogin || !this.passwordLogin) {
      console.error('Por favor, completa todos los campos.');
      return;
    }

    console.log('Iniciando sesión con:', {
      email: this.emailLogin,
      password: this.passwordLogin
    });

    this.activeModal.close(); // Opcional: Cierra el modal después de enviar
  }
}