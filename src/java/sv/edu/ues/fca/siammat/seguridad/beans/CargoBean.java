package sv.edu.ues.fca.siammat.seguridad.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Cargo;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class CargoBean extends FormBaseBean{
    //Entidad del tipo que se está gestionando
    private Cargo cargo=new Cargo();
    public CargoBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            cargo =(Cargo) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(cargo);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public Cargo getCargo() {
        return cargo;
    }

    
}
