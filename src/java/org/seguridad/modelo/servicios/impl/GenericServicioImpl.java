/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.servicios.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map; 
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.impl.SessionFactoryImpl;
import org.postgresql.util.PSQLException;
import org.seguridad.modelo.dao.GenericDAO;
import org.seguridad.modelo.servicios.GenericServicio;
import org.seguridad.modelo.util.DBOperation;
import org.seguridad.modelo.util.HibernateTemplateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateJdbcException;

/**
 *
 * @author Dannier Galicia
 */
public class GenericServicioImpl implements GenericServicio {
    private GenericDAO genericDAO;

    public Serializable save(Serializable s) throws HibernateJdbcException  {
        return genericDAO.create(s);
    }
    
    public void update(Serializable s) throws HibernateJdbcException {
        genericDAO.update(s);
    }

    public void delete(Serializable s) throws  PSQLException,HibernateJdbcException  {
       genericDAO.delete(s);
    }

    public List find(String query,int start,int maxResults) throws HibernateJdbcException {
       return genericDAO.find(query, start, maxResults);
    }

    public Serializable get(Class c, Serializable id) {
           try{
            return genericDAO.get(c,id);
        }catch(HibernateJdbcException hjex){
            hjex.printStackTrace();
        }
        return null;
    }

    public void executeNonQuery(String query) throws HibernateJdbcException {
        genericDAO.executeHQLNonQuery(query);
    }

    public void executeBatchOperations(List<DBOperation> operations)  throws HibernateJdbcException {
            for(DBOperation op:operations){
               if(op.getFlushSession()== DBOperation.FlushSession.BEFORE_OPERATION)
                 this.genericDAO.currentSession().flush();
               if(op.getOperationType()!=DBOperation.COMPLEX_OPERATION){
                   switch(op.getOperationType()){
                       case DBOperation.SAVE_OPERATION:
                           this.genericDAO.create(op.getData());
                       break;
                       case DBOperation.UPDATE_OPERATION:
                           this.genericDAO.update(op.getData());
                       break;
                       case DBOperation.DELETE_OPERATION:
                           this.genericDAO.delete(op.getData());
                       break;
                   }
               }
               else{
                this.genericDAO.executeHQLNonQuery(op.getQuery());
               }
              if(op.getFlushSession()== DBOperation.FlushSession.AFTER_OPERATION)
                 this.genericDAO.currentSession().flush();

            }
    }

    /**
     * @return the genericDAO
     */
    public GenericDAO getGenericDAO() {
        return genericDAO;
    }

    /**
     * @param genericDAO the genericDAO to set
     */
    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public long getRecordsCount(String query) throws HibernateJdbcException {
        //Long tmp=(Long)this.genericDAO.getUniqueValue(query);
        Number tmp=(Number)this.genericDAO.getUniqueValue(query);
        if(tmp!=null)
          return tmp.longValue();
        else
          return -1;
    }

    public long getRecordsCount(String query,Map m) throws HibernateJdbcException {
        Number tmp=(Number)this.genericDAO.getUniqueValue(query,m);

        if(tmp!=null)
          return tmp.longValue();
        else
          return -1;
    }


    public Object executeInTrans(HibernateCallback hbc) throws HibernateJdbcException,DataAccessException,RuntimeException{
        return this.genericDAO.executeInTrans(hbc);
    }

    public Object executeInTrans(HibernateTemplateCallBack hbc) throws HibernateJdbcException,DataAccessException,RuntimeException{
        return this.genericDAO.executeInTrans(hbc);
    }


    public List find(String query) throws HibernateJdbcException {
        return this.genericDAO.find(query);
    }

    public Object getUniqueValue(String query) throws HibernateJdbcException {
        return this.genericDAO.getUniqueValue(query);
    }

    public void getCollectionInit(Object obj, String collectionName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.genericDAO.getCollectionInit(obj, collectionName);
    }

    public List findByNamedQuery(String queryName, String[] paramNames, Object[] values) {
        return this.genericDAO.findByNamedQuery(queryName, paramNames, values);
    }

    public Session currentSession() {
        return this.genericDAO.currentSession();
    }

    public List find(String query,Map values){
        return this.genericDAO.find(query, values);
    }

    public List find(String query, Map params, int start, int maxResults) throws HibernateJdbcException {
        return this.genericDAO.find(query, params, start, maxResults);
    }

    public List find(String query, Object[] values) {
        return this.genericDAO.find(query, values);
    }


    public void saveOrUpdate(Serializable s) throws HibernateJdbcException {
        genericDAO.saveOrUpdate(s);
    }

    public Connection getConexion() {
       return genericDAO.getConexion();
    }

}
