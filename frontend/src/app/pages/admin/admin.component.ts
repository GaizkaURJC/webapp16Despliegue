import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonIcon } from '@ionic/angular/standalone';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { wine, musicalNotes, people, construct, chatbubble, logIn } from 'ionicons/icons';
import { addIcons } from 'ionicons';
import { logoIonic } from 'ionicons/icons';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, IonIcon],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  activeSection: string = 'clubbing';

  showSection(section: string): void {
    console.log('Changing section to:', section);
      this.activeSection = section;
  }

  constructor(
    private modalService: NgbModal
  ) {
    addIcons({
      wine,
      musicalNotes,
      people,
      construct,
      chatbubble,
      logIn,
    });
  }
}
