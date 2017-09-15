/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.modelo.Actividad;

/**
 *
 * @author franck
 */
@ManagedBean
@ViewScoped
public class ActividadListBean extends ListBaseBean{

    /**
     * Creates a new instance of tractorListBean
     */
    public ActividadListBean() {
        setPathForm("/actividad/edit");
    }
    
    @Override
    public List<Actividad> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from Actividad a ";
        return hql;
    }
    
         @Override
    public void doAfterServiceLocatorSet() {
        onSearch();
    }

    @Override
    public void doAfterRemove(Serializable removedObject) {
        onSearch();
    }
    
    
}
