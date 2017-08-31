/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class RolListBean extends ListBaseBean{
    public RolListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/roles/edit");
    }
    
    @PostConstruct
    private void init(){
        onSearch();
    }
    
    @Override
    public List<Rol> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from Rol r";
        return hql;
    }
    
    
}
