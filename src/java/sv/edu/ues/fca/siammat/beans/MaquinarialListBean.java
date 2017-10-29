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
import sv.edu.ues.fca.siammat.modelo.Maquinaria;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class MaquinarialListBean extends ListBaseBean {

    private SimpleFilterElement<String> sfMarca = new SimpleFilterElement<String>("m.marca", SimpleFilterElement.AND, "LIKE");
    private SimpleFilterElement<String> sfModelo = new SimpleFilterElement<String>("m.modelo", SimpleFilterElement.AND, "LIKE");

    public MaquinarialListBean() {
        //Fijando la uri del formulario de edición
        super();
        setPathForm("/maquinaria/edit");
        getFiltros().addFilterElement(sfMarca);
        getFiltros().addFilterElement(sfModelo);
    }

    @Override
    public List<Maquinaria> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems();
    }

    @Override
    public String setupQuery() {
        String hql = "from Maquinaria m";
        String wc = getFiltros().generateWhereClause();
        if (wc != null && !wc.equals("")) {
            hql += " where " + wc;
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

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "500px");
    }

}
