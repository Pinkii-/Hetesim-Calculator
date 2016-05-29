package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Dominio.CtrlDominio;
import Dominio.CtrlGraph;
import Dominio.Node;

import javax.swing.SpringLayout;
import javax.swing.JList;

//public class PanelEditNode extends AbstractPanel{
public class PanelEditNode extends AbstractPanel{


	DefaultListModel<String> model = new DefaultListModel<String>();
	JList<String> relationsList;

	JLabel nameLabel = new JLabel("Name: ");
	JTextField nameTextField = new JTextField();

	JLabel typeLabel = new JLabel("Type: ");
	JComboBox<String> typeComboBox;

	JLabel labelLabel = new JLabel("Label: ");
	JComboBox<String> labelComboBox;

	JLabel relationsLabel = new JLabel("Relations: ");

	JButton addRelationButton = new JButton("Add new relation");
	JButton saveButton = new JButton("Save");
	JButton exitButton = new JButton("Exit");
	JButton eraseRelationButton = new JButton("Erase Relation");

	private static final long serialVersionUID = 1L;
	CtrlGraph ctrlGraph;
	ArrayList<String> nodeInfo;
	private final JPanel nodeInfoPanel = new JPanel();
	private final JPanel nodeRelationsPanel = new JPanel();
	private final JLabel mainInfoLabel = new JLabel("Node Info:");
	
	private boolean unsavedChanges = false;
	
	//private final JTextPane textPane = new JTextPane();

	//@Override
	public int closeIt() {
		if(unsavedChanges){
			String[] buttons = {"Salir", "Cancelar"};
			int result = VistaDialog.setDialog("Titulo", "�Estas seguro que quieres salir?\n"
					+ "Todos los cambion no guardados seran perdidos",
					buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
			return result;
		}
		return 0;
	}


	//@Override
	public void setEnabledEverything(Boolean b) {
		
	}

	//public PanelEditNode(VistaAbstracta vp) {
	//	super(vp);
	public PanelEditNode(VistaAbstracta vp){
		super(vp);
		ctrlGraph = cd.getCtrlGraph();
		initDefaultValues();
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
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, eraseRelationButton, 20, SpringLayout.EAST, addRelationButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, eraseRelationButton, 0, SpringLayout.SOUTH, addRelationButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, exitButton, -10, SpringLayout.SOUTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, saveButton, -10, SpringLayout.WEST, exitButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, exitButton, -10, SpringLayout.EAST, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, saveButton, -10, SpringLayout.SOUTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.NORTH, relationsLabel, 10, SpringLayout.NORTH, nodeRelationsPanel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, relationsLabel, 10, SpringLayout.WEST, nodeRelationsPanel);
		nodeRelationsPanel.setLayout(sl_nodeRelationsPanel);
		nodeRelationsPanel.add(relationsLabel);
		nodeRelationsPanel.add(addRelationButton);
		nodeRelationsPanel.add(eraseRelationButton);
		nodeRelationsPanel.add(saveButton);
		nodeRelationsPanel.add(exitButton);
		
		relationsList = new JList<String>(model);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.NORTH, relationsList, 20, SpringLayout.SOUTH, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, relationsList, 0, SpringLayout.WEST, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, relationsList, -10, SpringLayout.NORTH, addRelationButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, relationsList, -10, SpringLayout.EAST, nodeRelationsPanel);
		nodeRelationsPanel.add(relationsList);
		//TODO erase!
		try {
			cd.importGraph("C:/Users/Usuari/Desktop/PROP/GraphForTesting");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNodeToEdit(0, "Autor");
	}
	
	private void initDefaultValues(){
		saveButton.setEnabled(false);
		ArrayList<String> typeData = new ArrayList<String>();
		typeData.add("None");
		typeData.addAll(CtrlDominio.getTypes());
		String[] typeDataArray = typeData.toArray(new String[typeData.size()]); 
		typeComboBox = new JComboBox<String>(typeDataArray);
		typeComboBox.setEnabled(false);
		
		labelComboBox = new JComboBox<String>();
		ArrayList<String> labelData = new ArrayList<String>();
		labelData.add("None");
		labelData.addAll(CtrlDominio.getLabels());
		String[] labelDataArray = labelData.toArray(new String[labelData.size()]);
		labelComboBox = new JComboBox<String>(labelDataArray);
		labelComboBox.addActionListener(
				new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	if(!(labelComboBox.getSelectedIndex() == (CtrlDominio.getIndexOfNodeLabel(nodeInfo.get(3)) + 1))){
                    		System.out.println(labelComboBox.getSelectedIndex());
                    		System.out.println(CtrlDominio.getIndexOfNodeLabel(nodeInfo.get(3)) + 1);
                        	unsavedChanges = true;
                    		saveButton.setEnabled(true);
                    	}

                    }
				}
				);
		nameTextField.addActionListener(
				new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	if(!nameTextField.getText().equals(nodeInfo.get(1))){
                        	unsavedChanges = true;
                    		saveButton.setEnabled(true);
                    	}

                    }
				}
				);
		saveButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						unsavedChanges = false;
						saveButton.setEnabled(false);
						nodeInfo.set(3, CtrlDominio.getNodeLabelOfIndex(labelComboBox.getSelectedIndex() + 1) );
						nodeInfo.set(1, nameTextField.getText());
					}
				}
				);
		exitButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						close();
					}
				}
				);
	}

	public void setNodeToEdit(Integer index, String nodeType){
		nodeInfo = ctrlGraph.getNodeFormatted(index, nodeType);
		updatePanel();
	}

	private void updatePanel(){
		drawRelations();
		updateNodeInfo();
	}
	
	private void updateNodeInfo(){
		nameTextField.setText(nodeInfo.get(1));
		int n = CtrlDominio.getNodeTypeIndex(nodeInfo.get(2)) + 1;
		typeComboBox.setSelectedIndex(n);
		int m = CtrlDominio.getIndexOfNodeLabel(nodeInfo.get(3)) + 1;
		labelComboBox.setSelectedIndex(m);
	}
	
	private void drawRelations(){
		ArrayList<ArrayList<String>> data = ctrlGraph.getNodeRelationsFormatted(Integer.parseInt(nodeInfo.get(0)),
				nodeInfo.get(2));
		int i = 0;
		for(ArrayList<String> arr: data){
			System.out.println(i);
			String columnData = new String();
			for(String s: arr){
				columnData += s + " ";
			}
			model.addElement(columnData);
			++i;
		}
	}
}
