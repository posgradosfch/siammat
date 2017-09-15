/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.UnidadTransporte;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class UnidadTransporteListBean extends ListBaseBean{
    private SimpleFilterElement<String> sfMarca = new SimpleFilterElement<String>("u.marca", SimpleFilterElement.AND, "LIKE");
    private SimpleFilterElement<String> sfModelo= new SimpleFilterElement<String>("u.modelo", SimpleFilterElement.AND, "LIKE");
    private SimpleFilterElement<Integer> sfTipo = new SimpleFilterElement<Integer>("u.tipo",SimpleFilterElement.AND,"=");
    public UnidadTransporteListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/unidad_transporte/edit");
        getFiltros().addFilterElement(sfMarca);
        getFiltros().addFilterElement(sfModelo);
        getFiltros().addFilterElement(sfTipo);
    }
    
    @Override
    public List<UnidadTransporte> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from UnidadTransporte u";
        String wc = getFiltros().generateWhereClause();
        if(wc!=null && !wc.equals("")){
            hql+= " where "+wc;
        }
        return hql;
    }

    @Override
    public void onRemove(Serializable object) {
        super.onRemove(object); //To change body of generated methods, choose Tools | Templates.
        this.getItems().remove(object);
    }
    
    
    public SimpleFilterElement<String> getSfMarca() {
        return sfMarca;
    }

    public void setSfMarca(SimpleFilterElement<String> sfMarca) {
        this.sfMarca = sfMarca;
    }

    public SimpleFilterElement<String> getSfModelo() {
        return sfModelo;
    }

    public void setSfModelo(SimpleFilterElement<String> sfModelo) {
        this.sfModelo = sfModelo;
    }

    public SimpleFilterElement<Integer> getSfTipo() {
        return sfTipo;
    }

    public void setSfTipo(SimpleFilterElement<Integer> sfTipo) {
        this.sfTipo = sfTipo;
    }
    
}
