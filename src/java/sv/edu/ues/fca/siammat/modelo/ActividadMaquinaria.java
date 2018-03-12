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
@Table(name = "actividad_maquinaria")
public class ActividadMaquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad_maquinaria")
    private Integer idActividadMaquinaria;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "extension_trabajo")
    private Double extensionTrabajo;
    @Column(name = "combustible_inicial")
    private Double combustibleInicial;
    @Column(name = "combustible_final")
    private Double combustibleFinal;
    @Column(name = "combustible_gastado")
    private Double combustibleGastado;
    @Column(name = "inicio_horometro")
    private Double inicioHorometro;
    @Column(name = "fin_horometro")
    private Double finHorometro;
    @Column(name = "inicio_horometroap")
    private Double inicioHorometroAP;
    @Column(name = "fin_horometroap")
    private Double finHorometroAP;
    @Column(name = "inicio_reloj")
    @Temporal(TemporalType.TIME)
    private Date inicioReloj;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fin_reloj")
    @Temporal(TemporalType.TIME)
    private Date finReloj;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    @ManyToOne
    private Actividad idActividad;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado idEmpleado;
    @JoinColumn(name = "id_lote", referencedColumnName = "id_lote")
    @ManyToOne
    private Lote idLote;
    @JoinColumn(name = "id_maquinaria", referencedColumnName = "id_maquinaria")
    @ManyToOne
    private Maquinaria idMaquinaria;
    @JoinColumn(name = "id_apero", referencedColumnName = "id_apero")
    private String idApero;

    public ActividadMaquinaria() {
    }

    public ActividadMaquinaria(Integer idActividadMaquinaria) {
        this.idActividadMaquinaria = idActividadMaquinaria;
    }

  
    /*public int gastado(Integer combustibleFinal, Integer combustibleInicial) {
        combustibleGastado = combustibleInicial - combustibleFinal;
        return combustibleGastado;
    }*/
    
    public Integer getIdActividadMaquinaria() {
        return idActividadMaquinaria;
    }

    public void setIdActividadMaquinaria(Integer idActividadMaquinaria) {
        this.idActividadMaquinaria = idActividadMaquinaria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getExtensionTrabajo() {
        return extensionTrabajo;
    }

    public void setExtensionTrabajo(Double extensionTrabajo) {
        this.extensionTrabajo = extensionTrabajo;
    }

    public Double getCombustibleInicial() {
        return combustibleInicial;
    }

    public void setCombustibleInicial(Double combustibleInicial) {
        this.combustibleInicial = combustibleInicial;
    }

    public Double getCombustibleFinal() {
        return combustibleFinal;
    }

    public void setCombustibleFinal(Double combustibleFinal) {
        this.combustibleFinal = combustibleFinal;
    }

    public Double getCombustibleGastado() {
        return combustibleGastado;
    }

    public void setCombustibleGastado(Double combustibleGastado) {
        this.combustibleGastado = combustibleGastado;
    }

    public Double getInicioHorometro() {
        return inicioHorometro;
    }

    public void setInicioHorometro(Double inicioHorometro) {
        this.inicioHorometro = inicioHorometro;
    }

    public Double getFinHorometro() {
        return finHorometro;
    }

    public void setFinHorometro(Double finHorometro) {
        this.finHorometro = finHorometro;
    }

    

    public Date getInicioReloj() {
        return inicioReloj;
    }

    public void setInicioReloj(Date inicioReloj) {
        this.inicioReloj = inicioReloj;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFinReloj() {
        return finReloj;
    }

    public void setFinReloj(Date finReloj) {
        this.finReloj = finReloj;
    }

    public Actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividad idActividad) {
        this.idActividad = idActividad;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        this.idLote = idLote;
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
        hash += (idActividadMaquinaria != null ? idActividadMaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadMaquinaria)) {
            return false;
        }
        ActividadMaquinaria other = (ActividadMaquinaria) object;
        if ((this.idActividadMaquinaria == null && other.idActividadMaquinaria != null) || (this.idActividadMaquinaria != null && !this.idActividadMaquinaria.equals(other.idActividadMaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.ActividadMaquinaria[ idActividadMaquinaria=" + idActividadMaquinaria + " ]";
    }

    public String getIdApero() {
        return idApero;
    }

    public void setIdApero(String idApero) {
        this.idApero = idApero;
    }

    public Double getInicioHorometroAP() {
        return inicioHorometroAP;
    }

    public void setInicioHorometroAP(Double inicioHorometroAP) {
        this.inicioHorometroAP = inicioHorometroAP;
    }

    public Double getFinHorometroAP() {
        return finHorometroAP;
    }

    public void setFinHorometroAP(Double finHorometroAP) {
        this.finHorometroAP = finHorometroAP;
    }
    
}