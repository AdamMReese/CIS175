package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Developer;

public class DeveloperHelper {

    // Create EntityManagerFactory
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VideoGameDB");

    // Insert Developer
    public void insertDeveloper(Developer d) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();
    }

    // View All Developers
    public List<Developer> viewAllDevelopers() {
        EntityManager em = emfactory.createEntityManager();
        TypedQuery<Developer> typedQuery = em.createQuery("SELECT d FROM Developer d", Developer.class);
        List<Developer> viewAllDevelopers = typedQuery.getResultList();
        em.close();
        return viewAllDevelopers;
    }

    // Search Developer by ID
    public Developer searchForDeveloperById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Developer found = em.find(Developer.class, idToEdit);
        em.close();
        return found;
    }

    // Update Developer
    public void updateDeveloper(Developer toEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(toEdit);
        em.getTransaction().commit();
        em.close();
    }

    // Delete Developer
    public void deleteDeveloper(Developer toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Developer> typedQuery = em.createQuery("SELECT d FROM Developer d WHERE d.id = :selectedId",
                Developer.class);
        typedQuery.setParameter("selectedId", toDelete.getId());
        typedQuery.setMaxResults(1);
        Developer result = typedQuery.getSingleResult();
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }
}