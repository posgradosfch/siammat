/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.CuidadoMaquinaria;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class CuidadoMaquinariaBean extends FormBaseBean {
    private CuidadoMaquinaria cuidadoMaquinaria = new CuidadoMaquinaria();
    private List<Empleado> empleados;
    private List<Maquinaria> Maquinaria;

    /**
     * Creates a new instance of CuidadoMaquinariaBean
     */
    public CuidadoMaquinariaBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            cuidadoMaquinaria = (CuidadoMaquinaria) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(cuidadoMaquinaria);
        }
    }
    
    @Override
    public boolean validate() {
        return true;
    }

    public CuidadoMaquinaria getCuidadoMaquinaria() {
        return cuidadoMaquinaria;
    }

    public List<Empleado> getEmpleados() {
        if (empleados == null) {
            empleados = getServiceLocator().getGenericServicio().find("from Empleado e");
        }
        return empleados;
    }

    public List<Maquinaria> getMaquinarias() {
        if (Maquinaria == null) {
            Maquinaria = getServiceLocator().getGenericServicio().find("from Maquinaria m");
        }
        return Maquinaria;
    }
    
     @Override
    public void onSave() {   
        Float gasto=cuidadoMaquinaria.getCostoTaller()+cuidadoMaquinaria.getCostoMaterial();
        cuidadoMaquinaria.setGastoTotal(gasto);
    super.onSave();
}
}
