import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { CommentModalComponent } from '../../components/comment-modal/comment-modal.component';
import { HeaderComponent } from '../../shared/header/header.component';
import { EventService } from '../../services/event.service'; 
import { CommentService } from '../../services/comment.service';
import { EventDTO } from '../../dtos/event.dto';
import { CommentDTO } from '../../dtos/comment.dto';
import { AuthService } from '../../services/login.service';
import { AuthStateService } from '../../services/auth-state.service';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { Router } from '@angular/router';
import { UserDTO } from '../../dtos/user.dto'; 
import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component';


@Component({
  selector: 'app-concert',
  standalone: true, 
  imports: [
    CommonModule,
    FormsModule,     
    HeaderComponent,
    LoginModalComponent
  ],
  templateUrl: './concert.component.html',
  styleUrls: ['./concert.component.css']
})

export class ConcertComponent implements OnInit {
  items = [
    { name: 'Home', icon: 'home', link: '/' },
    { name: 'Clubbing', icon: 'information-circle', link: '/' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/' },
    { name: 'Eventos', icon: 'mail', link: '/events' },
    { name: 'Contactanos', icon: 'party', link: '/' },
    { name: 'Login', icon: 'people', link: '' }
  ];

  userLogged: UserDTO | null = null;
  isLoadingUser = false;
  isAuthenticated = false;
  userName: string = '';

  imgUrl = "assets/img/concertpek.jpg";
  event: EventDTO | null = null; 
  comments: CommentDTO[] = [];
  username: string = '';
  userId!: number;

  constructor(
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private eventService: EventService,
    private commentService: CommentService ,
    private authService: AuthService,
    private authState: AuthStateService,
    private router: Router
  ) {
    this.authState.isAuthenticated$.subscribe(auth => {
      this.isAuthenticated = auth;
      if (auth) {
        this.loadUserData(); 
      } else {
        this.userName = '';
      }
      this.updateMenuItems();
    });
  }
  
  ngOnInit() {
    this.loadEventData();
  }

  private loadEventData(): void {
    const concertId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadEvent(concertId);
    this.loadComments(concertId);
    this.loadEventImage(concertId);
  }

  private loadUserData(): void {
    this.isLoadingUser = true;
    this.authState.getAuthenticatedUser().subscribe({
      next: (user: UserDTO | null) => {
        if (user) {
          this.userId = user.id;
          this.username = user.name;
        } else {
          console.error('User is null');
        }
      },
      error: (err) => {
        console.error('Error loading user data:', err);
        this.isLoadingUser = false;
      }
    });
  }

  private loadEvent(eventId: number): void {
    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err) => {
        console.error('Error al obtener el evento:', err);
      }
    });
  }

  private loadComments(eventId: number): void {
    this.commentService.getCommentsByEventId(eventId).subscribe({
      next: (data) => {
        this.comments = data;
      },
      error: (err) => {
        console.error('Error al obtener los comentarios:', err);
      }
    });
  }

  private loadEventImage(eventId: number): void {
    this.eventService.getEventImage(eventId).subscribe({
      next: (blob) => {
        const objectURL = URL.createObjectURL(blob);
        this.imgUrl = objectURL;
      },
      error: (err) => {
        console.error('Error al obtener la imagen del evento:', err);
      }
    });
  }

  openCommentModal() {
    const concertId = Number(this.route.snapshot.paramMap.get('id'));
    const modalRef = this.modalService.open(CommentModalComponent, {
      centered: true,
      backdrop: 'static'
    });
  
    modalRef.componentInstance.eventId = concertId; 
  
    modalRef.result.then(
      (result) => {
        console.log('Comentario enviado:', result);
        this.loadComments(concertId);
      },
      (reason) => {
        console.log('Modal cerrado sin enviar comentario');
      }
    );
  }

  loginData = {
    email: '',
    password: ''
  };
  
  ticket = {
    name: '',
    dni: '',
    gender: ''
  };
  

  private updateMenuItems(): void {
    if (this.isAuthenticated) {
      this.items = this.items.filter(item => item.name !== 'Login' && item.name !== 'Logout');
    } else {
      this.items = this.items.filter(item => item.name !== 'Logout');
      if (!this.items.some(item => item.name === 'Login')) {
        this.items.push({ name: 'Login', icon: 'people', link: '' });
      }
    }
  }

    openLoginModal(event: Event) {
      event.preventDefault();
      this.modalService.open(LoginModalComponent, { centered: true });
    }
  
    logout(event: Event) {
      event.preventDefault();
      this.authService.logout();
      this.authState.setAuthenticated(false);
      this.router.navigate(['/']);
    }

    goToProfile(event: Event) {
      event.preventDefault();
      this.router.navigate(['/profile']);
    }

    openBuyModal(): void {
      this.authState.getAuthenticatedUser().subscribe((user) => {
        const modalRef = this.modalService.open(BuyModalComponent, {
          centered: true,
          backdrop: 'static'
        });
    
        modalRef.componentInstance.event = this.event;
        modalRef.componentInstance.token = this.authService.getToken();
        modalRef.componentInstance.currentUser = user;
    
        modalRef.result.then(
          result => {
            console.log('Compra confirmada:', result);
          },
          reason => {
            console.log('Modal cerrado sin comprar');
          }
        );
      });
    }
    
    
}