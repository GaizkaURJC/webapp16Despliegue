import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CommentService } from '../../services/comment.service';
import { UserService } from '../../services/user.service';
import { CommentDTO } from '../../dtos/comment.dto';
import { AuthStateService } from '../../services/auth-state.service'; // Añadir este import
import { UserDTO } from '../../dtos/user.dto';

@Component({
  selector: 'app-comment-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './comment-modal.component.html',
  styleUrls: ['./comment-modal.component.css']
})
export class CommentModalComponent implements OnInit {
  comentario: string = '';
  rate: number = 0;
  eventId: number = 0;
  username: string = '';
  userId!: number; // Recibirá el ID del usuario

  constructor(
    public activeModal: NgbActiveModal,
    private commentService: CommentService,
    private userService: UserService,
    private authStateService: AuthStateService
  ) {}

  ngOnInit(): void {
    this.loadUserData();
  }

  private loadUserData(): void {

    this.authStateService.getAuthenticatedUser().subscribe({
      next: (user) => {
        this.userId = user.id; 
        this.username = user.name; 
      },
      error: (err) => {
        console.error('Error al obtener datos del usuario:', err);
        alert('No se pudo obtener la información del usuario. Por favor, inicia sesión.');
        this.activeModal.dismiss();
      }
    });
  }
  
  loadUsername(): void {
    this.userService.getUserById(this.userId).subscribe({
      next: (user) => {
        this.username = user.name;
      },
      error: (err) => {
        console.error('Error al obtener el nombre de usuario:', err);
        alert('No se pudo obtener el nombre de usuario. Por favor, verifica tu sesión.');
      }
    });
  }

  closeModal() {
    this.activeModal.dismiss();
  }

  submitComment() {
    const newComment: CommentDTO = {
      username: this.username, 
      comentario: this.comentario,
      eventId: this.eventId,
      rate: this.rate
    };
  
    this.commentService.createComment(newComment).subscribe({
      next: (response) => {
        console.log('Comentario publicado:', response);
        this.activeModal.close(response); 
      },
      error: (err) => {
        console.error('Error al publicar el comentario:', err);
        alert('Hubo un error al publicar el comentario. Por favor, inténtalo de nuevo.');
      }
    });
  }
}