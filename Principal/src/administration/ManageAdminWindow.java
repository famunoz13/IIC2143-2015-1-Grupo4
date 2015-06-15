package administration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import structures.Mesa;

public class ManageAdminWindow extends JFrame {

	private LogicaAdministracion logica;
	
	private JComboBox<String> cb_options;
	
	public ManageAdminWindow (LogicaAdministracion l)
	{
		super("Gestion de usuarios");
		
		logica = l;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Gestion de usuarios");
		
		//Layout
	    setLayout(new GridBagLayout());
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;    
	    
	    gbc.insets = new Insets(5,16,5,16);
	    gbc.weightx = 1;
	    
	    JLabel label = new JLabel("Gestion de usuarios",JLabel.CENTER);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    add(label,gbc);
	    
	    
//	    for(Entry<String, String> entry : logica.getUsers().entrySet()) {
//	        String key = entry.getKey();
//	        cb_options.addItem(key);
//	    }
		
	    this.pack();
	    this.setVisible(true);
	    setLocationRelativeTo(null);
	}
}
