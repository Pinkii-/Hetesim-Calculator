package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends AbstractPanel {

	private JComboBox<String> node1Select, node2Select;
	private static final long serialVersionUID = 1L;

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		
		initComponents();
	}
	/**
	 * Initialize the components for the panel
	 * 
	 * @param nodeSelect: Dropdown list for the type of Node
	 * 
	 * 
	 */
	public void initComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		node1Select = new JComboBox<String>(new String[]{"Paper", "Autor", "Conferencia", "Term"});
		node1Select.setEditable(true);
		node1Select.setSelectedItem("");
		node1Select.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node1Select, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node1Select, 50, SpringLayout.NORTH, this);
		add(node1Select);
		
		node2Select = new JComboBox<String>(new String[]{"Paper", "Autor", "Conferencia", "Term"});
		node2Select.setEditable(true);
		node2Select.setEnabled(false);
		node2Select.setSelectedItem("");
		node2Select.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node2Select, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node2Select, 80, SpringLayout.NORTH, this);
		add(node2Select);
		
		

	}
	
	public void assignListeners(){
		
		node1Select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		node2Select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (node1Select.getSelectedItem()!="") {
					node2Select.setEnabled(true);
				}
				else node2Select.setEnabled(false);
			}
		});
		
	}

	@Override
	public int closeIt() {
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		
	}
}