# Bus Management System

Système de gestion de réservations de bus basé sur une architecture microservices avec Spring Cloud et React.

## Services et Ports

Le projet est découpé en plusieurs microservices :

- service-registry : 8761 (Eureka)
- api-gateway : 8765
- authentification-service : 8080 (JWT)
- bus-information-service : 8083 (Gestion bus/trajets)
- reservation-service : 8082
- reservation-viewer-service : 8084
- location-service : 8080 (MySQL)
- notification-service : 8500 (MongoDB + MailDev)
- frontend-service : 8081 (BFF)

## Stack

- Backend : Java 19/23, Spring Boot 3, Spring Cloud.
- Frontend : React 18, Vite, MUI, React Query.
- Bases de données : PostgreSQL, MySQL, MongoDB.
- Infra : Docker Compose, Kubernetes.

## Lancement

### 1. Infrastructure
Lancer les bases de données et outils via Docker :
```bash
docker-compose up -d
```
Cela active PostgreSQL, MongoDB, MySQL et MailDev.

### 2. Backend
Lancer les services dans l'ordre (Registry en premier) avec :
```bash
./mvnw spring-boot:run
```

### 3. Frontend
```bash
cd frontend
npm install
npm run dev
```

## Divers
- Mails : MailDev est accessible sur http://localhost:1080 pour voir les notifications envoyées.
- K8s : Les manifests sont dans le dossier /k8s.
