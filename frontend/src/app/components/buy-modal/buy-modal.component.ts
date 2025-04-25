import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-buy-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './buy-modal.component.html',
  styleUrls: ['./buy-modal.component.css'],
  providers: [NgbActiveModal]
})
export class BuyModalComponent {
  @Input() event: any;
  @Input() token: string = '';

  ticketName = '';
  dni = '';
  gender = 'Hombre';

  constructor(public activeModal: NgbActiveModal) { }

  submitForm() {
    const ticketData = {
      ticketName: this.ticketName,
      dni: this.dni,
      gender: this.gender,
      eventName: this.event?.title,
      category: this.event?.category,
      eventId: this.event?.id,
      _csrf: this.token
    };

    console.log('Ticket enviado:', ticketData);
    this.activeModal.close(ticketData);
  }
}
