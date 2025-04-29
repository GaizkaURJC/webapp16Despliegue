import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EventService } from '../../services/event.service';
import { EventWithImageDTO, EventDTO } from '../../dtos/event.dto';
import { AuthStateService } from '../../services/auth-state.service'; // Añadir este import


@Component({
  selector: 'app-party-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './party-modal.component.html',
  styleUrls: ['./party-modal.component.css']
  
})
export class PartyModalComponent implements OnInit {

    title: string = '';
    type: string = '';
    description: string = '';
    category: string = '';
    imageBase64: string = '';

  constructor(
    public activeModal: NgbActiveModal,
    private eventService: EventService,
    private authStateService: AuthStateService
  ) {}

  ngOnInit(): void {
  }

  
  
  

  closeModal() {
    this.activeModal.dismiss();
  }

  submitParty() {
    const newParty: EventDTO = {
      id: 0,
      title: this.title, 
      type: 'party',
      description: this.description,
      category: 'party',
    };
  
    this.eventService.createEvent(newParty).subscribe({
      next: (response) => {
        this.activeModal.close(response); 
      },
      error: (err) => {
        console.error('Error al crear la fiesta:', err);
        alert('Hubo un error al crear la fiesta. Por favor, inténtalo de nuevo.');
      }
    });
  }
}