package Presentacion;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelModificaGraph extends AbstractPanel {

	private JButton buttonLlamadaDominio = new JButton("IokSe Equisde");
	
	public PanelModificaGraph(VistaPrincipal vp) {
		super(vp);
		setBackground(Color.green);
		setLayout(new FlowLayout());
		add(buttonLlamadaDominio);
		
	}

	@Override
	public void closeIt() {
		// Preguntar si estas seguro de que te pueden cerrar desde un sitio externo
		vp.continueAction();
	}

}
