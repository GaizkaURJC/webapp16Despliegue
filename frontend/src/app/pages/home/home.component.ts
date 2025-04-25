import { EventWithImageDTO } from './../../dtos/event.dto';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventService } from '../../services/event.service';
import { EventDTO } from '../../dtos/event.dto';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterModule],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  // Aquí puedes definir la lógica y propiedades de tu componente
  // Por ejemplo, puedes tener un título o una lista de elementos para mostrar
  title2 = 'Home Page';
  items = ['Item 1', 'Item 2', 'Item 3'];
  concerTypes = [
    "Todos", "Rock","Trap","Pop","Rap","Flamenco"]
  parties: EventWithImageDTO[] = [];
  concerts: EventWithImageDTO[] = [];
  loading = true;

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.loadEventsWithImages();
  }

  loadEventsWithImages(): void {
    this.eventService.getEventsWhithImages().subscribe({
      next: (response) => {
        this.parties = response.content.filter(e => e.type === 'party');
        this.concerts = response.content.filter(e => e.type === 'concert');
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading events:', error);
        this.loading = false;
      }
    });
  }


  getFilteredConcerts(category: string): EventDTO[] {
    if (category === 'Todos') {
      return this.concerts;
    }
    return this.concerts.filter(concert => concert.category.toLowerCase() === category.toLowerCase());
  }
}
