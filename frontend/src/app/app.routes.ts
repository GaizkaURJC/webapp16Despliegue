import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './pages/home/home.component'; // Ajusta esta ruta según tu estructura real
import { ErrorComponent } from './shared/error/error.component'; //
import {  HttpClientModule } from '@angular/common/http';
//  Ajusta esta ruta según tu estructura real
export const routes: Routes = [

      { path: '', component: HomeComponent },    // Respuesta a /new
      { path: 'home', component: HomeComponent }, // Respuesta a /new/home
      { path: 'error', component: ErrorComponent }, // Respuesta a /new/error
  { path: '**', redirectTo: 'error' } // Redirige rutas no encontradas
];

export class AppRoutingModule { }