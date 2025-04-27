import { Component, OnInit } from '@angular/core';
import { ChartData, ChartOptions } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-gender-chart',
  templateUrl: './gender-chart.component.html',
  imports: [BaseChartDirective],
  styleUrls: ['./gender-chart.component.css'],
  standalone: true
})

export class GenderChartComponent implements OnInit {
  // Datos de la gráfica
  public genderChartData: ChartData<'pie'> = {
    labels: ['Hombre', 'Mujer'],  // etiquetas de la gráfica
    datasets: [
      {
        data: [70, 30],  // estos datos los reemplazas con las variables de tu backend
        backgroundColor: ['#f2f526', 'black'],  // colores
        borderColor: ['#f2f526', 'black'],
        borderWidth: 1
      }
    ]
  };

  // Opciones para la gráfica
  public genderChartOptions: ChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom'
      },
      title: {
        display: true,
        text: 'Distribución por género'
      }
    }
  };

  constructor() { }

  ngOnInit(): void {
    // Aquí puedes cargar los datos desde tu backend si es necesario
  }

}
