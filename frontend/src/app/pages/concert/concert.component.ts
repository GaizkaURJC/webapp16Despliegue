import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { HeaderComponent } from '../../shared/header/header.component';

@Component({
  selector: 'app-concert',
  standalone: true, // Añadir esta línea
  imports: [
    CommonModule,
    FormsModule,     
    HeaderComponent
  ],
  templateUrl: './concert.component.html',
  styleUrl: './concert.component.css'
})
export class ConcertComponent {
  imgUrl = "assets/img/concertpek.jpg";

  event = {
    title: 'Ejemplo Concierto',
    date: '10 de Diciembre 2025',
    price: '20€',
    type: 'Rock',
    description: 'Una noche inolvidable llena de música y emoción.',
    id: 'concert1',
    category: 'Música'
  };
  
  coments = [
    { username: 'Usuario1', comentario: '¡Increíble experiencia!', rate: 5 },
    { username: 'Usuario2', comentario: 'Buen ambiente y sonido.', rate: 4 }
  ];
  
  loginData = {
    email: '',
    password: ''
  };
  
  ticket = {
    name: '',
    dni: '',
    gender: ''
  };
  
}

