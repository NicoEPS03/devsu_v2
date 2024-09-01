# Utiliza una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el archivo .jar al contenedor
COPY target/prueba-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escucha (ajusta el puerto si es necesario)
EXPOSE 8080

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
