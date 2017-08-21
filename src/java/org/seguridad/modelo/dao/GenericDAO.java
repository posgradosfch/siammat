package org.seguridad.modelo.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.seguridad.modelo.util.HibernateTemplateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateJdbcException;

public interface GenericDAO {
    public Serializable create(Serializable obj) throws HibernateJdbcException;
     public void saveOrUpdate(Serializable obj) throws HibernateJdbcException;
    public void update(Serializable obj) throws HibernateJdbcException;
    public void delete(Serializable obj) ;
    public Serializable get(Class s,Serializable id) throws HibernateJdbcException;
    public List find(Class s) throws HibernateJdbcException;
    public List find(String query) throws HibernateJdbcException;
    public List find(final String hqlQuery,final int start,final int end) throws HibernateJdbcException;
    public List find(String query,Object[] values);
    public List find(String query,Map values);
    public List find(final String hqlQuery,final Map values,final int start,final int end) throws HibernateJdbcException;
    public List findSQL(final String sqlQuery,final int start,final int end) throws HibernateJdbcException;
    public Boolean executeHQLNonQuery(final String hqlQuery) throws HibernateJdbcException;
    public Boolean executeSQLNonQuery(final String sqlQuery) throws HibernateJdbcException;
    public Object getUniqueValue(final String query);
    public Object getUniqueValue(final String query,final Map m);
    public Object executeInTrans(HibernateCallback hcb);
    public Object executeInTrans(HibernateTemplateCallBack hcb) throws DataAccessException,RuntimeException;
    public Session currentSession();
    public List findByNamedQuery(String queryName,String[] paramNames,Object[] values);
    public void getCollectionInit(Object obj,String collectionName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    public Connection getConexion();
}
