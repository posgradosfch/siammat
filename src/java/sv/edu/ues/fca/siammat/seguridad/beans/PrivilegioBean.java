package sv.edu.ues.fca.siammat.seguridad.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.orm.hibernate3.HibernateCallback;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Recurso;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class PrivilegioBean extends FormBaseBean {

    //Entidad del tipo que se está gestionando
    private Rol rol = new Rol();
    private TreeNode[] selectedNodes;
    private List<Privilegio> privilegios;
    private TreeNode menu;

    public PrivilegioBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            rol = (Rol) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(rol);
        }
    }

    @PostConstruct
    private void init() {
        menu = new DefaultTreeNode("Menu", null);
        String hqlAux = "select p.recurso.idRecurso  from Privilegio p where p.rol.idRol = " + rol.getIdRol();

        String hql = "from Recurso r where r.recursoPadre=null and r.idRecurso not in ("+hqlAux+")";

        List<Recurso> padres = getServiceLocator().getGenericServicio().find(hql);
        for (Recurso recurso : padres) {
            crearTree(recurso, menu);
        }
    }

    @Override
    public boolean validate() {
        return true;
    }

    public Rol getRol() {
        return rol;
    }

    @Override
    public void onSave() {
        privilegios = new ArrayList<>();
        createList(menu);
        getServiceLocator().getGenericServicio().executeInTrans(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                for (Privilegio p : privilegios) {
                    sn.save(p);
                }

                return 0;
            }
        });
        onCancel();
    }

    private void createList(TreeNode treeNode) {
        if (treeNode.getData() instanceof Recurso && treeNode.isSelected() && treeNode.getChildCount() == 0) {
            Privilegio p = new Privilegio();
            p.setRecurso((Recurso) treeNode.getData());
            p.setRol(rol);
            privilegios.add(p);
        }
        if (treeNode.getChildCount() > 0) {
            for (TreeNode t : treeNode.getChildren()) {
                createList(t);
            }
        }
    }

    public void crearTree(Recurso r, TreeNode padre) {

        String hqlAux = "select p.recurso.idRecurso  from Privilegio p where p.rol.idRol = " + rol.getIdRol();

        String hql = "from Recurso r where r.recursoPadre.idRecurso=" + r.getIdRecurso() + " and r.idRecurso not in(" + hqlAux + ")";
        List<Recurso> opciones = getServiceLocator().getGenericServicio().find(hql);
        TreeNode hijo = new DefaultTreeNode(r, padre);
        hijo.setExpanded(true);

        if (opciones != null && !opciones.isEmpty()) {
            for (Recurso recurso : opciones) {
                crearTree(recurso, hijo);
            }
        }
    }

    public TreeNode getMenu() {
        return menu;
    }

    public void setMenu(TreeNode menu) {
        this.menu = menu;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

}
