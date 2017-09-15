/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sv.edu.ues.fca.siammat.seguridad.modelo.Empleado;

/**
 *
 * @author galicia
 */
@Entity
@Table(name = "control_combustible")
@NamedQueries({
    @NamedQuery(name = "ControlCombustible.findAll", query = "SELECT c FROM ControlCombustible c")})
public class ControlCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_control_combustible")
    private Integer idControlCombustible;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "no_vale")
    private String noVale;
    @Column(name = "placa")
    private String placa;
    @Column(name = "monto")
    private BigInteger monto;
    @Column(name = "unidad_solicitante")
    private String unidadSolicitante;
    @Column(name = "destino")
    private String destino;
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Column(name = "kilometraje")
    private BigInteger kilometraje;
    @JoinColumn(name = "id_empleado_recibe", referencedColumnName = "id_empleado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleadoRecibe;
    @JoinColumn(name = "id_responsable", referencedColumnName = "id_empleado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado responsable;

    public ControlCombustible() {
    }

    public ControlCombustible(Integer idControlCombustible) {
        this.idControlCombustible = idControlCombustible;
    }

    public Integer getIdControlCombustible() {
        return idControlCombustible;
    }

    public void setIdControlCombustible(Integer idControlCombustible) {
        this.idControlCombustible = idControlCombustible;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNoVale() {
        return noVale;
    }

    public void setNoVale(String noVale) {
        this.noVale = noVale;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public String getUnidadSolicitante() {
        return unidadSolicitante;
    }

    public void setUnidadSolicitante(String unidadSolicitante) {
        this.unidadSolicitante = unidadSolicitante;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public BigInteger getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(BigInteger kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Empleado getEmpleadoRecibe() {
        return empleadoRecibe;
    }

    public void setEmpleadoRecibe(Empleado empleadoRecibe) {
        this.empleadoRecibe = empleadoRecibe;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlCombustible != null ? idControlCombustible.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlCombustible)) {
            return false;
        }
        ControlCombustible other = (ControlCombustible) object;
        if ((this.idControlCombustible == null && other.idControlCombustible != null) || (this.idControlCombustible != null && !this.idControlCombustible.equals(other.idControlCombustible))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.ControlCombustible[ idControlCombustible=" + idControlCombustible + " ]";
    }
    
}
