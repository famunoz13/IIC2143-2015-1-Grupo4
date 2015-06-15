package administration;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import app.Restaurant;
import structures.Mesa;


public class LogicaAdministracion {
  private JPanel admin_panel;
  private HashMap<String,String> users;
  private login_form form;
  private admin_menu menu;
  private editor_mesas editor;
  
  @SuppressWarnings("unused")
  private Reportes_backend reportes;
  private Menus_backend menus;
  private ArrayList<Mesa> mesas;
  private HashMap<JButton,Mesa> btn_mesa;
  private JButton selected = null;
  
  public Restaurant restaurant;
  
  public LogicaAdministracion(Restaurant r){
    restaurant = r;
    
    //Cargar cuentas de administradores
    users = new HashMap<>();
    loadUsersXML("administradores.xml");
    
    //Crear frontend inicial
    initPanel();
    setFormPanel();

  }
  
  public HashMap<String, String> getUsers(){
	  return users;
  }
  
  
  private void setFormPanel(){
    admin_panel.removeAll();
    
    form = new login_form();   
    admin_panel.add(form);
    initLoginButton();
    admin_panel.updateUI();
  }
  
  public void setMenuPanel(){
    admin_panel.removeAll();
    
    menu = new admin_menu();
    admin_panel.add(menu);
    initMenuButtons();
    admin_panel.updateUI();
  }
  
  private void setEditorPanel(){
    admin_panel.removeAll();
      
    editor = new editor_mesas();
    admin_panel.add(editor);
    initEditor();    
    admin_panel.updateUI();
  }
  
  private void setReportesPanel(){
    admin_panel.removeAll();
    
    Reporte_frontend f = new Reporte_frontend();
    reportes = new Reportes_backend(this, f);
    admin_panel.add(f);
    admin_panel.updateUI();
  }
  
