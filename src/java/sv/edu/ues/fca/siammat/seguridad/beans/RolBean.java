package sv.edu.ues.fca.siammat.seguridad.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class RolBean extends FormBaseBean{
    //Entidad del tipo que se está gestionando
    private Rol rol=new Rol();
    public RolBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            rol =(Rol) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(rol);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public Rol getRol() {
        return rol;
    }
    
}
