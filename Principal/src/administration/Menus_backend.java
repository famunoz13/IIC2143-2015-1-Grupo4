package administration;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
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

import structures.Menu;
import structures.MenuItem;

public class Menus_backend {
  private LogicaAdministracion administracion;
  private Menus_frontend frontend;
  private Menu menu;
  
  public Menus_backend(LogicaAdministracion l){
    administracion = l;
    
    menu = new Menu();
    loadMenuXML("menu.xml");
    
    frontend = new Menus_frontend(menu);
    
    frontend.getTable().setRowSelectionInterval(0, 0);
    
    final Menus_backend menus = this;
    
    frontend.getBtn_crear().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MenuItem e = new MenuItem(generateId(), "", 0, 0, "", "");
        new Menus_itemWindow(menus, e, 2);
      }
    });
    
    frontend.getBtn_modificar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        JTable table = frontend.getTable();
        int selected_id = (int)table.getValueAt(table.getSelectedRow(), 0);
        
        MenuItem selected = getItemById(selected_id);
        if(selected == null)
          return;
        new Menus_itemWindow(menus, selected, 1);
      }
    });
    
    frontend.getBtn_eliminar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        JTable table = frontend.getTable();
        int selected_id = (int)table.getValueAt(table.getSelectedRow(), 0);
        MenuItem selected = getItemById(selected_id);
        if(selected == null)
          return;
        
        int dialogResult = JOptionPane.showConfirmDialog(null, 
            "El ítem será completamente borrado\n¿Está seguro que desea realizar esta acción?",
            "Advertencia",
            JOptionPane.YES_NO_OPTION);
        if(dialogResult != JOptionPane.YES_OPTION){
          return;
        }
        
        ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
        menu.remove(selected);
        
        table.setColumnSelectionInterval(0, 0);
      }
    });
    
    frontend.getBtn_guardar().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        saveMenuXML("menu.xml");
        administracion.restaurant.setMenu(menu);
        administracion.setMenuPanel();
      }
    });
    frontend.getBtn_exit().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
            "Los cambios realizados se perderán\n¿Está seguro que desea realizar esta acción?",
            "Advertencia",
            JOptionPane.YES_NO_OPTION);
        if(dialogResult != JOptionPane.YES_OPTION){
          return;
        }
        
        administracion.setMenuPanel();
      }
    });
  }
  
  private MenuItem getItemById(int id){
    for(MenuItem i:menu.getItems()){
      if(i.getId() == id)
        return i;
    }
    return null;
  }
  
  private int generateId(){
    int id = 1;
    for(MenuItem i:menu.getItems()){
      if(i.getId() > id)
        id = i.getId();
    }
    return id + 1;
  }
  
  public void saveMenuXML(String path){
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
   
      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("menu");
      doc.appendChild(rootElement);
      
      
      for (MenuItem row : menu.getItems()) {
        Element log_node = doc.createElement("menuitem");
        rootElement.appendChild(log_node);
        
        Attr id = doc.createAttribute("id");
        id.setValue(row.getId() + "");
        log_node.setAttributeNode(id);
        
        Attr nombre = doc.createAttribute("nombre");
        nombre.setValue(row.getNombre());
        log_node.setAttributeNode(nombre);
        
        Attr tipo = doc.createAttribute("tipo");
        tipo.setValue(row.getTipo());
        log_node.setAttributeNode(tipo);
        
        Attr descripcion = doc.createAttribute("descripcion");
        descripcion.setValue(row.getDescripcion());
        log_node.setAttributeNode(descripcion);
        
        Attr precio = doc.createAttribute("precio");
        precio.setValue(row.getPrecio() + "");
        log_node.setAttributeNode(precio);

        Attr costo = doc.createAttribute("costo");
        costo.setValue(row.getCosto() + "");
        log_node.setAttributeNode(costo);
        
      }
  
      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(path));
      transformer.transform(source, result);
 
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
  }
  
  private void loadMenuXML(String path) {
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("menuitem");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          int id = Integer.parseInt(eElement.getAttribute("id"));
          String tipo = eElement.getAttribute("tipo");
          int precio = Integer.parseInt(eElement.getAttribute("precio"));
          int costo = Integer.parseInt(eElement.getAttribute("costo"));
          String nombre = eElement.getAttribute("nombre");
          String descripcion = eElement.getAttribute("descripcion");

          menu.add(new MenuItem(id, tipo, precio, costo, nombre, descripcion));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public Component getFrontend() {
    return frontend;
  }

  public void UpdateMenu(MenuItem item) {
    JTable table = frontend.getTable();
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    int row = 0;
    for(int i=0; i<model.getRowCount(); i++){
      int id = (int)model.getValueAt(i, 0);
      if(id == item.getId()){
        model.setValueAt(item.getNombre(), i, 1);
        model.setValueAt(item.getTipo(), i, 2);
        model.setValueAt(item.getDescripcion(), i, 3);
        model.setValueAt(item.getPrecio(), i, 4);
        model.setValueAt(item.getCosto(), i, 5);
        
        row = i;
        continue;
      }
    }

    model.fireTableChanged(null);
  
    ListSelectionModel selectionModel = table.getSelectionModel();
    selectionModel.setSelectionInterval(row, row);
  }

  public void AddToMenu(MenuItem item) {
    menu.getItems().add(item);
    
    JTable table = frontend.getTable();
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    
    Object[] row = new Object[6];
    row[0] = item.getId();
    row[1] = item.getNombre();
    row[2] = item.getTipo();
    row[3] = item.getDescripcion();
    row[4] = item.getPrecio();
    row[5] = item.getCosto();
    model.addRow(row);

    model.fireTableChanged(null);
  
    ListSelectionModel selectionModel = table.getSelectionModel();
    selectionModel.setSelectionInterval(0, 0);
    
  }
}
