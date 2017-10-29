/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;

/**
 *
 * @author franck
 */
@ManagedBean
@ViewScoped
public class ActividadBean extends FormBaseBean{
    private Actividad actividad=new Actividad();

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
   public void validacion(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
      if ((((String)arg2).length()>3)||(((String)arg2).length()<3)) {
         throw new ValidatorException(new FacesMessage("La abreviatura debe ser de 3 digitos!!"));
      }
   }
}
