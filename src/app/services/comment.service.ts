import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentDTO } from '../dtos/comment.dto';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiURL = 'https://localhost:443/api/v1/comments'; 

  constructor(private http: HttpClient) {}


  getAllComments(): Observable<CommentDTO[]> {
    return this.http.get<CommentDTO[]>(`${this.apiURL}/`);
  }

  getCommentById(id: number): Observable<CommentDTO> {
    return this.http.get<CommentDTO>(`${this.apiURL}/${id}`);
  }

  createComment(comment: CommentDTO): Observable<CommentDTO> {
    return this.http.post<CommentDTO>(`${this.apiURL}/`, comment, { 
      headers: this.getAuthHeaders() 
    });
  }

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`, {
      headers: this.getAuthHeaders()});
  }

  replaceComment(id: number, updatedComment: CommentDTO): Observable<CommentDTO> {
    return this.http.put<CommentDTO>(`${this.apiURL}/${id}`, updatedComment);
  }

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