  private void setMenusPanel(){
    admin_panel.removeAll();
    
    menus = new Menus_backend(this);

    admin_panel.add(menus.getFrontend());
    admin_panel.updateUI();
  }
  
  
  private void initEditor(){
    mesas = new ArrayList<>();
    loadMesasXML("mesas.xml");
    
    btn_mesa = new HashMap<>();
    for (Mesa mesa : mesas) {
      addBtnMesa(mesa);
    }
    
    //Listeners
    editor.getBtn_create().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        aCreate();
      }
    });
    
    editor.getBtn_delete().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        aDelete();
      }
    });
    
    editor.getBtn_move().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        btn_mesa.get(selected).setIposx(selected.getBounds().x);
        btn_mesa.get(selected).setIposy(selected.getBounds().y);
        editor.getEditArea().setComponentZOrder(selected, 0);
        aMove();
      }
    });
    
    editor.getBtn_drop().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        aDrop();
      }
    });
    
    editor.getBtn_save().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        aSave();
      }
    });
    
    editor.getBtn_exit().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        aExit();
      }
    });
  }

  public void initLoginButton(){
    JButton b = form.getButton();
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        String user = form.getUser();
        String pass = form.getPassword();
        
        String pass_stored = users.get(user);
        
        if(pass_stored == null){
          JOptionPane.showMessageDialog(form,
              "Ese usuario no existe",
              "Error al ingresar",
              JOptionPane.WARNING_MESSAGE);
        }else{

          if(!pass_stored.equals(pass)){
            JOptionPane.showMessageDialog(form,
                "El usuario y la contraseña no coinciden",
                "Error al ingresar",
                JOptionPane.WARNING_MESSAGE);
          }else{
            setMenuPanel();
          }
        }  
      }
     });
  }
      
  private void initMenuButtons(){
    menu.getBtnEditor().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setEditorPanel();
      }
    });
    
    menu.getBtn_reportes().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setReportesPanel();
      }
    });
    
    menu.getBtn_editor_menu().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setMenusPanel();
      }
    });
    
    menu.getBtnExit().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setFormPanel();
      }
    });
    
    
  }
  
  
  //actions
  private void aCreate(){
    int id = 0;
    for(int i=1; i<=mesas.size(); i++){
      boolean free = true;
      for (Mesa mesa : mesas) {
        if(mesa.getId() == i){
          free = false;
          break;
        }
      }
      if(free){
        id = i;
        break;
      }
    }
    
    if(id == 0)
      id = mesas.size() + 1;
    
    Mesa mesa = new Mesa(id,350,350,4);
    mesas.add(mesa);
    
    if(selected != null){
      aDrop();
    }
    
    selected = addBtnMesa(mesa);
    aMove();
    
    Display(mesa.toString() + " creada!");
  }
  
  private void aDelete(){
    if(selected != null){
      Mesa m_selected = btn_mesa.get(selected);
      mesas.remove(m_selected);
      btn_mesa.remove(selected);
      
      editor.disableBtns();
      editor.removeBtnMesa(selected);
      
      selected = null;
      
      Display("La mesa " + m_selected.toString() + " ha sido eliminada");
    }
  }
  
  private void aMove(){
    if(selected != null){
      selected.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
      
      for(ActionListener al : selected.getActionListeners())
        selected.removeActionListener(al);
      
      
      editor.getEditArea().addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
          selected.setBounds(10*(int)((e.getX() - 30)/10),10*(int)((e.getY() - 30)/10), 60, 60);
          
          boolean can = canBeDropped(selected);
          if(can){
            selected.setBackground(Color.cyan);
          }else{
            selected.setBackground(Color.RED);
          }
          
          editor.updateUI();
          Display("Moviendo la mesa " + btn_mesa.get(selected).toString() + " (" + selected.getBounds().x + "," + selected.getBounds().y+")");
        }
      });
      
      selected.addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
          int x = selected.getBounds().x + e.getX() - 30;
          int y = selected.getBounds().y + e.getY() - 30;
          selected.setBounds(10*(int)(x/10), 10*(int)(y/10), 60, 60);
          
          boolean can = canBeDropped(selected);
          if(can){
            selected.setBackground(Color.cyan);
          }else{
            selected.setBackground(Color.RED);
          }
          
          editor.updateUI();
          Display("Moviendo la mesa " + btn_mesa.get(selected).toString() + " (" + selected.getBounds().x + "," + selected.getBounds().y+")");
        }
      });
      
      selected.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          for(MouseMotionListener al : editor.getEditArea().getMouseMotionListeners())
            editor.getEditArea().removeMouseMotionListener(al);
          
          for(MouseMotionListener al : selected.getMouseMotionListeners())
            selected.removeMouseMotionListener(al);
          
          selected.removeActionListener(this);
          setBtnMesaListeners(selected);
          
          boolean can = canBeDropped(selected);
          selected.setBackground(Color.cyan);
          Mesa mesa = btn_mesa.get(selected);
          if(can){
            mesa.setPosx(selected.getBounds().x);
            mesa.setPosy(selected.getBounds().y);
            Display("La mesa " + btn_mesa.get(selected).toString() + " fue colocada en (" + selected.getBounds().x + "," + selected.getBounds().y+")");
          }else{
            mesa.setPosx(mesa.getIposx());
            mesa.setPosy(mesa.getIposy());
            
            selected.setBounds(mesa.getIposx(), mesa.getIposy(), 60, 60);
            editor.updateUI();
            
            Display("La mesa " + btn_mesa.get(selected).toString() + " no puede ser colocada en ese lugar");
          }
         }
      });
    }
  }
  
  private void aDrop(){
    if(selected != null && btn_mesa.get(selected) != null){
      Mesa mesa = btn_mesa.get(selected);
      mesa.setPosx(selected.getBounds().x);
      mesa.setPosy(selected.getBounds().y);
      
      int cap = (int) editor.getSpinner_change().getValue();
      if(cap < 1)
        cap = 1;
      mesa.setCapacidad(cap);
      
      selected = null;
      editor.disableBtns();
    }
  }
  
  private void aSave(){
    int dialogResult = JOptionPane.showConfirmDialog(null, 
        "Se perderán todas las órdenes sin atender\n ¿Está seguro que desea realizar esta acción?",
        "Advertencia",
        JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION){
      SaveXML("mesas.xml");
      restaurant.reload();
      setMenuPanel();
    }
  }
  
  private void aExit(){
    int dialogResult = JOptionPane.showConfirmDialog(null, 
        "Todos los cambios realizados se perderán\n¿Está seguro que desea realizar esta acción?",
        "Advertencia",
        JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION){
      setMenuPanel();
    }
  }
  
  //Save function
  private void SaveXML(String path){
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
   
      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("mesas");
      doc.appendChild(rootElement);
      
      
      for (Mesa mesa : mesas) {
        Element mesa_node = doc.createElement("mesa");
        rootElement.appendChild(mesa_node);
     
        Attr id = doc.createAttribute("id");
        id.setValue(mesa.getId()+"");
        mesa_node.setAttributeNode(id);
        
        Attr posx = doc.createAttribute("posx");
        posx.setValue(mesa.getPosx()+"");
        mesa_node.setAttributeNode(posx);
  
        Attr posy = doc.createAttribute("posy");
        posy.setValue(mesa.getPosy()+"");
        mesa_node.setAttributeNode(posy);
        
        Attr capacidad = doc.createAttribute("capacidad");
        capacidad.setValue(mesa.getCapacidad()+"");
        mesa_node.setAttributeNode(capacidad);
        
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
  
  private JButton addBtnMesa(final Mesa mesa){
    final JButton b = editor.addBtnMesa(mesa);
    btn_mesa.put(b, mesa);
    
    setBtnMesaListeners(b);
    
    return b;
  }
  
  private int d = 1;
  private boolean canBeDropped(JButton s){
    for (JButton btn : btn_mesa.keySet()) {
      if(btn != s){
        if(contains(s, btn.getBounds().x + d, btn.getBounds().y + d)){
          return false;
        }else if(contains(s, btn.getBounds().x + d, btn.getBounds().y + 60 - d)){
          return false;
        }else if(contains(s, btn.getBounds().x + 60 - d, btn.getBounds().y + d)){
          return false;
        }else if(contains(s, btn.getBounds().x + 60 - d, btn.getBounds().y + 60 - d)){
          return false;
        }
      }
    }
    return true;
  }
  
  private void setBtnMesaListeners(final JButton b){
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if(selected != null){
          aDrop();
        }
        
        selected = b;
        editor.enableBtns();
        
        editor.getSpinner_change().setValue(btn_mesa.get(selected).getCapacidad());
        
        Display("Mesa seleccionada: " + btn_mesa.get(b).toString());
      }
    });
  }

  private boolean contains(JButton s, int x, int y){
    if(x < s.getBounds().x)
      return false;
    
    if(x > s.getBounds().x + 60)
      return false;
    
    if(y < s.getBounds().y)
      return false;
    
    if(y > s.getBounds().y + 60)
      return false;
    
    return true;
  }
  
  private void Display(String str){
    editor.getStatusBar().setText(str);
  }
  
  private void initPanel(){
    admin_panel = new JPanel();
    admin_panel.setLayout(new GridLayout(1,1));
  }

  public JPanel getPanel(){
    return admin_panel;
  }
  
  private void loadUsersXML(String path) {
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("user");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          String username = eElement.getAttribute("username");
          String password = eElement.getAttribute("password");

          users.put(username, password);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void loadMesasXML(String path) {
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("mesa");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          int id = Integer.parseInt(eElement.getAttribute("id"));
          int posx = Integer.parseInt(eElement.getAttribute("posx"));
          int posy = Integer.parseInt(eElement.getAttribute("posy"));
          int capacidad = Integer.parseInt(eElement.getAttribute("capacidad"));

          mesas.add(new Mesa(id, posx, posy, capacidad));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
