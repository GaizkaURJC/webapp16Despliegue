import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
import { EventService } from '../../services/event.service';
import { EventWithImageDTO } from '../../dtos/event.dto';

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterModule],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  concertTypes = ["Todos", "Rock", "Trap", "Pop", "Rap", "Flamenco"];
  parties: EventWithImageDTO[] = [];
  allConcerts: EventWithImageDTO[] = []; // Todos los conciertos cargados
  displayedConcerts: EventWithImageDTO[] = []; // Conciertos mostrados actualmente
  filteredConcerts: EventWithImageDTO[] = []; // Conciertos después de filtrar
  selectedCategory: string = "Todos";
  loading = true;
  currentPage = 0;
  concertsPerPage = 4;
  hasMoreConcerts = true;

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.loading = true;
    this.eventService.getAllEventsWithImages().subscribe({
      next: (response: any) => {
        const events = response.content || response;
        this.parties = events.filter((e: any) => e.type === 'party');
        this.allConcerts = events.filter((e: any) => e.type === 'concert');
        this.applyFilter(); // Aplica el filtro inicial y carga los primeros conciertos
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading events:', error);
        this.loading = false;
      }
    });
  }

  applyFilter(): void {
    // Aplicar filtro según categoría seleccionada
    if (this.selectedCategory === 'Todos') {
      this.filteredConcerts = [...this.allConcerts];
    } else {
      this.filteredConcerts = this.allConcerts.filter(
        concert => concert.category.toLowerCase() === this.selectedCategory.toLowerCase()
      );
    }
    
    // Resetear paginación
    this.currentPage = 0;
    this.displayedConcerts = [];
    
    // Cargar primeros conciertos
    this.loadMoreConcerts();
  }

  loadMoreConcerts(): void {
    const startIndex = this.currentPage * this.concertsPerPage;
    const endIndex = startIndex + this.concertsPerPage;
    
    // Obtener el siguiente lote de conciertos
    const newConcerts = this.filteredConcerts.slice(startIndex, endIndex);
    this.displayedConcerts = [...this.displayedConcerts, ...newConcerts];
    
    // Actualizar estado
    this.currentPage++;
    this.hasMoreConcerts = endIndex < this.filteredConcerts.length;
  }

  filterConcerts(category: string): void {
    this.selectedCategory = category;
    this.applyFilter(); // Reaplicar el filtro cuando cambia la categoría
  }
}