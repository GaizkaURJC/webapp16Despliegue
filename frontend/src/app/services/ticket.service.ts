import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TicketDTO } from '../dtos/ticket.dto';
import { SafeUrl } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
    private apiUrl = 'https://localhost:8443/api/v1/tickets/';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getUserTickets(): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}MyTickets`, {
      headers: this.getHeaders()
    });
  }

  getTicketsByUserOwner(userOwner: string): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(
      `${this.apiUrl}userOwner/${encodeURIComponent(userOwner)}`,  
      { headers: this.getHeaders() }
    );
  }

  deleteTicket(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}MyTicket/${id}`);
  }

  createTicket(ticketData: any): Observable<any> {
    return this.http.post(this.apiUrl, ticketData, { 
      headers: this.getHeaders() 
    });
  }


}