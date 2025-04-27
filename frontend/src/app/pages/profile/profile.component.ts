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

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [HeaderComponent, CommonModule, RouterModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  activeTab: string = 'perfil';
  userLogged: UserDTO = {
    id: 0,
    name: '',
    email: '',
    username: '',
    roles: [],
    phone: '',
    image: false
  };
  isLoading: boolean = true;
  profileImage: SafeUrl = '/assets/images/default-profile.png';
  tickets = [
    {
      id: 1,
      title: 'Concierto de Melendi',
      ticketDate: '15 Junio 2023',
      category: 'Concierto'
    },
    {
      id: 2,
      title: 'Festival de Verano',
      ticketDate: '20 Julio 2023',
      category: 'Festival'
    }
  ];

  constructor(
    private eventService: EventService,
    private authStateService: AuthStateService,
    private userService: UserService,
    private sanitizer: DomSanitizer,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadUserData();
  }

  showTab(tabId: string): void {
    this.activeTab = tabId;
  }

  private loadUserData(): void {
    this.authStateService.getAuthenticatedUser().subscribe({
      next: (user: UserDTO) => {
        this.userLogged = user;
        this.loadProfileImage();
        this.isLoading = false;
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
      // Use default image if user has no image
      this.profileImage = this.getDefaultImagePath();
    }
  }

  private getDefaultImagePath(): string {
    return 'assets/img/ferias.png';
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('image', file);
      
      if (this.userLogged.image) {
        // Replace existing image
        this.userService.replaceUserImage(this.userLogged.id, formData).subscribe({
          next: () => {
            this.loadProfileImage(); // Refresh the image
          },
          error: (err) => {
            console.error('Error updating profile image:', err);
          }
        });
      } else {
        // Create new image
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

  private loadTickets(): void {
    // this.ticketService.getUserTickets().subscribe(tickets => {
    //   this.tickets = tickets;
    // });
  }

  logout(event: Event) {
    event.preventDefault();
    this.authService.logout();
    this.authStateService.setAuthenticated(false);
    this.router.navigate(['/']);
  }
}