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

/**
 *
 * @author galicia
 */
@Entity
@Table(name = "maquinaria")
@NamedQueries({
    @NamedQuery(name = "Maquinaria.findAll", query = "SELECT m FROM Maquinaria m")})
public class Maquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_maquinaria")
    private Integer idMaquinaria;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anio")
    private String anio;
    @Column(name = "placa")
    private String placa;
    @Column(name = "color")
    private String color;
    @Column(name = "no_motor")
    private String noMotor;
    @Column(name = "marca")
    private String marca;
    @Column(name = "no_inventario")
    private String noInventario;
    @Column(name = "tipo_combustible")
    private Short tipoCombustible;
    @Column(name = "estado")
    private Short estado;
    

    public Maquinaria() {
    }

    public Maquinaria(Integer idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    public Integer getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(Integer idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaquinaria != null ? idMaquinaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinaria)) {
            return false;
        }
        Maquinaria other = (Maquinaria) object;
        if ((this.idMaquinaria == null && other.idMaquinaria != null) || (this.idMaquinaria != null && !this.idMaquinaria.equals(other.idMaquinaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.Maquinaria[ idMaquinaria=" + idMaquinaria + " ]";
    }

    public String getMarca() {
        return marca;
    }

    public String getEstadoName(){
        switch(this.estado){
            case 1:
                return "Funcionando";
            case 2:
                return "En repación";
            case 3: 
                return "En desuso";
        }
        return "";
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

}
