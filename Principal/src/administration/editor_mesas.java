package administration;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import structures.Mesa;

public class editor_mesas extends JPanel{
  private static final long serialVersionUID = 1L;
  private JPanel buttons, edit_area;
  private JButton btn_create, btn_delete, btn_move, btn_drop, btn_save, btn_exit;
  private JSpinner change;
  private JLabel status_bar;
  public editor_mesas(){
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    //################# Elementos ##################
    
    //Label: titulo
    JLabel user_label = new JLabel("- Editar mesas -");
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    add(user_label, c);
    
    //Buttons bar
    initButtons();
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(6, 6, 6, 6);
    add(buttons, c);
    
    //edit area
    edit_area = new JPanel(); 
    edit_area.setLayout(null);
    edit_area.setBackground(Color.white); 
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    edit_area.setBorder(border);
    
    JScrollPane scroll_ordenes =  new JScrollPane(edit_area,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(6, 6, 6, 6);
    add(scroll_ordenes, c);
    
    //Label: status bar
    status_bar = new JLabel("Iniciado", JLabel.RIGHT);
    status_bar.setAlignmentX(RIGHT_ALIGNMENT);
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 3;
    c.insets = new Insets(6, 6, 6, 6);
    add(status_bar, c);
    
    disableBtns();
  }

  private void initButtons(){
    buttons = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    btn_create = new JButton("Crear");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_create, c);
    
    btn_delete = new JButton("Eliminar");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 1;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_delete, c);
    
    btn_move = new JButton("Mover");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 2;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_move, c);
    
    JLabel cap = new JLabel("Capacidad:"); 
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 0;
    c.insets = new Insets(6, 12, 6, 0);
    buttons.add(cap, c);
    
    SpinnerModel model = new SpinnerNumberModel(0, 0, 100, 1);
    change = new JSpinner();
    change.add(new JSpinner(model));
    change.setPreferredSize(new Dimension(40,20));
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 4;
    c.gridy = 0;
    c.insets = new Insets(8, 2, 6, 6);
    buttons.add(change, c);
    
    btn_drop = new JButton("Soltar");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 5;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_drop, c);
    
    JLabel space = new JLabel(""); 
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 6;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(space, c);
    
    btn_save   = new JButton("Guardar y cargar");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 7;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_save, c);
    
    btn_exit   = new JButton("Salir");
    c.weightx = 0;
    c.weighty = 1;
    c.fill = GridBagConstraints.CENTER;
    c.gridx = 8;
    c.gridy = 0;
    c.insets = new Insets(6, 6, 6, 6);
    buttons.add(btn_exit, c);
    
  }

  public void enableBtns(){
    btn_delete.setEnabled(true);
    btn_move.setEnabled(true);
    change.setEnabled(true);
    btn_drop.setEnabled(true);
  }
  
  public void disableBtns(){
    btn_delete.setEnabled(false);
    btn_move.setEnabled(false);
    change.setEnabled(false);
    btn_drop.setEnabled(false);
  }
  
  public JButton addBtnMesa(Mesa mesa){
    JButton btn = new JButton(mesa.toString());
    
    edit_area.add(btn);
    
    btn.setBounds(mesa.getPosx(), mesa.getPosy(), 60, 60);
    btn.setMargin(new Insets(0, 0, 0, 0));
    btn.setFont(new Font("Arial", Font.PLAIN, 12));
    
    btn.setBackground(Color.cyan);
    
    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    return btn;
  } 

  public void removeBtnMesa(JButton b){
    if(edit_area.isAncestorOf(b)){
      edit_area.remove(b);
      edit_area.updateUI();
    }
  }
  
  public JPanel getEditArea(){
    return edit_area;
  }
  public JButton getBtn_create() {
    return btn_create;
  }

  public JButton getBtn_delete() {
    return btn_delete;
  }

  public JButton getBtn_move() {
    return btn_move;
  }

  public JSpinner getSpinner_change() {
    return change;
  }
  
  public JButton getBtn_drop() {
    return btn_drop;
  }

  public JButton getBtn_save() {
    return btn_save;
  }

  public JButton getBtn_exit() {
    return btn_exit;
  }

  public JLabel getStatusBar(){
    return status_bar;
  }

}
