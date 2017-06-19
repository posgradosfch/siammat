/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class PrivilegioListBean extends ListBaseBean {
    private SimpleFilterElement<Integer> sfRol = new SimpleFilterElement<Integer>("p.rol.idRol", SimpleFilterElement.AND, "=");

    public PrivilegioListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/privilegios/edit");
        getFiltros().addFilterElement(sfRol);
    }

    @Override
    public String setupQuery() {
        String hql = "from Privilegios p";
        return hql;
    }

    @Override
    public List<Privilegio> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems();
    }

}
