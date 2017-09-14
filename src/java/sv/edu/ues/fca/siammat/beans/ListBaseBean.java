    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import sv.edu.ues.fca.siammat.filtros.FilterElementGroup;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.postgresql.util.PSQLException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.seguridad.modelo.util.ServiceLocator;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author galicia
 */
public abstract class ListBaseBean implements Serializable {

    private List items;
    private Privilegio privilegio;
    private HashMap<String, Object> parametros = new HashMap<>();
    private String pathForm;
    private String query;
    @ManagedProperty("#{serviceLocator}")
    private ServiceLocator serviceLocator;
    private FilterElementGroup filtros=new FilterElementGroup();

    public ListBaseBean() {
        setUpParametros();
    }

    @PostConstruct
    private void init() {
        String uri = (String) Util.getParamFromSessionMap("recurso");
        Usuario u = (Usuario) Util.getParamFromSessionMap("usuario");

        if (uri != null && u != null) {
            String hql = "from Privilegio p where p.recurso.uri='" + uri + "' and p.rol.idRol=" + u.getRol().getIdRol();
            privilegio = (Privilegio) serviceLocator.getGenericServicio().getUniqueValue(hql);
        }

    }

    public void onSearch() {

        items = serviceLocator.getGenericServicio().find(setupQuery());
        if (items == null || items.isEmpty()) {
            Util.addMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros", "");
        }
    }

    public void onDialogReturn(SelectEvent selectEvent) {
        Object selected = selectEvent.getObject();
        if (selectEvent != null) {
            items.add(selected);
        }
    }

    public Boolean beforeSearch() {
        if (getFiltros().generateWhereClause() == null || getFiltros().generateWhereClause().equals("")) {
            return false;

        }
        return true;
    }

    public void onNew() {
        beforeNew();
        Util.putParamIntoSessionMap("accion", "1");
        RequestContext.getCurrentInstance().openDialog(pathForm, parametros, null);
    }

    public Boolean beforeNew() {
        return true;
    }

    public void onRemove(Serializable object) {
        try {
            serviceLocator.getGenericServicio().delete(object);
        } catch (PSQLException ex) {
            Logger.getLogger(ListBaseBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateJdbcException ex) {
            Logger.getLogger(ListBaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrlReportes() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return request.getContextPath() + "/servlets/report/PDF";
    }

    public void onEdit(Serializable object) {
        Util.putParamIntoSessionMap("accion", "2");
        Util.putParamIntoSessionMap("objeto", object);
        RequestContext.getCurrentInstance().openDialog(pathForm, parametros, null);
    }

    public abstract String setupQuery();

    //Getters y Setters
    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public FilterElementGroup getFiltros() {
        return filtros;
    }

    public void setFiltros(FilterElementGroup filtros) {
        this.filtros = filtros;
    }

    /**
     * Uri del formalario de edicíon
     *
     * @return
     */
    public String getPathForm() {
        return pathForm;
    }

    public void setPathForm(String pathForm) {
        this.pathForm = pathForm;
    }

    public void setUpParametros() {
        parametros.put("modal", true);
        parametros.put("resizable", false);
       // parametros.put("contentWidth", "100%");
       // parametros.put("contentHeight", "100%");
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }


    /**
     * Accion a realizar despues de eliminar un objeto
     *
     * @param removedObject El objeto eliminado
     */
    public void doAfterRemove(Serializable removedObject) {
        items.remove(removedObject);
    }

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        doAfterServiceLocatorSet();
    }
    
    public void doAfterServiceLocatorSet(){
    
    }
    
    
}
