# Usamos la imagen oficial de Maven con OpenJDK 17 en su versión "slim"
FROM maven:3.8.4-openjdk-17-slim

# Establecer el directorio de trabajo en el contenedor para construir la aplicación
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar el codigo fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -X

# Exponer el puerto 8010
EXPOSE 8011

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "target/cloudapi-0.0.2.jar"]