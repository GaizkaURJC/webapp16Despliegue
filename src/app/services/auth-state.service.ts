import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../dtos/user.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private currentUserSubject = new BehaviorSubject<UserDTO | null>(null);

  // Cambia el nombre de currentUser$ a getAuthenticatedUser$ para mantener consistencia
  getAuthenticatedUser(): Observable<UserDTO | null> {
    return this.currentUserSubject.asObservable();
  }  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient) {
    this.checkInitialAuthState();
  }

  private checkInitialAuthState(): void {
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('currentUser');
    
    if (token && userData) {
      try {
        const user: UserDTO = JSON.parse(userData);
        this.setAuthenticated(true, user);
      } catch (e) {
        console.error('Error parsing user data:', e);
        this.setAuthenticated(false);
      }
    }
  }

  setAuthenticated(isAuth: boolean, user?: UserDTO): void {
    this.isAuthenticatedSubject.next(isAuth);
    this.currentUserSubject.next(user || null);
  }

  getCurrentUser(): UserDTO | null {
    return this.currentUserSubject.value;
  }
}