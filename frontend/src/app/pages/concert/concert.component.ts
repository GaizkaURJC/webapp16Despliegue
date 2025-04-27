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


@Component({
  selector: 'app-concert',
  standalone: true, 
  imports: [
    CommonModule,
    FormsModule,     
    HeaderComponent
  ],
  templateUrl: './concert.component.html',
  styleUrls: ['./concert.component.css']
})

export class ConcertComponent implements OnInit {
  imgUrl = "assets/img/concertpek.jpg";
  event: EventDTO | null = null; 
  comments: CommentDTO[] = [];

  constructor(
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private eventService: EventService,
    private commentService: CommentService 
  ) {}

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
  

}