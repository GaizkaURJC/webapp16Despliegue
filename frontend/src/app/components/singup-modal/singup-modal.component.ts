import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-singup-modal',
  standalone: true,
  imports: [CommonModule, FormsModule], // Aquí importamos lo necesario
  templateUrl: './singup-modal.component.html',
  styleUrls: ['./singup-modal.component.css']
})
export class SingupModalComponent {
  // Variables para la información del formulario
  name = '';
  email = '';
  telefono = '';
  password = '';
  errorMessage = '';
  isLoading = false;  // Indicador para el estado de carga

  constructor(public activeModal: NgbActiveModal) { }

  // Método para cerrar el modal
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

  // Método para manejar el registro
  submitSingup() {
    // Validación simple de los campos requeridos
    if (!this.name || !this.email || !this.telefono || !this.password) {
      this.errorMessage = 'Por favor, completa todos los campos.';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';  // Limpiar cualquier mensaje de error previo

    // Simula una solicitud para enviar los datos del formulario
    setTimeout(() => {
      console.log('Formulario enviado:', {
        name: this.name,
        email: this.email,
        telefono: this.telefono,
        password: this.password
      });
      this.isLoading = false;
      this.activeModal.close();  // Cierra el modal después de enviar
    }, 1000);  // Simula un retardo de 1 segundo (en un caso real, harías una solicitud HTTP aquí)
  }
}
