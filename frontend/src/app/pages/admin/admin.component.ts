import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonIcon } from '@ionic/angular/standalone';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';
import { CommentService } from '../../services/comment.service';
import { BookingService } from '../../services/booking.service'; 
import { wine, musicalNotes, people, construct, chatbubble, logIn } from 'ionicons/icons';
import { addIcons } from 'ionicons';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, IonIcon],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  activeSection: string = 'clubbing';

  parties: any[] = [];
  concerts: any[] = [];
  bookings: any[] = [];
  comments: any[] = [];
  users: any[] = [];

  showSection(section: string): void {
    console.log('Changing section to:', section);
      this.activeSection = section;
  }

  constructor(
    private modalService: NgbModal,
    private eventService: EventService,
    private userService: UserService,
    private commentService: CommentService,
    private bookingService: BookingService
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

  ngOnInit(): void {
    this.eventService.getEventsByType("party").subscribe(data => this.parties = data);
    this.eventService.getEventsByType("concert").subscribe(data => this.concerts = data);
    this.userService.getAllUsers().subscribe(data => this.users = data);
    this.commentService.getAllComments().subscribe(data => this.comments = data);
    this.bookingService.getAllBookings().subscribe(data => this.bookings = data);
    
  }
}
