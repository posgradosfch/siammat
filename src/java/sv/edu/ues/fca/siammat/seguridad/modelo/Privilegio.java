/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author galicia
 */
@Entity
@Table(name = "privilegio")
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p")})
public class Privilegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_privilegio")
    private Integer idPrivilegio;
    @Column(name = "insertar", columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean insertar = false;
    @Column(name = "eliminar", columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean eliminar = false;
    @Column(name = "editar", columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean editar = false;
    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Recurso recurso;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;

    public Privilegio() {
    }

    public Privilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilegio != null ? idPrivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = null;

        if (object instanceof HibernateProxy) {
            other = (Privilegio) ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();
        } else {
            other = (Privilegio) object;
        }

        if ((this.idPrivilegio == null && other.idPrivilegio != null) || (this.idPrivilegio != null && !this.idPrivilegio.equals(other.idPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.seguridad.modelo.Privilegio[ idPrivilegio=" + idPrivilegio + " ]";
    }

    public Boolean getInsertar() {
        return insertar;
    }

    public void setInsertar(Boolean insertar) {
        this.insertar = insertar;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

}
