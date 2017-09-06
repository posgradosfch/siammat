/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.modelo.Lote;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class LoteBean extends FormBaseBean{
    private Lote lote=new Lote();

    /**
     * Creates a new instance of LoteBean
     */
    public LoteBean() {
        super();
        if(getAccion().equals("2")){//Modo edicion, recuperando objeto del padre
            lote =(Lote) getMainObject();
        }else{//Modo inserción, seteando objeto al padre
            setMainObject(lote);
        }
    }
    
    @Override
    public boolean validate() {
        return true;
    }

    public Lote getLote() {
        return lote;
    } 
    
}
