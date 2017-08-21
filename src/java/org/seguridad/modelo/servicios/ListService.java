/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.servicios;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateJdbcException;

/**
 *
 * @author Dannier Galicia
 */
public interface  ListService {
  public List find(String query,int start,int maxResults)throws HibernateJdbcException;
  public List find(String query)throws HibernateJdbcException;
}
