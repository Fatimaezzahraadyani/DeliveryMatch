# 🚚 DeliveryMatch

**DeliveryMatch** est une application web collaborative qui met en relation des expéditeurs de colis et des conducteurs disposant d’espace libre dans leur véhicule. Le but est d’optimiser les trajets, réduire les coûts de transport et limiter l’impact environnemental.

---

## 📌 Fonctionnalités principales

### Utilisateur
- Création et gestion de compte
- Connexion / déconnexion sécurisée
- Modification des informations personnelles

### Conducteur
- Publication d’annonces de trajet (lieu, étapes, capacité, type de marchandise)
- Consultation des demandes reçues
- Acceptation ou refus de demandes
- Historique des trajets effectués

### Expéditeur
- Recherche et filtrage des trajets disponibles
- Envoi de demandes de transport (dimensions, poids, type de colis)
- Historique des colis envoyés

### Administrateur
- Dashboard de gestion des utilisateurs, annonces et demandes
- Validation/suspension de comptes et vérification d’identité
- Statistiques dynamiques avec Chart.js

---

## 🧱 Architecture technique

- **Front-end** : Angular 16+, Angular Material, Bootstrap  
- **Back-end** : Spring Boot, Spring Security, JWT  
- **Base de données** : MySQL  
- **Déploiement local** : Docker  
- **Authentification** : JSON Web Token (JWT)

---

## Diagrammes UMLs

### Diagramme de séquence
![diagramme de sequence](https://github.com/user-attachments/assets/9ebf8bc0-cd12-4b58-8798-1cf6c279a778)

### Diagramme de cas d'utilisation
<img width="443" alt="uml DMUS" src="https://github.com/user-attachments/assets/d91da0c7-c2e7-445d-9e40-ea2bf3716300" />

### Diagramme de classes
<img width="576" alt="dm classD" src="https://github.com/user-attachments/assets/13f63d46-6b7a-4a74-9029-5027825a3505" />

