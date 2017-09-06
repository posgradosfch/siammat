/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class EmpleadoListBean extends ListBaseBean{
    public EmpleadoListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/empleados/edit");
    }
    
    @Override
    public List<Empleado> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from Empleado e join fetch e.cargo";
        return hql;
    }
    
    @PostConstruct
    private void init(){
        onSearch();
    }
    
    
}
