/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author Jaime
 */
@Entity
@Table(name = "unidad_transporte")
@NamedQueries({
    @NamedQuery(name = "UnidadTransporte.findAll", query = "SELECT u FROM UnidadTransporte u")})
public class UnidadTransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad")
    private Integer idUnidad;
    @Column(name = "tipo")
    private Short tipo;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "placa")
    private String placa;
    @Column(name = "color")
    private String color;
    @Column(name = "no_motor")
    private String noMotor;
    @Column(name = "no_inventario")
    private String noInventario;
    @Column(name = "tipo_combustible")
    private Short tipoCombustible;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "anio")
    private String anio;

    public UnidadTransporte() {
    }

    public UnidadTransporte(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }
    
    public String getTipoName(){
        switch(this.tipo){
            case 1:
                return "Automóvil";
            case 2:
                return "Autobús";
            case 3: 
                return "Pick-up";
            case 4:
                return "Motocicleta";
        }
        return "";
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNoMotor() {
        return noMotor;
    }

    public void setNoMotor(String noMotor) {
        this.noMotor = noMotor;
    }

    public String getNoInventario() {
        return noInventario;
    }

    public void setNoInventario(String noInventario) {
        this.noInventario = noInventario;
    }

    public Short getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(Short tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }
    
    public String getEstadoName(){
        switch(this.estado){
            case 1:
                return "Funcionando";
            case 2:
                return "En reparación";
            case 3: 
                return "En desuso";
        }
        return "";
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadTransporte)) {
            return false;
        }
        UnidadTransporte other = null;
        if(object instanceof HibernateProxy){
            other = (UnidadTransporte) ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();
        }else{
            other = (UnidadTransporte) object;
        }
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.UnidadTransporte[ idUnidad=" + idUnidad + " ]";
    }
    
}
