import { Component } from '@angular/core';
import { IonIcon } from '@ionic/angular/standalone';
import { logoTwitter, logoInstagram, logoYoutube, logoPinterest, logoApple } from 'ionicons/icons';
import { addIcons } from 'ionicons';

@Component({
  standalone: true,
  selector: 'app-footer',
  imports: [IonIcon],
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  
  Titulo = [{ name: "titulo", content: "Teatro Daw" },
  { name: "texto", content: "Somos una de la mayores discotecas de Madrid, con una capacidad de 6000 personas y una de las mejores ofertas de fiestas en la capital. También, ofertamos fiestas privadas y eventos de todo tipo." },
  { name: "sala", content: "Sala DEGRADEV" }]

  // Método para obtener el contenido por nombre
  getTituloContent(name: string): string | undefined {
    const item = this.Titulo.find(t => t.name === name);
    return item ? item.content : undefined;
  }

  constructor() {
    addIcons({
      logoTwitter,
      logoInstagram,
      logoYoutube,
      logoPinterest,
      logoApple
    });
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
