package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> node1Select, node2Select;
	private static final long serialVersionUID = 1L;
	private ComboBoxModel[] nodeStrings = new ComboBoxModel[4];
	

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		
		initComponents();
		initStrings();
		assignListeners();
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
		node1Select.setSelectedIndex(-1);
		node1Select.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node1Select, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node1Select, 50, SpringLayout.NORTH, this);
		add(node1Select);
		
		node2Select = new JComboBox<String>(new String[]{"Paper2", "Autor2", "Conferencia2", "Term2"});
		node2Select.setEditable(true);
		node2Select.setEnabled(false);
		node2Select.setSelectedIndex(-1);
		node2Select.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node2Select, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node2Select, 80, SpringLayout.NORTH, this);
		add(node2Select);
		
	}
	
	public void initStrings(){
		
		//Papers
		nodeStrings[0] = new DefaultComboBoxModel(
				new String[]{"Paper1","Paper2","Paper3","Etc"});
		//Autors
		nodeStrings[1] = new DefaultComboBoxModel(
				new String[]{"Autor1","Autor2","Autor3","Autor4","Autor5","Autor6","Autor7","Autor8","This shouldn't be displayed"});
		//Conferencies
		nodeStrings[2] = new DefaultComboBoxModel(
				new String[]{"Conferencia1"});
		//Terms
		nodeStrings[3] = new DefaultComboBoxModel(
				new String[]{"Term0", "Term1"});
		
	}
	
	public void assignListeners(){
		
		node1Select.addActionListener(this);
		node2Select.addActionListener(this);
		
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
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource().equals(node1Select)){
			if (node1Select.getSelectedIndex() != -1){
				node2Select.setEnabled(true);
				node2Select.setModel(nodeStrings[node1Select.getSelectedIndex()]);
				node2Select.setSelectedIndex(0);
			}
			else {
				node2Select.setSelectedIndex(-1);
				node2Select.setModel(nodeStrings[0]);
				node2Select.setEnabled(false);
			}
		}
		
	}
}