package frontend;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import structures.Orden;
import logic.Cocina;
import logic.FILA;

public class CocinaWindow extends JFrame {
  private static final long serialVersionUID = 1L;
  
  @SuppressWarnings("unused")
  private Cocina c;
  
  private JScrollPane scroll_espera, scroll_atendiendo, scroll_atendidas;
  private JPanel col_espera, col_atendiendo, col_atendidas;
  
  private HashMap<Orden,JOrden> jordenes;
  
  public CocinaWindow(Cocina cocina){
    super("Cocina");
    
    c = cocina;
    jordenes = new HashMap<>();
    
    setSize(960, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    init();
    
    setLocationRelativeTo(null);
  }
 
  private void init(){
    JPanel panel = new JPanel(false);
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    //Titulo
    JLabel titulo = new JLabel("- Cocina -", JLabel.CENTER);
    titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    titulo.setFont(new Font("Arial", Font.PLAIN, 18));
    c.weightx = 0.33;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 3;
    c.insets = new Insets(6, 6, 6, 6);
    panel.add(titulo, c);
    
    c.gridwidth = 1;
    
    //Titulo: espera
    JLabel titulo_espera = new JLabel("- Órdenes en espera -", JLabel.CENTER);
    titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    c.weightx = 0.33;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    panel.add(titulo_espera, c);
    
    //Titulo: atendiendo
    JLabel titulo_atendiendo = new JLabel("- Siendo atendidas -", JLabel.CENTER);
    titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    c.weightx = 0.33;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    panel.add(titulo_atendiendo, c);
    
    //Titulo: atendidas
    JLabel titulo_atendidas = new JLabel("- Órdenes listas -", JLabel.CENTER);
    titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    c.weightx = 0.33;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 2;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    panel.add(titulo_atendidas, c);
    
    // Panel espera
    col_espera = new JPanel(false);
    col_espera.setLayout(new GridLayout(4, 1));
    c.weightx = 0.33;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(6, 6, 6, 6);
    scroll_espera =  new JScrollPane(col_espera,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel.add(scroll_espera, c);

    // Panel atentiendo
    col_atendiendo = new JPanel(false);
    col_atendiendo.setLayout(new GridLayout(4, 1));
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.33;
    c.weighty = 1;
    c.gridx = 1;
    c.gridy = 2;
    scroll_atendiendo = new JScrollPane(col_atendiendo,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel.add(scroll_atendiendo, c);

    // Panel atendidas
    col_atendidas = new JPanel(false);
    col_atendidas.setLayout(new GridLayout(4, 1));
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.33;
    c.weighty = 1;
    c.gridx = 2;
    c.gridy = 2;
    scroll_atendidas = new JScrollPane(col_atendidas,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel.add(scroll_atendidas, c);

    add(panel);
  }

  public void Move(Orden o, FILA from, FILA to){
    //Mueve un elemento de una fila a otra
    JPanel col_from, col_to;
    
    switch(from){
    case ATENDIDA:
      col_from = col_atendidas;
      break;
    case ATENDIENDO:
      col_from = col_atendiendo;
      break;
    case ESPERA: default:
      col_from = col_espera;
      break;
    }
    
    switch(to){
    case ATENDIDA:
      col_to = col_atendidas;
      break;
    case ATENDIENDO:
      col_to = col_atendiendo;
      break;
    case ESPERA: default:
      col_to = col_espera;
      break;
    }
    
    JOrden j = jordenes.get(o);
    
    if(col_from.isAncestorOf(j)){
      col_from.remove(j);
      if(col_from.getComponentCount() < 4)
        col_from.setLayout(new GridLayout(4, 1));
      else
        col_from.setLayout(new GridLayout(col_from.getComponentCount(), 1));
    }
      
    col_to.add(j);
    if(col_to.getComponentCount() < 4)
      col_to.setLayout(new GridLayout(4, 1));
    else
      col_to.setLayout(new GridLayout(col_to.getComponentCount(), 1));
    
    col_espera.updateUI();
    col_atendiendo.updateUI();
    col_atendidas.updateUI();
    
    j.UpdateView(to);
  }
  
  public JOrden addOrden(FILA fila, Orden o){
    JOrden j = new JOrden(o);
    JPanel col;
     
    switch (fila) {
    case ESPERA:
      col = col_espera;
      break;
    case ATENDIENDO:
      col = col_atendiendo;
      break;
    case ATENDIDA:
      col = col_atendidas;
      break;
    default:
      col = col_espera;
      break;
    }
    
    col.add(j);
    
    int ordenes_size = col.getComponentCount();
    
    if(ordenes_size <= 4)
      col.setLayout(new GridLayout(4, 1));
    else
      col.setLayout(new GridLayout(ordenes_size + 1, 1));
    

    col.updateUI();
    
    jordenes.put(o, j);
    j.UpdateView(fila);
    
    return j;
  }
  
  public void removeOrden(Orden o){
	Orden orden = null;
	for (Orden key : jordenes.keySet())
	  if(key.getId() == o.getId())
		orden = key;
	
	if(orden == null)
		return;
	
    JOrden j = jordenes.get(orden);
    JPanel col = (JPanel) j.getParent();
    
    col.remove(j);
    
    int ordenes_size = col.getComponentCount();
    
    if(ordenes_size <= 4)
      col.setLayout(new GridLayout(4, 1));
    else
      col.setLayout(new GridLayout(ordenes_size + 1, 1));
    

    col.updateUI();
    
  }
  
  public JPanel getCol_espera() {
    return col_espera;
  }

  public JPanel getCol_atendiendo() {
    return col_atendiendo;
  }

  public JPanel getCol_atendidas() {
    return col_atendidas;
  }
}