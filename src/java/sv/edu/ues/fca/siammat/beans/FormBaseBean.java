/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import org.primefaces.context.RequestContext;
import org.seguridad.modelo.util.ServiceLocator;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author galicia
 */
public abstract class FormBaseBean implements Serializable {

    private String winTitle;
    private Privilegio privilegio;
    private Serializable mainObject;
    @ManagedProperty("#{serviceLocator}")
    private ServiceLocator serviceLocator;
    private String accion;

    public FormBaseBean() {
        accion = (String) Util.getParamFromSessionMap("accion");
        if (accion.equals("2")) {
            mainObject = (Serializable) Util.getParamFromSessionMap("objeto");
            winTitle = "Editar";
        } else {
            winTitle = "Agregar";
        }
    }

    @PostConstruct

    private void init() {
        String uri = (String) Util.getParamFromSessionMap("recurso");
        Usuario u = (Usuario) Util.getParamFromSessionMap("usuario");

        if (uri != null && u != null) {
            String hql = "from Privilegio p join fetch p.recurso join fetch p.recurso where p.recurso.uri='" + uri + "' and p.rol.idRol=" + u.getRol().getIdRol();
            privilegio = (Privilegio) serviceLocator.getGenericServicio().getUniqueValue(hql);
        }

    }

    public void onSave() {
        if (!validate()) {
            return;
        }
        serviceLocator.getGenericServicio().saveOrUpdate(mainObject);

        RequestContext.getCurrentInstance().closeDialog(mainObject);
    }

    public void onCancel() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public abstract boolean validate();

    /**
     * Entidad que se está gestionando
     *
     * @return
     */
    public Serializable getMainObject() {
        return mainObject;
    }

    public void setMainObject(Serializable mainObject) {
        this.mainObject = mainObject;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getWinTitle() {
        return winTitle;
    }

    public void setWinTitle(String winTitle) {
        this.winTitle = winTitle;
    }


    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public Boolean getCanSave() {
        if (accion.equals("1") && privilegio.getInsertar()) {
            return true;
        } else if (accion.equals("2") && privilegio.getEditar()) {
            return true;
        }
        return false;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
    
}
