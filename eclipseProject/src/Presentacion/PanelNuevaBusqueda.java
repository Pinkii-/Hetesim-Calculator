package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 * Panel para el caso de uso de nueva Búsqueda
 * 
 * @author Xavier Peñalosa
 *
 */
public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> node1SelectType, node2SelectType;
	private MyComboBox node1Select, node2Select;
	private JList resultList;
	private static final long serialVersionUID = 1L;
	

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		initComponents();
		assignListeners();
	}
	
	/**
	 * Initialize the components for the panel
	 * <p>
	 * <b>node1SelectType</b>: Dropdown list to pick the NodeType for the first node <p>
	 * <b>node2SelectType</b>: Dropdown list to pick the NodeType for the second node <p>
	 * <b>node1Select</b>: Dropdown list to pick the first node. Only the nodes that belong to the selected NodeType in node1SelectType will be displayed <p>
	 * <b>node2Select</b>: Dropdown list to pick the last node. Only the nodes that belong to the selected NodeType in node2SelectType will be displayed
	 */
	public void initComponents() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		node1SelectType = new JComboBox<String>(new String[]{" - Pick a type -","Paper", "Autor", "Conferencia", "Term"});
		node1SelectType.setPreferredSize(new Dimension(148,24));
		add(node1SelectType);
		
			node1Select = new MyComboBox();
			node1Select.linkToParent(node1SelectType);
			node1Select.loadNodesToLists(this.cd);
			add(node1Select);
		
		node2SelectType = new JComboBox<String>(new String[]{" - Pick a type -", "Paper ", "Autor ", "Conferencia ", "Term "});
		node2SelectType.setPreferredSize(new Dimension(148,24));
		node2SelectType.setEnabled(false);
		add(node2SelectType);
		
			node2Select = new MyComboBox();
			node2Select.linkToParent(node2SelectType);
			node2Select.loadNodesToLists(this.cd);
			node2Select.setAutocomplete(true);
			add(node2Select);
			
		resultList = new MyList();
		resultList.setSelectionMode(0);
		add(resultList);
		
		putConstraints(springLayout);
	}
	
	private void putConstraints(SpringLayout sl){
		sl.putConstraint(SpringLayout.WEST, node1SelectType, 50, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, node1SelectType, 50, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.WEST, node1Select, 20, SpringLayout.EAST, node1SelectType);
		sl.putConstraint(SpringLayout.NORTH, node1Select, 50, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.WEST, node2SelectType, 50, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, node2SelectType, 20, SpringLayout.SOUTH, node1SelectType);
		sl.putConstraint(SpringLayout.WEST, node2Select, 20, SpringLayout.EAST, node2SelectType);
		sl.putConstraint(SpringLayout.NORTH, node2Select, 20, SpringLayout.SOUTH, node1Select);
		
		sl.putConstraint(SpringLayout.NORTH, resultList, 20, SpringLayout.SOUTH, node2SelectType);
		sl.putConstraint(SpringLayout.WEST, resultList, 50, SpringLayout.WEST, this);
	}
	
	public void assignListeners(){
		
		node1SelectType.addActionListener(this);
		
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
				//If there isn't a selected type for node 2 type selector
				if (node2SelectType.getSelectedIndex() < 1) {
					//Enable the JComboBox for interaction
					node2SelectType.setEnabled(true);
					//Set default option to "Pick a type"
					node2SelectType.setSelectedIndex(0);
				}
			}
			//If the selected option is "Pick a type"
			else {
				//Disable the JComboBox for interaction
				node2SelectType.setEnabled(false);
				//Set default option to "Pick a type"
				node2SelectType.setSelectedIndex(0);
			}
		}
		
	}
}