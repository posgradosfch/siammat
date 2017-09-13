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
import sv.edu.ues.fca.siammat.modelo.Actividad;
import sv.edu.ues.fca.siammat.modelo.Lote;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "actividad_maquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadMaquinaria.findAll", query = "SELECT am FROM ActividadMaquinaria am")})/*,
    @NamedQuery(name = "ActividadMaquinaria.findByIdActividadMaquinaria", query = "SELECT a FROM ActividadMaquinaria a WHERE a.idActividadMaquinaria = :idActividadMaquinaria"),
    @NamedQuery(name = "ActividadMaquinaria.findByFecha", query = "SELECT a FROM ActividadMaquinaria a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "ActividadMaquinaria.findByExtensionTrabajo", query = "SELECT a FROM ActividadMaquinaria a WHERE a.extensionTrabajo = :extensionTrabajo"),
    @NamedQuery(name = "ActividadMaquinaria.findByCombustibleInicial", query = "SELECT a FROM ActividadMaquinaria a WHERE a.combustibleInicial = :combustibleInicial"),
    @NamedQuery(name = "ActividadMaquinaria.findByCombustibleFinal", query = "SELECT a FROM ActividadMaquinaria a WHERE a.combustibleFinal = :combustibleFinal"),
    @NamedQuery(name = "ActividadMaquinaria.findByCombustibleGastado", query = "SELECT a FROM ActividadMaquinaria a WHERE a.combustibleGastado = :combustibleGastado"),
    @NamedQuery(name = "ActividadMaquinaria.findByInicioHorometro", query = "SELECT a FROM ActividadMaquinaria a WHERE a.inicioHorometro = :inicioHorometro"),
    @NamedQuery(name = "ActividadMaquinaria.findByFinHorometro", query = "SELECT a FROM ActividadMaquinaria a WHERE a.finHorometro = :finHorometro"),
    @NamedQuery(name = "ActividadMaquinaria.findByInicioReloj", query = "SELECT a FROM ActividadMaquinaria a WHERE a.inicioReloj = :inicioReloj"),
    @NamedQuery(name = "ActividadMaquinaria.findByObservacion", query = "SELECT a FROM ActividadMaquinaria a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "ActividadMaquinaria.findByFinReloj", query = "SELECT a FROM ActividadMaquinaria a WHERE a.finReloj = :finReloj")})*/
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
    private Float extensionTrabajo;
    @Column(name = "combustible_inicial")
    private Integer combustibleInicial;
    @Column(name = "combustible_final")
    private Integer combustibleFinal;
    @Column(name = "combustible_gastado")
    private Integer combustibleGastado;
    @Column(name = "inicio_horometro")
    private Float inicioHorometro;
    @Column(name = "fin_horometro")
    private Float finHorometro;
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

    public Float getExtensionTrabajo() {
        return extensionTrabajo;
    }

    public void setExtensionTrabajo(Float extensionTrabajo) {
        this.extensionTrabajo = extensionTrabajo;
    }

    public Integer getCombustibleInicial() {
        return combustibleInicial;
    }

    public void setCombustibleInicial(Integer combustibleInicial) {
        this.combustibleInicial = combustibleInicial;
    }

    public Integer getCombustibleFinal() {
        return combustibleFinal;
    }

    public void setCombustibleFinal(Integer combustibleFinal) {
        this.combustibleFinal = combustibleFinal;
    }

    public Integer getCombustibleGastado() {
        return combustibleGastado;
    }

    public void setCombustibleGastado(Integer combustibleGastado) {
        this.combustibleGastado = combustibleGastado;
    }

    public Float getInicioHorometro() {
        return inicioHorometro;
    }

    public void setInicioHorometro(Float inicioHorometro) {
        this.inicioHorometro = inicioHorometro;
    }

    public Float getFinHorometro() {
        return finHorometro;
    }

    public void setFinHorometro(Float finHorometro) {
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
    
}
