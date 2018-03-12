/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.filtros.BetweenFilterElement;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.CuidadoMaquinaria;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class CuidadoMaquinariaListBean extends ListBaseBean{
    private SimpleFilterElement<Date> sfFecha = new SimpleFilterElement<Date>("cm.fecha", SimpleFilterElement.AND, "=");
    private SimpleFilterElement<Integer> sfFactura = new SimpleFilterElement<Integer>("cm.factura", SimpleFilterElement.AND, "=");
    //private SimpleFilterElement<String> sfMaquinaria=new SimpleFilterElement<String>("cm.idMaquinaria.modelo", SimpleFilterElement.AND, "=");

    /**
     * Creates a new instance of CuidadoMaquinariaListBean
     */
    public CuidadoMaquinariaListBean() {
        setPathForm("/cuidado_maquinaria/edit");
        getFiltros().addFilterElement(sfFactura);
        getFiltros().addFilterElement(sfFecha);
        //getFiltros().addFilterElement(sfMaquinaria);
    }
    
        @Override
    public List<CuidadoMaquinaria> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from CuidadoMaquinaria cm";
        String filtro = getFiltros().generateWhereClause();
        if(filtro!=null && !filtro.equals("")){
            hql+= " where "+filtro;
        }
        return hql;       
    } 

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "600px");
    }

    public SimpleFilterElement<Integer> getSfFactura() {
        return sfFactura;
    }

    public void setSfFactura(SimpleFilterElement<Integer> sfFactura) {
        this.sfFactura = sfFactura;
    }
    
       /**
     * @return the sfFecha
     */
    public SimpleFilterElement<Date> getSfFecha() {
        return sfFecha;
    }


    public void setSfFecha(SimpleFilterElement<Date> sfFecha) {
        this.sfFecha = sfFecha;
    }

}
