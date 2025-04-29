import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonIcon } from '@ionic/angular/standalone';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';
import { CommentService } from '../../services/comment.service';
import { BookingService } from '../../services/booking.service'; 
import {AuthService} from '../../services/login.service'; // Asegúrate de que la ruta sea correcta
import { wine, musicalNotes, people, construct, chatbubble, logIn } from 'ionicons/icons';
import { addIcons } from 'ionicons';

import { AuthStateService } from '../../services/auth-state.service'; // Añadir este import
import { Router } from '@angular/router'; // Importar Router
import { BookingDTO } from '../../dtos/booking.dto';


@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, IonIcon],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  activeSection: string = 'clubbing';

  parties: any[] = [];
  concerts: any[] = [];
  bookings: any[] = [];
  comments: any[] = [];
  users: any[] = [];
  username: string = '';
  userId!: number;

  showSection(section: string): void {
    console.log('Changing section to:', section);
      this.activeSection = section;
  }

  constructor(
    private modalService: NgbModal,
    private eventService: EventService,
    private userService: UserService,
    private commentService: CommentService,
    private bookingService: BookingService,
    private authStateService: AuthStateService,
    private authService: AuthService,
    private router: Router // Añadir el router aquí

  ) {
    addIcons({
      wine,
      musicalNotes,
      people,
      construct,
      chatbubble,
      logIn,
    });
  }

  ngOnInit(): void {
    this.loadUserData();
    this.eventService.getEventsByType("party").subscribe(data => this.parties = data);
    this.eventService.getEventsByType("concert").subscribe(data => this.concerts = data);
    this.userService.getAllUsers().subscribe(data => this.users = data);
    this.commentService.getAllComments().subscribe(data => this.comments = data);
    this.bookingService.getAllBookings().subscribe(data => this.bookings = data);
    
  }

  
  private loadUserData(): void {
    // Obtener el usuario autenticado en lugar de por ID
    this.authStateService.getAuthenticatedUser().subscribe({
      next: (user) => {
        if (user){
        this.userId = user.id; // Asignar el ID del usuario
        this.username = user.name; // Asignar el nombre directamente
        } else {
          console.error('No se encontró el usuario autenticado.');
        }
        
      },
      error: (err) => {
        console.error('Error al obtener datos del usuario:', err);
        alert('No se pudo obtener la información del usuario. Por favor, inicia sesión.');
        
      }
    });
  }

  deletebooking(bookingId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este booking?')) {
      this.bookingService.deleteBooking(bookingId).subscribe({
        next: () => {
          // Update the bookings array to remove the deleted booking
          this.bookings = this.bookings.filter(booking => booking.id !== bookingId);
        },
        error: (error) => {
          console.error('Error deleting booking:', error);
          alert('No se pudo eliminar el booking.');
        }
      });
    }
  }

  deleteEvent(partyId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar esta fiesta?')) {
      this.eventService.deleteEvent(partyId).subscribe({
        next: () => {
          // Update the parties array to remove the deleted party
          this.parties = this.parties.filter(party => party.id !== partyId);
          this.concerts = this.concerts.filter(concert => concert.id !== partyId); // Assuming you want to remove from concerts as well
        },
        error: (error) => {
          console.error('Error deleting party:', error);
          alert('No se pudo eliminar la fiesta.');
        }
      });
    }
  }

  deleteUser(userId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
      this.userService.deleteUser(userId).subscribe({
        next: () => {
          // Update the users array to remove the deleted user
          this.users = this.users.filter(user => user.id !== userId);
        },
        error: (error) => {
          console.error('Error deleting user:', error);
          alert('No se pudo eliminar el usuario.');
        }
      });
    }
  }

  deleteComment(commentId: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este comentario?')) {
      this.commentService.deleteComment(commentId).subscribe({
        next: () => {
          // Update the comments array to remove the deleted comment
          this.comments = this.comments.filter(comment => comment.id !== commentId);
        },
        error: (error) => {
          console.error('Error deleting comment:', error);
          alert('No se pudo eliminar el comentario.');
        }
      });
    }
  }
  logout(): void {
    this.authService.logout();
    alert('Has cerrado sesión correctamente.');
    this.router.navigate(['/']);
  }

  acceptBooking(bookingId: number): void {
    if (confirm('¿Estás seguro de que deseas aceptar esta reserva?')) {
      this.bookingService.acceptBooking(bookingId).subscribe({
        next: () => {
          // Update the bookings array to reflect the accepted booking
          const booking = this.bookings.find(b => b.id === bookingId);
          if (booking) {
            booking.status = 'aceptada'; // Assuming the booking object has a status property
          }
        },
        error: (error) => {
          console.error('Error accepting booking:', error);
          alert('No se pudo aceptar la reserva.');
        }
      });
    }
  }

  rejectBooking(bookingId: number): void {
    if (confirm('¿Estás seguro de que deseas rechazar esta reserva?')) {
      this.bookingService.rejectBooking(bookingId).subscribe({
        next: () => {
          // Update the bookings array to reflect the accepted booking
          const booking = this.bookings.find(b => b.id === bookingId);
          if (booking) {
            booking.status = 'rechazada'; // Assuming the booking object has a status property
          }
        },
        error: (error) => {
          console.error('Error rejecting booking:', error);
          alert('No se pudo rechazar la reserva.');
        }
      });
    }
  }
}
