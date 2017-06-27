package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class MaquinariaBean extends FormBaseBean{
    //Entidad del tipo que se está gestionando
    private Maquinaria maquinaria=new Maquinaria();
    public MaquinariaBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            maquinaria = (Maquinaria) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(maquinaria);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }


    
}
