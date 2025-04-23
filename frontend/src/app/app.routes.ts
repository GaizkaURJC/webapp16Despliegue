import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './pages/home/home.component'; // Ajusta esta ruta según tu estructura real
import { ErrorComponent } from './shared/error/error.component'; //
//  Ajusta esta ruta según tu estructura real
export const routes: Routes = [

      { path: '', component: HomeComponent },    // Respuesta a /new
      { path: 'home', component: HomeComponent }, // Respuesta a /new/home
      { path: 'error', component: ErrorComponent }, // Respuesta a /new/error
  { path: '**', redirectTo: 'error' } // Redirige rutas no encontradas
];
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
  declarations:[
                
                ], // Asegúrate de declarar los componentes aquí si no son standalone
  providers: [],
  bootstrap:[] // Puedes agregar servicios aquí si es necesario
})
export class AppRoutingModule { }