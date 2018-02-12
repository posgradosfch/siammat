/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Apero;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author negra-ii
 */
@ManagedBean
@ViewScoped
public class AperoBean extends FormBaseBean {

    private Apero apero = new Apero();

    public AperoBean() {
        super();
        if (getAccion().equals("2")) {
            apero = (Apero) getMainObject();
        } else {
            setMainObject(apero);
        }
    }

    @Override
    public boolean validate() {
        return true;

    }

    public Apero getApero() {
        return apero;
    }

}
