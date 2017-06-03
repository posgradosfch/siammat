/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.ues.fca.siammat.servicios.ServiceLocator;

/**
 *
 * @author galicia
 */
@ManagedBean
@RequestScoped
public class testbeanservices {

    /**
     * Creates a new instance of testbeanservices
     */
    private List<Usuarios> lista;
    public testbeanservices() {
       //
    }

    public List<Usuarios> getLista() {
        if(lista==null)
             lista=ServiceLocator.getBasicService().find("from Usuarios");
        return lista;
    }

    public void setLista(List<Usuarios> lista) {
        this.lista = lista;
    }
    
    
    
}
