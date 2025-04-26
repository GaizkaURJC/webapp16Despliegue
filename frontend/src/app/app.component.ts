import { Component } from '@angular/core';
import { FooterComponent } from './shared/footer/footer.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [RouterModule, FooterComponent]
})
export class AppComponent {
  public isCollapsed = true;
}