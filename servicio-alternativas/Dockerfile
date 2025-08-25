FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/servicio-alternativas-*.jar app.jar

EXPOSE 8082

# Permite sobreescribir propiedades de Spring Boot con variables de entorno
ENTRYPOINT ["java","-jar","app.jar"]
