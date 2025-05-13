import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { take } from 'rxjs/operators';
import { HeaderComponent } from '../../shared/header/header.component';
import { LoginModalComponent } from '../../components/login-modal/login-modal.component';
import { CommentModalComponent } from '../../components/comment-modal/comment-modal.component';
import { BuyModalComponent } from '../../components/buy-modal/buy-modal.component';
import { EventService } from '../../services/event.service';
import { CommentService } from '../../services/comment.service';
import { AuthService } from '../../services/login.service';
import { AuthStateService } from '../../services/auth-state.service';
import { Router } from '@angular/router';
import { EventDTO } from '../../dtos/event.dto';
import { CommentDTO } from '../../dtos/comment.dto';
import { UserDTO } from '../../dtos/user.dto';

@Component({
  selector: 'app-concert',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    HeaderComponent,
    LoginModalComponent
  ],
  templateUrl: './concert.component.html',
  styleUrls: ['./concert.component.css']
})
export class ConcertComponent implements OnInit {
  items = [
    { name: 'Home', icon: 'home', link: '/home' },
    { name: 'Clubbing', icon: 'information-circle', link: '/home', fragment: 'clubbing' },
    { name: 'Conciertos', icon: 'musical-notes', link: '/home', fragment: 'concerts' },
    { name: 'Eventos', icon: 'mail', link: '/home', fragment: 'events' },
    { name: 'Login', icon: 'people', link: '' }
  ];

  isAuthenticated = false;
  userName = '';
  imgUrl = 'assets/img/concertpek.jpg';
  event: EventDTO | null = null;
  comments: CommentDTO[] = [];

  constructor(
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private eventService: EventService,
    private commentService: CommentService,
    private authService: AuthService,
    private authState: AuthStateService,
    private router: Router
  ) {
    this.authState.isAuthenticated$.subscribe(auth => {
      this.isAuthenticated = auth;
      this.updateMenuItems();
    });
  }
    openLoginModal(event: Event): void {
    event.preventDefault();
    this.modalService.open(LoginModalComponent, { centered: true });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (!isNaN(id)) {
        this.eventService.getEventById(id).subscribe({
          next: e => (this.event = e),
          error: err => console.error(err)
        });
        this.commentService.getCommentsByEventId(id).subscribe({
          next: c => (this.comments = c),
          error: err => console.error(err)
        });
        this.eventService.getEventImage(id).subscribe({
          next: blob => (this.imgUrl = URL.createObjectURL(blob)),
          error: err => console.error(err)
        });
      }
    });
  }

  openCommentModal(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    const ref = this.modalService.open(CommentModalComponent, { centered: true, backdrop: 'static' });
    ref.componentInstance.eventId = id;
    ref.result.then(
      () => this.ngOnInit(),
      () => {}
    );
  }

  openBuyModal(): void {
    if (!this.isAuthenticated) {
      this.modalService.open(LoginModalComponent, { centered: true });
      return;
    }
    this.authState.getAuthenticatedUser()
      .pipe(take(1))
      .subscribe(
        user => {
          const ref = this.modalService.open(BuyModalComponent, { centered: true, backdrop: 'static' });
          ref.componentInstance.event = this.event;
          ref.componentInstance.token = this.authService.getToken();
          ref.componentInstance.currentUser = user!;
        },
        err => console.error(err)
      );
  }

  logout(event: Event): void {
    event.preventDefault();
    this.authService.logout();
    this.authState.setAuthenticated(false);
    this.router.navigate(['/']);
  }

  goToProfile(event: Event): void {
    event.preventDefault();
    this.router.navigate(['/profile']);
  }

  private updateMenuItems(): void {
    this.items = this.items.filter(i => i.name !== (this.isAuthenticated ? 'Login' : 'Logout'));
    if (!this.isAuthenticated) {
      this.items.push({ name: 'Login', icon: 'people', link: '' });
    }
  }
}