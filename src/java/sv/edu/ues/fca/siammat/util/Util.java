/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author galicia
 */
public class Util {

    public static void putParamIntoSessionMap(String key, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (key != null && object != null) {
            context.getExternalContext().getSessionMap().put(key, object);
        } else {
            System.out.println("Error al poner el parametro en session");
            if (key == null) {
                System.out.println("Key es null");
            }
            if (object == null) {
                System.out.println("Objeto es null");
            }
        }

    }

    public static void removeParamFromSessionMap(String key) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (key != null) {
            context.getExternalContext().getSessionMap().remove(key);
        } else {
            System.out.println("Error al poner el parametro en session");

        }

    }

    public static Object getParamFromSessionMap(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        Object object;
        if (key != null) {
            object = context.getExternalContext().getSessionMap().get(key);

            if (object != null) {
                return object;
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public static void addMessage(FacesMessage.Severity severity, String title, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, title, detail));
    }
    
    public static void addErrorMessage(String  text) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, text, ""));
    }

    public static void showDialog(String dialog, Map<String, Object> mapa) {
        Map<String, Object> options = new HashMap<>();
        if (mapa != null) { 
            options = mapa;
        }

        options.put("resizable", false);
       options.put("draggable", false);
       //options.put("modal", true);

        //options.put("height", "390px");
        RequestContext.getCurrentInstance().openDialog(dialog, options, null);

    }

    public static void redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera el reporte y lo envia al servlet
     *
     * @param reporte archivo con extension .jasper
     * @param parametros parametros necesarios en el reporte
     */
    public static void showReport(String reporte, Map parametros) {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        ServletContext context = (ServletContext) externalContext.getContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//
//        String path = context.getRealPath("/reportes/" + reporte);
//
//        JasperPrint jasperPrint = null;
//        try {
//            jasperPrint = JasperFillManager.fillReport(path, parametros, ServiceLocator.getBasicService().getConexion());
//            request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
//        String javascript = "document.getElementById('srpt').click();";
//        RequestContext.getCurrentInstance().execute(javascript);
//        //FacesContext.getCurrentInstance().responseComplete();
    }

    public static String getAplicationPath(String path) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        return context.getRealPath(path);
    }
}
