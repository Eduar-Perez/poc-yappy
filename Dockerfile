# Se define el lenguaje que se utiliza par construir la app
FROM eclipse-temurin:17

#Se define el correo y nombre de la persona que da mantenimiento
LABEL maitainer="Eduar Perez <eduarperez@periferia-it.com>"

#Se define el lugar donde se empaqueta la app en la maquina para poderla copiar a la imagen
COPY target/yappy-0.0.1-SNAPSHOT.jar app.jar

#Se define el comando principal que se ejecutará cuando se inicie un contenedor a partir de una imagen  
ENTRYPOINT ["java", "-jar", "/app.jar"]