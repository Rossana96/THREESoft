/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer;

import com.threesoft.amoxcalitimer.exceptions.NonexistentEntityException;
import com.threesoft.amoxcallitimer.model.Academico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rossa
 */
public class AcademicoJpaController implements Serializable {

    public AcademicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Academico academico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(academico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Academico academico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            academico = em.merge(academico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = academico.getIdAcademico();
                if (findAcademico(id) == null) {
                    throw new NonexistentEntityException("The academico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academico academico;
            try {
                academico = em.getReference(Academico.class, id);
                academico.getIdAcademico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The academico with id " + id + " no longer exists.", enfe);
            }
            em.remove(academico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Academico> findAcademicoEntities() {
        return findAcademicoEntities(true, -1, -1);
    }

    public List<Academico> findAcademicoEntities(int maxResults, int firstResult) {
        return findAcademicoEntities(false, maxResults, firstResult);
    }

    private List<Academico> findAcademicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Academico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Academico findAcademico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Academico.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcademicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Academico> rt = cq.from(Academico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
 
    public Academico findAcademicoCorreo(String correo) {
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Alumno.findByCorreoAca")
                .setParameter("correo",correo);
                
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Academico) q.getSingleResult();
    }
    
    public Academico findAcademicoPassword(String password){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Alumno.findByPassword")
                .setParameter("password",password);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Academico) q.getSingleResult();        
    }
    
    public boolean findAcdmcCorreoYPass(String correo, String pass){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Academico.findByCorreoAndPassword")
                .setParameter(1,correo)
                .setParameter(2,pass);
        return !q.getResultList().isEmpty();
    }
    
}
