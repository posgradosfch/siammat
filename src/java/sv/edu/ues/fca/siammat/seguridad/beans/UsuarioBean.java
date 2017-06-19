package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.FormBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;
import sv.edu.ues.fca.siammat.seguridad.modelo.Usuario;
import sv.edu.ues.fca.siammat.util.Util;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends FormBaseBean {

    //Entidad del tipo que se está gestionando
    private Usuario usuario = new Usuario();
    private List<Empleado> empleados;
    private List<Rol> roles;

    public UsuarioBean() {
        super();
        if (getAccion().equals("2")) {//Modo edicion, recuperando objeto del padre
            usuario = (Usuario) getMainObject();
        } else {//Modo inserción, seteando objeto al padre
            setMainObject(usuario);
        }
    }

    @Override
    public boolean validate() {

        if (getAccion().equals("1")) {
            Usuario u = (Usuario) getBasicService().getSingle("from Usuario u where u.empleado.idEmpleado=" + usuario.getEmpleado().getIdEmpleado());
            if (u != null) {
                Util.addErrorMessage("El empleado seleccioando ya posee un usuario");
                return false;
            }
        }

        //Encriptando la clave del usuario
        String cryptedPass = CryptoUtils.encrypt(usuario.getClave());
        usuario.setClave(cryptedPass);
        return true;
    }

    public Usuario getUsuario() {

        return usuario;
    }

    public List<Empleado> getEmpleados() {
        if (empleados == null) {
            empleados = getBasicService().find("from Empleado e order by e.apellido");
        }
        return empleados;
    }

    public List<Rol> getRoles() {
        if (roles == null) {
            roles = getBasicService().find("from Rol r");
        }
        return roles;
    }

}
