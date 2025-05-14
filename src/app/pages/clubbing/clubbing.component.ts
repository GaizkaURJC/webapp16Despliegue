import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { RouterModule, ActivatedRoute, ParamMap } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { forkJoin, take } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import JsPDF from 'jspdf';
import { IonIcon } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { menuOutline, timeOutline, locationOutline, phonePortraitSharp } from 'ionicons/icons';
import { BaseChartDirective } from 'ng2-charts';
import { Chart, ArcElement, Tooltip, Legend, PieController, ChartType, ChartData } from 'chart.js';

import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { EventService } from '../../services/event.service';
import { AuthStateService } from '../../services/auth-state.service';
import { AuthService } from '../../services/login.service';
import { EventDTO } from '../../dtos/event.dto';
import { TicketDTO } from '../../dtos/ticket.dto';
import { UserDTO } from '../../dtos/user.dto';

Chart.register(ArcElement, Tooltip, Legend, PieController);

@Component({
  selector: 'app-clubbing',
  standalone: true,
  imports: [
    NgIf,
    RouterModule,
    FooterComponent,
    IonIcon,
    BaseChartDirective
  ],
  templateUrl: './clubbing.component.html',
  styleUrls: ['./clubbing.component.css']
})
export class ClubbingComponent implements OnInit {
  imgUrl = 'assets/img/ochoymedio.jpg';
  event: EventDTO | null = null;
  public pieChartType: ChartType = 'pie';
  public pieChartData: ChartData<'pie', number[], string> = {
    labels: [],
    datasets: [{ data: [] }]
  };

  constructor(
    private http: HttpClient,
    private modalService: NgbModal,
    private eventService: EventService,
    private authState: AuthStateService,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {
    addIcons({ menuOutline, timeOutline, locationOutline, phonePortraitSharp });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const eventId = Number(params.get('id'));
      if (!isNaN(eventId)) {
        this.eventService.getEventById(eventId).subscribe({
          next: data => {
            this.event = data;
            this.loadGenderDistribution(data.title);
          },
          error: err => console.error('Error al obtener el evento:', err)
        });
        this.eventService.getEventImage(eventId).subscribe({
          next: blob => this.imgUrl = URL.createObjectURL(blob),
          error: err => console.error('Error al obtener la imagen del evento:', err)
        });
      } else {
        console.error('ID de evento no válido:', params.get('id'));
      }
    });
  }

  abrirModalCompra(): void {
    const modalRef = this.modalService.open(BuyModalComponent, {
      centered: true,
      backdrop: 'static'
    });
    modalRef.componentInstance.event = this.event;
    modalRef.componentInstance.token = this.authService.getToken();

    this.authState.getAuthenticatedUser()
      .pipe(take(1))
      .subscribe({
        next: (user: UserDTO | null) => {
          if (user) {
            modalRef.componentInstance.currentUser = user;
          } else {
            console.error('No authenticated user found');
          }
        },
        error: err => console.error('Error fetching authenticated user:', err)
      });

    modalRef.result.then(
      result => this.generatePDF(result),
      () => {}
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
    doc.save(`entrada_${ticketData.ticketName}.pdf`);
  }

  private loadGenderDistribution(title: string): void {
    const t = encodeURIComponent(title);
    forkJoin({
      male: this.http.get<TicketDTO[]>(`https://appWeb16.dawgis.etsii.urjc.es:443/api/v1/tickets/gender/Hombre/${t}`),
      female: this.http.get<TicketDTO[]>(`https://appWeb16.dawgis.etsii.urjc.es:443/api/v1/tickets/gender/Mujer/${t}`)
    }).subscribe({
      next: ({ male, female }) => {
        this.pieChartData = {
          labels: ['Hombres', 'Mujeres'],
          datasets: [{
            data: [male.length, female.length],
            backgroundColor: ['yellow', 'black']
          }]
        };
      },
      error: err => console.error('Error loading gender distribution', err)
    });
  }
}