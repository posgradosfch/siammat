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
import sv.edu.ues.fca.siammat.modelo.Maquinaria;

/**
 *
 * @author franck
 */
@ManagedBean
@ViewScoped
public class ActividadBean extends FormBaseBean{
    private Actividad actividad=new Actividad();
    private List<Maquinaria> maquinarias;

    /**
     * Creates a new instance of actividadBean
     */
    public ActividadBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            actividad =(Actividad) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(actividad);
        }
    }
    
     @Override
    public boolean validate() {
        return true;
    }

    public Actividad getActividad() {
        return actividad;
    }   
   
    public List<Maquinaria> getMaquinarias() {
        if (maquinarias == null) {
            maquinarias = getBasicService().find("from Maquinaria m");
        }
        return maquinarias;
    }
}
