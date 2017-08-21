/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.impl.SessionFactoryImpl;
import org.seguridad.modelo.dao.GenericDAO;
import org.seguridad.modelo.util.HibernateTemplateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Dannier Galicia
 */
public class GenericDAOHibernateImpl extends HibernateDaoSupport implements GenericDAO{

    public Serializable create(Serializable obj) throws HibernateJdbcException {
        return getHibernateTemplate().save(obj);
    }
    
    public void saveOrUpdate(Serializable obj) throws HibernateJdbcException {
        getHibernateTemplate().saveOrUpdate(obj);
    }


    public void update(Serializable obj) throws HibernateJdbcException {
        getHibernateTemplate().update(obj);
    }

    public void delete(Serializable obj) {
        getHibernateTemplate().delete(obj);
    }

    public Serializable get(Class s, Serializable id) throws HibernateJdbcException {
         return (Serializable)getHibernateTemplate().get(s, id);
    }

    public List find(Class s) throws HibernateJdbcException {
        return getHibernateTemplate().loadAll(s);
    }

    public List find(String query) throws HibernateJdbcException {
        return getHibernateTemplate().find(query);
    }

    public List find(String query,Object[] values){
        return getHibernateTemplate().find(query, values);
    }



    public List find(final String hqlQuery, final int start,final int end) throws HibernateJdbcException {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                return arg0.createQuery(hqlQuery)
                           .setFirstResult(start)
                           .setMaxResults(end)
                           .list();
            }
        });
    }

    public List find(final String hqlQuery,final Map props, final int start,final int end) throws HibernateJdbcException {
        //return getHibernateTemplate().find(hqlQuery, params);
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                return arg0.createQuery(hqlQuery)
                            .setProperties(props)
                           .setFirstResult(start)
                           .setMaxResults(end)
                           .list();
            }
        });
    }

    public List find(final String query, final Map values) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                return arg0.createQuery(query)
                            .setProperties(values)
                           .list();
            }
        });

    }


    public List findSQL(final String sqlQuery,final int start,final int end) throws HibernateJdbcException {
        return getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {                
                return arg0.createSQLQuery(sqlQuery)
                           .setFirstResult(start)
                           .setMaxResults(end).list();
            }
        });
    }

    public Boolean executeHQLNonQuery(final String hqlQuery) throws HibernateJdbcException {
        return (Boolean)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                 arg0.createQuery(hqlQuery).executeUpdate();                 
                 return Boolean.valueOf(true);
            }
        });
    }

    public Boolean executeSQLNonQuery(final String sqlQuery) throws HibernateJdbcException {
        return (Boolean)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                 arg0.createSQLQuery(sqlQuery).executeUpdate();
                 return Boolean.valueOf(true);
            }
        });
    }

    public Object getUniqueValue(final String query) {
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                 //return arg0.createSQLQuery(query).uniqueResult();
                 return arg0.createQuery(query).uniqueResult();
            }
        });

    }

    public Object getUniqueValue(final String query,final Map m) {
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
                 //return arg0.createSQLQuery(query).uniqueResult();
                 return arg0.createQuery(query).setProperties(m).uniqueResult();
            }
        });

    }


    public Object executeInTrans(HibernateCallback hcb) throws DataAccessException,RuntimeException{
          return getHibernateTemplate().execute(hcb);
    }

    public Object executeInTrans(HibernateTemplateCallBack hcb) throws DataAccessException,RuntimeException{
          hcb.setHibernateTemplate(getHibernateTemplate());
          return getHibernateTemplate().execute(hcb);
    }

    public Session currentSession(){
        return this.getSession();
    }

    public List findByNamedQuery(String queryName,String[] paramNames,Object[] values){
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
    }


    public void getCollectionInit(Object obj,String collectionName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method m=obj.getClass().getMethod(collectionName, (Class[])null);
        getHibernateTemplate().initialize(m.invoke(obj,new Object[]{}));        
    }

    @Override
    public Connection getConexion() {
               try {
            SessionFactoryImpl factoryImpl = (SessionFactoryImpl) currentSession().getSessionFactory();
            return factoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAOHibernateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
