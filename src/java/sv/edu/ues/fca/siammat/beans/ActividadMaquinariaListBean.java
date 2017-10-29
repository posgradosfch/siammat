/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.filtros.BetweenFilterElement;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.modelo.ActividadMaquinaria;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class ActividadMaquinariaListBean extends ListBaseBean{
    private List<Actividad> actividades;
    private BetweenFilterElement<Date> sfFecha=new BetweenFilterElement<Date>("am.fecha", SimpleFilterElement.AND);
    //private SimpleFilterElement<Date> sfFecha = new SimpleFilterElement<Date>("am.fecha", SimpleFilterElement.AND, "=");
    private SimpleFilterElement<String> sfActividad= new SimpleFilterElement<String>("am.idActividad.abreviatura", SimpleFilterElement.AND, "LIKE");
    /**
     * Creates a new instance of ActividadMaquinariaListBean
     */
    public ActividadMaquinariaListBean() {
        setPathForm("/actividad_maquinaria/edit");
        getFiltros().addFilterElement(sfFecha);
        getFiltros().addFilterElement(sfActividad);
    }
    
    @PostConstruct
    private void init(){
        onSearch();
    }
    @Override
    public List<ActividadMaquinaria> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from ActividadMaquinaria am";
        String filtro = getFiltros().generateWhereClause();
        if(filtro!=null && !filtro.equals("")){
            hql+= " where "+filtro;
        }

        return hql;
        
    }    



    /**
     * @return the sfActividad
     */
    public SimpleFilterElement<String> getSfActividad() {
        return sfActividad;
    }

    /**
     * @param sfActividad the sfActividad to set
     */
    public void setSfActividad(SimpleFilterElement<String> sfActividad) {
        this.sfActividad = sfActividad;
    }

    /**
     * @return the sfFecha
     
    public SimpleFilterElement<Date> getSfFecha() {
        return sfFecha;
    }*/

    /**
     * @param sfFecha the sfFecha to set
     
    public void setSfFecha(SimpleFilterElement<Date> sfFecha) {
        this.sfFecha = sfFecha;
    }*/
    
    public BetweenFilterElement<Date> getSfFecha() {
        return sfFecha;
    }

    public void setSfFecha(BetweenFilterElement<Date> sfFecha) {
        this.sfFecha = sfFecha;
    }

    public List<Actividad> getActividades() {
        if (actividades == null) {
            actividades = getServiceLocator().getGenericServicio().find("from Actividad a");
        }
        return actividades;
    }

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "650px");
    }


    
}
