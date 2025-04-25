import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { EventDTO, EventWhithImageDTO} from "../dtos/event.dto";

@Injectable({
    providedIn: 'root'
})
export class EventService{
    private apiURL= 'http://localhost:8443/api/v1/events';

    constructor(private http: HttpClient) { }

    getEventsWhithImages(page: number = 0, size:number=3): Observable<PageResponse<EventWhithImageDTO>> {
        return this.http.get<PageResponse<EventWhithImageDTO>>(`${this.apiURL}/images?page=${page}&size=${size}`);
    }
    getEventImage(id: number): Observable <Blob> {
        return this.http.get(`${this.apiURL}/images/${id}`, { responseType: 'blob' });
    }
    getEventsByType(type:string): Observable<EventDTO[]>{
        return this.http.get<EventDTO[]>(`${this.apiURL}/type/${type}`);
    }
    getEventById(id: number): Observable<EventDTO> {
        return this.http.get<EventDTO>(`${this.apiURL}/${id}`);
    }
}
interface PageResponse<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
}