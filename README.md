# Micro-service de Gestion de Comptes Bancaires (REST)

Ce projet consiste en la création d'un micro-service complet pour la gestion de comptes bancaires, réalisé dans le cadre du cours du **Pr. Mohamed Youssfi**. Il explore les différentes architectures d'exposition d'API (REST, Spring Data REST) et les bonnes pratiques de structuration (Couche Service, DTOs, Mappers).

##Fonctionnalités
* Gestion CRUD de comptes bancaires.
* API RESTful manuelle et automatisée (Spring Data REST).
* Documentation interactive avec Swagger/OpenAPI.
* Architecture découplée avec DTOs et Mappers.

---

##Technologies Utilisées
* **Framework :** Spring Boot 3.x
* **Persistance :** Spring Data JPA
* **Base de données :** H2 (Base en mémoire)
* **API :** REST
* **Outils :** Lombok, SpringDoc OpenAPI, MapStruct/BeanUtils

---

##Étapes de Réalisation

### 1. Initialisation du Projet
Création du projet via Spring Initializr avec les dépendances suivantes :
- `Spring Web`
- `Spring Data JPA`
- `H2 Database`
- `Lombok`
- `SpringDoc OpenAPI` (pour Swagger)

### 2. Modèle de Données (Entité JPA)
Création de l'entité `BankAccount` représentant un compte avec :
- `id` (String, UUID)
- `createdAt` (Date)
- `balance` (Double)
- `currency` (String)
- `type` (Enum : `CURRENT_ACCOUNT`, `SAVING_ACCOUNT`)

### 3. Couche Accès aux Données (DAO)
Implémentation de l'interface `AccountRepository` héritant de `JpaRepository` pour bénéficier des opérations CRUD standards de Spring Data JPA.

### 4. Tests de la Couche DAO
Initialisation de la base de données au démarrage de l'application via un `CommandLineRunner` pour insérer des comptes de test et vérifier la persistance.

### 5 & 6. Web Service RESTful & Tests Postman
Création d'un `RestController` pour exposer manuellement les ressources :
- `GET /bankAccounts` : Liste des comptes.
- `GET /bankAccounts/{id}` : Détails d'un compte.
- `POST /bankAccounts` : Création.
- `PUT /bankAccounts/{id}` : Mise à jour.
- `DELETE /bankAccounts/{id}` : Suppression.
*Tests effectués via Postman pour valider les endpoints.*

### 7. Documentation Swagger (OpenAPI)
Intégration de la documentation interactive.
- **Dépendance utilisée :** `springdoc-openapi-starter-webmvc-ui` (v2.8.13).
- **Accès :** `http://localhost:8081/swagger-ui.html`

### 8. Spring Data REST & Projections
Exposition automatique du dépôt via `@RepositoryRestResource`. Utilisation de **Projections** (ex: `AccountProjection`) pour permettre aux clients de choisir une vue partielle des données (ex: afficher uniquement le solde et le type).

### 9 & 10. Couche Service, DTOs et Mappers
Mise en œuvre d'une architecture propre pour isoler la base de données de l'API :
- **DTOs :** `BankAccountRequestDTO` (entrée) et `BankAccountResponseDTO` (sortie).
- **Mappers :** Transformation des entités en DTOs et vice-versa.
- **Service Layer :** L'interface `AccountService` gère la logique métier (génération des IDs, mappage).


---

##Structure du Projet
```text
src/main/java/org/sid/bankaccountservice
├── entities        # Entités JPA
├── repositories    # Interfaces Spring Data JPA
├── web             # Contrôleurs REST
├── dto             # Data Transfer Objects
├── mappers         # Logique de mapping Entité/DTO
├── service         # Couche Métier (Interface & Impl)
└── enums           # Types de comptes (Enum)
src/main/resources
└── application.properties


Comment lancer le projet ?
Cloner le dépôt :

Bash
git clone [https://github.com/salmaad/bank-account-service.git](https://github.com/salmaad/bank-account-service.git)


Importer le projet dans votre IDE (IntelliJ ou Eclipse).
Lancer l'application via BankAccountServiceApplication.
Accéder aux services :
H2 Console : http://localhost:8081/h2-console
Swagger UI : http://localhost:8081/swagger-ui.html


Réalisé par [AYAD Salma] dans le cadre des travaux pratiques sur les architectures micro-services.
