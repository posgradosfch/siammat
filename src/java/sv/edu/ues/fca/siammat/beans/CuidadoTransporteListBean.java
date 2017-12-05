/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.filtros.BetweenFilterElement;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.CuidadoTransporte;
import sv.edu.ues.fca.siammat.modelo.UnidadTransporte;

/**
 *
 * @author Jaime
 */
@ManagedBean
@ViewScoped
public class CuidadoTransporteListBean extends ListBaseBean{
     private SimpleFilterElement<Integer> sfFactura = new SimpleFilterElement<Integer>("c.factura", SimpleFilterElement.AND, "=");
     private SimpleFilterElement<String> sfUnidadPlaca = new SimpleFilterElement<String>("c.idUnidad.placa",SimpleFilterElement.AND,"LIKE");
   
    /**
     * Creates a new instance of CuidadoTransporteListBean
     */
    public CuidadoTransporteListBean() {
        setPathForm("/cuidado_transporte/edit");
        getFiltros().addFilterElement(sfFactura);
        getFiltros().addFilterElement(sfUnidadPlaca);

    }
    

    @Override
    public List<CuidadoTransporte> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from CuidadoTransporte c";
        String filtro = getFiltros().generateWhereClause();
        if(filtro!=null && !filtro.equals("")){
            hql+= " where "+filtro;
        }

        return hql;
        
    } 
    
    @Override
    public void onRemove(Serializable object) {
        super.onRemove(object); //To change body of generated methods, choose Tools | Templates.
        this.getItems().remove(object);
    }

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "500px");
    }

    public SimpleFilterElement<Integer> getSfFactura() {
        return sfFactura;
    }

    public void setSfFactura(SimpleFilterElement<Integer> sfFactura) {
        this.sfFactura = sfFactura;
    }

    public SimpleFilterElement<String> getSfUnidadPlaca() {
        return sfUnidadPlaca;
    }

    public void setSfUnidadPlaca(SimpleFilterElement<String> sfUnidadPlaca) {
        this.sfUnidadPlaca = sfUnidadPlaca;
    }

    
}
