import { Component } from '@angular/core';
@Component({
  standalone: true,
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  imports: []
})
export class FooterComponent {

  socialIcon = [
    { name: 'Facebook', icon: 'logo-facebook' },
    { name: 'Twitter', icon: 'logo-twitter' },
    { name: 'Instagram', icon: 'logo-instagram' },
    { name: 'LinkedIn', icon: 'logo-linkedin' },
    { name: 'YouTube', icon: 'logo-youtube' }
  ];
  Titulo = [{ name: "titulo", content: "Teatro Daw" },
  { name: "texto", content: "Somos una de la mayores discotecas de Madrid, con una capacidad de 6000 personas y una de las mejores ofertas de fiestas en la capital. También, ofertamos fiestas privadas y eventos de todo tipo." },
  { name: "sala", content: "Sala DEGRADEV" }]

  // Método para obtener el contenido por nombre
  getTituloContent(name: string): string | undefined {
    const item = this.Titulo.find(t => t.name === name);
    return item ? item.content : undefined;
  }

  links = [
    "Pagina principal", "Sobre Nosotros", "Artistas", "Contacta con Nosotros"
  ]

  aditionalInfo = [

    "Calle del Arenal, Madrid",
    "degradev@teatrodaw.com",
    "+34 123 456 789",
    "Horario de atención: Lunes a Viernes de 9:00 a 18:00"
  ]


}
