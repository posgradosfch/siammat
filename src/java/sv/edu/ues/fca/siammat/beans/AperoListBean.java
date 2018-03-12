/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.modelo.Apero;

/**
 *
 * @author negra-ii
 */
@ManagedBean
@ViewScoped
public class AperoListBean extends ListBaseBean {

    private SimpleFilterElement<String> sfMarca = new SimpleFilterElement<String>("marca", SimpleFilterElement.AND, "LIKE");
    private SimpleFilterElement<String> sfModelo = new SimpleFilterElement<String>("modelo", SimpleFilterElement.AND, "LIKE");

    /**
     * Creates a new instance of AperoListBean
     */
    public AperoListBean() {
        super();
        setPathForm("/apero/edit");
        getFiltros().addFilterElement(sfMarca);
        getFiltros().addFilterElement(sfModelo);
    }

    @Override
    public List<Apero> getItems() {
        return super.getItems();
    }

    @Override
    public String setupQuery() {
        String hql = "from Apero ";
        String wc = getFiltros().generateWhereClause();
        if (wc != null && !wc.equals("")) {
            hql += " where " + wc;
        }
        return hql;
    }

    @Override
    public void onRemove(Serializable objet) {
        super.onRemove(objet);
        this.getItems().remove(objet);
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
