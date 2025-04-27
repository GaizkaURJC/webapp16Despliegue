import { SafeUrl } from "@angular/platform-browser";

export interface TicketDTO {
    id: number;
    title: string;
    category: string;
    ticketDate: Date;
    eventId: number;
    eventImage?: SafeUrl; // Campo para almacenar la imagen del evento
    // ... otros campos que ya tengas
  }