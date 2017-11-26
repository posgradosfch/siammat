/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

/**
 *
 * @author ed
 */
public class Reporte {
    private int idReporte;
    private String nombre;

    public Reporte(int idReporte, String nombre) {
        this.idReporte = idReporte;
        this.nombre = nombre;
    }

    public Reporte() {
    }
    
    

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
