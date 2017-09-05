/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.orm.hibernate3.HibernateCallback;
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

    private List<Rol> roles = new ArrayList<>();
    private SelectOneMenu selectOneMenu;
    private Rol rolSelected;
    private TreeNode menu;
    private List<Privilegio> privilegios;

    public PrivilegioListBean() {
        super();
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/privilegios/edit");
    }

    public void onSave() {
        createList(menu);
        getServiceLocator().getGenericServicio().executeInTrans(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                for (Privilegio p : privilegios) {
                    sn.update(p);
                }

                return 0;
            }
        });
        Util.addMessage(FacesMessage.SEVERITY_INFO, "Guardar", "Privilegios actualizados");
    }

    private void createList(TreeNode treeNode) {
        if (privilegios == null) {
            privilegios = new ArrayList<>();
        }

        if (treeNode.getData() instanceof Privilegio) {
            privilegios.add((Privilegio) treeNode.getData());
        }
        if (treeNode.getChildCount() > 0) {
            for (TreeNode t : treeNode.getChildren()) {
                createList(t);
            }
        }
    }

    @PostConstruct
    private void init() {
        menu = new DefaultTreeNode("Menu", null);
        roles = getServiceLocator().getGenericServicio().find("from Rol r");
    }

    @Override
    public String setupQuery() {
        String hql = "select distinct(r) from Recurso r join fetch r.recursoList where r.recursoPadre=null";
        return hql;
    }

    @Override
    public List<Recurso> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems();
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public Rol getRolSelected() {
        return rolSelected;
    }

    public void setRolSelected(Rol rolSelected) {
        this.rolSelected = rolSelected;
    }

    public TreeNode getMenu() {
        return menu;
    }

    public void setMenu(TreeNode menu) {
        this.menu = menu;
    }

    public void onSelectRol(ValueChangeEvent valueChangeEvent) {

        Rol r = (Rol) valueChangeEvent.getNewValue();
        if (r == null) {
            menu = new DefaultTreeNode("Menu", null);
            return;
        }
        menu = new DefaultTreeNode("Menu", null);
        List<Privilegio> privilegios = new ArrayList<>();
        String hql = "from Privilegio p join fetch p.rol join fetch p.recurso where p.rol.idRol=" + r.getIdRol() + " and p.recurso.recursoPadre=null";
        privilegios = getServiceLocator().getGenericServicio().find(hql);

        for (Privilegio privilegio : privilegios) {
            crearTree(privilegio, menu);
        }

    }

    public void crearTree(Privilegio privilegio, TreeNode padre) {

        String hql = "from Privilegio p join fetch p.rol join fetch p.recurso where p.recurso.recursoPadre.idRecurso=" + privilegio.getRecurso().getIdRecurso() + " and p.rol.idRol=" + privilegio.getRol().getIdRol();
        List<Privilegio> opciones = getServiceLocator().getGenericServicio().find(hql);
        TreeNode hijo = new DefaultTreeNode(privilegio, padre);
        hijo.setExpanded(true);

        if (opciones != null && !opciones.isEmpty()) {
            for (Privilegio p : opciones) {
                crearTree(p, hijo);
            }
        }
    }

    public SelectOneMenu getSelectOneMenu() {
        return selectOneMenu;
    }

    public void setSelectOneMenu(SelectOneMenu selectOneMenu) {
        this.selectOneMenu = selectOneMenu;
    }

    public void updateTree() {
        selectOneMenu.broadcast(new ValueChangeEvent(selectOneMenu, null, selectOneMenu.getValue()));
    }
}
