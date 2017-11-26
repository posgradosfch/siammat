/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.seguridad.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.beans.ListBaseBean;
import sv.edu.ues.fca.siammat.modelo.Reporte;

/**
 *
 * @author galicia-Fernando
 */
@ManagedBean
@ViewScoped
public class ReporteUsuarioListBean extends ListBaseBean {

    private List<Reporte> reportes;
    private int idReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private String lstPlacas;

    public ReporteUsuarioListBean() {
        reportes = new ArrayList<Reporte>();
        reportes.add(new Reporte(1, "Gastos de Combustible de Maquinaría"));
        reportes.add(new Reporte(2, "Gastos de Combustible de Unidades de Transporte"));
        //reportes.add(new Reporte(3, "Gastos de Reparación y Mantenimiento de Maquinaría"));
        //reportes.add(new Reporte(4, "Gastos de Reparación y Mantenimiento de Unidades de Transporte"));
    }

    public void generarReporte() throws ParseException {
        Map m = new HashMap();

        lstPlacas = lstPlacas.replaceAll("\\s", "");
        lstPlacas = lstPlacas.replace(",", "','");
        lstPlacas = "'" + lstPlacas + "'";

        m.put("fechaInicio", fechaInicio);
        m.put("fechaFin", fechaFin);
        m.put("placas", lstPlacas);

        if (idReporte == 1) {
            this.showReport("maq_combu_mensual.jasper", m);
        }
        if (idReporte == 2) {
            this.showReport("trans_combu_mensual.jasper", m);
        }

    }

    @Override
    public String setupQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getLstPlacas() {
        return lstPlacas;
    }

    public void setLstPlacas(String lstPlacas) {
        this.lstPlacas = lstPlacas;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

}
