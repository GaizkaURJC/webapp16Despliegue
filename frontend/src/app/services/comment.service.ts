import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentDTO } from '../dtos/comment.dto';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiURL = 'https://localhost:8443/api/v1/comments'; // URL base para los comentarios

  constructor(private http: HttpClient) {}

  // Método para obtener todos los comentarios
  getAllComments(): Observable<CommentDTO[]> {
    return this.http.get<CommentDTO[]>(`${this.apiURL}/`);
  }

  // Método para obtener un comentario por ID
  getCommentById(id: number): Observable<CommentDTO> {
    return this.http.get<CommentDTO>(`${this.apiURL}/${id}`);
  }

  // Método para crear un nuevo comentario
  createComment(comment: CommentDTO): Observable<CommentDTO> {
    return this.http.post<CommentDTO>(`${this.apiURL}/`, comment, { 
      headers: this.getAuthHeaders() 
    });
  }

  // Método para eliminar un comentario por ID
  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`, {
      headers: this.getAuthHeaders()});
  }

  // Método para reemplazar un comentario existente
  replaceComment(id: number, updatedComment: CommentDTO): Observable<CommentDTO> {
    return this.http.put<CommentDTO>(`${this.apiURL}/${id}`, updatedComment);
  }

  // Método para obtener comentarios por el ID del evento
  getCommentsByEventId(eventId: number): Observable<CommentDTO[]> {
    return this.http.get<CommentDTO[]>(`${this.apiURL}/event/${eventId}`);
  }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('No authentication token found');
    }
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  
}