# Etapa 1: Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
# Copia solo el pom primero para aprovechar la caché de Maven
COPY pom.xml .
# Descarga las dependencias primero (se cachean si el pom no cambia)
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecutar la aplicación
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia solo el JAR necesario
COPY --from=build /app/target/app.jar app.jar
# Asegura que el puerto esté expuesto
EXPOSE 8443
# Mejor entrypoint para manejar señales de terminación
ENTRYPOINT ["java", "-jar", "app.jar"]