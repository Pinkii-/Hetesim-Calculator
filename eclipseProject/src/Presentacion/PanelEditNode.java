package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Dominio.CtrlDominio;
import Dominio.CtrlGraph;
import Dominio.Node;

import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javax.swing.JList;

//public class PanelEditNode extends AbstractPanel{
public class PanelEditNode extends AbstractPanel implements INodeNeeder{


	DefaultListModel<String> relationsListModel = new DefaultListModel<String>();
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
	JButton resetValuesButton = new JButton("Revert changes");

	JPanel nodeInfoPanel = new JPanel();
	JPanel nodeRelationsPanel = new JPanel();
	JLabel mainInfoLabel = new JLabel("Node Info:");

	ArrayList<String> nodeInfo;
	ArrayList<String> newNodeInfo;
	ArrayList<ArrayList<String>> nodeRelationsData = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> relationsToErase = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> relationsToAdd = new ArrayList<ArrayList<String>>();
	private static final long serialVersionUID = 1L;
	CtrlGraph ctrlGraph;

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

		springLayout.putConstraint(SpringLayout.WEST, resetValuesButton, 10, SpringLayout.WEST, nodeInfoPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, resetValuesButton, -10, SpringLayout.SOUTH, nodeInfoPanel);

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
		nodeInfoPanel.add(resetValuesButton);

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

		relationsList = new JList<String>(relationsListModel);

