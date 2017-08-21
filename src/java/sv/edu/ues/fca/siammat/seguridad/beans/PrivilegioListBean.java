/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
    private TreeNode menu;

    public PrivilegioListBean() {
        super();
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/privilegios/edit");
    }
    
    @PostConstruct
    private void init(){
        menu= new DefaultTreeNode("Menu", null);
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
        String hql = "select distinct(r) from Recurso r join fetch r.recursoList where r.recursoPadre=null";
        return hql;
    }

    @Override
    public List<Recurso> getItems() {//Asegurando que retorna el tipo de datos, y aprovechar sugerencias del editor
        return super.getItems();
    }

    
    public void onSelectRol(){
        menu= new DefaultTreeNode("Menu", null);
        String hql="from Recurso r where r.recursoPadre=null";
        
        List<Recurso> padres=getServiceLocator().getGenericServicio().find(hql);
        for(Recurso recurso:padres){
            crearTree(recurso, menu);
        }
    }
    
    public void crearTree(Recurso r,TreeNode padre){
        String hql="from Recurso r where r.recursoPadre.idRecurso="+r.getIdRecurso();
        List<Recurso> opciones=getServiceLocator().getGenericServicio().find(hql);
        TreeNode hijo = new DefaultTreeNode(r, padre);
        hijo.setExpanded(true);
        String query="from Privilegio p where p.rol.idRol="+rolSelected.getIdRol()+" and p.recurso.idRecurso="+r.getIdRecurso();
        List l= getServiceLocator().getGenericServicio().find(query);
        hijo.setSelected(l!=null && !l.isEmpty() );

        if(opciones!=null && !opciones.isEmpty()){
            for(Recurso recurso:opciones){
                crearTree(recurso, hijo);
            } 
        }else{
           TreeNode pi = new DefaultTreeNode(new Recurso("Insertar"), hijo);
           TreeNode pm= new DefaultTreeNode(new Recurso("Modificar"), hijo);
           TreeNode pe=new DefaultTreeNode(new Recurso("Eliminar"), hijo);
           
        }
    }
    
    

    public List<Rol> getRoles() {
        if (roles == null) {
            roles = getServiceLocator().getGenericServicio().find("select distinct(r) from Rol r join fetch r.privilegioList ");
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
                getServiceLocator().getGenericServicio().save(p);
               // getServiceLocator().getGenericServicio().refresh(rol);

            } else {
                Util.addMessage(FacesMessage.SEVERITY_WARN, "", "El rol ya posee este permiso");

            }
        }
    }


    @Override
    public void onRemove(Serializable object) {
        super.onRemove(object); //To change body of generated methods, choose Tools | Templates.
        //getBasicService().refresh(this.rolSelected);
    }

    public void onRowEdit() {
        //getBasicService().refresh(this.rolSelected);
    }

    private boolean hasPrivilege(Recurso recurso, Rol rol) {

        String hql = "from Privilegio p where p.rol.idRol=" + rol.getIdRol() + " and p.recurso.idRecurso=" + recurso.getIdRecurso();

        Privilegio p = (Privilegio)  getServiceLocator().getGenericServicio().getUniqueValue(hql);

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

    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(List<Privilegio> privilegios) {
        this.privilegios = privilegios;
    }

    public TreeNode getMenu() {
        return menu;
    }

    public void setMenu(TreeNode menu) {
        this.menu = menu;
    }
    

}
