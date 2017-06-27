/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio;
import sv.edu.ues.fca.siammat.seguridad.modelo.Recurso;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author galicia
 */
@ManagedBean
@SessionScoped
public class LoginBean extends ListBaseBean {

    private Usuario usuario = new Usuario();
    private String forwardUrl;
    private static final String HOME_PAGE = "/";
    private static final String PAGE_AFTER_LOGOUT = HOME_PAGE; // Another good option is the login page back again

    private static final String SESSION_USER_VARIABLE_NAME = "usuario";
    private ExternalContext externalContext;

    MenuModel menu;

    public LoginBean() {

    }

    @PostConstruct
    public void init() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.forwardUrl = extractRequestedUrlBeforeLogin();
    }

    private void crearMenu() {
       getBasicService().refresh(usuario.getRol());
        List<Privilegio> padres = usuario.getRol().getPrivilegioList();
        
        menu = new DefaultMenuModel();

        for (Privilegio privilegio : padres) {
            if (privilegio.getRecurso().getRecursoPadre() == null) {
                fillMenuItem(privilegio.getRecurso(), null);
            }

        }
    }

    private void fillMenuItem(Recurso recurso, DefaultSubMenu padre) {
        //Verificando que tenga los permisos
        if (!hasPrivilege(recurso)) {
            return;
        }
        //Elemento que vamos agreagarle al padre
        MenuElement menuHijo = null;

        //Si es un menu
        if (recurso.isDetalle()) {
            DefaultMenuItem menuItem = new DefaultMenuItem(recurso.getDescripcion());
            menuItem.setOutcome(recurso.getUri());
            menuHijo = menuItem;
        } else {//Si es una agrupacion, se crea un submenu y se recorre la lista creando los recursos hijos
            DefaultSubMenu defaultSubMenu = new DefaultSubMenu(recurso.getDescripcion());
            menuHijo = defaultSubMenu;

            for (Recurso r : recurso.getRecursoList()) {
                fillMenuItem(r, defaultSubMenu);
            }
        }
        if (padre == null) {
            menu.addElement(menuHijo);
        } else {
            padre.addElement(menuHijo);
        }

    }

    private boolean hasPrivilege(Recurso recurso) {

        String hql = "from Privilegio p where p.rol.idRol=" + usuario.getRol().getIdRol() + " and p.recurso.idRecurso=" + recurso.getIdRecurso();

        Privilegio p = (Privilegio) getBasicService().getSingle(hql);

        return p != null;
    }

    public void onLogin() {
        String cryptedPass=CryptoUtils.encrypt(usuario.getClave());
        
        String query = "from Usuario u where u.usuario='" + usuario.getUsuario() + "' and u.clave='" + cryptedPass + "'";
        Usuario u = (Usuario) getBasicService().getSingle(query);
        if (u != null) {
            Util.putParamIntoSessionMap(SESSION_USER_VARIABLE_NAME, u);
            String ctxPath = ((ServletContext) externalContext.getContext()).getContextPath();
            usuario=u;
            crearMenu();
            Util.redirect(ctxPath);
        } else {
            Util.addMessage(FacesMessage.SEVERITY_ERROR, "Iniciar sesión", "Datos incorrectos");
        }
    }

    public void onLogOut() {
        Util.removeParamFromSessionMap(SESSION_USER_VARIABLE_NAME);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        Util.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + HOME_PAGE);
    }


    public boolean isUserLogged() {
        
        return Util.getParamFromSessionMap(SESSION_USER_VARIABLE_NAME) != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private String extractRequestedUrlBeforeLogin() {
        String requestedUrl = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (requestedUrl == null) {
            return externalContext.getRequestContextPath() + HOME_PAGE;
        }
        String queryString = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
        return requestedUrl + (queryString == null ? "" : "?" + queryString);
    }

    public static String getHome() {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + HOME_PAGE;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    @Override
    public String setupQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
