## *webapp16 - Teatro DAW*

Gestión de venta de entradas de clubbing, conciertos y eventos 

---

## 📌 *Preparación 1: Definición de la Aplicación*

### 🎭 *Nombre de la Aplicación*

*TEATRO DAW* - Gestión de eventos y venta de entradas para el teatro.

---
| ID  | Nombre          | Email                           | Github         | 
|-----|-----------------|---------------------------------|----------------| 
| 1   | Alberto Acebes  | a.acebes.2021@alumnos.urjc.es   | alberr22       |     
| 2   | Jon Mazcuñan    | j.mazcunan.2022@alumnos.urjc.es | jonmazh        |  
| 3   | Alejandro Rico  | a.rico.2022@alumnos.urjc.es     | ALEJANDR0-RIC0 | 
| 3   | Gaizka Arranbari| g.aranbarri.2022@alumnos.urjc.es| GaizkaURJC     | 

---
## *Entidades*

### *1. Evento*
Representa los diferentes tipos de eventos que se pueden realizar en el teatro, como clubbing, conciertos y eventos corporativos.
- *Atributos*: 
  - ID
  - Nombre
  - Tipo [Clubbing, Concierto, Evento Corporativo]
  - Fecha
  - Hora
  - Descripción
  - Precio de entrada
  - Capacidad máxima
  - Entradas por vender

### *2. Entrada*
Representa las entradas vendidas para cada evento.
- *Atributos*: 
  - ID
  - EventoID
  - Precio
  - Fecha de venta
  - Estado [Disponible, Vendida]

### *3. Usuario*
Representa a los usuarios registrados en el sistema.
- *Atributos*:
  - ID
  - Nombre
  - Email
  - Contraseña (Hash)
  - Teléfono
  - TipoUsuario [Administrador, Registrado]
  - Fecha de Registro

### *4. Reserva*
Representa las reservas realizadas para los eventos corporativos.
- *Atributos*:
  - ID
  - ClienteID
  - EventoID
  - Estado [Confirmado (Pagado), Pendiente, Cancelado]
  - Fecha de Inicio
  - Fecha de Fin

### *5. Comentarios*
Representa los comemtarios realizados por los usuarios en los eventos .
- *Atributos*:
  - Evento_ID
  - ClienteID
  - Comentario
  - Fecha_Comentario

