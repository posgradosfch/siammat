/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.seguridad.modelo.Rol;

/**
 *
 * @author galicia
 */
@ManagedBean
@ViewScoped
public class ReporteUsuarioListBean extends ListBaseBean{
    
    private List<Rol> roles;
    private Integer idRol;
    
    public ReporteUsuarioListBean() {
        //Fijando la uri del formulario de edición
        setPathForm("/seguridad/cargos/edit");
    }

    public void generarReporte(){
        Map m = new HashMap();
        m.put("idRol", idRol);
        this.showReport("rptUsuarios.jasper", m);
    }
    
    @Override
    public String setupQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAfterServiceLocatorSet() {
        roles = getServiceLocator().getGenericServicio().find("from Rol");
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

}
