import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
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
    private eventService: EventService,
    private commentService: CommentService 
  ) {}

  ngOnInit() {   // Por ejemplo se coge 6, cuando este todo se asigna el numero que haga falta
    const eventId = 6; 
    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err) => {
        console.error('Error al obtener el evento:', err);
      }
    });

    this.commentService.getCommentsByEventId(eventId).subscribe({
      next: (data) => {
        this.comments = data;
      },
      error: (err) => {
        console.error('Error al obtener los comentarios:', err);
      }
    });
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