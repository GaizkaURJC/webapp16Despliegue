import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component'; // Ajusta ruta si es necesario

import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-clubbing',
  standalone: true,
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './clubbing.component.html',
  styleUrls: ['./clubbing.component.css']
})
export class ClubbingComponent {

  event = {
    id: 1,
    title: 'ocho&medio',
    category: 'Fiesta',
    descLinea1: 'Una experiencia sonora increíble.',
    descLinea2: 'Con los mejores DJs de la escena.'
  };

  token = '1234567890abcdef'; // Reemplaza con el valor real si lo tienes
  userLogged = {
    name: 'Alejandro'
  };

  constructor(private modalService: NgbModal) { }

  abrirModalCompra(): void {
    const modalRef = this.modalService.open(BuyModalComponent, {
      centered: true,
      backdrop: 'static'
    });

    modalRef.componentInstance.event = this.event;
    modalRef.componentInstance.token = this.token;

    modalRef.result.then(
      result => {
        console.log('Compra confirmada:', result);
      },
      reason => {
        console.log('Modal cerrado sin comprar');
      }
    );
  }
}
