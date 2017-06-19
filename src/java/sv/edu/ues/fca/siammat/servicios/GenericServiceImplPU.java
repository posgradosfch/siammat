/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.servicios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;

/**
 *
 * @author GALICIA
 */
public class GenericServiceImplPU implements GenericService {

    private static EntityManagerFactory factory;
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GenericServiceImplPU(String persistenceUnitName) {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = factory.createEntityManager();
    }

    @Override
    public void remove(Serializable entidad) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entidad);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void remove(List entidades) {

        try {
            entityManager.getTransaction().begin();
            for (Object object : entidades) {
                entityManager.remove(object);
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void save(Serializable entidad) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidad);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.getTransaction().commit();
        }

    }

    public Object getByPK(Class clase, Object PK) {
        Object r = null;
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }

        r = entityManager.find(clase, PK);
        entityManager.getTransaction().commit();
        return r;
    }

    @Override
    public void refresh(Serializable entidad) {
        entityManager.getTransaction().begin();
        entityManager.refresh(entidad);
        entityManager.getTransaction().commit();

    }

    @Override
    public void save(List entidades) {

        try {
            entityManager.getTransaction().begin();
            for (Object object : entidades) {
                entityManager.persist(object);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List find(String query) {
        List l = new ArrayList();
        try {
            entityManager.getTransaction().begin();
            l = this.getEntityManager().createQuery(query).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
        }

        return l;
    }

    public List executeSQL(String sql) {
        List r = null;
        if (!getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().begin();
        }
        r = this.getEntityManager().createNativeQuery(sql).getResultList();
        getEntityManager().getTransaction().commit();
        return r;
    }

    public Connection getConexion() {
        try {
            EntityManagerImpl entityManagerImpl = (EntityManagerImpl) getEntityManager();
            HibernateEntityManagerFactory hibernateEntityManagerFactory = (HibernateEntityManagerFactory) entityManagerImpl.getFactory();

            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();

            return sessionFactoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GenericServiceImplPU.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object getSingle(String query) {
        Object l = null;
        try {
            l = this.getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return l;
    }
}
