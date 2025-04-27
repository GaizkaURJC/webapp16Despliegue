import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookingDTO } from '../dtos/booking.dto';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private apiUrl: string = 'https://localhost:8443/api/v1/bookings';

  constructor(private http: HttpClient) {}

  // GET /api/v1/bookings/
  getAllBookings(): Observable<BookingDTO[]> {
    return this.http.get<BookingDTO[]>(`${this.apiUrl}/`, { 
      headers: this.getAuthHeaders() 
    });
  }

  // GET /api/v1/bookings/{id}
  getBooking(id: number): Observable<BookingDTO> {
    return this.http.get<BookingDTO>(`${this.apiUrl}/${id}`);
  }

  // POST /api/v1/bookings/
  createBooking(booking: BookingDTO): Observable<BookingDTO> {
    return this.http.post<BookingDTO>(`${this.apiUrl}/`, booking);
  }

  // PUT /api/v1/bookings/{id}
  updateBooking(id: number, booking: BookingDTO): Observable<BookingDTO> {
    return this.http.put<BookingDTO>(`${this.apiUrl}/${id}`, booking);
  }

  // DELETE /api/v1/bookings/{id}
  deleteBooking(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders() });
  }

  acceptBooking(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/accept/${id}`, null, {
      headers: this.getAuthHeaders() });
  }

  rejectBooking(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/reject/${id}`, null, {
      headers: this.getAuthHeaders() });
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