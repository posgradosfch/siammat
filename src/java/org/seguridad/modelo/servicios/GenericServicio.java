/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.servicios;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.postgresql.util.PSQLException;
import org.seguridad.modelo.util.DBOperation;
import org.seguridad.modelo.util.HibernateTemplateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateJdbcException;

/**
 *
 * @author Dannier Galicia
 */
public interface GenericServicio {
  public Serializable save(Serializable s) throws HibernateJdbcException;
  public void saveOrUpdate(Serializable s) throws HibernateJdbcException;
  public void update(Serializable s) throws HibernateJdbcException;
  public void delete(Serializable s)  throws  PSQLException,HibernateJdbcException  ;
  public long getRecordsCount(String query) throws HibernateJdbcException;
  public long getRecordsCount(String query,Map m) throws HibernateJdbcException;
  public Object getUniqueValue(String query) throws HibernateJdbcException;
  public List find(String query,int start,int maxResults) throws HibernateJdbcException;
  public List find(String query,java.util.Map params,int start,int maxResults) throws HibernateJdbcException;
  public List find(String query) throws HibernateJdbcException;
  public List find(String query,Map values);
  public List find(String query,Object[] values);
  public void executeBatchOperations(List<DBOperation> operations) throws HibernateJdbcException;
  public Serializable get(Class c,Serializable id) throws HibernateJdbcException;
  public Object executeInTrans(HibernateCallback hbc)throws HibernateJdbcException;
  public Object executeInTrans(HibernateTemplateCallBack hbc) throws HibernateJdbcException,DataAccessException,RuntimeException;
  public List findByNamedQuery(String queryName,String[] paramNames,Object[] values);
  public void getCollectionInit(Object obj,String collectionName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  public Session currentSession();
   public Connection getConexion();
}
