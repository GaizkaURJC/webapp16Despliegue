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
  - Fecha_Reserva
  - Estado [Confirmado (Pagado), Pendiente, Cancelado]

### *5. Alquiler*
Representa los alquileres del teatro para eventos corporativos.
- *Atributos*:
  - ID
  - ClienteID
  - Fecha de Inicio
  - Fecha de Fin
  - Descripción del Evento
  - Coste Total

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
primer comit
