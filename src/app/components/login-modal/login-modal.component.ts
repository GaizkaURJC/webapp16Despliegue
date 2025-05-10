import { Component } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';  
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/login.service';
import { Router } from '@angular/router';
import { SingupModalComponent } from '../singup-modal/singup-modal.component';  
import { AuthStateService } from '../../services/auth-state.service'; // Asegúrate de que la ruta sea correcta

@Component({
  selector: 'app-login-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {
  emailLogin: string = '';
  passwordLogin: string = '';
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private authService: AuthService,
    private router: Router,
    private modalService: NgbModal,
    private authState: AuthStateService // Inyecta el servicio correctamente
  ) { }

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
        this.activeModal.close();
        
        // El AuthStateService ya fue actualizado por el tap() en el servicio
        if (this.authState.getCurrentUser()?.roles?.includes('ADMIN')) {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/']);
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
          this.errorMessage = error.error?.message || 'Error en el servidor. Por favor, inténtalo más tarde.';
        }
      }
    });
  }

  openSignUpModal() {
    const modalRef = this.modalService.open(SingupModalComponent); 
    modalRef.result.then(
      (result) => {
        console.log('Modal cerrado con resultado:', result);
      },
      (reason) => {
        console.log('Modal cerrado por:', reason);
      }
    );
    this.activeModal.dismiss();
  }
}
