/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seguridad.modelo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seguridad.modelo.servicios.GenericServicio;



/**
 *
 * @author Dannier Galicia
 */
public class ServiceLocator {
    private GenericServicio genericServicio;
    private static final Log logger = LogFactory.getLog(ServiceLocator.class);
    
    public ServiceLocator() {
            logger.debug("ServiceLocatorBean Creado");
                //(GenericServicio)SpringUtil.getBean("genericServicio");
                //(GenericServicio)(new DelegatingVariableResolver()).resolveVariable("genericServicio");
    }

    /**
     * @return the genericServicio
     */
    public GenericServicio getGenericServicio() {
        return genericServicio;
    }

    /**
     * @param genericServicio the genericServicio to set
     */
    public void setGenericServicio(GenericServicio genericServicio) {
       if(this.genericServicio==null)
        this.genericServicio = genericServicio;
    }



}
