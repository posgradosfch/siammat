/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

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
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import sv.edu.ues.fca.siammat.seguridad.modelo.Recurso;
/**
 *
 * @author franck
 */

@Entity
@Table(name = "actividad")
@NamedQueries({
@NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")})
public class Actividad implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "horometro_inicial")
    private Float horometroInicial;
    @Column(name = "horometro_final")
    private Float horometroFinal;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne
    private Lote idLote;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Integer idActividad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "abreviatura")
    private String abreviatura;
    
    public Actividad() {//Constructor
    }
    
    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }
    
    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other =  null;
        if (object instanceof HibernateProxy) {
            other = (Actividad) ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();
        } else {
            other = (Actividad) object;
        }
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.Actividad[ idActividad=" + idActividad + " ]";
    }

    public Float getHorometroInicial() {
        return horometroInicial;
    }

    public void setHorometroInicial(Float horometroInicial) {
        this.horometroInicial = horometroInicial;
    }

    public Float getHorometroFinal() {
        return horometroFinal;
    }

    public void setHorometroFinal(Float horometroFinal) {
        this.horometroFinal = horometroFinal;
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        this.idLote = idLote;
    }
        
}
