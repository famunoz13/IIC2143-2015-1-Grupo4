package app;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import structures.Menu;
import structures.MenuItem;
import structures.Mesa;
import structures.Orden;

public class GenerarOrdenWindow extends JFrame implements ActionListener{
  private static final long serialVersionUID = 1L;
  private MainWindow main;
  private Menu menu;
  private JButton button1, button2;
  
  private JLabel label_nota;
  
  private JComboBox<Mesa> cb_options;
  private ArrayList<JSpinner> cantidades;
  
  public GenerarOrdenWindow(MainWindow m) {
    super("Ingresar pago");
    
    main = m;
    menu = m.getMenu();
    
    cantidades = new ArrayList<>();
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setTitle("Generar Orden");

    //Layout
    setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(5,16,5,16);
    gbc.weightx = 1;
    
    JLabel label = new JLabel("Ingresar orden",JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(label,gbc);
    
    cb_options = new JComboBox<>();
    
    for(Mesa mesa:main.getMesas()){
       cb_options.addItem(mesa);
    }
    
    cb_options.setSelectedIndex(0);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(cb_options,gbc);
    
    
    JPanel p_menu = new JPanel(new GridBagLayout());
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.weightx = 0.5;
    add(p_menu,gbc);
    
    label_nota = new JLabel("Nota:");
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    add(label_nota,gbc);
    
    final JTextField textfield = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    add(textfield,gbc);
    
    JPanel p = new JPanel(new FlowLayout());
    gbc.fill = GridBagConstraints.NONE;   
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weighty = 0;
    gbc.weightx = 0;
    gbc.gridwidth = 2;
    add(p,gbc);
    
    
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.fill = GridBagConstraints.BOTH;    
    gbc2.insets = new Insets(4,4,4,4);
    
    //Añadir menu
    p_menu.setBackground(Color.WHITE);
    p_menu.setBorder(new EmptyBorder(4, 4, 4, 4));
    
    int j = 0;
    for(final MenuItem i:menu.getItems()){
      JLabel label_plato = new JLabel(i.getNombre());
      gbc2.gridx = 0;
      gbc2.gridy = j;
      gbc2.weighty = 0;
      gbc2.weightx = 1;
      p_menu.add(label_plato,gbc2);
      
      SpinnerModel model = new SpinnerNumberModel(0, 0, 100, 1);
      cantidades.add(new JSpinner(model));
      gbc2.gridx = 1;
      gbc2.gridy = j;
      gbc2.weighty = 0;
      gbc2.weightx = 0;
      p_menu.add(cantidades.get(j),gbc2);
      
      j++;
    }
    
    button1 = new JButton("Crear Orden");
    p.add(button1);
    
    button2 = new JButton("Cancelar");
    p.add(button2);
    
    
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
        
        ArrayList<MenuItem> items = new ArrayList<>();
        ArrayList<Integer> items_q = new ArrayList<>();
        Mesa selected_mesa = (Mesa) cb_options.getSelectedItem();
        
        int i = 0;
        for(JSpinner s:cantidades){
          int value = (int) s.getValue();
          if(value != 0){
            items.add(menu.get(i));
            items_q.add(value);
          }
          i++;
        }
        
        if(items.size() == 0){
          JOptionPane.showMessageDialog(main,
              "No se ingresaron items del menú",
              "La orden no se creará",
              JOptionPane.WARNING_MESSAGE);
        }else{
          main.AddOrden(new Orden(0, selected_mesa, items, items_q, textfield.getText()));
        }
        return;
    }
    });
    
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        dispose();
    }
    });
    
    
    this.pack();
  }

  public void generarBoleta(){
    
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
  }
}
