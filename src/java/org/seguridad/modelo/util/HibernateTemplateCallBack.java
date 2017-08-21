/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.util;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
/**
 *
 * @author Dannier Galicia
 */
public class HibernateTemplateCallBack implements HibernateCallback{
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplateCallBack() {

    }


    public HibernateTemplateCallBack(HibernateTemplate ht) {
        this.hibernateTemplate=ht;
    }



    public Object doInHibernate(Session sn) throws HibernateException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}
