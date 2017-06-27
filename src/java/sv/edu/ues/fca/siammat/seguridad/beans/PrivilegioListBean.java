/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Recurso;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class PrivilegioListBean extends ListBaseBean {

    private List<Rol> roles;
    private Rol rolSelected;
    private Recurso recursoSelected;
    private Recurso recursoPadreSelected;
    private List<Privilegio> privilegios;
    private TreeNode recursos;

    public PrivilegioListBean() {
        super();
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/privilegios/edit");
        onSearch();
    }

    @Override
    public void onSearch() {
        super.onSearch(); //To change body of generated methods, choose Tools | Templates.
    }


    public void onRecursoDrop(DragDropEvent ddEvent) {
        // Recurso recurso =  (Recurso) ddEvent.getData();
        System.out.println(ddEvent);
    }

    @Override
    public String setupQuery() {
        String hql = "from Recurso r where r.recursoPadre=null";
        return hql;
    }

    @Override
    public List<Recurso> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems();
    }

    public TreeNode getRecursos() {
        return recursos;
    }

    public List<Rol> getRoles() {
        if (roles == null) {
            roles = getBasicService().find("from Rol r");
        }
        return roles;
    }

    public Rol getRolSelected() {
        return rolSelected;
    }

    public void setRolSelected(Rol rolSelected) {
        this.rolSelected = rolSelected;
    }

    public void onChangeValue() {
        System.out.println("df");
    }

    public void onAddPrivilegio(Rol rol) {
        if (recursoSelected != null) {

            if (!hasPrivilege(recursoSelected, rol)) {
                Privilegio p = new Privilegio();
                p.setRol(rol);
                p.setRecurso(recursoSelected);
                getBasicService().save(p);
                getBasicService().refresh(rol);

            } else {
                Util.addMessage(FacesMessage.SEVERITY_WARN, "", "El rol ya posee este permiso");

            }
        }
    }

    public void treeToTable() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String property = params.get("property");
        String indice = params.get("indice");
        Privilegio p = new Privilegio();

        Rol rol = roles.get(Integer.parseInt(indice));

        Recurso r = (Recurso) getBasicService().getSingle("from Recurso r where r.descripcion='" + property + "'");
        p.setRol(rol);
        p.setRecurso(r);

        if (!hasPrivilege(r, rol)) {
            getBasicService().save(p);
            getBasicService().refresh(rol);

        }

    }

    @Override
    public void onRemove(Serializable object) {
        super.onRemove(object); //To change body of generated methods, choose Tools | Templates.
        getBasicService().refresh(this.rolSelected);
    }

    public void onRowEdit() {
        getBasicService().refresh(this.rolSelected);
    }

    private boolean hasPrivilege(Recurso recurso, Rol rol) {

        String hql = "from Privilegio p where p.rol.idRol=" + rol.getIdRol() + " and p.recurso.idRecurso=" + recurso.getIdRecurso();

        Privilegio p = (Privilegio) getBasicService().getSingle(hql);

        return p != null;
    }

    public Recurso getRecursoSelected() {
        return recursoSelected;
    }

    public void setRecursoSelected(Recurso recursoSelected) {
        this.recursoSelected = recursoSelected;
    }

    public Recurso getRecursoPadreSelected() {
        return recursoPadreSelected;
    }

    public void setRecursoPadreSelected(Recurso recursoPadreSelected) {
        this.recursoPadreSelected = recursoPadreSelected;
    }

}
