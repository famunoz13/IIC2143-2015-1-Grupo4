package administration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reportes_backend {
  private LogicaAdministracion administracion;
  private Reporte_frontend frontend;
  
  public Reportes_backend(LogicaAdministracion l, Reporte_frontend f){
    administracion = l;
    frontend = f;
    
    frontend.getBtn_principal().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.setGananciaView();
      }
    });
    
    frontend.getBtn_productos().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frontend.setProductosView();
      }
    });
    
    frontend.getBtn_exit().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        administracion.setMenuPanel();
      }
    });
    
    
  }
}
