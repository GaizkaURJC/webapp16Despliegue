// login.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthStateService } from './auth-state.service'; // Importa el servicio de estado de autenticación
import { tap } from 'rxjs/operators'; // O esta alternativa
import { UserDTO } from '../dtos/user.dto';

interface LoginResponse {
  token: string;
  user: UserDTO;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth'; 
  private URL= 'https://localhost:8443/api/v1/users';
  private readonly API_URL = '/api/auth';
  private userUrl = '/api/v1/users';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authState: AuthStateService
  ) { }

  getCsrfToken(): Observable<any> {
    return this.http.get('/api/csrf-token');
  }

  login(email: string, password: string): Observable<LoginResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { email, password },{headers}).pipe(
      tap((response: LoginResponse) => {
        console.log('Respuesta del login:', response);
        if (response?.token) {
          localStorage.setItem('token', response.token);
          if (response?.user) {
            localStorage.setItem('currentUser', JSON.stringify(response.user));
            this.authState.setAuthenticated(true, response.user);
          }
        }
      }),
      catchError(error => {
        console.error('Error en el login:', error);
        return throwError(() => error);
      })
    );
  }

  private fetchCurrentUser(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.userUrl}/me`).pipe(
      tap((user: UserDTO) => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.authState.setAuthenticated(true, user);
      })
    );
  }


  loginAfterRegister(email: string, password: string): Observable<any> {
    return this.login(email, password);
  }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.URL}/`, userData).pipe(
      catchError(error => {
        return throwError(() => error);
      })
    );
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.authState.setAuthenticated(false);
    this.router.navigate(['/']);
  }
}



