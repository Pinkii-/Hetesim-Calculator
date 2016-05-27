package Presentacion;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dominio.CtrlGraph;

import javax.swing.SpringLayout;
import javax.swing.JList;

//public class PanelEditNode extends AbstractPanel{
public class PanelEditNode extends AbstractPanel{


	JTable relationsTable = new JTable();

	JLabel nameLabel = new JLabel("Name: ");
	JTextField nameTextField = new JTextField();

	JLabel typeLabel = new JLabel("Type: ");
	MyComboBox typeComboBox = new MyComboBox();

	JLabel labelLabel = new JLabel("Label: ");
	MyComboBox labelComboBox = new MyComboBox();

	JLabel relationsLabel = new JLabel("Relations: ");

	JButton addRelationButton = new JButton("Add new relation");
	JButton saveButton = new JButton("Save");
	JButton exitButton = new JButton("Exit");

	private static final long serialVersionUID = 1L;
	CtrlGraph ctrlGraph;
	ArrayList<String> nodeInfo;
	private final JPanel nodeInfoPanel = new JPanel();
	private final JPanel nodeRelationsPanel = new JPanel();
	private final JLabel mainInfoLabel = new JLabel("Node Info:");
	//private final JTextPane textPane = new JTextPane();

	//@Override
	public int closeIt() {
		return 0;
	}


	//@Override
	public void setEnabledEverything(Boolean b) {

	}

	//public PanelEditNode(VistaAbstracta vp) {
	//	super(vp);
	public PanelEditNode(VistaAbstracta vp){
		super(vp);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(nodeInfoPanel);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.SOUTH, mainInfoLabel);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, mainInfoLabel);
		springLayout.putConstraint(SpringLayout.NORTH, mainInfoLabel, 10, SpringLayout.NORTH, nodeInfoPanel);
		springLayout.putConstraint(SpringLayout.WEST, mainInfoLabel, 10, SpringLayout.WEST, nodeInfoPanel);
		springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 0, SpringLayout.WEST, nameTextField);
		springLayout.putConstraint(SpringLayout.SOUTH, typeComboBox, 0, SpringLayout.SOUTH, typeLabel);
		springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 10, SpringLayout.SOUTH, labelLabel);
		springLayout.putConstraint(SpringLayout.WEST, typeLabel, 0, SpringLayout.WEST, labelLabel);
		springLayout.putConstraint(SpringLayout.WEST, labelComboBox, 0, SpringLayout.WEST, nameTextField);
		springLayout.putConstraint(SpringLayout.SOUTH, labelComboBox, 0, SpringLayout.SOUTH, labelLabel);
		springLayout.putConstraint(SpringLayout.NORTH, labelLabel, 10, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, labelLabel, 0, SpringLayout.WEST, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, nameTextField, 10, SpringLayout.EAST, nameLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, nameTextField, 0, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, saveButton, 0, SpringLayout.SOUTH, exitButton);
		springLayout.putConstraint(SpringLayout.EAST, saveButton, -10, SpringLayout.WEST, exitButton);
		springLayout.putConstraint(SpringLayout.SOUTH, exitButton, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, exitButton, -10, SpringLayout.EAST, this);
		nodeInfoPanel.setLayout(springLayout);
		nodeInfoPanel.add(mainInfoLabel);
		nodeInfoPanel.add(nameLabel);
		nameTextField.setColumns(19);
		nodeInfoPanel.add(nameTextField);
		nodeInfoPanel.add(typeLabel);
		nodeInfoPanel.add(typeComboBox);
		typeComboBox.setMaximumSize(typeComboBox.getPreferredSize());
		nodeInfoPanel.add(labelLabel);
		nodeInfoPanel.add(labelComboBox);
		labelComboBox.setMaximumSize(labelComboBox.getPreferredSize());

		this.add(nodeRelationsPanel);
		SpringLayout sl_nodeRelationsPanel = new SpringLayout();
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, addRelationButton, 20, SpringLayout.WEST, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, addRelationButton, -10, SpringLayout.NORTH, exitButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, exitButton, -10, SpringLayout.SOUTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, saveButton, -10, SpringLayout.WEST, exitButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, exitButton, -10, SpringLayout.EAST, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, saveButton, -10, SpringLayout.SOUTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.NORTH, relationsLabel, 10, SpringLayout.NORTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, relationsLabel, 10, SpringLayout.WEST, nodeRelationsPanel);
		nodeRelationsPanel.setLayout(sl_nodeRelationsPanel);
		nodeRelationsPanel.add(relationsLabel);
		nodeRelationsPanel.add(addRelationButton);
		nodeRelationsPanel.add(saveButton);
		nodeRelationsPanel.add(exitButton);
		
		@SuppressWarnings("rawtypes")
		JList list = new JList();
		sl_nodeRelationsPanel.putConstraint(SpringLayout.NORTH, list, 20, SpringLayout.SOUTH, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, list, 0, SpringLayout.WEST, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, list, -10, SpringLayout.NORTH, addRelationButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, list, -10, SpringLayout.EAST, nodeRelationsPanel);
		nodeRelationsPanel.add(list);
		setNodeToEdit(0, "Autor");
	}

	public void setNodeToEdit(Integer index, String nodeType){
		nodeInfo = ctrlGraph.getNodeFormatted(index, nodeType);
		updatePanel();
	}

	private void updatePanel(){
		drawRelations();		
	}
	private void drawRelations(){
		ArrayList<ArrayList<String>> data = ctrlGraph.getNodeRelationsFormatted(Integer.parseInt(nodeInfo.get(0)),
				nodeInfo.get(2));
		int dataRows = data.size();
		int dataCols = 3;
		Object[][] relationsTableData = new Object[dataRows][dataCols - 1];
		int i,j;
		i = 0;
		for(ArrayList<String> arr: data){
			j = -1;
			System.out.println(i);
			for(String s: arr){
				System.out.println(j);
				if(j >= 0)
					relationsTableData[i][j] = s;
				++j;
			}
			++i;
		}
		String[] columnNames = {"Name", "Type"};
		relationsTable = new JTable(relationsTableData, columnNames);
		//relationsPanel.remove(relationsTable);
		//relationsPanel.remove(addRelationButton);
		//relationsPanel.add(relationsTable);
		//relationsPanel.add(addRelationButton);
		//relationsTable.repaint();
		//relationsTable.revalidate();
		//this.repaint();
		//this.revalidate();
	}
}
