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
import sv.edu.ues.fca.siammat.filtros.BetweenFilterElement;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.modelo.ActividadMaquinaria;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.modelo.Apero;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class ActividadMaquinariaListBean extends ListBaseBean {
    private List<Actividad> actividadList;
    private List<Maquinaria> maquinariaList;
    private BetweenFilterElement<Date> sfFecha=new BetweenFilterElement<Date>("am.fecha", SimpleFilterElement.AND);
    //private SimpleFilterElement<Date> sfFecha = new SimpleFilterElement<Date>("am.fecha", SimpleFilterElement.AND, "=");
    private SimpleFilterElement<Integer> sfActividad= new SimpleFilterElement<Integer>("am.idActividad.idActividad", SimpleFilterElement.AND, "=");
    private SimpleFilterElement<Integer> sfMaquinaria= new SimpleFilterElement<Integer>("am.idMaquinaria.idMaquinaria", SimpleFilterElement.AND, "=");
    /**
     * Creates a new instance of ActividadMaquinariaListBean
     */
    public ActividadMaquinariaListBean() {
        setPathForm("/actividad_maquinaria/edit");
        getFiltros().addFilterElement(sfFecha);
        getFiltros().addFilterElement(sfMaquinaria);
        getFiltros().addFilterElement(sfActividad);
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
        }else{
            Util.addErrorMessage("Debe usar por lo menos un filtro");
                return "";
        }
        return hql;
        
    }    



    /**
     * @return the sfActividad
     */
    public SimpleFilterElement<Integer> getSfActividad() {
        return sfActividad;
    }

    /**
     * @param sfActividad the sfActividad to set
     */
    public void setSfActividad(SimpleFilterElement<Integer> sfActividad) {
        this.sfActividad = sfActividad;
    }

    public BetweenFilterElement<Date> getSfFecha() {
        return sfFecha;
    }

    public void setSfFecha(BetweenFilterElement<Date> sfFecha) {
        this.sfFecha = sfFecha;
    }

    public List<Actividad> getActividadList() {
        if (actividadList == null) {
            actividadList = getServiceLocator().getGenericServicio().find("from Actividad");
        }
        return actividadList;
    }

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", 500);
        getParametros().put("position", "top");
        getParametros().put("fitViewport", true);
        getParametros().put("showEffect", "fade");

    }

    public SimpleFilterElement<Integer> getSfMaquinaria() {
        return sfMaquinaria;
    }

    public void setSfMaquinaria(SimpleFilterElement<Integer> sfMaquinaria) {
        this.sfMaquinaria = sfMaquinaria;
    }

    public List<Maquinaria> getMaquinariaList() {
        if(maquinariaList==null){
            maquinariaList=getServiceLocator().getGenericServicio().find("from Maquinaria");
        }
        
        return maquinariaList;
    }

    public void setMaquinariaList(List<Maquinaria> maquinariaList) {
        this.maquinariaList = maquinariaList;
    }

   
    
}
