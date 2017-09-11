package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.UnidadTransporte;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author Jaime
 */
@ManagedBean
@ViewScoped
public class UnidadTransporteBean extends FormBaseBean{
    //Entidad del tipo que se está gestionando
    private UnidadTransporte unidadTransporte=new UnidadTransporte();
    public UnidadTransporteBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            unidadTransporte = (UnidadTransporte) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(unidadTransporte);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public UnidadTransporte getUnidadTransporte() {
        return unidadTransporte;
    }


    
}
