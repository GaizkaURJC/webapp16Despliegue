import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './pages/home/home.component'; // Ajusta esta ruta según tu estructura real
import { ErrorComponent } from './error/error.component'; //
//  Ajusta esta ruta según tu estructura real
export const routes: Routes = [

      { path: '', component: HomeComponent },    // Respuesta a /new
      { path: 'home', component: HomeComponent }, // Respuesta a /new/home
      { path: 'error', component: ErrorComponent }, // Respuesta a /new/error
  { path: '**', redirectTo: 'error' } // Redirige rutas no encontradas
];
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }