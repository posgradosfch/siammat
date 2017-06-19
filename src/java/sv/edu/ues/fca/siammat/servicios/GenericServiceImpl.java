package sv.edu.ues.fca.siammat.servicios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

/**
 * Servicio con los métodos basicos para el manejo de entidades
 *
 *
 * @author galicia
 */
public class GenericServiceImpl implements GenericService{

    private static SessionFactory sessionFactory;
    private Session session;

    public GenericServiceImpl() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }

    public void save(Serializable entidad) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(entidad);
        session.getTransaction().commit();
    }

    /**
     * Elimina la entidad
     *
     * @param entidad
     */
    public void remove(Serializable entidad) {
        session = sessionFactory.getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        session.delete(entidad);
        session.getTransaction().commit();
    }

    /**
     * Elimina una lista de entidades
     *
     * @param entidades
     */
    public void remove(List entidades) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Object object : entidades) {
            session.delete(object);
        }
    }
    
    

    /**
     * Refresca el objeto a su estado original
     *
     * @param entidad
     */
    public void refresh(Serializable entidad) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.refresh(entidad);
        session.getTransaction().commit();
    }

    /**
     * Guarda una lista de entidades
     *
     * @param entidades Lista de entidades a guardar
     */
    public void save(List entidades) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Object object : entidades) {
            session.saveOrUpdate(object);
        }
        session.getTransaction().commit();
    }

    /**
     *
     * @param hql Consulta en formato hql
     * @return lista de registros
     */
    public List find(String hql) {
        List lista = new ArrayList();
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        lista = session.createQuery(hql).list();
        session.getTransaction().commit();
        return lista;
    }

    /**
     *
     * @return La conexion activa manejada por hibernate
     * @throws SQLException
     */
    @Override
    public Connection getConexion() {
        try {
            SessionFactoryImpl factoryImpl = (SessionFactoryImpl) getSessionFactory();
            return factoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GenericServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }
    /**
     * Retorna un solo objeto
     * @param query La consulta que devuelve un objeto
     * @return  el objeto encontrado
     */
    @Override
    public Object getSingle(String query) {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object o = session.createQuery(query).uniqueResult();
        session.getTransaction().commit();
        return o;
    }

}
