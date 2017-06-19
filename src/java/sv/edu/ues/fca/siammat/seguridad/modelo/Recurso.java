/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.modelo;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author galicia
 */
@Entity
@Table(name = "recurso")
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r")})
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recurso")
    private Integer idRecurso;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "uri")
    private String uri;
    @Column(name = "detalle", columnDefinition = "smallint")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean detalle;
    @Column(name = "orden")
    private Short orden;
    @OneToMany(mappedBy = "recursoPadre", fetch = FetchType.LAZY)
    private List<Recurso> recursoList;
    @JoinColumn(name = "id_recurso_padre", referencedColumnName = "id_recurso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Recurso recursoPadre;
    @OneToMany(mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Privilegio> privilegioList;

    public Recurso() {
    }

    public Recurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(Boolean detalle) {
        this.detalle = detalle;
    }


    public Short getOrden() {
        return orden;
    }

    public void setOrden(Short orden) {
        this.orden = orden;
    }

    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    public Recurso getRecursoPadre() {
        return recursoPadre;
    }

    public void setRecursoPadre(Recurso recursoPadre) {
        this.recursoPadre = recursoPadre;
    }

    public List<Privilegio> getPrivilegioList() {
        return privilegioList;
    }

    public void setPrivilegioList(List<Privilegio> privilegioList) {
        this.privilegioList = privilegioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecurso != null ? idRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idRecurso == null && other.idRecurso != null) || (this.idRecurso != null && !this.idRecurso.equals(other.idRecurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.seguridad.modelo.Recurso[ idRecurso=" + idRecurso + " ]";
    }

}