		relationsList.addListSelectionListener(
				new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if(!relationsList.isSelectionEmpty()){
							eraseRelationButton.setEnabled(true);
						}

					}

				}
				);
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(relationsList);

		sl_nodeRelationsPanel.putConstraint(SpringLayout.NORTH, sc, 20, SpringLayout.SOUTH, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.WEST, sc, 0, SpringLayout.WEST, relationsLabel);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.SOUTH, sc, -10, SpringLayout.NORTH, addRelationButton);
		sl_nodeRelationsPanel.putConstraint(SpringLayout.EAST, sc, -10, SpringLayout.EAST, nodeRelationsPanel);
		nodeRelationsPanel.add(sc);
		System.out.println(CtrlDominio.getTypes());
		//TODO erase!
		//try {
		//	cd.importGraph("C:/Users/Usuari/Desktop/PROP/GraphForTesting");
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		//setNodeToEdit(0, "Paper");
	}

	private void initTypeInfo(){
		ArrayList<String> typeData = new ArrayList<String>();
		typeData.add("None");
		typeData.addAll(CtrlDominio.getTypes());
		String[] typeDataArray = typeData.toArray(new String[typeData.size()]); 
		typeComboBox = new JComboBox<String>(typeDataArray);
		typeComboBox.setEnabled(false);
	}

	private void initLabelInfo(){
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
							//System.out.println(labelComboBox.getSelectedIndex());
							//System.out.println(CtrlDominio.getIndexOfNodeLabel(nodeInfo.get(3)) + 1);
							newNodeInfo.set(3, String.valueOf(labelComboBox.getSelectedIndex()));
							unsavedChanges = true;
							saveButton.setEnabled(true);
						}

					}
				});
	}

	private void initButtons(){

		saveButton.addMouseListener(
				new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent arg0){
						unsavedChanges = false;
						saveAll();
						saveButton.setEnabled(false);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
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
		PanelEditNode aux = this;
		addRelationButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						aux.addVista(PanelSelectNode.class, false);
						PanelSelectNode aux2 = (PanelSelectNode) childs.get(0).getContentPane().getComponent(0);
						unsavedChanges = true;
						saveButton.setEnabled(true);
						//If the node we are treating is a paper, we'll ask differently for a node, y'know?
						if(nodeInfo.get(2).equals("Paper")){
							aux2.setNeeder(aux, true);
						}
						else
							aux2.setNeeder(aux, false);
					}
				});
		eraseRelationButton.setEnabled(false);
		eraseRelationButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						unsavedChanges = true;
						saveButton.setEnabled(true);
						ArrayList<String> erasedRelation = nodeRelationsData.get(relationsList.getSelectedIndex());
						softEraseRelation(relationsList.getSelectedIndex(), erasedRelation);
					}
				});
		resetValuesButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(unsavedChanges){
							setNodeToEdit(Integer.valueOf(nodeInfo.get(0)), nodeInfo.get(2));
							unsavedChanges = false;
							saveButton.setEnabled(false);
						}
					}
				});
	}


	private void initDefaultValues(){

		saveButton.setEnabled(false);		
		initTypeInfo();		
		initLabelInfo();			
		initButtons();

		nameTextField.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String newText = nameTextField.getText();
						if(!newText.equals(nodeInfo.get(1))){
							newNodeInfo.set(1, newText);
							unsavedChanges = true;
							saveButton.setEnabled(true);
						}

					}
				}
				);


	}

	@SuppressWarnings("unchecked")
	public void setNodeToEdit(Integer index, String nodeType){
		relationsToAdd.clear();
		relationsToErase.clear();
		nodeRelationsData.clear();
		
		nodeInfo = ctrlGraph.getNodeFormatted(index, nodeType);
		
		newNodeInfo = (ArrayList<String>) nodeInfo.clone();
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
	
	private void softEraseRelation(int index, ArrayList<String> node){
		nodeRelationsData.remove(index);
		relationsListModel.remove(index);
		relationsToErase.add(node);
	}
	
	private void softAddRelation(ArrayList<String> node){
		String columnData = new String();
		columnData = node.get(1) + " - " + node.get(2);
		nodeRelationsData.add(node);
		int modelSize = relationsListModel.getSize();
		int relationIndex = (modelSize - 1) < 0 ? 0 : modelSize;
		relationsListModel.add(relationIndex, columnData);
		relationsToAdd.add(node);
	}
	
	private void saveAll(){
		System.out.println(newNodeInfo.get(1) + " " + nodeInfo.get(1));
		if(!newNodeInfo.get(1).equals(nodeInfo.get(1))){
			ctrlGraph.modifyNode(Integer.valueOf(nodeInfo.get(0)), nodeInfo.get(2), newNodeInfo.get(1));
		}
		if(nodeInfo.get(2).equals("Paper")){
			for(ArrayList<String> relation: relationsToErase){
				ctrlGraph.eraseNodeRelation(Integer.valueOf(nodeInfo.get(0)), Integer.valueOf(relation.get(0)), relation.get(2));
			}
			for(ArrayList<String> relation: relationsToAdd){
				ctrlGraph.addNodeRelation(Integer.valueOf(nodeInfo.get(0)), Integer.valueOf(relation.get(0)), relation.get(2));
			}
		}
		else{
			for(ArrayList<String> relation: relationsToErase){
				ctrlGraph.eraseNodeRelation(Integer.valueOf(relation.get(0)), Integer.valueOf(nodeInfo.get(0)), nodeInfo.get(2));
			}
			for(ArrayList<String> relation: relationsToAdd){
				ctrlGraph.addNodeRelation(Integer.valueOf(relation.get(0)), Integer.valueOf(nodeInfo.get(0)), nodeInfo.get(2));
			}
		}
		
	}

	private void drawRelations(){
		nodeRelationsData = ctrlGraph.getNodeRelationsFormatted(Integer.parseInt(nodeInfo.get(0)),
				nodeInfo.get(2));

		relationsListModel.clear();
		int i = 0;
		for(ArrayList<String> arr: nodeRelationsData){
			System.out.println(i);
			String columnData = new String();
			columnData = arr.get(1) + " - " + arr.get(2);
			relationsListModel.addElement(columnData);
			++i;
		}
	}


	@Override
	public void setNode(ArrayList<String> nodeInfo) {
		softAddRelation(nodeInfo);	
	}
}
