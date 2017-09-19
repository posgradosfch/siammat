/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.filtros.BetweenFilterElement;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.ControlCombustible;

/**
 *
 * @author franck
 */
@ManagedBean
@ViewScoped
public class ControlCombustibleListBean extends ListBaseBean{

    /**
     * Creates a new instance of tractorListBean
     */
    private BetweenFilterElement<Date> sfFecha=new BetweenFilterElement<Date>("cc.fecha", SimpleFilterElement.AND);
    private SimpleFilterElement<String> sfPlaca=new SimpleFilterElement<String>("cc.placa", SimpleFilterElement.AND, "=");
    
    public ControlCombustibleListBean() {
        setPathForm("/vales_combustible/edit");
        getFiltros().addFilterElement(sfFecha);
        getFiltros().addFilterElement(sfPlaca);
    }
    
    @Override
    public List<ControlCombustible> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from ControlCombustible cc join fetch cc.responsable r join fetch cc.empleadoRecibe ";
        String wc=getFiltros().generateWhereClause();
        if(wc!=null && !wc.isEmpty()){
            hql+=" where " +wc;
        }
        return hql;
    }

    public SimpleFilterElement<String> getSfPlaca() {
        return sfPlaca;
    }

    public void setSfPlaca(SimpleFilterElement<String> sfPlaca) {
        this.sfPlaca = sfPlaca;
    }

    public BetweenFilterElement<Date> getSfFecha() {
        return sfFecha;
    }

    public void setSfFecha(BetweenFilterElement<Date> sfFecha) {
        this.sfFecha = sfFecha;
    }

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "600px");
    }
    
}
