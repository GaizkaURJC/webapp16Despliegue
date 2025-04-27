import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component'; // Ajusta ruta si es necesario

import { FooterComponent } from '../../shared/footer/footer.component';
import { EventService } from '../../services/event.service';
import { EventDTO } from '../../dtos/event.dto';
import { GenderChartComponent } from '../../components/gender-chart/gender-chart.component'

@Component({
  selector: 'app-clubbing',
  standalone: true,
  imports: [FooterComponent, GenderChartComponent, NgIf],
  templateUrl: './clubbing.component.html',
  styleUrls: ['./clubbing.component.css']
})

export class ClubbingComponent {
  imgUrl = "assets/img/ochoymedio.jpg";
  event: EventDTO | null = null;

  constructor(
    private modalService: NgbModal,
    private eventService: EventService
  ) { }

  abrirModalCompra(): void {
    const modalRef = this.modalService.open(BuyModalComponent, {
      centered: true,
      backdrop: 'static'
    });

    modalRef.result.then(
      result => {
        console.log('Compra confirmada:', result);
      },
      reason => {
        console.log('Modal cerrado sin comprar');
      }
    );
  }

  ngOnInit() {
    const eventId = 1;
    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err) => {
        console.error('Error al obtener el evento:', err);
      }
    });

    // Cargar la imagen del evento
    this.eventService.getEventImage(eventId).subscribe({
      next: (blob) => {
        const objectURL = URL.createObjectURL(blob); // Crear una URL para el Blob
        this.imgUrl = objectURL; // Asignar la URL a imgUrl
      },
      error: (err) => {
        console.error('Error al obtener la imagen del evento:', err);
      }
    });
  }

}
