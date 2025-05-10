import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserDTO } from '../dtos/user.dto';
import { CreateRequestUserDTO } from '../dtos/requestUser.dto';

interface PageResponse<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
  }

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL: string = 'https://localhost:443/api/v1/users';
  

  constructor(private http: HttpClient) {}

  // Retrieve the current logged user info by email
  getMe(email: string): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.apiURL}/me?email=${email}`);
  }

  // Get all users
  getAllUsers(): Observable<UserDTO[]> {
    return this.http.get<PageResponse<UserDTO>>(`${this.apiURL}/`).pipe(
      map(response => response.content)
    );
  }

  // Get user by ID
  getUserById(id: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.apiURL}/${id}`);
  }

  // Create a new user; payload should match your DTO structure
  createUser(user: UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(this.apiURL, user);
  }

  // Replace/update an existing user
  replaceUser(id: number, user: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.apiURL}/${id}`, user);
  }

  // Delete a user by ID
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}`, {
      headers: this.getAuthHeaders()});
  }

  // Delete all users having a specific role, if applicable
  deleteAllUsers(): Observable<UserDTO[]> {
    return this.http.delete<UserDTO[]>(`${this.apiURL}/all`);
  }

  // Retrieve a user's image as a Blob
  getUserImage(id: number): Observable<Blob> {
    return this.http.get(`${this.apiURL}/${id}/image`, { responseType: 'blob' });
  }

  // Create/upload a user image using FormData
  createUserImage(id: number, formData: FormData): Observable<any> {
    return this.http.post(`${this.apiURL}/${id}/image`, formData);
  }

  // Replace a user image using FormData
  replaceUserImage(id: number, formData: FormData): Observable<any> {
    return this.http.put(`${this.apiURL}/${id}/image`, formData);
  }

  // Delete a user image
  deleteUserImage(id: number): Observable<any> {
    return this.http.delete(`${this.apiURL}/${id}/image`, {
      headers: this.getAuthHeaders()});
  }

  private getAuthHeaders(): HttpHeaders {
      const token = localStorage.getItem('token');
      if (!token) {
        throw new Error('No authentication token found');
      }
      return new HttpHeaders({
        Authorization: `Bearer ${token}`
      });
    }
  
  editUser(id: number, user: CreateRequestUserDTO): Observable<UserDTO> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  
    return this.http.put<UserDTO>(`${this.apiURL}/me`, user, { headers });
  }
  
}