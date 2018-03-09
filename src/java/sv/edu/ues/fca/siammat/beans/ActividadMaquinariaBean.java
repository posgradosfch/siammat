/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.modelo.ActividadMaquinaria;
import sv.edu.ues.fca.siammat.modelo.Lote;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.modelo.Apero;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class ActividadMaquinariaBean extends FormBaseBean {

    private ActividadMaquinaria actividadMaquinaria = new ActividadMaquinaria();
    private List<Empleado> empleados;
    private List<Lote> lotes;
    private List<Actividad> actividades;
    private List<Maquinaria> maquinarias;
    private List<Apero> aperos;

    /**
     * Creates a new instance of ActividadMaquinariaBean
     */
    public ActividadMaquinariaBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            actividadMaquinaria = (ActividadMaquinaria) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(actividadMaquinaria);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public ActividadMaquinaria getActividadMaquinaria() {
        return actividadMaquinaria;
    }

    public List<Empleado> getEmpleados() {
        if (empleados == null) {
            empleados = getServiceLocator().getGenericServicio().find("from Empleado e");
        }
        return empleados;
    }

    
    public List<Lote> getLotes() {
        if (lotes == null) {
            lotes = getServiceLocator().getGenericServicio().find("from Lote l");
        }
        return lotes;
    }

    public List<Actividad> getActividades() {
        if (actividades == null) {
            actividades = getServiceLocator().getGenericServicio().find("from Actividad a");
        }
        return actividades;
    }

    public List<Maquinaria> getMaquinarias() {
        if (maquinarias == null) {
            maquinarias = getServiceLocator().getGenericServicio().find("from Maquinaria m");
        }
        return maquinarias;
    }

 public List<Apero> getAperos() {
     if (aperos == null) {
            aperos = getServiceLocator().getGenericServicio().find("from Apero ap");
        }
        return aperos;
    }

    @Override
    public void onSave() {   
        Double gasto=actividadMaquinaria.getCombustibleInicial() - actividadMaquinaria.getCombustibleFinal();
        actividadMaquinaria.setCombustibleGastado(gasto);
    super.onSave();

}
    
}
