import { Component } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  // Aquí puedes definir la lógica y propiedades de tu componente
  // Por ejemplo, puedes tener un título o una lista de elementos para mostrar
  title2 = 'Home Page';
  items = ['Item 1', 'Item 2', 'Item 3'];
}
