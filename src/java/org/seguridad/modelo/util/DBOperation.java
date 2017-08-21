package org.seguridad.modelo.util;

import java.io.Serializable;

/**
 * Representación de una operación de BD a ejecutarse por un ORM.
 * Permite especificar el tipo de operación a realizar, si se requiere efectuar
 * una operación de Flush y el dato a persistir o la instrucción a ejecutar.
 *
 *
 * 
 * @author Dannier Galicia
 */
public class DBOperation {
    public static final int SAVE_OPERATION=1;
    public static final int UPDATE_OPERATION=2;
    public static final int DELETE_OPERATION=3;
    public static final int COMPLEX_OPERATION=4;
    private int operationType;
    public enum FlushSession{NEVER,BEFORE_OPERATION,AFTER_OPERATION};

    /**
     * Indica el tipo de flush a realizar cuando se ejecute la operación.
     */
    private FlushSession flushSession=FlushSession.NEVER;

    /**
     * Dato a persistir.
     */
    private java.io.Serializable data=null;

    /**
     * Query a ejecutarse para los tipos COMPLEX_OPERATION, puede ser SQL o HQL, todo dependerá de
     * como se utilize en el método encargado de ejecutar esta instrucción,
     */
    private String query=null;


    /**
     *
     * @return the flushSession
     */
    public FlushSession getFlushSession() {
        return flushSession;
    }

    /**
     * @param flushSession the flushSession to set
     */
    public void setFlushSession(FlushSession flushSession) {
        this.flushSession = flushSession;
    }

    public DBOperation(String query) {
        this.operationType = COMPLEX_OPERATION;
        this.query = query;
    }

    public DBOperation(Serializable data,int opType) {
        this.operationType = opType;
        this.data=data;
    } /**
     * @return the operationType
     */
    public int getOperationType() {
        return operationType;
    }

    /**
     * @return the data
     */
    public java.io.Serializable getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(java.io.Serializable data) {
        this.data = data;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }


}
