/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author Jaime
 */
@Entity
@Table(name = "cuidado_transporte")
@NamedQueries({
    @NamedQuery(name = "CuidadoTransporte.findAll", query = "SELECT c FROM CuidadoTransporte c")})
public class CuidadoTransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuidado_transporte")
    private Integer idCuidadoTransporte;
    @Basic(optional = false)
    @Column(name = "accion")
    private int accion;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "empresa")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "factura")
    private int factura;
    @Basic(optional = false)
    @Column(name = "componente")
    private String componente;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @JoinColumn(name = "id_responsable", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleado idResponsable;
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad")
    @ManyToOne(optional = false)
    private UnidadTransporte idUnidad;

    public CuidadoTransporte() {
    }

    public CuidadoTransporte(Integer idCuidadoTransporte) {
        this.idCuidadoTransporte = idCuidadoTransporte;
    }

    public CuidadoTransporte(Integer idCuidadoTransporte, int accion, String descripcion, Date fechaInicio, Date fechaFin, String empresa, int factura, String componente, float total) {
        this.idCuidadoTransporte = idCuidadoTransporte;
        this.accion = accion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.empresa = empresa;
        this.factura = factura;
        this.componente = componente;
        this.total = total;
    }

    public Integer getIdCuidadoTransporte() {
        return idCuidadoTransporte;
    }

    public void setIdCuidadoTransporte(Integer idCuidadoTransporte) {
        this.idCuidadoTransporte = idCuidadoTransporte;
    }

    public String getAccionName() {
        switch(this.accion){
            case 1:
                return "Mantenimiento";
            case 2:
                return "Reparación";
        }
        return "";
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Empleado getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Empleado idResponsable) {
        this.idResponsable = idResponsable;
    }

    public UnidadTransporte getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(UnidadTransporte idUnidad) {
        this.idUnidad = idUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuidadoTransporte != null ? idCuidadoTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuidadoTransporte)) {
            return false;
        }
        CuidadoTransporte other = (CuidadoTransporte) object;
        if ((this.idCuidadoTransporte == null && other.idCuidadoTransporte != null) || (this.idCuidadoTransporte != null && !this.idCuidadoTransporte.equals(other.idCuidadoTransporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.CuidadoTransporte[ idCuidadoTransporte=" + idCuidadoTransporte + " ]";
    }
    
}