## Diagrama entidad relación (provisional)
![erDAW drawio](https://github.com/user-attachments/assets/7ccc0ac7-46f3-494e-b808-3659a4460e7c)


---

## *Tipos de Usuario*

### *1. Usuario Anónimo/No Registrado*
- Solo puede navegar por la web para:
  - Consultar las fiestas disponibles, precios y lo que incluyen.
  - Ver los conciertos anunciados.
  - Consultar fotos de eventos realizados.
- *Acción posible*: Registro para transformarse en un usuario registrado.

### *2. Usuario Registrado*
- Debe autenticarse para:
  - Comprar entradas de cualquier tipo.
  - Dejar comentarios en eventos.
  - Consultar el historial de compras.
  - Solicitar presupuestos para eventos corporativos.

### *3. Usuario Administrador*
- Uso exclusivo para el jefe de sala.
- Puede realizar las siguientes acciones:
  - Editar información de la web.
  - Cambiar precios de entradas.
  - Añadir/eliminar eventos (clubbing, conciertos).
  - Confirmar o rechazar eventos corporativos.
  - Gestionar usuarios registrados.

---

## *Permisos de los Usuarios*

### *1. Usuario No Registrado*
- Solo tiene permisos para navegar y registrarse.

### *2. Usuario Registrado*
- Permisos:
  - Acceder y editar los datos de su cuenta.
  - Consultar historial de pedidos.
  - Borrar o editar sus comentarios en eventos.

### *3. Usuario Administrador*
- Permisos:
  - Crear, editar y eliminar eventos.
  - Confirmar o rechazar eventos corporativos.
  - Gestionar cuentas de usuarios registrados.
  - Acceso completo a las funciones de un usuario registrado.

---

## *Imágenes*
Nuestra web permitirá:
- Subida de imágenes para avatares de usuarios y artistas en conciertos.
- Subida de fotos de eventos realizados en el teatro (solo por administradores).

---

## *Gráficos*
- Gráfico de ocupación de sesiones de noche:
  - Porcentaje de ocupación.
  - Sexo de los clientes.
  - Media de edad.

---

## *Tecnología Complementaria*
- *Envío de correos electrónicos*: Confirmación de creación de cuentas.
- *Generador de PDFs*: Presupuestos para eventos corporativos.
- *Mapa interactivo*: Ubicación del teatro.

---

## *Algoritmo o Consulta Avanzada*

Nuestra aplicación contará con un sistema de recomendación de eventos para usuarios registrados.

### *Algoritmo de Recomendación*
- *Tipo*: Filtrado basado en contenido.
- *Lógica*:
  - Si el usuario ha asistido a eventos de un género musical específico, se le recomendarán eventos similares basados en descripción o tipo de evento.
- *Implementación*:
  - Usar la base de datos para almacenar las preferencias de los usuarios.
  - Consultas avanzadas para encontrar eventos similares.

--------
### *Capturas de las pantallas*
### *Capturas de pantalla*

![image](https://github.com/user-attachments/assets/4ed5001b-4c6a-4219-9af5-41d6c3fb8f88)

Comenzamos la navegacion por nuestra pagina web en la pantalla principal, aqui he os optado por la simplicidad con un fondo de la sala de fiesta, arriba encontramos un header con un background-color transparente para darle un toque de elegancia, y donde encontramos una navegacion con la libreria aos, y un login donde el usuario, en caso de no ser anonimo pueda registrarse o iniciar sesion.

![image](https://github.com/user-attachments/assets/e168634e-98a3-467f-a0d8-2bd196342f5b)

Una vez le das al icono de login, se desplegara un modal, en el que rellenando los campos de forma correcta, cada usuario podra acceder a su cuenta y poder realizar diversas funciones.

![image](https://github.com/user-attachments/assets/a04a34ee-e805-497a-af52-17b4bcf3eb96)

en caso de no tener cuenta, nuestra aplicacion te da la opcion de registrarte y asi poder obtener los permisos propios de cualquier usuario registrado. Añadiremos una cuenta especial, que sera con un correo especial, que sera la cuneta de administrador, el cual podra hacer funciones especiales y administrar la pagina.

![image](https://github.com/user-attachments/assets/9e8aa25e-17c5-4e44-9304-8bb01ea0e974)

La primera seccion de nuetra aplicacion web, encontramos la seccion de clubbing, la cual muestra las tres fiestas que se celebrar semanalmente en nuestra sala, el usuario podra elegir cualquiera de la tres para ver tematica, hora, precio, estadisticas varias, y en caso de estar registrado y loggeado, podra adquirir su entrada.

![image](https://github.com/user-attachments/assets/6393e50c-056c-4fb6-b432-12e82a4391a6)
![image](https://github.com/user-attachments/assets/ecdcd8c2-b5ba-4d1b-a822-38cf6106cdce)

En esta seccion, encontramos los conciertos disponibles, donde el usuario podra elegir filtrar entre generos musicales, o navegar y ver todos los que hay disponibles.
Si quiere consultar mas detalles de alguno de ellos, podra pinchar en el boton y consultar mas informacion, y en caso de estar registrado, comprar una o varias entradas.

![image](https://github.com/user-attachments/assets/d7817bcd-0e5d-4e08-9ae1-82750f463a6f)
![image](https://github.com/user-attachments/assets/74e46d91-c6d9-4e3d-81ac-319de482c991)
![image](https://github.com/user-attachments/assets/243dc40f-a1ca-476b-b115-984fd4d1496b)

En la parte de las pagina detalle, encontramos una foto del concierto en grande, con una descripcion y atributos del evento, ademas de un boton que te dejara comprar la entrada si estas registrado.
Abajo encontramos los comentarios de la gente, con valoraciones.

![image](https://github.com/user-attachments/assets/0a22dd24-d2ed-441d-b592-7806f45de59a) 

Por ultimo encontramos la seccion de solicitud de evento corporativo, destinado a jefes o direccion de empresas, que quieran realizar algun tipo de evento para sus trabajadores, etc.
El solicitante debera rellenar el fomrulario, y se le generara un pdf, con el presupuesto en base a lo que haya solicitado.

![image](https://github.com/user-attachments/assets/b0f6e028-e132-41a1-b089-786f811a0848)

Por ultimo encontramos un footer sencillo, con distina infomracion de la sala y la empresa que la dirige, ademas de los links a las RRSS

-------
### *Diagrama de navegación*
![Diseño sin título](https://github.com/user-attachments/assets/59eb7b9e-4f72-45a8-afc9-af67864b45ad)
Como se puede ver en el diagrama, de la página principal se puede ir al login, a la página de los conciertos o fiestas y a un form en el que se podrá solicitar un evento.
En la página de conciertos o fiestas, se va a poder comprar entradas y si se quisiese escribir un comentario.


-------
## 📌 *Práctica 1: Web con HTML generado en servidor y AJAX*
### *Diagrama de navegación actualizado*
![Diseño sin título (1)](https://github.com/user-attachments/assets/ef45f2d5-4e5f-4bb0-a01c-a4b9a78812dd)
En este nuevo diagrama de navegación se observa como ha mejorado la página respecto a la anterior fase. Empezando por la paleta de colores y el diseño de alguna de las páginas, como por la adición de nuevas páginas como la de admin y la implementación de todas las funcionalidades como por ejemplo que se vean a simple vista: el botón de cargar más y la posibilidad de añadir comentarios.

-----------
### *Instrucciones de ejecución*

1. **Abrir docker**

2. **Clonar el repositorio:**
```sh
[git clone https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-13.git](https://github.com/CodeURJC-DAW-2024-25/webapp16.git)
```

3. **Configurar la base de datos:**  
Descargar Docker   
Usuario: `root` | Contraseña: `password` |
docker run --rm -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=sala_DB -p 3306:3306 --name mySQL_sala -d mysql:8.0.33 --max-allowed-packet=256M 


4. **Compilar y ejecutar la aplicación:**  
   Abrir el Docker y meterse en la conexión creada anteriormente  
   Run Application en VSCode con Spring Boot

5. **Acceder a la aplicación:**  
Ve a [https://localhost/8080/](https://localhost:8443)

### Se necesita

- Java: JDK 21
   - Windows: https://www.oracle.com/java/technologies/downloads/#jdk21-windows
   - Linux:  
      ```sh
      sudo apt install openjdk-21   
      ```

- MySQL: v.8.0.33
   - Windows: https://dev.mysql.com/downloads/installer/
   - Linux:  
      ```sh
      sudo apt install mysql-server=8.0.33
      ```

- Maven: 4.0.0
   - Windows: https://maven.apache.org/download.cgi

- Spring Boot 3.4.2

- Docker 

- VSCode + SpringBoot


-----------
### *Diagrama de entidades de la base de datos*
A continuación se puede ver el diagrama de la base de datos, la cual ha cambiado bastante al propuesto en la preparación.
![Diagrama en blanco](https://github.com/user-attachments/assets/932d6aa6-80e1-4e3f-b251-8774ea0412b5)


## *Participación de los miembros*

Cada miembro del equipo debe indicar su contribución en la práctica.

### Jon

- Tareas realizadas

1. Implementación de la funcionalidad de tickets:
   - Los tickets ahora se generan y son descargables desde el perfil del usuario, con la información obtenida de la base de datos.

2. Integración de iconos de inicio/cierre de sesión:
   - Se añadieron iconos funcionales para las acciones de login y logout en la interfaz de usuario.

3. Desarrollo de la sección "Clubbing" desde la base de datos:
   - La sección "Clubbing" ahora muestra información obtenida directamente de la base de datos, mejorando la dinámica del contenido.

4. Creación de la página de detalle de eventos:
   - Se implementó una página de detalle para cada evento, con carga de datos e imágenes desde la base de datos.

5. Diseño de la página de detalle de conciertos con imágenes:
   - Se diseñó la página de detalle de conciertos, incluyendo la visualización de fotos asociadas a cada concierto.

---

- Commits más significativos

Cada apartado realizado está separado por varios commits, no está todo el contenido en uno solo:

- [Commit 1](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/fafc5d8b6965079724be25a99401dbce4790008a): Implementación de la funcionalidad de tickets descargables desde el perfil del usuario.
- [Commit 2](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/d8fd6e2682392401a06e761c2324cf293bf37ec1): Integración de iconos funcionales para las acciones de login y logout.
- [Commit 3](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/fb106ac40a63d7ccb4ebdfadca06bdd3ec0a9ec2): Desarrollo de la sección "Clubbing" con datos obtenidos desde la base de datos.
- [Commit 4](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/c8e9f20f67bc7819878c1d0a0bc0f7289d16c165): Creación de la página de detalle de eventos con carga de datos e imágenes desde la base de datos.
- [Commit 5](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/411272f66c614a0140d95db33c7826eab0cf3d6e): Diseño de la página de detalle de conciertos con visualización de imágenes asociadas.

---

- Ficheros en los que se ha participado

1. src/main/java/com/daw/daw/PageController.java`
   - Controlador principal que gestiona las rutas y la lógica de navegación de la aplicación.

2. `src/main/java/com/daw/daw/controller/TicketController.java`
   - Controlador encargado de la gestión y generación de tickets para los usuarios.

3. `src/main/resources/templates/paginaPerfil.html`
   - Plantilla que define la estructura y el diseño de la página de perfil de usuario.

4. `src/main/resources/templates/clubing.html`
   - Plantilla que muestra la sección "Clubbing" con información dinámica obtenida de la base de datos.

5. `src/main/java/com/daw/daw/controller/EventController.java`
   - Controlador responsable de la gestión de eventos, incluyendo la carga de datos y la interacción con la base de datos.

---
 

### Alberto

- Tareas: 

- 5 commits más significativos (cada apartado realizado está separado por varios commits, no está todo el contenido en uno solo):
[Commit 1]()
[Commit 2]()
[Commit 3]()
[Commit 4]()
[Commit 5]()

- 5 ficheros en los que se ha participado:

### Alejandro
- Tareas:
- Filtro de conciertos: filtro que muestra los conciertos por género cuando se pulsa (rock, trap, flamenco...).
- Botón de cargar más: función que carga solo 4 conciertos hasta que se pulsa nuevamente y muestra 4 más (esta función estaba implementada antes pero se tube que hacer nuevamente porque chocaba con el filtro y no funcionaba bien).
- Comprar tickets: función que tras rellenar el formulario de la compra de tickets se guarda en base de datos.
- Crear un evento: lógica y formulario para la creación de conciertos y fiestas.

- 5 commits más significativos :
Algunos commits no tienen toda la lógica o les falta un poco, pero son los principales.
- [Commit 1](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/e51855780b8dd006b455ffa7d228542ca4b88ae2): Creación de eventos
- [Commit 2](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/1f601b3f4f8c792525fd2c2773a5d079e060c5c0): Compra de tickets
- [Commit 3](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/563e9e5185734905e3853c56e1b5b6ddd2448e0f): Filtro de conciertos
- [Commit 4](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/dd7c873bb2bd2edbb2687623f356b05688ce8232): Corrección cargar más
- [Commit 5](https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/56aebd87ae00a8b4fab04a15992fedb39a7bd227): Validación del formulario
  

- 5 ficheros en los que se ha participado:

1. src/main/java/com/daw/daw/TicketController.java`
   - Controlador que se encarga de las funciones de los tickets, en este caso en concrerto crear tickets y descargarlos.

2. `src/main/java/com/daw/daw/controller/Ticket.java`
   - Modelo para las tablas de base de datos de los tickets con sus atributos y sus geters y seters.

3. 'src/main/java/com/daw/daw/EventController.java`
   - Controlador de los eventos (conciertos y fiestas) con sus funciones como crearConcierto o crearFiesta.

4. `src/main/resources/static/js/validaciones.js`
   - Archivo con el código para la validación en tiempo real de los forms de login y singup.

5. `src/main/resources/static/js/filtroConciertos.js`
   - Archivo con el código para el filtro de elementos y el cargar más.

---

### Gaizka

- Tareas: 
- Funciones del administrador: Eliminacion de usuarios y diferentes tipos de eventos, aceptacion/rechazo de entrevistas.
- Implementacion del algoritmo de recomendación de conciertos.
- Extraccion de comentarios de la base de datos.
- Grafico que muestra la distinccion por generos a la hora de comprar entradas.


- 5 commits más significativos:
- [Commit 1] (https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/d147f6db73b535571a4649636ff3eec0617d90a4): Arreglo de creación de fiestas y js para la pagina admin.
- [Commit 2] (https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/299914055fdc6f7212173c9dcc28eada53f9aad4): Se ponen todas las funciones necesarias para el administrador.
- [Commit 3] (https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/80255664eb8a58a37d3c9a9f386debb742dceef8): Ordenados los conciertos para cada usuario.
- [Commit 4] (https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/eb7caf493b3e5de35f1b2a3f6f26005a3d742d53): Grafico distribucion de genero
- [Commit 5] (https://github.com/CodeURJC-DAW-2024-25/webapp16/commit/67876d680fc87d504f6d03a9b6d78ab9b1df9df1): Admin totalmente funcional

- 5 ficheros en los que se ha participado:

1. `src/main/resources/templates/admin.html`
   - Todas las funcionalidades y redirecciones de esta pagina.

2. `src/main/java/com/daw/daw/controller/EventController.java`
   - Añadida la funcion de eliminacion de eventos, añadido datos de genero y arreglo de creacion de fiestas.

3. `src/main/java/com/daw/daw/PageController.java`
   - Implementado el algoritmo para ordenar conciertos segun preferencias.

4. `src/main/resources/template/clubing.html`
   - Añadido el grafico de distribución de genero

5. `src/main/resources/js/adminSection.js`
   -Creacion del JavaScript para gestionar las diferentes secciones de admin.

 ## DIAGRAMA DE CLASES Y TEMPLATES



![Mapa conceptual](https://github.com/user-attachments/assets/13db8b64-ddd3-4f0c-a0dc-fdf14a89559d)
<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:lucid="lucid" width="2597.48" height="805.1"><g transform="translate(215.38963339272277 -957.9676525967756)" lucid:page-tab-id="0_0"><path d="M-500 500h3000v1500H-500z" fill="#f2f3f5"/><path d="M7.4 965.47a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.028210151304137,963.7684593406179) translate(74.8994140625 49.5625)"/><path d="M444.93 965.47a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#b" transform="matrix(1,0,0,1,461.0611986460366,963.7684593406179) translate(18.71826171875 49.5625)"/><path d="M444.93 1108.68a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#c" transform="matrix(1,0,0,1,461.0611986460365,1106.986176645336) translate(50.6533203125 49.5625)"/><path d="M444.93 1538.34a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#d" transform="matrix(1,0,0,1,461.06119864603636,1536.63932855949) translate(64.02783203125 49.5625)"/><path d="M444.93 1681.56a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#e" transform="matrix(1,0,0,1,461.06119864603636,1679.857045864208) translate(35.19677734375 49.5625)"/><path d="M972.3 965.47a6 6 0 0 1 6-6h310.62a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H978.3a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#ffd9d9"/><use xlink:href="#f" transform="matrix(1,0,0,1,988.4179627892582,963.7684593597696) translate(32.59423828125 49.5625)"/><path d="M444.93 1251.9a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#g" transform="matrix(1,0,0,1,461.0611986460366,1250.203893950054) translate(47.390625 49.5625)"/><path d="M444.93 1395.12a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H450.93a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#c3f7c8"/><use xlink:href="#h" transform="matrix(1,0,0,1,461.0611986460365,1393.421611254772) translate(56.0234375 49.5625)"/><path d="M2057.95 965.47a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6h-310.65a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#fff"/><use xlink:href="#i" transform="matrix(1,0,0,1,2074.081082527144,963.7684593406179) translate(90.57373046875 49.5625)"/><path d="M2057.95 1108.68a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.65a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#fff"/><use xlink:href="#j" transform="matrix(1,0,0,1,2074.081082527144,1106.986176645336) translate(111.12109375 49.5625)"/><path d="M2057.95 1607.4a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6h-310.65a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#fff"/><use xlink:href="#k" transform="matrix(1,0,0,1,2074.081082527144,1605.6985794199138) translate(95.66455078125 49.5625)"/><path d="M2057.95 1274.92a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.65a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#fff"/><use xlink:href="#l" transform="matrix(1,0,0,1,2074.081082527144,1273.223644236862) translate(107.8583984375 49.5625)"/><path d="M2057.95 1441.16a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.65a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#fff"/><use xlink:href="#m" transform="matrix(1,0,0,1,2074.081082527144,1439.4611118283879) translate(116.4912109375 49.5625)"/><path d="M1511.78 965.47a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6h-310.64a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#b8f5ed"/><use xlink:href="#n" transform="matrix(1,0,0,1,1527.9118216193194,963.7684593406179) translate(24.7294921875 49.5625)"/><path d="M1511.78 1108.68a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.64a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#b8f5ed"/><use xlink:href="#o" transform="matrix(1,0,0,1,1527.9118216193194,1106.986176645336) translate(45.27685546875 49.5625)"/><path d="M1511.78 1607.4a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6h-310.64a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#b8f5ed"/><use xlink:href="#p" transform="matrix(1,0,0,1,1527.9118216193194,1605.6985794199138) translate(29.8203125 49.5625)"/><path d="M1511.78 1274.92a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.64a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#b8f5ed"/><use xlink:href="#q" transform="matrix(1,0,0,1,1527.9118216193194,1273.223644236862) translate(42.01416015625 49.5625)"/><path d="M1511.78 1441.16a6 6 0 0 1 6-6h310.64a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6h-310.64a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#b8f5ed"/><use xlink:href="#r" transform="matrix(1,0,0,1,1527.9118216193194,1439.4611118283879) translate(50.44384765625 49.5625)"/><path d="M978.36 1538.34a6 6 0 0 1 6-6H1295a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H984.35a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#ffd9d9"/><use xlink:href="#s" transform="matrix(1,0,0,1,994.4865101326778,1536.63932855949) translate(78.01806640625 49.5625)"/><path d="M978.36 1681.56a6 6 0 0 1 6-6H1295a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H984.35a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#ffd9d9"/><use xlink:href="#t" transform="matrix(1,0,0,1,994.4865101326778,1679.857045864208) translate(74.66015625 49.5625)"/><path d="M7.4 1108.68a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.02821015130391,1106.986176645336) translate(74.8994140625 49.5625)"/><path d="M7.4 1538.34a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.02821015130391,1536.63932855949) translate(74.8994140625 49.5625)"/><path d="M7.4 1681.56a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.02821015130391,1679.857045864208) translate(74.8994140625 49.5625)"/><path d="M7.4 1251.9a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.02821015130391,1250.203893950054) translate(74.8994140625 49.5625)"/><path d="M7.4 1395.12a6 6 0 0 1 6-6h220.8a6 6 0 0 1 6 6v74.02a6 6 0 0 1-6 6H13.4a6 6 0 0 1-6-6z" stroke="#3a414a" stroke-width="3" fill="#dedeff"/><use xlink:href="#a" transform="matrix(1,0,0,1,19.02821015130391,1393.421611254772) translate(74.8994140625 49.5625)"/><path d="M441.93 1001.66l-182.73.74" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1003.13h-1.53v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1002.46l12.97-7.55.06 15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M441.93 1145l-182.73.63" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1146.47h-1.53v-2.94h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1145.68l12.97-7.54.06 15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M441.93 1575.35H259.2" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1576.82h-1.54v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1575.35l13-7.5v15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M441.93 1718.56H259.2" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1720.04h-1.54v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1718.56l13-7.5v15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M441.93 1288.9H259.2" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1290.4h-1.54v-2.96h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1288.9l13-7.5v15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M441.93 1432.13H259.2" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M443.43 1433.6h-1.54v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M244.7 1432.13l13-7.5v15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.57 1002.48H953.3" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M770.6 1003.95h-1.53V1001h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M967.8 1002.48l-13 7.5v-15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.57 1575.35h188.8" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M770.6 1576.82h-1.53v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M973.86 1575.35l-13 7.5v-15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.57 1718.56h188.8" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M770.6 1720.04h-1.53v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M973.86 1718.56l-13 7.5v-15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.3 1576.2l192.15 132.44" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M771.17 1575l-1.67 2.43-.43-.3v-3.58z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M974.4 1716.86l-14.97-1.2 8.52-12.35z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.57 1288.9h130V998l52.76 3.36" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M770.6 1290.4h-1.53v-2.96h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M967.8 1002.3l-13.45 6.64.95-14.97z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M770.57 1432.13h130v-429.65h52.73" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M770.6 1433.6h-1.53v-2.95h1.53z" stroke="#008a0e" stroke-width=".05" fill="#008a0e"/><path d="M967.8 1002.48l-13 7.5v-15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M1297.92 1002.48h194.87" stroke="#e81313" stroke-width="3" fill="none"/><path d="M1297.96 1003.95h-1.54V1001h1.54z" stroke="#e81313" stroke-width=".05" fill="#e81313"/><path d="M1507.28 1002.48l-13 7.5v-15z" stroke="#e81313" stroke-width="3" fill="#e81313"/><path d="M1297.92 1002.48h81.95v309.45h112.92" stroke="#e81313" stroke-width="3" fill="none"/><path d="M1297.96 1003.95h-1.54V1001h1.54z" stroke="#e81313" stroke-width=".05" fill="#e81313"/><path d="M1507.28 1311.93l-13 7.5v-15z" stroke="#e81313" stroke-width="3" fill="#e81313"/><path d="M1297.92 1002.48h82v143.2h112.87" stroke="#e81313" stroke-width="3" fill="none"/><path d="M1297.96 1003.95h-1.54V1001h1.54z" stroke="#e81313" stroke-width=".05" fill="#e81313"/><path d="M1507.28 1145.7l-13 7.5v-15z" stroke="#e81313" stroke-width="3" fill="#e81313"/><path d="M1297.92 1002.48h82.97v475.7h111.9" stroke="#e81313" stroke-width="3" fill="none"/><path d="M1297.96 1003.95h-1.54V1001h1.54z" stroke="#e81313" stroke-width=".05" fill="#e81313"/><path d="M1507.28 1478.17l-13 7.5v-15z" stroke="#e81313" stroke-width="3" fill="#e81313"/><path d="M1826.9 1006.94h212.06M1826.95 1006.94h-1.54" stroke="#000" stroke-width="3" fill="none"/><path d="M2053.45 1006.94l-13 7.5v-15z" stroke="#000" stroke-width="3"/><path d="M1837.42 1145.7h201.54" stroke="#000" stroke-width="3" fill="none"/><path d="M1837.45 1147.17h-1.53v-2.95h1.53z" stroke="#000" stroke-width=".05"/><path d="M2053.45 1145.7l-13 7.5v-15z" stroke="#000" stroke-width="3"/><path d="M1837.42 1311.93h201.54" stroke="#000" stroke-width="3" fill="none"/><path d="M1837.45 1313.4h-1.53v-2.94h1.53z" stroke="#000" stroke-width=".05"/><path d="M2053.45 1311.93l-13 7.5v-15z" stroke="#000" stroke-width="3"/><path d="M1837.42 1478.17h201.54" stroke="#000" stroke-width="3" fill="none"/><path d="M1837.45 1479.64h-1.53v-2.95h1.53z" stroke="#000" stroke-width=".05"/><path d="M2053.45 1478.17l-13 7.5v-15z" stroke="#000" stroke-width="3"/><path d="M1837.42 1644.4h201.54" stroke="#000" stroke-width="3" fill="none"/><path d="M1837.45 1645.88h-1.53v-2.95h1.53z" stroke="#000" stroke-width=".05"/><path d="M2053.45 1644.4l-13 7.5v-15z" stroke="#000" stroke-width="3"/><path d="M768.5 1147.5H901v-145.02h52.3M768.54 1147.5H767" stroke="#008a0e" stroke-width="3" fill="none"/><path d="M967.8 1002.48l-13 7.5v-15z" stroke="#008a0e" stroke-width="3" fill="#008a0e"/><path d="M-197.33 973.43a6 6 0 0 1 6-6h176.78a6 6 0 0 1 6 6v46.4a6 6 0 0 1-6 6h-176.78a6 6 0 0 1-6-6z" fill="none"/><use xlink:href="#u" transform="matrix(1,0,0,1,-192.33261359430628,972.4338926897028) translate(0 13.217447916666664)"/><use xlink:href="#v" transform="matrix(1,0,0,1,-192.33261359430628,972.4338926897028) translate(0 29.350260416666664)"/><use xlink:href="#w" transform="matrix(1,0,0,1,-192.33261359430628,972.4338926897028) translate(0 45.48307291666667)"/><a xlink:href="http://index.html" target="_blank" transform="matrix(1,0,0,1,-192.33261359430628,972.4338926897028)"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 .3h65.02v16.13H0z"/></a><path d="M-207.53 1106.36a6 6 0 0 1 6-6h176.78a6 6 0 0 1 6 6v78.67a6 6 0 0 1-6 6h-176.78a6 6 0 0 1-6-6z" fill="none"/><use xlink:href="#x" transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166) translate(0 13.084635416666668)"/><use xlink:href="#y" transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166) translate(0 29.217447916666668)"/><use xlink:href="#z" transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166) translate(0 45.35026041666667)"/><use xlink:href="#A" transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166) translate(0 61.48307291666667)"/><use xlink:href="#B" transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166) translate(0 77.61588541666667)"/><g transform="matrix(1,0,0,1,-202.53149826410356,1105.3564060899166)"><a xlink:href="http://paginaDetalleConcierto.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 16.3h178.78v16.13H0z"/></a><a xlink:href="http://clubing.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 32.43h77.17v16.13H0z"/></a><a xlink:href="http://admin.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 48.57h69.43V64.7H0z"/></a></g><path d="M-215.4 1247.24a6 6 0 0 1 6-6h176.8a6 6 0 0 1 6 6v78.68a6 6 0 0 1-6 6h-176.8a6 6 0 0 1-6-6z" fill="none"/><use xlink:href="#x" transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058) translate(0 13.084635416666668)"/><use xlink:href="#y" transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058) translate(0 29.217447916666668)"/><use xlink:href="#z" transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058) translate(0 45.35026041666667)"/><use xlink:href="#A" transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058) translate(0 61.48307291666667)"/><use xlink:href="#B" transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058) translate(0 77.61588541666667)"/><g transform="matrix(1,0,0,1,-210.38963339272277,1246.245159583058)"><a xlink:href="http://paginaDetalleConcierto.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 16.3h178.78v16.13H0z"/></a><a xlink:href="http://clubing.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 32.43h77.17v16.13H0z"/></a><a xlink:href="http://admin.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 48.57h69.43V64.7H0z"/></a></g><path d="M-215.4 1388.13a6 6 0 0 1 6-6h176.8a6 6 0 0 1 6 6v78.68a6 6 0 0 1-6 6h-176.8a6 6 0 0 1-6-6z" fill="none"/><g><use xlink:href="#x" transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994) translate(0 13.084635416666668)"/><use xlink:href="#y" transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994) translate(0 29.217447916666668)"/><use xlink:href="#z" transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994) translate(0 45.35026041666667)"/><use xlink:href="#A" transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994) translate(0 61.48307291666667)"/><use xlink:href="#B" transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994) translate(0 77.61588541666667)"/></g><g transform="matrix(1,0,0,1,-210.38963339272277,1387.1339130761994)"><a xlink:href="http://paginaDetalleConcierto.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 16.3h178.78v16.13H0z"/></a><a xlink:href="http://clubing.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 32.43h77.17v16.13H0z"/></a><a xlink:href="http://admin.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 48.57h69.43V64.7H0z"/></a></g><path d="M-215.4 1529.02a6 6 0 0 1 6-6h176.8a6 6 0 0 1 6 6v78.68a6 6 0 0 1-6 6h-176.8a6 6 0 0 1-6-6z" fill="none"/><g><use xlink:href="#x" transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408) translate(0 13.084635416666668)"/><use xlink:href="#y" transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408) translate(0 29.217447916666668)"/><use xlink:href="#z" transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408) translate(0 45.35026041666667)"/><use xlink:href="#A" transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408) translate(0 61.48307291666667)"/><use xlink:href="#B" transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408) translate(0 77.61588541666667)"/></g><g transform="matrix(1,0,0,1,-210.38963339272277,1528.0226665693408)"><a xlink:href="http://paginaDetalleConcierto.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 16.3h178.78v16.13H0z"/></a><a xlink:href="http://clubing.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 32.43h77.17v16.13H0z"/></a><a xlink:href="http://admin.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 48.57h69.43V64.7H0z"/></a></g><path d="M-215.4 1669.9a6 6 0 0 1 6-6h176.8a6 6 0 0 1 6 6v78.68a6 6 0 0 1-6 6h-176.8a6 6 0 0 1-6-6z" fill="none"/><g><use xlink:href="#x" transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822) translate(0 13.084635416666668)"/><use xlink:href="#y" transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822) translate(0 29.217447916666668)"/><use xlink:href="#z" transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822) translate(0 45.35026041666667)"/><use xlink:href="#A" transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822) translate(0 61.48307291666667)"/><use xlink:href="#B" transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822) translate(0 77.61588541666667)"/></g><g transform="matrix(1,0,0,1,-210.38963339272277,1668.9114200624822)"><a xlink:href="http://paginaDetalleConcierto.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 16.3h178.78v16.13H0z"/></a><a xlink:href="http://clubing.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 32.43h77.17v16.13H0z"/></a><a xlink:href="http://admin.html" target="_blank"><path class="lucid-link lucid-hotspot lucid-overlay-hotspot" fill-opacity="0" d="M0 48.57h69.43V64.7H0z"/></a></g><defs><path d="M600 0L52-1490h200c166 485 304 806 458 1336 156-533 285-846 449-1336h202L819 0H600" id="C"/><path d="M158 0v-1118h180V0H158zm91-1301c-68 0-125-53-125-119s57-119 125-119c69 0 126 53 126 119s-57 119-126 119" id="D"/><path d="M628 24c-324 0-524-230-524-574 0-343 198-582 503-582 237 0 487 146 487 559v75H286c9 234 145 362 343 362 132 0 231-58 273-172l174 48C1024-91 857 24 628 24zM287-650h624c-17-190-120-322-304-322-192 0-309 151-320 322" id="E"/><path d="M409 0L70-1118h191c89 329 165 560 243 925 75-353 154-601 240-925h192c85 325 161 564 235 922 77-354 157-598 244-922h191L1267 0h-179c-86-307-176-590-250-913C763-588 675-308 588 0H409" id="F"/><g id="a"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#C"/><use transform="matrix(0.0126953125,0,0,0.0126953125,17.9384765625,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,24.2353515625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,38.923828125,0)" xlink:href="#F"/></g><path d="M783 20c-382 0-661-292-661-764 0-473 279-766 661-766 302 0 548 182 601 489h-190c-42-204-217-313-411-313-268 0-476 208-476 590 0 381 209 588 476 588 195 0 369-110 411-313h190c-52 303-296 489-601 489" id="G"/><path d="M613 24c-304 0-509-231-509-576 0-350 205-580 509-580 305 0 511 230 511 580 0 345-206 576-511 576zm0-161c226 0 329-195 329-415 0-222-103-419-329-419-223 0-326 196-326 419 0 220 103 415 326 415" id="H"/><path d="M158 0v-1118h175l1 205c55-151 181-225 313-225 147 0 245 90 285 228 53-141 190-228 352-228 194 0 352 125 352 384V0h-181v-749c0-161-105-225-225-225-151 0-243 103-243 244V0H807v-767c0-124-93-207-219-207-131 0-250 92-250 270V0H158" id="I"/><path d="M338-670V0H158v-1118h173l1 207c72-158 192-221 342-221 226 0 378 139 378 422V0H872v-695c0-172-96-275-252-275-161 0-282 109-282 300" id="J"/><path d="M598-1118v154H368v674c0 100 37 144 132 144 23 0 62-6 92-12L629-6c-37 13-88 20-134 20-193 0-307-107-307-290v-688H20v-154h168v-266h180v266h230" id="K"/><path d="M538 24C308 24 148-78 108-271l171-41c32 123 123 178 257 178 156 0 256-77 256-169 0-77-54-128-164-154l-186-44c-203-48-300-148-300-305 0-192 176-326 414-326 230 0 351 112 402 269l-163 42c-31-80-94-158-238-158-133 0-233 69-233 162 0 83 57 129 188 160l169 40c203 48 298 149 298 302 0 196-179 339-441 339" id="L"/><path d="M158 0v-1118h174v172h12c41-113 157-188 290-188 26 0 70 2 91 3v181c-11-2-60-10-108-10-161 0-279 109-279 260V0H158" id="M"/><path d="M338-1490V0H158v-1490h180" id="N"/><g id="b"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,18.9921875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,34.58203125,0)" xlink:href="#I"/><use transform="matrix(0.0126953125,0,0,0.0126953125,57.357421875,0)" xlink:href="#I"/><use transform="matrix(0.0126953125,0,0,0.0126953125,80.1328125,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,95.291015625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,110.65234375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,118.904296875,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,132.6279296875,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,151.6201171875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,167.2099609375,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,182.5712890625,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,191.0771484375,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,200.43359375,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,216.0234375,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,222.3203125,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,228.6171875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,243.775390625,0)" xlink:href="#M"/></g><path d="M180 0v-1490h908v168H370v486h669v168H370v500h727V0H180" id="O"/><path d="M481 0L54-1118h197c107 314 236 620 325 951 87-331 217-637 324-951h197L670 0H481" id="P"/><g id="c"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#O"/><use transform="matrix(0.0126953125,0,0,0.0126953125,15.6279296875,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,29.732421875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,44.890625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.251953125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,68.7578125,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,87.75,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,103.33984375,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,118.701171875,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,127.20703125,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,136.5634765625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,152.1533203125,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,158.4501953125,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,164.7470703125,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,179.9052734375,0)" xlink:href="#M"/></g><path d="M180 0v-1490h510c348 0 508 209 508 474 0 266-160 477-507 477H370V0H180zm190-706h312c236 0 327-133 327-310 0-176-91-307-329-307H370v617" id="Q"/><path d="M577 24c-279 0-473-224-473-580 0-354 195-576 474-576 218 0 287 134 324 193h14v-551h180V0H922v-173h-20C865-111 790 24 577 24zm27-161c203 0 314-172 314-421 0-247-108-413-314-413-212 0-317 181-317 413 0 235 108 421 317 421" id="R"/><path d="M678-1118v154H420V0H240v-964H20v-154h220v-149c0-194 155-293 318-293 85 0 141 18 168 30l-50 154c-19-6-47-17-97-17-111 0-159 58-159 166v109h258" id="S"/><g id="d"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#Q"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.4658203125,0)" xlink:href="#R"/><use transform="matrix(0.0126953125,0,0,0.0126953125,32.3857421875,0)" xlink:href="#S"/><use transform="matrix(0.0126953125,0,0,0.0126953125,42.0087890625,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,61.0009765625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,76.5908203125,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,91.9521484375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,100.4580078125,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,109.814453125,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,125.404296875,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,131.701171875,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,137.998046875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,153.15625,0)" xlink:href="#M"/></g><path d="M180 0v-1490h510c348 0 508 194 508 460 0 198-88 351-276 417L1256 0h-220L726-579c-117 2-238 0-356 1V0H180zm190-747h312c235 0 327-108 327-283 0-177-92-293-329-293H370v576" id="T"/><path d="M471 26C259 26 90-98 90-318c0-256 228-303 435-329 202-27 287-16 287-108 0-139-79-219-234-219-161 0-248 86-283 164l-173-57c86-203 278-265 451-265 150 0 419 46 419 395V0H815v-152h-12C765-73 660 26 471 26zm31-159c199 0 310-134 310-271v-155c-30 35-226 55-295 64-131 17-246 59-246 186 0 116 97 176 231 176" id="U"/><g id="e"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.3515625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,31.509765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,45.2333984375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.3916015625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,70.5859375,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,85.0712890625,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,99.6708984375,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,118.6630859375,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,134.2529296875,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,149.6142578125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,158.1201171875,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,167.4765625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,183.06640625,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,189.36328125,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,195.66015625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,210.818359375,0)" xlink:href="#M"/></g><path d="M645 0H180v-1490h484c435 0 692 280 692 742 0 466-257 748-711 748zM370-168h263c363 0 539-218 539-580 0-358-176-574-521-574H370v1154" id="V"/><path d="M677 24c-213 0-288-135-325-197h-20V0H158v-1490h180v551h14c37-59 106-193 324-193 279 0 474 222 474 576 0 356-194 580-473 580zm-27-161c209 0 317-186 317-421 0-232-105-413-317-413-206 0-314 166-314 413 0 249 111 421 314 421" id="W"/><path d="M370-1490V0H180v-1490h190" id="X"/><path d="M126 0v-134l627-806v-11H146v-167h839v143L376-178v11h629V0H126" id="Y"/><g id="f"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#V"/><use transform="matrix(0.0126953125,0,0,0.0126953125,18.763671875,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,33.36328125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,41.99609375,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,56.595703125,0)" xlink:href="#W"/><use transform="matrix(0.0126953125,0,0,0.0126953125,72.76953125,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,87.369140625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,101.0927734375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,116.2509765625,0)" xlink:href="#X"/><use transform="matrix(0.0126953125,0,0,0.0126953125,123.2333984375,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,138.5947265625,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,144.8916015625,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,153.3974609375,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,159.6943359375,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,174.2939453125,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,180.5908203125,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,186.8876953125,0)" xlink:href="#Y"/><use transform="matrix(0.0126953125,0,0,0.0126953125,200.865234375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,216.0234375,0)" xlink:href="#M"/></g><path d="M98-1322v-168h1126v168H757V0H567v-1322H98" id="Z"/><path d="M613 24c-304 0-509-231-509-576 0-350 205-580 509-580 216 0 392 114 453 309l-173 49c-33-115-133-197-280-197-223 0-326 196-326 419 0 220 103 415 326 415 150 0 252-85 285-206l172 49C1010-95 832 24 613 24" id="aa"/><path d="M158 0v-1490h180v865h22l478-493h223L593-638 1096 0H865L456-523 338-412V0H158" id="ab"/><g id="g"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#Z"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.783203125,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,23.080078125,0)" xlink:href="#aa"/><use transform="matrix(0.0126953125,0,0,0.0126953125,37.93359375,0)" xlink:href="#ab"/><use transform="matrix(0.0126953125,0,0,0.0126953125,51.619140625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,66.77734375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,75.283203125,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,94.275390625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,109.865234375,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,125.2265625,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,133.732421875,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,143.0888671875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,158.6787109375,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,164.9755859375,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,171.2724609375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,186.4306640625,0)" xlink:href="#M"/></g><path d="M763 24c-353 0-583-224-583-531v-983h190v968c0 214 149 370 393 370 243 0 391-156 391-370v-968h190v983c0 307-230 531-581 531" id="ac"/><g id="h"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#ac"/><use transform="matrix(0.0126953125,0,0,0.0126953125,19.34765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,33.0712890625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,48.2294921875,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,58.017578125,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,77.009765625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,92.599609375,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,107.9609375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,116.466796875,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,125.8232421875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,141.4130859375,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,147.7099609375,0)" xlink:href="#N"/><use transform="matrix(0.0126953125,0,0,0.0126953125,154.0068359375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,169.1650390625,0)" xlink:href="#M"/></g><g id="i"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,18.9921875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,34.58203125,0)" xlink:href="#I"/><use transform="matrix(0.0126953125,0,0,0.0126953125,57.357421875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,72.515625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,87.876953125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,96.12890625,0)" xlink:href="#L"/></g><g id="j"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#O"/><use transform="matrix(0.0126953125,0,0,0.0126953125,15.6279296875,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,29.732421875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,44.890625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.251953125,0)" xlink:href="#K"/></g><g id="k"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.3515625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,31.509765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,45.2333984375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.3916015625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,70.5859375,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,85.0712890625,0)" xlink:href="#U"/></g><g id="l"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#Z"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.783203125,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,23.080078125,0)" xlink:href="#aa"/><use transform="matrix(0.0126953125,0,0,0.0126953125,37.93359375,0)" xlink:href="#ab"/><use transform="matrix(0.0126953125,0,0,0.0126953125,51.619140625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,66.77734375,0)" xlink:href="#K"/></g><g id="m"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#ac"/><use transform="matrix(0.0126953125,0,0,0.0126953125,19.34765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,33.0712890625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,48.2294921875,0)" xlink:href="#M"/></g><path d="M158 418v-1536h174v179h20c37-59 106-193 324-193 279 0 474 222 474 576 0 356-194 580-473 580-213 0-288-135-325-197h-14v591H158zm492-555c209 0 317-186 317-421 0-232-105-413-317-413-206 0-314 166-314 413 0 249 111 421 314 421" id="ad"/><path d="M140 405l46-156c120 36 205 19 263-150l32-93L54-1118h197c107 313 237 620 324 950 87-331 219-637 326-950h196L606 167c-68 178-176 259-334 259-64 0-113-12-132-21" id="ae"/><g id="n"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#G"/><use transform="matrix(0.0126953125,0,0,0.0126953125,18.9921875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,34.58203125,0)" xlink:href="#I"/><use transform="matrix(0.0126953125,0,0,0.0126953125,57.357421875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,72.515625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,87.876953125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,96.12890625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,109.8525390625,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,126.2041015625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,141.3623046875,0)" xlink:href="#ad"/><use transform="matrix(0.0126953125,0,0,0.0126953125,157.2822265625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,172.8720703125,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,186.595703125,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,192.892578125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,201.14453125,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,216.734375,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,226.9287109375,0)" xlink:href="#ae"/></g><g id="o"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#O"/><use transform="matrix(0.0126953125,0,0,0.0126953125,15.6279296875,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,29.732421875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,44.890625,0)" xlink:href="#J"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.251953125,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,68.7578125,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,85.109375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,100.267578125,0)" xlink:href="#ad"/><use transform="matrix(0.0126953125,0,0,0.0126953125,116.1875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,131.77734375,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,145.5009765625,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,151.7978515625,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,160.0498046875,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,175.6396484375,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,185.833984375,0)" xlink:href="#ae"/></g><g id="p"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.3515625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,31.509765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,45.2333984375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.3916015625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,70.5859375,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,85.0712890625,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,99.6708984375,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,116.0224609375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,131.1806640625,0)" xlink:href="#ad"/><use transform="matrix(0.0126953125,0,0,0.0126953125,147.1005859375,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,162.6904296875,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,176.4140625,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,182.7109375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,190.962890625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,206.552734375,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,216.7470703125,0)" xlink:href="#ae"/></g><g id="q"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#Z"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.783203125,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,23.080078125,0)" xlink:href="#aa"/><use transform="matrix(0.0126953125,0,0,0.0126953125,37.93359375,0)" xlink:href="#ab"/><use transform="matrix(0.0126953125,0,0,0.0126953125,51.619140625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,66.77734375,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,75.283203125,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,91.634765625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,106.79296875,0)" xlink:href="#ad"/><use transform="matrix(0.0126953125,0,0,0.0126953125,122.712890625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,138.302734375,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,152.0263671875,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,158.3232421875,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,166.5751953125,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,182.1650390625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,192.359375,0)" xlink:href="#ae"/></g><g id="r"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#ac"/><use transform="matrix(0.0126953125,0,0,0.0126953125,19.34765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,33.0712890625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,48.2294921875,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,58.423828125,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,74.775390625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,89.93359375,0)" xlink:href="#ad"/><use transform="matrix(0.0126953125,0,0,0.0126953125,105.853515625,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,121.443359375,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,135.1669921875,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,141.4638671875,0)" xlink:href="#K"/><use transform="matrix(0.0126953125,0,0,0.0126953125,149.7158203125,0)" xlink:href="#H"/><use transform="matrix(0.0126953125,0,0,0.0126953125,165.3056640625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,175.5,0)" xlink:href="#ae"/></g><path d="M657 26c-323 0-524-166-541-416h195c15 169 171 246 346 246 202 0 356-106 356-265 5-203-294-238-475-293-239-73-380-191-380-389 0-252 224-419 512-419 294 0 499 171 508 396H992c-17-145-151-228-328-228-193 0-321 102-321 242 0 156 175 211 284 241l149 41c160 44 422 134 422 412 0 244-197 432-541 432" id="af"/><g id="s"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#Q"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.4658203125,0)" xlink:href="#R"/><use transform="matrix(0.0126953125,0,0,0.0126953125,32.3857421875,0)" xlink:href="#S"/><use transform="matrix(0.0126953125,0,0,0.0126953125,42.0087890625,0)" xlink:href="#af"/><use transform="matrix(0.0126953125,0,0,0.0126953125,58.6904296875,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,73.8486328125,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,84.04296875,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,98.6552734375,0)" xlink:href="#D"/><use transform="matrix(0.0126953125,0,0,0.0126953125,104.9521484375,0)" xlink:href="#aa"/><use transform="matrix(0.0126953125,0,0,0.0126953125,119.8056640625,0)" xlink:href="#E"/></g><g id="t"><use transform="matrix(0.0126953125,0,0,0.0126953125,0,0)" xlink:href="#T"/><use transform="matrix(0.0126953125,0,0,0.0126953125,16.3515625,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,31.509765625,0)" xlink:href="#L"/><use transform="matrix(0.0126953125,0,0,0.0126953125,45.2333984375,0)" xlink:href="#E"/><use transform="matrix(0.0126953125,0,0,0.0126953125,60.3916015625,0)" xlink:href="#M"/><use transform="matrix(0.0126953125,0,0,0.0126953125,70.5859375,0)" xlink:href="#P"/><use transform="matrix(0.0126953125,0,0,0.0126953125,85.0712890625,0)" xlink:href="#U"/><use transform="matrix(0.0126953125,0,0,0.0126953125,99.6708984375,0)" xlink:href="#Q"/><use transform="matrix(0.0126953125,0,0,0.0126953125,116.13671875,0)" xlink:href="#R"/><use transform="matrix(0.0126953125,0,0,0.0126953125,132.056640625,0)" xlink:href="#S"/></g><path fill="#635dff" d="M158 0v-1118h180V0H158zm91-1301c-68 0-125-53-125-119s57-119 125-119c69 0 126 53 126 119s-57 119-126 119" id="ag"/><path fill="#635dff" d="M338-670V0H158v-1118h173l1 207c72-158 192-221 342-221 226 0 378 139 378 422V0H872v-695c0-172-96-275-252-275-161 0-282 109-282 300" id="ah"/><path fill="#635dff" d="M577 24c-279 0-473-224-473-580 0-354 195-576 474-576 218 0 287 134 324 193h14v-551h180V0H922v-173h-20C865-111 790 24 577 24zm27-161c203 0 314-172 314-421 0-247-108-413-314-413-212 0-317 181-317 413 0 235 108 421 317 421" id="ai"/><path fill="#635dff" d="M628 24c-324 0-524-230-524-574 0-343 198-582 503-582 237 0 487 146 487 559v75H286c9 234 145 362 343 362 132 0 231-58 273-172l174 48C1024-91 857 24 628 24zM287-650h624c-17-190-120-322-304-322-192 0-309 151-320 322" id="aj"/><path fill="#635dff" d="M65 0l393-574-370-544h210c88 136 192 297 267 435 67-142 176-302 264-435h206L661-564 1053 0H844c-94-144-206-310-287-458C484-308 366-142 273 0H65" id="ak"/><path fill="#635dff" d="M295 13c-75 0-135-60-135-135s60-135 135-135 135 60 135 135S370 13 295 13" id="al"/><path fill="#635dff" d="M338-670V0H158v-1490h180v566c73-149 190-208 336-208 226 0 379 139 379 422V0H872v-695c0-172-96-275-252-275-161 0-282 109-282 300" id="am"/><path fill="#635dff" d="M598-1118v154H368v674c0 100 37 144 132 144 23 0 62-6 92-12L629-6c-37 13-88 20-134 20-193 0-307-107-307-290v-688H20v-154h168v-266h180v266h230" id="an"/><path fill="#635dff" d="M158 0v-1118h175l1 205c55-151 181-225 313-225 147 0 245 90 285 228 53-141 190-228 352-228 194 0 352 125 352 384V0h-181v-749c0-161-105-225-225-225-151 0-243 103-243 244V0H807v-767c0-124-93-207-219-207-131 0-250 92-250 270V0H158" id="ao"/><path fill="#635dff" d="M338-1490V0H158v-1490h180" id="ap"/><g id="u"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,3.229166666666667,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,11.106770833333334,0)" xlink:href="#ai"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,19.270833333333336,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,26.744791666666668,0)" xlink:href="#ak"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,34.0234375,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,37.864583333333336,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,45.74869791666667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,50.110677083333336,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,61.790364583333336,0)" xlink:href="#ap"/><path fill="#8080ff" d="M-.67 2.72h66.35v.9H-.67z"/></g><path fill="#635dff" d="M158 418v-1536h174v179h20c37-59 106-193 324-193 279 0 474 222 474 576 0 356-194 580-473 580-213 0-288-135-325-197h-14v591H158zm492-555c209 0 317-186 317-421 0-232-105-413-317-413-206 0-314 166-314 413 0 249 111 421 314 421" id="aq"/><path fill="#635dff" d="M471 26C259 26 90-98 90-318c0-256 228-303 435-329 202-27 287-16 287-108 0-139-79-219-234-219-161 0-248 86-283 164l-173-57c86-203 278-265 451-265 150 0 419 46 419 395V0H815v-152h-12C765-73 660 26 471 26zm31-159c199 0 310-134 310-271v-155c-30 35-226 55-295 64-131 17-246 59-246 186 0 116 97 176 231 176" id="ar"/><path fill="#635dff" d="M611 442c-248 0-391-105-460-228l146-94c47 65 117 165 314 165 178 0 307-82 307-266v-224h-17C863-141 792-18 576-18c-268 0-472-195-472-546 0-346 197-568 476-568 216 0 288 133 326 193h17v-179h175V29c0 289-215 413-487 413zm-5-620c203 0 314-146 314-390 0-237-108-403-314-403-213 0-319 180-319 403 0 230 109 390 319 390" id="as"/><path fill="#635dff" d="M645 0H180v-1490h484c435 0 692 280 692 742 0 466-257 748-711 748zM370-168h263c363 0 539-218 539-580 0-358-176-574-521-574H370v1154" id="at"/><path fill="#635dff" d="M783 20c-382 0-661-292-661-764 0-473 279-766 661-766 302 0 548 182 601 489h-190c-42-204-217-313-411-313-268 0-476 208-476 590 0 381 209 588 476 588 195 0 369-110 411-313h190c-52 303-296 489-601 489" id="au"/><path fill="#635dff" d="M613 24c-304 0-509-231-509-576 0-350 205-580 509-580 305 0 511 230 511 580 0 345-206 576-511 576zm0-161c226 0 329-195 329-415 0-222-103-419-329-419-223 0-326 196-326 419 0 220 103 415 326 415" id="av"/><path fill="#635dff" d="M613 24c-304 0-509-231-509-576 0-350 205-580 509-580 216 0 392 114 453 309l-173 49c-33-115-133-197-280-197-223 0-326 196-326 419 0 220 103 415 326 415 150 0 252-85 285-206l172 49C1010-95 832 24 613 24" id="aw"/><path fill="#635dff" d="M158 0v-1118h174v172h12c41-113 157-188 290-188 26 0 70 2 91 3v181c-11-2-60-10-108-10-161 0-279 109-279 260V0H158" id="ax"/><g id="v"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#aq"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,8.294270833333334,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,15.78125,0)" xlink:href="#as"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,23.958333333333336,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,27.1875,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,35.06510416666667,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,42.552083333333336,0)" xlink:href="#at"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,52.17447916666667,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,59.94791666666667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,64.375,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,71.86197916666667,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,75.09114583333334,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,78.32031250000001,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,86.09375000000001,0)" xlink:href="#au"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,95.83333333333334,0)" xlink:href="#av"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,103.82812500000001,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,111.7057291666667,0)" xlink:href="#aw"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,119.3229166666667,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,122.55208333333336,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,130.32552083333337,0)" xlink:href="#ax"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,135.5533854166667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,139.78515625000003,0)" xlink:href="#av"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,147.77994791666669,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,151.62109375000003,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,159.50520833333337,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,163.86718750000003,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,175.54687500000003,0)" xlink:href="#ap"/></g><path fill="#635dff" d="M537 14c-226 0-379-139-379-422v-710h180v695c0 172 97 275 253 275 160 0 281-109 281-300v-670h181V0H879v-209C806-46 684 14 537 14" id="ay"/><path fill="#635dff" d="M677 24c-213 0-288-135-325-197h-20V0H158v-1490h180v551h14c37-59 106-193 324-193 279 0 474 222 474 576 0 356-194 580-473 580zm-27-161c209 0 317-186 317-421 0-232-105-413-317-413-206 0-314 166-314 413 0 249 111 421 314 421" id="az"/><g id="w"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#aw"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,7.6171875,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,10.846354166666668,0)" xlink:href="#ay"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,18.73046875,0)" xlink:href="#az"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,26.89453125,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,30.123697916666668,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,38.001302083333336,0)" xlink:href="#as"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,46.17838541666667,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,50.01953125000001,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,57.90364583333335,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,62.265625,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,73.9453125,0)" xlink:href="#ap"/></g><g id="x"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,3.229166666666667,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,11.106770833333334,0)" xlink:href="#ai"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,19.270833333333336,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,26.744791666666668,0)" xlink:href="#ak"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,34.0234375,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,37.864583333333336,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,41.70572916666667,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,49.58984375000001,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,53.95182291666667,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,65.63151041666667,0)" xlink:href="#ap"/></g><g id="y"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#aq"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,8.294270833333334,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,15.78125,0)" xlink:href="#as"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,23.958333333333336,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,27.1875,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,35.06510416666667,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,42.552083333333336,0)" xlink:href="#at"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,52.17447916666667,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,59.94791666666667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,64.375,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,71.86197916666667,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,75.09114583333334,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,78.32031250000001,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,86.09375000000001,0)" xlink:href="#au"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,95.83333333333334,0)" xlink:href="#av"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,103.82812500000001,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,111.7057291666667,0)" xlink:href="#aw"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,119.3229166666667,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,122.55208333333336,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,130.32552083333337,0)" xlink:href="#ax"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,135.5533854166667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,139.78515625000003,0)" xlink:href="#av"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,147.77994791666669,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,151.62109375000003,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,159.50520833333337,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,163.86718750000003,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,175.54687500000003,0)" xlink:href="#ap"/><path fill="#8080ff" d="M-.67 2.72h180.1v.9H-.66z"/></g><g id="z"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#aw"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,7.6171875,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,10.846354166666668,0)" xlink:href="#ay"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,18.73046875,0)" xlink:href="#az"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,26.89453125,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,30.123697916666668,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,38.001302083333336,0)" xlink:href="#as"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,46.17838541666667,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,50.01953125000001,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,57.90364583333335,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,62.265625,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,73.9453125,0)" xlink:href="#ap"/><path fill="#8080ff" d="M-.67 2.72h78.5v.9H-.66z"/></g><g id="A"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,7.486979166666667,0)" xlink:href="#ai"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,15.651041666666668,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,27.330729166666668,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,30.559895833333336,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,38.4375,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,42.278645833333336,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,50.16276041666667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,54.524739583333336,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,66.20442708333334,0)" xlink:href="#ap"/><path fill="#8080ff" d="M-.67 2.72H70.1v.9H-.67z"/></g><path fill="#635dff" d="M180 0v-1490h510c348 0 508 209 508 474 0 266-160 477-507 477H370V0H180zm190-706h312c236 0 327-133 327-310 0-176-91-307-329-307H370v617" id="aA"/><path fill="#635dff" d="M678-1118v154H420V0H240v-964H20v-154h220v-149c0-194 155-293 318-293 85 0 141 18 168 30l-50 154c-19-6-47-17-97-17-111 0-159 58-159 166v109h258" id="aB"/><g id="B"><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,0,0)" xlink:href="#aq"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,8.294270833333334,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,15.78125,0)" xlink:href="#as"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,23.958333333333336,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,27.1875,0)" xlink:href="#ah"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,35.06510416666667,0)" xlink:href="#ar"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,42.552083333333336,0)" xlink:href="#aA"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,50.99609375,0)" xlink:href="#aj"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,58.76953125,0)" xlink:href="#ax"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,63.997395833333336,0)" xlink:href="#aB"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,68.93229166666667,0)" xlink:href="#ag"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,72.16145833333334,0)" xlink:href="#ap"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,75.39062500000001,0)" xlink:href="#al"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,79.23177083333334,0)" xlink:href="#am"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,87.11588541666667,0)" xlink:href="#an"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,91.47786458333334,0)" xlink:href="#ao"/><use transform="matrix(0.006510416666666667,0,0,0.006510416666666667,103.15755208333334,0)" xlink:href="#ap"/></g></defs></g></svg>



