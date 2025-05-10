import { BookingService } from './../../services/booking.service';
import { BookingDTO } from './../../dtos/booking.dto';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
import { IonIcon } from '@ionic/angular/standalone';
import { EventService } from '../../services/event.service';
import { EventWithImageDTO } from '../../dtos/event.dto';
import { FormsModule } from '@angular/forms';
import { linkOutline } from 'ionicons/icons';
import { addIcons } from 'ionicons';
import jsPDF from 'jspdf'

interface BookingRequest {
  userName: string;
  userEmail: string;
  bussinesName: string;
  capacity: number;
  eventDescript: string;
}

@Component({
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterModule,FormsModule, IonIcon],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  concertTypes = ["Todos", "Rock", "Trap", "Pop", "Rap", "Flamenco"];
  parties: EventWithImageDTO[] = [];
  allConcerts: EventWithImageDTO[] = [];
  displayedConcerts: EventWithImageDTO[] = [];
  filteredConcerts: EventWithImageDTO[] = [];
  selectedCategory: string = "Todos";
  loading = true;
  currentPage = 0;
  concertsPerPage = 4;
  hasMoreConcerts = true;
  isLoading: boolean = false;
  
  // Variables para el formulario de reserva
  userName = '';
  userEmail = '';
  bussinesName = '';
  num_personas = 0;
  eventDescript = '';
  bookingSuccess = false;
  bookingError = false;
  bookingErrorMessage = '';

  constructor(
    private eventService: EventService,
    private bookingService: BookingService
  ) {
    addIcons({
      linkOutline
    });
   }

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
        this.applyFilter();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading events:', error);
        this.loading = false;
      }
    });
  }

  applyFilter(): void {
    if (this.selectedCategory === 'Todos') {
      this.filteredConcerts = [...this.allConcerts];
    } else {
      this.filteredConcerts = this.allConcerts.filter(
        concert => concert.category.toLowerCase() === this.selectedCategory.toLowerCase()
      );
    }
    
    this.currentPage = 0;
    this.displayedConcerts = [];
    this.loadMoreConcerts();
  }

  loadMoreConcerts(): void {
    this.isLoading = true; // Mostrar el loader
    const startIndex = this.currentPage * this.concertsPerPage;
    const endIndex = startIndex + this.concertsPerPage;

    // Simular un retraso para mostrar el loader (puedes reemplazar esto con una llamada real al backend)
    setTimeout(() => {
        const newConcerts = this.filteredConcerts.slice(startIndex, endIndex);
        this.displayedConcerts = [...this.displayedConcerts, ...newConcerts];

        this.currentPage++;
        this.hasMoreConcerts = endIndex < this.filteredConcerts.length;

        this.isLoading = false; // Ocultar el loader
    }, 500); // Simula un retraso de 2 segundos
}

  filterConcerts(category: string): void {
    this.selectedCategory = category;
    this.applyFilter();
  }

  submitBooking(): void {
    this.bookingSuccess = false;
    this.bookingError = false;

    if (!this.validateBookingForm()) {
      this.bookingError = true;
      this.bookingErrorMessage = 'Por favor complete todos los campos requeridos';
      return;
    }

    // Verifica si el usuario está autenticado
    const token = localStorage.getItem('token');
    if (!token) {
      this.bookingError = true;
      this.bookingErrorMessage = 'Debe iniciar sesión para realizar una reserva';
      // Aquí podrías redirigir al login
      // this.router.navigate(['/login']);
      return;
    }

    const bookingData = {
      userName: this.userName,
      userEmail: this.userEmail,
      bussinesName: this.bussinesName,
      capacity: this.num_personas,
      eventDescript: this.eventDescript
    };

    this.bookingService.book(bookingData).subscribe({
      next: (response) => {
        this.bookingSuccess = true;
        this.resetBookingForm();

        // Generar el PDF después de una reserva exitosa
        this.generatePDF(bookingData);
      },
      error: (error) => {
        if (error.status === 401) {
          this.bookingErrorMessage = 'Sesión expirada. Por favor inicie sesión nuevamente.';
          // localStorage.removeItem('token'); // Opcional: limpiar token inválido
          // this.router.navigate(['/login']);
        } else {
          this.bookingErrorMessage = error.error?.message || 'Error al realizar la reserva';
        }
        this.bookingError = true;
      }
    });
}

generatePDF(bookingData: any): void {
    const doc = new jsPDF();

    doc.setFontSize(18);
    doc.text('Confirmación de Reserva', 10, 10);

    doc.setFontSize(12);
    doc.text(`Nombre: ${bookingData.userName}`, 10, 30);
    doc.text(`Email: ${bookingData.userEmail}`, 10, 40);
    doc.text(`Empresa: ${bookingData.bussinesName}`, 10, 50);
    doc.text(`Número de personas: ${bookingData.capacity}`, 10, 60);
    doc.text('Descripción del evento:', 10, 70);
    doc.text(bookingData.eventDescript, 10, 80, { maxWidth: 180 });

    // Descargar el PDF
    doc.save(`reserva_${bookingData.userName}.pdf`);
}

  private validateBookingForm(): boolean {
    return !!this.userName && 
           !!this.userEmail && 
           !!this.bussinesName && 
           this.num_personas > 0 && 
           !!this.eventDescript;
  }

  private formatDate(dateString: string): string {
    // Formatea la fecha para que coincida con el formato esperado por el backend
    const date = new Date(dateString);
    return date.toISOString().split('T')[0]; // Formato YYYY-MM-DD
  }

  private resetBookingForm(): void {
    this.userName = '';
    this.userEmail = '';
    this.bussinesName = '';
    this.num_personas = 0;
    this.eventDescript = '';
  }
}