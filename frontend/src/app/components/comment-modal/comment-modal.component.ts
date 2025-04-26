import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CommentService } from '../../services/comment.service';
import { CommentDTO } from '../../dtos/comment.dto';

@Component({
  selector: 'app-comment-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './comment-modal.component.html',
  styleUrls: ['./comment-modal.component.css']
})
export class CommentModalComponent {
  comentario: string = '';
  rate: number = 0;
  eventId: number = 0; // Asegúrate de pasar el ID del evento al abrir el modal

  constructor(
    public activeModal: NgbActiveModal,
    private commentService: CommentService // Inyecta el servicio
  ) {}

  closeModal() {
    this.activeModal.dismiss();
  }

  submitComment() {
    const newComment: CommentDTO = {
      id: 0, 
      username: 'UsuarioActual', 
      comentario: this.comentario,
      eventId: this.eventId,
      rate: this.rate
    };

    this.commentService.createComment(newComment).subscribe({
      next: (response) => {
        console.log('Comentario publicado:', response);
        this.activeModal.close(response); // Cierra el modal y devuelve el comentario creado
      },
      error: (err) => {
        console.error('Error al publicar el comentario:', err);
      }
    });
  }
}