/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.ues.fca.siammat.modelo.Maquinaria;
import sv.edu.ues.fca.siammat.modelo.UnidadTransporte;

/**
 *
 * @author fran
 */
@ManagedBean
@ViewScoped
public class RptGastosMaquinariaListBean extends ListBaseBean {

    private List<Maquinaria> maquinariaList;
    private List<UnidadTransporte> unidadesTransporte;
    private Integer idMaquinaria;
    private Integer anio;
    private String reporte;

    @Override
    public String setupQuery() {
        return "";

    }

    public List<Maquinaria> getMaquinariaList() {

        if (maquinariaList == null) {
            maquinariaList = getServiceLocator().getGenericServicio().find("from Maquinaria a");
        }

        return maquinariaList;
    }

    public List<UnidadTransporte> getUnidadesTransporte() {
        if (unidadesTransporte == null) {
            unidadesTransporte = getServiceLocator().getGenericServicio().find("from UnidadTransporte a");
        }

        return unidadesTransporte;
    }

    public void generarReporte() {
        Map m = new HashMap();
        m.put("idMaquinaria", idMaquinaria);
        m.put("anho", anio);
        this.showReport(reporte, m);
    }

    @Override
    public void setUpParametros() {
        super.setUpParametros();
        getParametros().put("height", "650px");
    }

    public Integer getIdMaquinaria() {
        return idMaquinaria;
    }

    public void setIdMaquinaria(Integer idMaquinaria) {
        this.idMaquinaria = idMaquinaria;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

}
