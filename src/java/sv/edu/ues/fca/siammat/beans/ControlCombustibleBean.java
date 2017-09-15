/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sv.edu.ues.fca.siammat.modelo.ControlCombustible;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author franck
 */
@ManagedBean
@ViewScoped
public class ControlCombustibleBean extends FormBaseBean{
    private ControlCombustible control=new ControlCombustible();
    private List<Empleado> empleados;

    /**
     * Creates a new instance of actividadBean
     */
    public ControlCombustibleBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            control = (ControlCombustible) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(control);
        }
    }
    
     @Override
    public boolean validate() {
        return true;
    }

    public ControlCombustible getControl() {
        return control;
    }

    public void setControl(ControlCombustible control) {
        this.control = control;
    }

    public List<Empleado> getEmpleados() {
        if(empleados==null){
            empleados=getServiceLocator().getGenericServicio().find("from Empleado e");
        }
        return empleados;
    }
}
