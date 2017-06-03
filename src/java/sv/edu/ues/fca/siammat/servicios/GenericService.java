package sv.edu.ues.fca.siammat.servicios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class GenericService {

    private static SessionFactory sessionFactory;

    public GenericService() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public void save(Serializable entidad) {
        Session session = sessionFactory.getCurrentSession();
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
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entidad);
        session.getTransaction().commit();
    }

    /**
     * Elimina una lista de entidades
     *
     * @param entidades
     */
    public void remove(List entidades) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Object object : entidades) {
            session.delete(object);
        }
        session.getTransaction().commit();
    }

    /**
     * Refresca el objeto a su estado original
     *
     * @param entidad
     */
    public void refresh(Serializable entidad) {
        Session session = sessionFactory.getCurrentSession();
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
        Session session = sessionFactory.getCurrentSession();
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
        Session session = getSessionFactory().getCurrentSession();
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
    public Connection getConexion() throws SQLException {
        SessionFactoryImpl factoryImpl = (SessionFactoryImpl) getSessionFactory();
        return factoryImpl.getConnectionProvider().getConnection();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
