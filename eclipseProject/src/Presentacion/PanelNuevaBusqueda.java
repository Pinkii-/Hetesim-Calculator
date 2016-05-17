package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * 
 * @author Xavier Peñalosa
 *
 */
public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> node1SelectType, node2SelectType;
	private MyComboBox node1Select, node2Select;
	private static final long serialVersionUID = 1L;
	private ComboBoxModel<String>[] nodeStrings = new ComboBoxModel[4];
	

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		initComponents();
		assignListeners();
	}
	
	/**
	 * Initialize the components for the panel
	 * 
	 * <strong>node1SelectType</strong>: Dropdown list to pick the NodeType for the first node <p>
	 * <strong>node2SelectType</strong>: Dropdown list to pick the NodeType for the second node <p>
	 * <strong>node1Select</strong>: Dropdown list to pick the first node. Only the nodes that belong to the selected NodeType in node1SelectType will be displayed <p>
	 * <strong>node2Select</strong>: Dropdown list to pick the last node. Only the nodes that belong to the selected NodeType in node2SelectType will be displayed <p>
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
		
			node1Select = new MyComboBox();
			node1Select.setParent(node1SelectType);
			node1Select.tempUseCtrlDominio(this.cd);
			springLayout.putConstraint(SpringLayout.WEST, node1Select, 20, SpringLayout.EAST, node1SelectType);
			springLayout.putConstraint(SpringLayout.NORTH, node1Select, 50, SpringLayout.NORTH, this);
			add(node1Select);
		
		node2SelectType = new JComboBox<String>(new String[]{" - Pick a type -", "Paper ", "Autor ", "Conferencia ", "Term "});
		node2SelectType.setEditable(true);
		node2SelectType.setEnabled(false);
		node2SelectType.setSelectedIndex(0);
		node2SelectType.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.WEST, node2SelectType, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, node2SelectType, 20, SpringLayout.SOUTH, node1SelectType);
		add(node2SelectType);
		
			node2Select = new MyComboBox();
			node2Select.setParent(node2SelectType);
			node2Select.tempUseCtrlDominio(this.cd);
			springLayout.putConstraint(SpringLayout.WEST, node2Select, 20, SpringLayout.EAST, node2SelectType);
			springLayout.putConstraint(SpringLayout.NORTH, node2Select, 20, SpringLayout.SOUTH, node1Select);
			add(node2Select);
		
	}
	
	
	
	public void assignListeners(){
		
		node1SelectType.addActionListener(this);
		node1SelectType.addActionListener(node1Select);
		node2SelectType.addActionListener(node2Select);
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