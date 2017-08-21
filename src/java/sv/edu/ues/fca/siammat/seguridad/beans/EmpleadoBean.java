package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Cargo;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class EmpleadoBean extends FormBaseBean {

    //Entidad del tipo que se está gestionando
    private Empleado empleado = new Empleado();
    private List<Cargo> cargos;

    public EmpleadoBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            empleado = (Empleado) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(empleado);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public List<Cargo> getCargos() {
        if (cargos == null) {
            cargos = getServiceLocator().getGenericServicio().find("from Cargo c");
        }
        return cargos;
    }

}
