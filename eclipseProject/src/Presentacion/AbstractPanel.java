package Presentacion;

import javax.swing.JPanel;

abstract public class AbstractPanel extends JPanel {
	
	VistaPrincipal vp;
	
	AbstractPanel(VistaPrincipal vp) {
		this.vp = vp;
	}
	
	// Preguntar si estás seguro de querer cerrarlo
	abstract public void closeIt(); 
	
}
