/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.modelo.CuidadoTransporte;
import sv.edu.ues.fca.siammat.modelo.UnidadTransporte;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author Jaime
 */
@ManagedBean
@ViewScoped
public class CuidadoTransporteBean extends FormBaseBean {

    private CuidadoTransporte cuidadoTransporte = new CuidadoTransporte();
    private List<Empleado> empleados;
    private List<UnidadTransporte> unidadesTransporte;

    /**
     * Creates a new instance of CuidadoTransporteBean
     */
    public CuidadoTransporteBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            cuidadoTransporte = (CuidadoTransporte) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(cuidadoTransporte);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public CuidadoTransporte getCuidadoTransporte() {
        return cuidadoTransporte;
    }

    public List<Empleado> getEmpleados() {
        if (empleados == null) {
            empleados = getServiceLocator().getGenericServicio().find("from Empleado e ORDER BY apellido ASC");
        }
        return empleados;
    }

    public List<UnidadTransporte> getUnidadTransportes() {
        if (unidadesTransporte == null) {
            unidadesTransporte = getServiceLocator().getGenericServicio().find("from UnidadTransporte u ORDER BY marca ASC");
        }
        return unidadesTransporte;
    }

    
}
