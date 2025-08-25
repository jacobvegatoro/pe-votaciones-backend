# Dockerfile para servicio-eureka-server
FROM eclipse-temurin:17-jre-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar generado al contenedor
COPY target/servicio-eureka-server-*.jar app.jar

# Exponer el puerto de Eureka (por defecto 8761)
EXPOSE 8761

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
