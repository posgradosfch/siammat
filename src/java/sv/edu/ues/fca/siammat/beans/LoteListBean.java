/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import sv.edu.ues.fca.siammat.modelo.Lote;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class LoteListBean extends ListBaseBean{

    /**
     * Creates a new instance of LoteListBean
     */
    public LoteListBean() {
        setPathForm("/lote/edit");
    }
    
    @Override
    public void doAfterServiceLocatorSet() {
        onSearch();
    }
    @Override
    public List<Lote> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems(); 
    }

    @Override
    public String setupQuery() {
        String hql="from Lote l ";
        return hql;
    }
    
}
