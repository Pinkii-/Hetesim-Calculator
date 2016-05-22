package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * Panel para el caso de uso de nueva Búsqueda
 * 
 * @author Xavier Peñalosa
 *
 */
public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> pathSelect;
	private MyComboBox node1Select, node2Select;
	private JLabel pathLabel, node1Label, node2Label;
	
	private JList resultList;
	
	private JButton calcHete;
	
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
		
		//Path
		pathSelect = new JComboBox<String>(arrayListToArray(cd.getCtrlPaths().getPathNames()));
		pathSelect.setPreferredSize(new Dimension(100,20));
		pathSelect.setSelectedIndex(-1);
		add(pathSelect);
		
		//Node 1
		node1Label = new JLabel();
		node1Label.setPreferredSize(new Dimension(168,20));
		node1Label.setText("Node type:");
		add(node1Label);
			node1Select = new MyComboBox();
			node1Select.loadNodesToLists(this.cd);
			add(node1Select);
		
		//Node 2
		node2Label = new JLabel();
		node2Label.setPreferredSize(new Dimension(168,20));
		node2Label.setText("Node type:");
		add(node2Label);
			node2Select = new MyComboBox();
			node2Select.loadNodesToLists(this.cd);
			add(node2Select);
		
		//Result list
		pathLabel = new JLabel();
		pathLabel.setPreferredSize(new Dimension(168,20));
		pathLabel.setText("Path");
		add(pathLabel);
			resultList = new MyList();
			resultList.setSelectionMode(0);
			add(resultList);
		
		calcHete = new JButton("Calculate Hetesim");
		calcHete.setPreferredSize(new Dimension(200,50));
		calcHete.setEnabled(false);
		add(calcHete);
		
		putConstraints(springLayout);
	}
	
	private void putConstraints(SpringLayout sl){
		//Path selection
		sl.putConstraint(SpringLayout.NORTH, pathSelect, 20, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.WEST, pathSelect, 20, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, pathLabel, 20, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.WEST, pathLabel, 15, SpringLayout.EAST, pathSelect);
		
		
		//Node 1
		sl.putConstraint(SpringLayout.NORTH, node1Select, 15, SpringLayout.SOUTH, pathSelect);
		sl.putConstraint(SpringLayout.WEST, node1Select, 20, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, node1Label, 15, SpringLayout.SOUTH, pathSelect);
		sl.putConstraint(SpringLayout.WEST, node1Label, 15, SpringLayout.EAST, node1Select);
		
		//Node 2
		sl.putConstraint(SpringLayout.NORTH, node2Select, 15, SpringLayout.SOUTH, node1Select);
		sl.putConstraint(SpringLayout.WEST, node2Select, 20, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.NORTH, node2Label, 15, SpringLayout.SOUTH, node1Label);
		sl.putConstraint(SpringLayout.WEST, node2Label, 15, SpringLayout.EAST, node2Select);

		//List
		sl.putConstraint(SpringLayout.NORTH, resultList, 15, SpringLayout.SOUTH, node2Label);
		sl.putConstraint(SpringLayout.WEST, resultList, 20, SpringLayout.WEST, this);
		
		//Calc hetesim
		sl.putConstraint(SpringLayout.NORTH, calcHete, 125, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.WEST, calcHete, 15, SpringLayout.EAST, resultList);
		
	}
	
	private void assignListeners(){
		
		pathSelect.addActionListener(this);
		calcHete.addActionListener(this);
		
	}

	private String[] arrayListToArray(ArrayList<String> alist){
		String[] temp = new String[alist.size()];
		return alist.toArray(temp);
	}
	
	
	@Override
	public int closeIt() {
		
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todos los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
		
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource().equals(calcHete)){
			System.out.println("hey!");
		}
		else if (e.getSource().equals(pathSelect)){
			String text = pathSelect.getItemAt(pathSelect.getSelectedIndex()).toString();
			boolean n1 = false, n2 = false;
			switch (text.charAt(0)){
				case 'P':
					//node1SelectType.setSelectedItem(node1SelectType.getItemAt(0));
					node1Label.setText("Node type: Paper");
					node1Select.setList(0);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'A':
					//node1SelectType.setSelectedItem(node1SelectType.getItemAt(1));
					node1Label.setText("Node type: Author");
					node1Select.setList(1);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'C':
					//node1SelectType.setSelectedItem(node1SelectType.getItemAt(2));
					node1Label.setText("Node type: Conference");
					node1Select.setList(2);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'T':
					//node1SelectType.setSelectedItem(node1SelectType.getItemAt(3));
					node1Label.setText("Node type: Term");
					node1Select.setList(3);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				default:
					//node1SelectType.setSelectedItem(node1SelectType.getItemAt(-1));
					node1Label.setText("INVALID PATH");
					node1Select.setEnabled(false);
					break;
			}
			switch (text.charAt(text.length()-1)){
				case 'P':
					//node2SelectType.setSelectedItem(node2SelectType.getItemAt(0));
					node2Label.setText("Node type: Paper");
					node2Select.setList(0);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'A':
					//node2SelectType.setSelectedItem(node2SelectType.getItemAt(1));
					node2Label.setText("Node type: Author");
					node2Select.setList(1);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'C':
					//node2SelectType.setSelectedItem(node2SelectType.getItemAt(2));
					node2Label.setText("Node type: Conference");
					node2Select.setList(2);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'T':
					//node2SelectType.setSelectedItem(node2SelectType.getItemAt(3));
					node2Label.setText("Node type: Term");
					node2Select.setList(3);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				default:
					//node2SelectType.setSelectedItem(node2SelectType.getItemAt(-1));
					node2Label.setText("INVALID PATH");
					node2Select.setEnabled(false);
					break;
			}
			if (node1Select.getEditor().getItem().toString().endsWith("!") ||
				node2Select.getEditor().getItem().toString().endsWith("!") ||
				!(n1 && n2)){
				
					calcHete.setEnabled(false);
				
			}
		}
		
	}
}