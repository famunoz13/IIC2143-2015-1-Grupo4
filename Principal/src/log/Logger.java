package log;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structures.Cuenta;
import structures.MenuItem;
import structures.Orden;

public class Logger {
  private static Logger l;
  
  private ArrayList<LogRow> logs;
  private String log_file = "logs.xml";
  
  private Logger(){
    logs = new ArrayList<>();
    load();
  }
  
  public static Logger getInstance(){
    if(l == null){
      l = new Logger();
    }

    return l;
  }
  
  public void log(Cuenta c){
    for(Orden o : c.getOrdenes()){
      ArrayList<MenuItem> items = o.getItems();
      ArrayList<Integer> cantidades = o.getCantidades();
      for(MenuItem item:items){
        LogRow row = new LogRow(GenerateId());
        row.setCantidad(cantidades.get(items.indexOf(item)));
        row.setFecha(o.getFecha());
        row.setId_item(item.getId());
        row.setNombre(item.getNombre());
        row.setPrecio(item.getPrecio());
        row.setCosto(item.getCosto());
        row.setTipo(item.getTipo());
        logs.add(row);
      }
    }
    
    save();
  }
  
  private int GenerateId(){
    int id = 0;
    for(LogRow row:logs){
      if(row.getId() >id)
        id = row.getId();
    }
    
    return id+1;
  }
  
  public void save(){
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
   
      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("logs");
      doc.appendChild(rootElement);
      
      
      for (LogRow row : logs) {
        Element log_node = doc.createElement("log");
        rootElement.appendChild(log_node);
        
        Attr id = doc.createAttribute("id");
        id.setValue(row.getId() + "");
        log_node.setAttributeNode(id);
        
        Attr id_item = doc.createAttribute("id_item");
        id_item.setValue(row.getId_item() + "");
        log_node.setAttributeNode(id_item);
        
        Attr fecha = doc.createAttribute("fecha");
        fecha.setValue(df.format(row.getFecha()) + "");
        log_node.setAttributeNode(fecha);
        
        Attr nombre = doc.createAttribute("nombre");
        nombre.setValue(row.getNombre());
        log_node.setAttributeNode(nombre);
        
        Attr tipo = doc.createAttribute("tipo");
        tipo.setValue(row.getTipo());
        log_node.setAttributeNode(tipo);
        
        Attr precio = doc.createAttribute("precio");
        precio.setValue(row.getPrecio() + "");
        log_node.setAttributeNode(precio);
        
        Attr costo = doc.createAttribute("costo");
        costo.setValue(row.getCosto() + "");
        log_node.setAttributeNode(costo);
        
        Attr cantidad = doc.createAttribute("cantidad");
        cantidad.setValue(row.getCantidad() + "");
        log_node.setAttributeNode(cantidad);
      }
  
      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(log_file));
      transformer.transform(source, result);
 
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
  }
  
  public void load() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    try {
      File fXmlFile = new File(log_file);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("log");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          
          LogRow row = new LogRow(Integer.parseInt(eElement.getAttribute("id")));
          row.setFecha(df.parse(eElement.getAttribute("fecha")));
          row.setId_item(Integer.parseInt(eElement.getAttribute("id_item")));
          row.setNombre(eElement.getAttribute("nombre"));
          row.setTipo(eElement.getAttribute("tipo"));
          row.setPrecio(Integer.parseInt(eElement.getAttribute("precio")));
          row.setCosto(Integer.parseInt(eElement.getAttribute("costo")));
          row.setCantidad(Integer.parseInt(eElement.getAttribute("cantidad")));
          
          logs.add(row);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<LogRow> getLogs(){
    return logs;
  }
}
