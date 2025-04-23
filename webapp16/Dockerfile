#Etapa 1: Contruir la aplicacion Angular
FROM node:18-slim AS angular-builder
WORKDIR /app
RUN npm install -g @angular/cli@19.2.8
# Copia solo los archivos necesarios para instalar dependencias
COPY frontend/package.json frontend/package-lock.json ./frontend/
RUN cd frontend && npm install
COPY frontend/ ./frontend/
# Construye la aplicación Angular
RUN cd frontend && \
    ng build --configuration=production --base-href=/spa/ --output-path=dist/frontend

# Etapa 2: Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
# Copia solo el pom primero para aprovechar la caché de Maven
COPY ./webapp16/pom.xml .
# Descarga las dependencias primero (se cachean si el pom no cambia)
RUN mvn dependency:go-offline
COPY ./webapp16/src ./src
RUN mvn clean package -DskipTests

# Etapa 3: Imagen final para ejecutar la aplicación
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia solo el JAR necesario
COPY --from=build /app/target/app.jar app.jar
# Copia los archivos de Angular construidos
COPY --from=angular-builder /app/frontend/dist/frontend/browser/ /app/static/spa
# Asegura que el puerto esté expuesto
EXPOSE 8443
# Mejor entrypoint para manejar señales de terminación
ENTRYPOINT ["java", "-jar", "app.jar"]