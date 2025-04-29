import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { EventDTO, EventWithImageDTO} from "../dtos/event.dto";

@Injectable({
    providedIn: 'root'
})
export class EventService{
    private apiURL= 'api/v1/events';
    private apiURLType= 'https://localhost:8443/api/v1/events';

    constructor(private http: HttpClient) { }

    getEventsWhithImages(page: number = 0, size:number=3): Observable<PageResponse<EventWithImageDTO>> {
        return this.http.get<PageResponse<EventWithImageDTO>>(`${this.apiURL}/images?page=${page}&size=${size}`);
    }
    getEventImage(id: number): Observable <Blob> {
        return this.http.get(`${this.apiURLType}/${id}/image`, { responseType: 'blob' });
    }
    getEventsByType(type:string): Observable<EventDTO[]>{
        return this.http.get<EventDTO[]>(`${this.apiURLType}/type/${type}`);
    }
    getEventById(id: number): Observable<EventDTO> {
        return this.http.get<EventDTO>(`${this.apiURLType}/${id}`);
      }
      getAllEventsWithImages(): Observable<EventWithImageDTO[]> {
        return this.http.get<EventWithImageDTO[]>(`${this.apiURLType}/with-images`);
      }
      getEventsWithImagesPaginated(page: number = 0, size: number = 4): Observable<PageResponse<EventWithImageDTO>> {
        return this.http.get<PageResponse<EventWithImageDTO>>(
          `${this.apiURL}/with-images?page=${page}&size=${size}`
        );
      }

      deleteEvent(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiURLType}/${id}`, {
          headers: this.getAuthHeaders()});
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
interface PageResponse<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
}