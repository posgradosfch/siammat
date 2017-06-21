/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.filtros.SimpleFilterElement;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Recurso;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class PrivilegioListBean extends ListBaseBean {

    private List<Rol> roles;
    private Rol rolSelected;
    private List<Privilegio> privilegios;
    private TreeNode recursos;

    public PrivilegioListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/privilegios/edit");
        onSearch();
    }

    @Override
    public void onSearch() {
        super.onSearch(); //To change body of generated methods, choose Tools | Templates.
        crearArbolRecursos();
    }

    private void crearArbolRecursos() {
        recursos = new DefaultTreeNode("Root", null);

        for (Object recurso : getItems()) {
            Recurso aux = (Recurso) recurso;
            agregarNodo(aux, recursos);
        }
    }

    private void agregarNodo(Recurso recurso, TreeNode parent) {
        TreeNode node = new DefaultTreeNode("column", recurso, parent);

        if (!recurso.isDetalle()) {
            for (Recurso sub : recurso.getRecursoList()) {
                agregarNodo(sub, node);
            }
        }

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
    
    
    
        public void treeToTable() {
//        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        String property = params.get("property");
//        String droppedColumnId = params.get("droppedColumnId");
//        String dropPos = params.get("dropPos");
//         
//        String[] droppedColumnTokens = droppedColumnId.split(":");
//        int draggedColumnIndex = Integer.parseInt(droppedColumnTokens[droppedColumnTokens.length - 1]);
//        int dropColumnIndex = draggedColumnIndex + Integer.parseInt(dropPos);
//         
//        //add to columns
//        this.columns.add(dropColumnIndex, new ColumnModel(property.toUpperCase(), property));
//         
//        //remove from nodes
//        TreeNode root = availableColumns.getChildren().get(0);
//        for(TreeNode node : root.getChildren()) {
//            ColumnModel model = (ColumnModel) node.getData();
//            if(model.getProperty().equals(property)) {
//                root.getChildren().remove(node);
//                break;
//            }
//        }
System.out.println("arrastrado");
    }
    

    public void tableToTree() {
//        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        int colIndex = Integer.parseInt(params.get("colIndex"));
//         
//        //remove from table
//        ColumnModel model = this.columns.remove(colIndex);
//         
//        //add to nodes
//        TreeNode property = new DefaultTreeNode("column", model, availableColumns.getChildren().get(0));
    }


}
