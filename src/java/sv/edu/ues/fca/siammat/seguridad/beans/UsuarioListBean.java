/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class UsuarioListBean extends ListBaseBean{
    public UsuarioListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/usuarios/edit");
    }


    @Override
    public List<Usuario> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from Usuario u";
        return hql;
    }
    
    
}
