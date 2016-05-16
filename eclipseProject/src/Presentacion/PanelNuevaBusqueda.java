package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> node1SelectType, node2SelectType;
	private JComboBox<String> node1Select, node2Select;
	private static final long serialVersionUID = 1L;
	private ComboBoxModel<String>[] nodeStrings = new ComboBoxModel[4];
	

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		
		initComponents();
		initStrings();
		assignListeners();
	}
	
	/**
	 * Initialize the components for the panel
	 * 
	 * node1SelectType: Dropdown list to pick the NodeType for the first node
	 * node2SelectType: Dropdown list to pick the NodeType for the second node
	 * node1Select: Dropdown list to pick the first node. Only the nodes that belong to the selected NodeType in node1SelectType will be displayed
	 * node2Select: Dropdown list to pick the last node. Only the nodes that belong to the selected NodeType in node2SelectType will be displayed
	 * 
	 * 
	 */
	public void initComponents() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		node1SelectType = new JComboBox<String>(new String[]{" - Pick a type -","Paper", "Autor", "Conferencia", "Term"});
		node1SelectType.setEditable(true);
		node1SelectType.setSelectedIndex(0);
		springLayout.putConstraint(SpringLayout.WEST, node1SelectType, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node1SelectType, 50, SpringLayout.NORTH, this);
		add(node1SelectType);
		
			node1Select = new JComboBox<String>();
			node1Select.setEditable(true);
			node1Select.setEnabled(false);
			node1Select.setSelectedIndex(-1);
			springLayout.putConstraint(SpringLayout.WEST, node1Select, 20, SpringLayout.EAST, node1SelectType);
			springLayout.putConstraint(SpringLayout.NORTH, node1Select, 50, SpringLayout.NORTH, this);
			add(node1Select);
		
		node2SelectType = new JComboBox<String>(new String[]{" - Pick a type -", "Paper 2", "Autor 2", "Conferencia 2", "Term 2"});
		node2SelectType.setEditable(true);
		node2SelectType.setEnabled(false);
		node2SelectType.setSelectedIndex(0);
		node2SelectType.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node2SelectType, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node2SelectType, 20, SpringLayout.SOUTH, node1SelectType);
		add(node2SelectType);
		
			node2Select = new JComboBox<String>();
			node2Select.setEditable(true);
			node2Select.setEnabled(false);
			node2Select.setSelectedIndex(-1);
			springLayout.putConstraint(SpringLayout.WEST, node2Select, 20, SpringLayout.EAST, node2SelectType);
			springLayout.putConstraint(SpringLayout.NORTH, node2Select, 20, SpringLayout.SOUTH, node1Select);
			add(node2Select);
		
	}
	
	public void initStrings(){
		
		//Papers
		nodeStrings[0] = new DefaultComboBoxModel<String>(
				new String[]{" - Pick a paper -","Paper 1","Paper 2","Paper 3","Etc"});
		//Autors
		nodeStrings[1] = new DefaultComboBoxModel<String>(
				new String[]{" - Pick an author -","Autor 1","Autor 2","Autor 3","Autor 4","Autor 5","Autor 6","Autor 7","Autor 8","This shouldn't be displayed", "Oh shit -------------------------------- It's broken"});
		//Conferencies
		nodeStrings[2] = new DefaultComboBoxModel<String>(
				new String[]{" - Pick a conference -","Conferencia 1"});
		//Terms
		nodeStrings[3] = new DefaultComboBoxModel<String>(
				new String[]{" - Pick a term -","Term 1", "Term 2"});
		
	}
	
	public void assignListeners(){
		
		node1SelectType.addActionListener(this);
		node2SelectType.addActionListener(this);
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
		
		/**
		 * For the node 1 type selection
		 * 
		 * Has control over the first node and node 2 type selection JComboBoxes
		 */
		if (e.getSource().equals(node1SelectType)){
			//If a type has been selected
			if (node1SelectType.getSelectedIndex() > 0){
				
				//Set the strings corresponding to the selected type
				node1Select.setModel(nodeStrings[node1SelectType.getSelectedIndex()-1]);
				//Enable the JComboBox for interaction
				node1Select.setEnabled(true);
				//Set default option to Pick an item
				node1Select.setSelectedIndex(0);
				
				//If there isn't a selected type for node 2 type selector
				//if (node2SelectType.getSelectedIndex() < 1) {
					//Enable the JComboBox for interaction
					node2SelectType.setEnabled(true);
					//Set default option to "Pick a type"
					node2SelectType.setSelectedIndex(0);
				//}
			}
			//If the selected option is "Pick a type"
			else {
				//Disable the JComboBox for interaction
				node1Select.setEnabled(false);
				//Set hidden option
				node1Select.setSelectedIndex(-1);
				
				//Disable the JComboBox for interaction
				node2SelectType.setEnabled(false);
				//Set default option to "Pick a type"
				node2SelectType.setSelectedIndex(0);
			}
		}
		
		/**
		 * For the node 2 type selection
		 * 
		 * Has control over the last node JComboBox
		 */
		else if (e.getSource().equals(node2SelectType)){
			//If a type has been selected
			if (node2SelectType.getSelectedIndex() > 0){
				//Set the strings corresponding to the selected type
				node2Select.setModel(nodeStrings[node2SelectType.getSelectedIndex()-1]);
				//Enable the JComboBox for interaction
				node2Select.setEnabled(true);
				//Set default option to Pick an item
				node2Select.setSelectedIndex(0);
			}
			//If the selected option is "Pick a type"
			else {
				//Disable the JComboBox for interaction
				node2Select.setEnabled(false);
				//Set hidden option
				node2Select.setSelectedIndex(-1);
			}
		}
		
	}
}