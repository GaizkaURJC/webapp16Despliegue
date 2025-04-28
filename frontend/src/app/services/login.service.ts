// login.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'https://localhost:8443/api/auth'; 
  private URL= 'https://localhost:8443/api/v1/users'

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { email, password }).pipe(
      catchError(error => {
        return throwError(() => error);
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
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}