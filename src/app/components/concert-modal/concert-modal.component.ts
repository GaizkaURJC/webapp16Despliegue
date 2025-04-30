import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EventService } from '../../services/event.service';
import { EventWithImageDTO, EventDTO } from '../../dtos/event.dto';
import { AuthStateService } from '../../services/auth-state.service'; // Añadir este import


@Component({
  selector: 'app-concert-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './concert-modal.component.html',
  styleUrls: ['./concert-modal.component.css']
  
})
export class ConcertModalComponent implements OnInit {

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

  submitConcert() {
    const newConcert: EventDTO = {
      id: 0,
      title: this.title, 
      type: 'concert',
      description: this.description,
      category: this.category,
    };
  
    this.eventService.createEvent(newConcert).subscribe({
      next: (response) => {
        this.activeModal.close(response); 
      },
      error: (err) => {
        console.error('Error al crear el concierto:', err);
        alert('Hubo un error al crear el concierto. Por favor, inténtalo de nuevo.');
      }
    });
  }
}