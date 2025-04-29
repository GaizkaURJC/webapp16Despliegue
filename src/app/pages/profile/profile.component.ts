// profile.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../shared/header/header.component';
import { EventService } from '../../services/event.service';
import { AuthStateService } from '../../services/auth-state.service';
import { UserService } from '../../services/user.service';
import { UserDTO } from '../../dtos/user.dto';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { AuthService } from '../../services/login.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { CreateRequestUserDTO } from '../../dtos/requestUser.dto';
import { TicketService } from '../../services/ticket.service';
import { TicketDTO } from '../../dtos/ticket.dto';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    HeaderComponent,
    CommonModule,
    RouterModule,
    ReactiveFormsModule
  ],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  editProfileForm: FormGroup;
  isSubmitting = false;
  editSuccess = false;
  editError = false;
  activeTab: string = 'perfil';
  userLogged: UserDTO = {
    id: 0,
    name: '',
    email: '',
    roles: [],
    phone: '',
    image: false
  };
  isLoading: boolean = true;
  profileImage: SafeUrl = '/assets/images/default-profile.png';
  tickets: TicketDTO[] = [];
  loadingTickets = false;
  ticketError = '';

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private eventService: EventService,
    private authStateService: AuthStateService,
    private sanitizer: DomSanitizer,
    private authService: AuthService,
    private router: Router,
    private ticketService: TicketService
  ) {
    this.editProfileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    this.loadUserData(); 
  }

  showTab(tabId: string): void {
    this.activeTab = tabId;
  }

private loadUserData(): void {
  this.authStateService.getAuthenticatedUser().subscribe({
    next: (user: UserDTO | null) => {
      if (user) {
      this.userLogged = user;
      this.loadProfileImage();
      this.loadUserTickets();
      this.isLoading = false;
    }else{
      console.error('No se encontró el usuario autenticado.');
    }
    },
    error: (err) => {
      console.error('Error loading user data:', err);
      this.isLoading = false;
    }
  });
}

  private loadProfileImage(): void {
    if (this.userLogged.image) {
      this.userService.getUserImage(this.userLogged.id).subscribe({
        next: (imageBlob: Blob) => {
          const objectUrl = URL.createObjectURL(imageBlob);
          this.profileImage = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
        },
        error: (err) => {
          console.error('Error loading profile image:', err);
          this.profileImage = this.getDefaultImagePath();
        }
      });
    } else {
      this.profileImage = this.getDefaultImagePath();
    }
  }

  private getDefaultImagePath(): string {
    return 'assets/img/ferias.png';
  }

private loadUserTickets(): void {
  if (!this.userLogged?.name) {
    console.error('Nombre de usuario no disponible');
    this.ticketError = 'No se pudo identificar al usuario';
    return;
  }

  this.loadingTickets = true;
  this.ticketError = '';

  this.ticketService.getTicketsByUserOwner(this.userLogged.name).subscribe({
    next: (tickets: TicketDTO[]) => {
      this.tickets = tickets;
      this.loadEventImagesForTickets();
      this.loadingTickets = false;
    },
    error: (err: HttpErrorResponse) => {
      console.error('Error cargando tickets:', err);
      this.loadingTickets = false;
      this.ticketError = err.status === 404 
        ? 'No se encontraron tickets' 
        : 'Error al cargar los tickets';
    }
  });
}


  deleteTicket(id: number, event: Event): void {
    event.preventDefault();
    event.stopPropagation();
    
    if (confirm('¿Estás seguro de que quieres eliminar este ticket?')) {
      this.ticketService.deleteTicket(id).subscribe({
        next: () => {
          this.tickets = this.tickets.filter(ticket => ticket.id !== id);
        },
        error: (err: any) => {
          console.error('Error deleting ticket:', err);
          alert('No se pudo eliminar el ticket');
        }
      });
    }
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('image', file);
      
      if (this.userLogged.image) {
        this.userService.replaceUserImage(this.userLogged.id, formData).subscribe({
          next: () => {
            this.loadProfileImage();
          },
          error: (err) => {
            console.error('Error updating profile image:', err);
          }
        });
      } else {
        this.userService.createUserImage(this.userLogged.id, formData).subscribe({
          next: () => {
            this.userLogged.image = true;
            this.loadProfileImage(); 
          },
          error: (err) => {
            console.error('Error uploading profile image:', err);
          }
        });
      }
    }
  }

  initEditForm(): void {
    this.editProfileForm.patchValue({
      name: this.userLogged.name,
      email: this.userLogged.email,
    });
    this.editSuccess = false;
    this.editError = false;
  }

  onSubmitEditProfile(): void {
    if (this.editProfileForm.invalid) {
      return;
    }
  
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
  
    this.isSubmitting = true;
    const userData: CreateRequestUserDTO = {
      name: this.editProfileForm.value.name,
      email: this.editProfileForm.value.email,
      password: this.editProfileForm.value.password || undefined,
      id: null,
      phone: '',
      roles: ["ROLE_USER"] 
    };
  
    this.userService.editUser(this.userLogged.id, userData).subscribe({
      next: (updatedUser) => {
      },
      error: (err) => {
        console.error('Error updating profile:', err);
        this.isSubmitting = false;
        this.editError = true;
        
        if (err.status === 401) {
          this.authService.logout();
          this.router.navigate(['/login']);
        }
      }
    });
  }

  logout(event: Event) {
    event.preventDefault();
    this.authService.logout();
    this.authStateService.setAuthenticated(false);
    this.router.navigate(['/']);
  }

  // En profile.component.ts
  private loadEventImagesForTickets(): void {
    this.tickets.forEach(ticket => {
      if (ticket.eventId) {
        this.eventService.getEventImage(ticket.eventId).subscribe({
          next: (imageBlob: Blob) => {
            const objectUrl = URL.createObjectURL(imageBlob);
            ticket.eventImage = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
          },
          error: (err) => {
            console.error('Error loading event image for ticket:', ticket.id, err);
            ticket.eventImage = this.getDefaultTicketImage(ticket.category);
          }
        });
      } else {
        ticket.eventImage = this.getDefaultTicketImage(ticket.category);
      }
    });
  }

  getTicketImage(ticket: TicketDTO): SafeUrl | string {
    if (ticket.eventImage) {
      return ticket.eventImage;
    }
    return this.getDefaultTicketImage(ticket.category);
  }

  private getDefaultTicketImage(category: string): string {
    return category === 'Conciertos' ? '/assets/img/concertpek.jpg' : '/assets/img/ferias.png';
  }
}