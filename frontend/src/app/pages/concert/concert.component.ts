import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { CommentModalComponent } from '../../components/comment-modal/comment-modal.component';
import { HeaderComponent } from '../../shared/header/header.component';
import { EventService } from '../../services/event.service'; 
import { CommentService } from '../../services/comment.service'; // Importa el servicio de comentarios
import { EventDTO } from '../../dtos/event.dto';
import { CommentDTO } from '../../dtos/comment.dto';
import { AuthService } from '../../services/login.service';
import { AuthStateService } from '../../services/auth-state.service';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { Router } from '@angular/router';

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
    { name: 'Clubbing', icon: 'information-circle', link: '/spa/clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/spa/concerts' },
    { name: 'Eventos', icon: 'mail', link: '/events' },
    { name: 'Contactanos', icon: 'party', link: '/contact' },
    { name: 'Login', icon: 'people', link: '' }
  ];

  
  isAuthenticated = false;


  imgUrl = "assets/img/concertpek.jpg";
  event: EventDTO | null = null; 
  comments: CommentDTO[] = [];

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
      this.updateMenuItems();
    });
  }



  ngOnInit() {   // Por ejemplo se coge 6, cuando este todo se asigna el numero que haga falta
    const concertId = Number(this.route.snapshot.paramMap.get('id'));
    this.eventService.getEventById(concertId).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err) => {
        console.error('Error al obtener el evento:', err);
      }
    });

    this.commentService.getCommentsByEventId(concertId).subscribe({
      next: (data) => {
        this.comments = data;
      },
      error: (err) => {
        console.error('Error al obtener los comentarios:', err);
      }
    });

    // Cargar la imagen del evento
    this.eventService.getEventImage(concertId).subscribe({
      next: (blob) => {
        const objectURL = URL.createObjectURL(blob); // Crear una URL para el Blob
        this.imgUrl = objectURL; // Asignar la URL a imgUrl
      },
      error: (err) => {
        console.error('Error al obtener la imagen del evento:', err);
      }
    });
  }
  
  openCommentModal() {
    const modalRef = this.modalService.open(CommentModalComponent, {
      centered: true,
      backdrop: 'static'
    });
  
    modalRef.componentInstance.eventId = 6;
  
    modalRef.result.then(
      (result) => {
        console.log('Comentario enviado:', result);
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
      // Filtra Login y añade Logout si no existe
      this.items = this.items.filter(item => item.name !== 'Login');
      if (!this.items.some(item => item.name === 'Logout')) {
        this.items.push({ name: 'Logout', icon: 'log-out', link: '' });
      }
    } else {
      // Filtra Logout y añade Login si no existe
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

}