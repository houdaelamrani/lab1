# Lab 1: Création d'un projet Maven avec Hibernate et H2

Ce dépôt contient le TP 1 sur Hibernate & JPA. 
Toutes les étapes requises et les exercices supplémentaires ont été implémentés.

## Fonctionnalités (Exercices et Exercices Supplémentaires)
- Configuration de Maven (groupId, artifactId) avec dépendances JPA, Hibernate Core, H2 et SLF4J.
- `persistence.xml` correctement configuré avec `hbm2ddl.auto=update`.
- Entité `Produit` avec annotations `@Entity`, `@Id`, `@GeneratedValue`.
- Entité supplémentaire `Categorie` avec une relation `OneToMany`/`ManyToOne` vers `Produit`.
- Classe `App` avec gestion d'`EntityManager` pour réaliser le CRUD.
- Lancement de la console web H2 accessible sur `http://localhost:8082`.
- Recherches personnalisées (par prix), mise à jour et suppression de produits.

## Instructions d'exécution
Pour lancer l'application :

```bash
mvn compile exec:java -Dexec.mainClass="com.example.App"
```

### Résultats attendus
Le programme va :
1. Démarrer le serveur H2
2. Insérer une catégorie et 3 produits
3. Afficher la liste de tous les produits
4. Afficher les produits dont le prix est supérieur à 500
5. Mettre à jour le prix du "Smartphone"
6. Supprimer la "Tablette"
7. Afficher la liste finale des produits

<img width="960" height="540" alt="Capture d'écran" src="https://github.com/user-attachments/assets/89f04949-083b-43cd-93f3-17ff61dde49a" />
