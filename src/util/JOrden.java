package util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import app.MainWindow;
import Listeners.ListenerBtnCancelarOrden;
import Listeners.ListenerBtnVerOrden;
import structures.*;

public class JOrden extends JPanel implements ActionListener{
  private MainWindow main;
  
  private Orden orden;
  
  private JLabel label,label2;
  private JButton btn_ver,btn_marcar,btn_cancelar;
  
  private JLabel status;
  
  private Timer t;
  private ActionListener taskPerformer;
  
  private static final long serialVersionUID = 1L;

  public JOrden(MainWindow m, Orden o) {
    main = m;
    orden = o;
    
    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;    
    
    gbc.insets = new Insets(3,0,3,0);
    
    
    label = new JLabel("Orden #" + orden.getId(),JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(label,gbc);
    
    label2 = new JLabel(o.getMesa().toString(),JLabel.LEFT);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(label2,gbc);
    
    btn_ver = new JButton("Ver orden");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(btn_ver,gbc);
    
    btn_marcar = new JButton("Marcar como entregada");
    btn_marcar.setEnabled(false);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(btn_marcar,gbc);
    
    JPanel p = new JPanel(new FlowLayout());

    gbc.gridx = 0;
    gbc.gridy = 4;
    
    status = new JLabel("Espera");
    btn_cancelar = new JButton("Cancelar");
    
    p.add(status);
    p.add(btn_cancelar);
    
    add(p,gbc);
    
    status.setOpaque(true);
    status.setBorder(new EmptyBorder(4,8,4,8));
    
    p.setBackground(Color.white); 
    setBackground(Color.white); 
    
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    setBorder(border);
    
    //Listeners
    btn_ver.addActionListener(new ListenerBtnVerOrden(main,orden));
    btn_cancelar.addActionListener(new ListenerBtnCancelarOrden(main, orden));
    btn_marcar.addActionListener(this);
    
    //Esto evita que las cuentas canceladas sean reprogramadas como nuevas órdenes
    if(orden.getEstado() == EstadoOrden.ESPERA){
      setEstado(EstadoOrden.ESPERA);
      //Cambio de estado programado
      Random randomGenerator = new Random();
      int delay = (randomGenerator.nextInt(4) + 1) * 1000; //A lo más cinco segundos
      taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              setEstado(EstadoOrden.LISTA);
              btn_marcar.setEnabled(true);
              t.removeActionListener(taskPerformer);
          }
      };
      t = new Timer(delay, taskPerformer);
      t.start();
    }else{
      setEstado(orden.getEstado());
      btn_marcar.setEnabled(true);
      btn_marcar.setText("Cancelar entrega");
    }
  }

  public Orden getOrden(){
    return orden;
  }
  
  public void setEstado(EstadoOrden e){
    orden.setEstado(e);
    
    switch(e){
    case ENTREGADA:
      status.setText("Entregada");
      status.setBackground(Color.GREEN); 
      break;
    case ESPERA:
      status.setText("Espera");
      status.setBackground(Color.pink); 
      break;
    case LISTA:
      status.setText("Lista");
      status.setBackground(Color.CYAN); 
      break;
    default:
      break;
    
    }
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(orden.getEstado() == EstadoOrden.LISTA){
      setEstado(EstadoOrden.ENTREGADA);
      btn_marcar.setText("Cancelar entrega");
    }else{
      setEstado(EstadoOrden.LISTA);
      btn_marcar.setText("Marcar como entregada");
    }
  }
}
