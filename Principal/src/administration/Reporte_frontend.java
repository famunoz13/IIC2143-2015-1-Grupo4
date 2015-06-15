package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import log.LogRow;
import log.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Reporte_frontend extends JPanel{
  private static final long serialVersionUID = 1L;
  private JPanel left_panel, right_panel;
  private JButton btn_principal, btn_productos, btn_exit;
  public Reporte_frontend(){
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    //################# Elementos ##################
    
    //Label: titulo
    JLabel user_label = new JLabel("- Reportes -");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.insets = new Insets(6, 6, 6, 6);
    add(user_label, c);
    
    c.gridwidth = 1;
    
    //Buttons bar
    initLeftPanel();
    c.weightx = 0.2;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(left_panel, c);
    
    //edit area
    initRightPanel();
    c.weightx = 0.8;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(right_panel, c);
      
  }

  private void initLeftPanel(){
    left_panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTH;
    
    btn_principal   = new JButton("Ganancias");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 16;
    c.ipady = 16;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_principal, c);

    
    btn_productos   = new JButton("Productos");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    left_panel.add(btn_productos, c);

    
    btn_exit   = new JButton("Salir");
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(20, 6, 6, 6);
    left_panel.add(btn_exit, c);
    
  }

  private void initRightPanel(){
    right_panel = new JPanel(new GridBagLayout());
    setGananciaView();
  }
  
  public void setGananciaView(){
    right_panel.removeAll();
    Logger logger = Logger.getInstance();
    DateFormat df = new SimpleDateFormat("dd/MM");
    
    //Primer gráfico: Ganancia total
    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
    
    Map<String, Integer> grafico1_map = new HashMap<String, Integer>();
    
    for(LogRow row:logger.getLogs()){
      String key = df.format(row.getFecha());
      int value = row.getCantidad() * (row.getPrecio() - row.getCosto());
      if(grafico1_map.containsKey(key)){
        Integer i = grafico1_map.get(key);
         i += value;
      }else{
        grafico1_map.put(key, value);
      }
    }
    
    List<String> g1_orden = new ArrayList<>();
    g1_orden.addAll(grafico1_map.keySet());
    Collections.sort(g1_orden);
    
    for(String k: g1_orden){
      dataset1.addValue(grafico1_map.get(k), "Ganancia Total", k);
    }
    
    JFreeChart lineChart1 = ChartFactory.createLineChart(
        "Ganancia total por fecha",
        "Fecha","Ganancia total ($)",
        dataset1,
        PlotOrientation.VERTICAL,
        true,true,false);
        
    ChartPanel chartPanel1 = new ChartPanel(lineChart1);
    

    GridBagConstraints c = new GridBagConstraints();
    
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(2, 2, 2, 2);
    right_panel.add(chartPanel1, c);
    
    
    //Segundo gráfico: ganancia y pérdida
    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset( );

    Map<String, Integer> grafico2_map1 = new HashMap<String, Integer>();
    Map<String, Integer> grafico2_map2 = new HashMap<String, Integer>();
    
    for(LogRow row:logger.getLogs()){
      String key = df.format(row.getFecha());
      int value1 = row.getCantidad() * (row.getPrecio());
      int value2 = row.getCantidad() * (row.getCosto());
      if(grafico2_map1.containsKey(key)){
        Integer i1 = grafico2_map1.get(key);
        i1 += value1;
        Integer i2 = grafico2_map2.get(key);
        i2 += value2;
      }else{
        grafico2_map1.put(key, value1);
        grafico2_map2.put(key, value2);
      }
    }
    
    List<String> g2_orden = new ArrayList<>();
    g2_orden.addAll(grafico2_map1.keySet());
    Collections.sort(g2_orden);
    
    for(String k: g2_orden){
      dataset2.addValue(grafico2_map1.get(k), "Ingresos", k);
      dataset2.addValue(grafico2_map2.get(k), "Costos", k);
    }
    
    JFreeChart lineChart2 = ChartFactory.createLineChart(
        "Ingresos/Costos por fecha",
        "Fecha","Monto ($)",
        dataset2,
        PlotOrientation.VERTICAL,
        true,true,false);
        
    ChartPanel chartPanel2 = new ChartPanel(lineChart2);
    
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(2, 2, 2, 2);
    right_panel.add(chartPanel2, c);
    
    
    right_panel.updateUI();
  }

  public void setProductosView(){
    right_panel.removeAll();
    Logger logger = Logger.getInstance();
    //DateFormat df = new SimpleDateFormat("dd/MM");
    
    //Primer gráfico: porcentaje por item
    DefaultPieDataset dataset1 = new DefaultPieDataset();
    
    Map<String, Integer> grafico1_map = new HashMap<String, Integer>();
    
    for(LogRow row:logger.getLogs()){
      String key = row.getNombre();
      int value = row.getCantidad() * (row.getPrecio() - row.getCosto());
      if(grafico1_map.containsKey(key)){
        Integer i = grafico1_map.get(key);
         i += value;
      }else{
        grafico1_map.put(key, value);
      }
    }
    
    List<String> g1_orden = new ArrayList<>();
    g1_orden.addAll(grafico1_map.keySet());
    Collections.sort(g1_orden);
    
    for(String k: g1_orden){
      dataset1.setValue(k, grafico1_map.get(k));
    }
    
    JFreeChart piechart1 = ChartFactory.createPieChart(      
        "Porcentaje de ventas por ítem del menú",  // chart title 
        dataset1,        // data    
        true,           // include legend   
        true, 
        false);

    ChartPanel chartPanel1 = new ChartPanel(piechart1);
    
    GridBagConstraints c = new GridBagConstraints();
    
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(2, 2, 2, 2);
    right_panel.add(chartPanel1, c);
    
    
    //Segundo gráfico: porcentaje por tipo
    DefaultPieDataset dataset2 = new DefaultPieDataset();
    
    Map<String, Integer> grafico2_map = new HashMap<String, Integer>();
    
    for(LogRow row:logger.getLogs()){
      String key = row.getTipo();
      int value = row.getCantidad() * (row.getPrecio() - row.getCosto());
      if(grafico2_map.containsKey(key)){
        Integer i = grafico2_map.get(key);
         i += value;
      }else{
        grafico2_map.put(key, value);
      }
    }
    
    List<String> g2_orden = new ArrayList<>();
    g2_orden.addAll(grafico2_map.keySet());
    Collections.sort(g2_orden);
    
    for(String k: g2_orden){
      dataset2.setValue(k, grafico2_map.get(k));
    }
    
    JFreeChart piechart2 = ChartFactory.createPieChart(      
        "Porcentaje de ventas por tipo de ítem",  // chart title 
        dataset2,        // data    
        true,           // include legend   
        true, 
        false);

    ChartPanel chartPanel2 = new ChartPanel(piechart2);
    
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(2, 2, 2, 2);
    right_panel.add(chartPanel2, c);
    
    
    right_panel.updateUI();
  }
  
  public JButton getBtn_principal() {
    return btn_principal;
  }

  public JButton getBtn_productos() {
    return btn_productos;
  }

  public JButton getBtn_exit() {
    return btn_exit;
  }
  

}
