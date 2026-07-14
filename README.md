# Yappy Payment Integration (POC)
## Descripción
 Este proyecto es un Proof of Concept (POC) desarrollado en Spring Boot que simula la integración con la plataforma de pagos Yappy.
Debido a que los servicios oficiales requieren credenciales y cuentas panameñas, se utiliza Mockoon para simular el comportamiento de la API de Yappy durante el desarrollo.
El proyecto implementa el flujo completo de creación de una orden de pago, validación del comercio y recepción de notificaciones (webhooks), almacenando la información en PostgreSQL.

                Cliente

                   │

                   ▼

          Spring Boot (Yappy)

        ┌──────────┼───────────┐

        ▼          ▼           ▼

 PostgreSQL    Mockoon      Webhook

                  │

                  ▼

        Simulación API Yappy
        
## Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Mockoon
- Maven

- Variables de entorno
| Variable    | Descripción                |
| ----------- | -------------------------- |
| DB_URL      | URL de PostgreSQL          |
| DB_USERNAME | Usuario de PostgreSQL      |
| DB_PASSWORD | Contraseña de PostgreSQL   |
| MERCHANT_ID | Identificador del comercio |
| SECRET_KEY  | Llave secreta              |
| BASE_URL    | URL de Mockoon             |
| WEBHOOK_URL | URL del webhook            |
--------------------------------------------

##Ejecución

### Clonar el proyecto
 git clone ...

### Construir app
 mvn clean package

### Levantar los servicios
 docker compose up --build

 EL PROYECTO LEVANTA AUTOMATICAMENTE
- Spring Boot
- PostgreSQL
- Mockoon debe estar ejecutandose

## Mejoras futuras

- Integración con la API oficial de Yappy.
- Pipeline CI/CD con Jenkins o GitHub Actions.
- Despliegue automático utilizando Docker Compose.
- Migración a Kubernetes.
- Pruebas de integración.
- Integración con SonarQube.
