/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

import java.io.Serializable;
import java.util.Objects;
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
 * @author Brallan Sanchez
 */
@Entity
@Table(name = "apero")
@NamedQueries({
    @NamedQuery(name = "Apero.findAll", query = "SELECT ap FROM Apero ap")})
public class Apero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_apero")
    private Integer idApero;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anio")
    private String anio;
    @Column(name = "marca")
    private String marca;
    @Column(name = "no_inventario")
    private String noInventario;
    @Column(name = "color")
    private String color;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "funcion")
    private String funcion;

    public Apero(Integer idApero) {
        this.idApero = idApero;
    }

    public Apero() {
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNoInventario() {
        return noInventario;
    }

    public void setNoInventario(String noInventario) {
        this.noInventario = noInventario;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getIdApero() {
        return idApero;
    }

    public void setIdApero(Integer idApero) {
        this.idApero = idApero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApero != null ? idApero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Apero)) {
            return false;
        }
        Apero other = null;
        if (obj instanceof HibernateProxy) {
            other = (Apero) ((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
            return false;
        } else {
            other = (Apero) obj;
        }
        if ((this.idApero == null && other.idApero != null) || (this.idApero != null && !this.idApero.equals(other.idApero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.fca.siammat.modelo.Apero[ id_apero=" + idApero + ']';
    }

    public String getEstadoName() {
        switch (this.estado) {
            case 1:
                return "Funcionando";
            case 2:
                return "En reparacion";
            case 3:
                return "En desuso";
        }
        return "";
    }

}
