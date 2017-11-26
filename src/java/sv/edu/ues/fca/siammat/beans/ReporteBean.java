package sv.edu.ues.fca.siammat.beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import sv.edu.ues.fca.siammat.modelo.Player;
 
@ManagedBean
@ViewScoped
public class ReporteBean implements Serializable {
 
    private PieChartModel pieModel1;
    private BarChartModel barModel;
    private LineChartModel dateModel;
    private LineChartModel grupalModel;
    
    private final static String[] playerNames;
    private List<String> years;
    private List<Player> players;
    
    static {
        playerNames = new String[10];
        playerNames[0] = "Enero";
        playerNames[1] = "Febrero";
        playerNames[2] = "Marzo";
        playerNames[3] = "Abril";
        playerNames[4] = "Mayo";
        playerNames[5] = "Junio";
        playerNames[6] = "Julio";
        playerNames[7] = "Agosto";
        playerNames[8] = "Septiembre";
        playerNames[9] = "Octubre";
    }
    
    private String tipoGrafico;
    private Date date3;
    private Date date4;
    private String[] selectedConsoles;
 
    @PostConstruct
    public void init() {
        createPieModels();
        createBarModels();
        createDateModel();
        createGrupalModel();
        
        years = new ArrayList<String>();
        years.add("FIAT 1");
        years.add("FIAT 2");
        years.add("FIAT 3");
        years.add("FIAT 4");
        years.add("FIAT 5");
        years.add("FIAT 6");
         
        players = new ArrayList<Player>();
        for(int i = 0; i < 10; i++) {
            players.add(new Player(playerNames[i], generateRandomGoalStatsData()));
        }
    }
    
    public String getTipoGrafico() {
        return tipoGrafico;
    }
 
    public void setTipoGrafico(String tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }
    
    public Date getDate3() {
        return date3;
    }
 
    public void setDate3(Date date3) {
        this.date3 = date3;
    }
 
    public Date getDate4() {
        return date4;
    }
 
    public void setDate4(Date date4) {
        this.date4 = date4;
    }
    
    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }
 
    public void onNew() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("edit", options, null);
    }
    
    public void onSearch() {
        
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
          
    private void createPieModels() {
        createPieModel1();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
     
    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
    public LineChartModel getDateModel() {
        return dateModel;
    }
     
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set("2014-01-01", 32);
        series2.set("2014-01-06", 73);
        series2.set("2014-01-12", 24);
        series2.set("2014-01-18", 12);
        series2.set("2014-01-24", 74);
        series2.set("2014-01-30", 62);
 
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
         
        dateModel.setTitle("Zoom for Details");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");
         
        dateModel.getAxes().put(AxisType.X, axis);
    }
    
    public LineChartModel getGrupalModel() {
        return grupalModel;
    }
    private void createGrupalModel() {
        grupalModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set("2014-01-30", 51);
        series1.set("2014-02-30", 22);
        series1.set("2014-03-30", 65);
        series1.set("2014-04-30", 74);
        series1.set("2014-05-30", 24);
        series1.set("2014-06-30", 51);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set("2014-01-30", 32);
        series2.set("2014-02-30", 73);
        series2.set("2014-03-30", 24);
        series2.set("2014-04-30", 12);
        series2.set("2014-05-30", 74);
        series2.set("2014-06-30", 62);
 
        grupalModel.addSeries(series1);
        grupalModel.addSeries(series2);
         
        //grupalModel.setTitle("Zoom for Details");
        grupalModel.setZoom(true);
        grupalModel.getAxis(AxisType.Y).setLabel("[Variable]");
        DateAxis axis = new DateAxis("[Periodos]");
        axis.setTickAngle(-50);
        //axis.setMax("2014-12-30");
        axis.setTickFormat("%b %#d, %y");
         
        grupalModel.getAxes().put(AxisType.X, axis);
    }
    
    private int getRandomAmount() {
        return (int) (Math.random() * 100000);
    }
 
    private int getRandomPercentage() {
        return (int) (Math.random() * 100);
    }

 
    public List<String> getYears() {
        return years;
    }
     
    public int getYearCount() {
        return years.size();
    }
 
    public List<Player> getPlayers() {
        return players;
    }
 
    private Map<String,Integer> generateRandomGoalStatsData() {
        Map<String,Integer> stats = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < 6; i++) {
            stats.put(years.get(i), getRandomGoals());
        }
         
        return stats;
    }
     
    private int getRandomGoals() {
        return (int) (Math.random() * 50);
    }

}