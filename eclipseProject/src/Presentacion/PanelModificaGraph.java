package Presentacion;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.VistaPrincipal.Panels;


public class PanelModificaGraph extends AbstractPanel {

	private JButton buttonAddNode = new JButton("Add New Node");
	
	public PanelModificaGraph(VistaAbstracta vp) {
		super(vp);
		setBackground(Color.green);
		
		initComponents();
		asignListeners();
		
		
	}
	
	private void initComponents() {
		setLayout(new FlowLayout());
		add(buttonAddNode);
	}
	
	private void asignListeners() {
		PanelModificaGraph aux = this;
		buttonAddNode.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aux.addVista(PanelModificaGraph.class, false);
			}
		});
	}

	@Override
	public int closeIt() {
		// Preguntar si estas seguro de que te pueden cerrar desde un sitio externo
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		buttonAddNode.setEnabled(b);
		try { // Solo para el test. Luego lo tengo que quitar cuando ya no sea una vista abstracta y sea una vista principal
			VistaPrincipal v = (VistaPrincipal) vp;
			v.setEnabledPrincipal(b);
		}
		catch (ClassCastException e) {
			
		}
	}

}
