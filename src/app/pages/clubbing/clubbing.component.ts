import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component'; // Ajusta ruta si es necesario
import { UserDTO } from '../../dtos/user.dto';
import { FooterComponent } from '../../shared/footer/footer.component';
import { EventService } from '../../services/event.service';
import { EventDTO } from '../../dtos/event.dto';
import { AuthStateService } from '../../services/auth-state.service';
import { AuthService } from '../../services/login.service';
import { RouterModule } from '@angular/router';
import { menuOutline, timeOutline ,locationOutline, phonePortraitSharp, add } from 'ionicons/icons';
import { addIcons } from 'ionicons';
import { IonIcon } from '@ionic/angular/standalone';
import { BaseChartDirective,  } from 'ng2-charts';
import { ChartData, ChartType, Chart, ArcElement, Tooltip, Legend, PieController } from 'chart.js';
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import { TicketDTO } from '../../dtos/ticket.dto';
Chart.register(ArcElement, Tooltip, Legend, PieController);

import JsPDF from 'jspdf'

@Component({
  selector: 'app-clubbing',
  standalone: true,
  imports: [FooterComponent, NgIf, RouterModule, IonIcon, BaseChartDirective],
  templateUrl: './clubbing.component.html',
  styleUrls: ['./clubbing.component.css']
})

export class ClubbingComponent implements OnInit {
  imgUrl = "assets/img/ochoymedio.jpg";
  event: EventDTO | null = null;

  public pieChartType: ChartType = 'pie';
  public pieChartData: ChartData<'pie', number[], string> = {
    labels: [],
    datasets: [{ data: [] }]
  };

  constructor(
    private http: HttpClient,
    private modalService: NgbModal,
    private eventService: EventService
    , private authState: AuthStateService,
    private auth: AuthService
  ) { 
    addIcons({
      menuOutline,
      timeOutline,
      locationOutline,
      phonePortraitSharp,
      
  });
}

  abrirModalCompra(): void {
    const modalRef = this.modalService.open(BuyModalComponent, {
      centered: true,
      backdrop: 'static'
    });
  
    modalRef.componentInstance.event = this.event;
    modalRef.componentInstance.token = this.auth.getToken(); 
    this.authState.getAuthenticatedUser().subscribe({
      next: (user: UserDTO | null) => {
        if (user) {
          modalRef.componentInstance.currentUser = { name: user.name }; // Reemplaza con el usuario real
        } else {
          console.error('No authenticated user found');
        }
      },
      error: (err) => {
        console.error('Error fetching authenticated user:', err);
      }
    });
  
    modalRef.result.then(
      result => {
        console.log('Compra confirmada:', result);
        this.generatePDF(result)
      },
      reason => {
        console.log('Modal cerrado sin comprar');
      }
    );
  }

  generatePDF(ticketData: any): void {
  const doc = new JsPDF();

  doc.setFontSize(18);
  doc.text('Confirmación de Compra de Entrada', 10, 10);

  doc.setFontSize(12);
  doc.text(`Nombre: ${ticketData.ticketName}`, 10, 30);
  doc.text(`DNI: ${ticketData.dni}`, 10, 40);
  doc.text(`Género: ${ticketData.gender}`, 10, 50);
  doc.text(`Evento: ${this.event?.title}`, 10, 60);
  doc.text(`Categoría: ${this.event?.category}`, 10, 70);
  doc.text(`Fecha de Compra: ${new Date().toLocaleDateString()}`, 10, 80);

  // Descargar el PDF
  doc.save(`entrada_${ticketData.ticketName}.pdf`);
}


  

  ngOnInit() {
    const eventId = 1;
    this.eventService.getEventById(eventId).subscribe({
      next: (data) => {
        this.event = data;
        this.loadGenderDistribution(data.title); // Cargar la distribución de género
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

  private loadGenderDistribution(title: string): void {
    const t = encodeURIComponent(title);
    forkJoin({
      male: this.http.get<TicketDTO[]>(`https://localhost:8443/api/v1/tickets/gender/Hombre/${t}`),
      female: this.http.get<TicketDTO[]>(`https://localhost:8443/api/v1/tickets/gender/Mujer/${t}`)
    }).subscribe(
      ({ male, female }) => {
        this.pieChartData = {
          labels: ['Hombres', 'Mujeres'],
          datasets: [{ data: [male.length, female.length],
            backgroundColor: ['yellow', 'black'],
           }]
        };
      },
      err => console.error('Error loading gender distribution', err)
    );
  }

}
