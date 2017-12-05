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
import javax.xml.bind.annotation.XmlRootElement;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "cuidado_maquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuidadoMaquinaria.findAll", query = "SELECT c FROM CuidadoMaquinaria c")})
public class CuidadoMaquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuidado_maquinaria")
    private Integer idCuidadoMaquinaria;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "factura")
    private Integer factura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_taller")
    private Float costoTaller;
    @Column(name = "costo_material")
    private Float costoMaterial;
    @Column(name = "gasto_total")
    private Float gastoTotal;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "accion")
    private int accion;
    @JoinColumn(name = "id_encargado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado idEncargado;
    @JoinColumn(name = "id_maquinaria", referencedColumnName = "id_maquinaria")
    @ManyToOne
    private Maquinaria idMaquinaria;

    public CuidadoMaquinaria() {
    }

    public CuidadoMaquinaria(Integer idCuidadoMaquinaria) {
        this.idCuidadoMaquinaria = idCuidadoMaquinaria;
    }

    public String getAccionName() {
        switch (this.accion) {
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

    public Integer getIdCuidadoMaquinaria() {
        return idCuidadoMaquinaria;
    }

    public void setIdCuidadoMaquinaria(Integer idCuidadoMaquinaria) {
        this.idCuidadoMaquinaria = idCuidadoMaquinaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public Float getCostoTaller() {
        return costoTaller;
    }

    public void setCostoTaller(Float costoTaller) {
        this.costoTaller = costoTaller;
    }

    public Float getCostoMaterial() {
        return costoMaterial;
    }

    public void setCostoMaterial(Float costoMaterial) {
        this.costoMaterial = costoMaterial;
    }

    public Float getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(Float gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleado getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Empleado idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Maquinaria getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(Maquinaria idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuidadoMaquinaria != null ? idCuidadoMaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuidadoMaquinaria)) {
            return false;
        }
        CuidadoMaquinaria other = (CuidadoMaquinaria) object;
        if ((this.idCuidadoMaquinaria == null && other.idCuidadoMaquinaria != null) || (this.idCuidadoMaquinaria != null && !this.idCuidadoMaquinaria.equals(other.idCuidadoMaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.CuidadoMaquinaria[ idCuidadoMaquinaria=" + idCuidadoMaquinaria + " ]";
    }

}
