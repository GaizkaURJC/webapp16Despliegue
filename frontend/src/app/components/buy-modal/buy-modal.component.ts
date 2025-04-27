import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-buy-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './buy-modal.component.html',
  styleUrls: ['./buy-modal.component.css'],
})
export class BuyModalComponent {
  @Input() event: any;
  @Input() token: string = '';
  @Input() currentUser?: { name: string };  // Añade esta línea para recibir el usuario actual

  ticketName = '';
  dni = '';
  gender = 'Hombre';
  isLoading = false;
  errorMessage = '';
  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService
  ) { }

  closeModal() {
    this.activeModal.dismiss();
  }

  ngAfterViewInit() {
    const modalContent = document.querySelector('.modal-content') as HTMLElement;
    if (modalContent) {
      modalContent.style.backgroundColor = 'rgba(0,0,0,0.7)';
      modalContent.style.backdropFilter = 'blur(12px)';
    }
  }

  submitForm() {
    console.log('Datos del usuario:', this.currentUser); // Para depuración
  
    if (!this.currentUser?.name) { // Cambia username por name
      this.errorMessage = 'No se pudo identificar al usuario';
      console.error('Usuario no definido:', this.currentUser);
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const ticketData = {
      title: this.event?.title,
      category: this.event?.category,
      eventId: this.event?.id,
      userOwner: this.currentUser.name, // Asume que currentUser tiene username
      dni: this.dni,
      gender: this.gender,
      ticketDate: new Date().toISOString(), // Fecha actual como string
      ticketName: this.ticketName,
      _csrf: this.token
    };

    this.ticketService.createTicket(ticketData).subscribe({
      next: (createdTicket) => {
        console.log('Ticket creado:', createdTicket);
        this.isLoading = false;
        this.activeModal.close(createdTicket);
      },
      error: (err) => {
        console.error('Error creando ticket:', err);
        this.isLoading = false;
        this.errorMessage = 'Error al comprar el ticket. Por favor intente nuevamente.';
      }
    });
  }
}
