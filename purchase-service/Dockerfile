# Usa una imagen base ligera de Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado (ajusta el nombre si tu JAR tiene nombre distinto)
COPY build/libs/*.jar app.jar

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]