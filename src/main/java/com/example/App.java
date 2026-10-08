package com.example;

import com.example.model.Categorie;
import com.example.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.h2.tools.Server;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Server webServer = null;
        try {
            // Démarrer le serveur H2
            webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("Console H2 démarrée: http://localhost:8082");
            System.out.println("JDBC URL: jdbc:h2:mem:testdb; user: sa; password: ");

            // Initialiser JPA
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo-pu");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            // Créer une catégorie
            Categorie cat1 = new Categorie("Électronique");
            em.persist(cat1);

            // Insérer des produits
            Produit p1 = new Produit("Ordinateur Portable", 1200.50, cat1);
            Produit p2 = new Produit("Smartphone", 800.00, cat1);
            Produit p3 = new Produit("Tablette", 300.00, cat1);
            
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            em.getTransaction().commit();

            // Lire et afficher les produits
            System.out.println("\n--- Liste de tous les produits ---");
            TypedQuery<Produit> queryAll = em.createQuery("SELECT p FROM Produit p", Produit.class);
            List<Produit> produits = queryAll.getResultList();
            for (Produit p : produits) {
                System.out.println(p);
            }

            // Recherche par prix (Exercice supplémentaire)
            System.out.println("\n--- Recherche de produits avec un prix > 500 ---");
            TypedQuery<Produit> queryPrice = em.createQuery("SELECT p FROM Produit p WHERE p.prix > :prixMin", Produit.class);
            queryPrice.setParameter("prixMin", 500.00);
            List<Produit> produitsChers = queryPrice.getResultList();
            for (Produit p : produitsChers) {
                System.out.println(p);
            }

            // Mise à jour d'un produit (Exercice supplémentaire)
            System.out.println("\n--- Mise à jour du prix du Smartphone ---");
            em.getTransaction().begin();
            Produit pUpdate = em.find(Produit.class, p2.getId());
            if (pUpdate != null) {
                pUpdate.setPrix(750.00);
                em.merge(pUpdate);
            }
            em.getTransaction().commit();

            // Suppression d'un produit (Exercice supplémentaire)
            System.out.println("\n--- Suppression de la Tablette ---");
            em.getTransaction().begin();
            Produit pDelete = em.find(Produit.class, p3.getId());
            if (pDelete != null) {
                em.remove(pDelete);
            }
            em.getTransaction().commit();

            // Afficher la liste finale
            System.out.println("\n--- Liste finale des produits ---");
            produits = em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
            for (Produit p : produits) {
                System.out.println(p);
            }

            System.out.println("\nLe traitement est terminé. Le serveur H2 s'arrêtera.");

            em.close();
            emf.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (webServer != null) {
                webServer.stop();
            }
        }
    }
}
