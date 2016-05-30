package Presentacion;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Dominio.CtrlDominio;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelSelectNode extends AbstractPanel{

	DefaultListModel<String> relationsListModel = new DefaultListModel<String>();
	JComboBox<String> typeComboBox = new JComboBox<String>();
	SpringLayout springLayout = new SpringLayout();

	JLabel lblSelectANode = new JLabel("Select a Node: ");
	JLabel lblNodeType = new JLabel("Node Type: ");
	JLabel lblNodePaper = new JLabel("Paper");
	
	ArrayList<ArrayList<String>> nodesInfo;
	INodeNeeder nodeNeeder; 
	JTextField searchTextField;
	ArrayList<Integer> indexOfNodes;

	public PanelSelectNode(VistaAbstracta vp) {
		super(vp);
		this.setLayout(springLayout);

		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, this);
		this.add(btnCancel);

		JButton btnGetNode = new JButton("Get node");
		springLayout.putConstraint(SpringLayout.NORTH, btnGetNode, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnGetNode, -10, SpringLayout.WEST, btnCancel);
		

		this.add(btnGetNode);

		springLayout.putConstraint(SpringLayout.NORTH, lblSelectANode, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectANode, 10, SpringLayout.WEST, this);
		this.add(lblSelectANode);

		springLayout.putConstraint(SpringLayout.NORTH, lblNodeType, 10, SpringLayout.SOUTH, lblSelectANode);
		springLayout.putConstraint(SpringLayout.WEST, lblNodeType, 10, SpringLayout.WEST, lblSelectANode);
		this.add(lblNodeType);

		JLabel lblNodeList = new JLabel("All nodes of the selected type: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNodeList, 10, SpringLayout.SOUTH, lblNodeType);
		springLayout.putConstraint(SpringLayout.WEST, lblNodeList, 0, SpringLayout.WEST, lblNodeType);
		this.add(lblNodeList);
		
		JList<String> list = new JList<String>(relationsListModel);
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(list);
		springLayout.putConstraint(SpringLayout.NORTH, sc, 10, SpringLayout.SOUTH, lblNodeList);
		springLayout.putConstraint(SpringLayout.WEST, sc, 0, SpringLayout.WEST, lblNodeList);
		springLayout.putConstraint(SpringLayout.SOUTH, sc, -10, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, sc, -10, SpringLayout.EAST, this);
		this.add(sc);

		//Adding usability to the list
		searchTextField = new JTextField();
		
		//springLayout.putConstraint(SpringLayout.NORTH, searchTextField, 10, SpringLayout.SOUTH, lblNodeList);
		
		btnGetNode.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ArrayList<String> node = nodesInfo.get(list.getSelectedIndex());
						nodeNeeder.setNode(node);
						close();
					}
				}
				);		
	}
	
	private void setNodesList(int comboBoxIndex){
		int realIndex = comboBoxIndex >= CtrlDominio.getNodeTypeIndex("Paper") ?
				comboBoxIndex + 1 :  comboBoxIndex;
		String nodeType = CtrlDominio.getNodeTypeOfIndex(realIndex);
		drawList(nodeType);
	}
	
	private void drawList(String nodeType){
		relationsListModel.clear();
		nodesInfo = cd.getCtrlGraph().getformattedNodesOfType(nodeType);
		for (int i = 0; i < nodesInfo.size(); i++) {
			ArrayList<String> nodesInfoRow = nodesInfo.get(i);
			String listString = new String();
			listString = nodesInfoRow.get(1);
			relationsListModel.addElement(listString);
		}
	}
	
	public void setNeeder(INodeNeeder daddy, boolean isAPaper){
		if(isAPaper){
			initTypeComboBox();			
			setNodesList(typeComboBox.getSelectedIndex());
			searchTextField.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					relationsListModel.clear();
					ArrayList<ArrayList<String>> nodes = cd.getCtrlGraph().getformattedNodesOfType((String) typeComboBox.getSelectedItem());
					indexOfNodes = new ArrayList<Integer>();
					System.gc();
					for (int i = 0; i < nodes.size(); ++i) {
						if (nodes.get(i).get(1).toLowerCase().contains(searchTextField.getText().toLowerCase())) {
							relationsListModel.addElement(nodes.get(i).get(1));
							indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
						}
					}
				}});
		}
		else{
			initTypeLabel();
			drawList("Paper");
			searchTextField.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					relationsListModel.clear();
					ArrayList<ArrayList<String>> nodes = cd.getCtrlGraph().getformattedNodesOfType("Paper");
					indexOfNodes = new ArrayList<Integer>();
					System.gc();
					for (int i = 0; i < nodes.size(); ++i) {
						if (nodes.get(i).get(1).toLowerCase().contains(searchTextField.getText().toLowerCase())) {
							relationsListModel.addElement(nodes.get(i).get(1));
							indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
						}
					}
				}});
		}
		nodeNeeder = daddy;
	}
	
	private void initTypeLabel(){
		springLayout.putConstraint(SpringLayout.NORTH, lblNodePaper, 10, SpringLayout.SOUTH, lblSelectANode);
		springLayout.putConstraint(SpringLayout.WEST, lblNodePaper, 10, SpringLayout.EAST, lblNodeType);
		this.add(lblNodePaper);
	}
	
	private void initTypeComboBox(){
		//Initializing the type combo box
		ArrayList<String> typeData = new ArrayList<String>();
		typeData.addAll(CtrlDominio.getTypes());
		//Remove Paper type nodes, because we'll only use the combo box w/ papers
		typeData.remove(CtrlDominio.getNodeTypeIndex("Paper"));
		String[] typeDataArray = typeData.toArray(new String[typeData.size()]); 
		typeComboBox = new JComboBox<String>(typeDataArray);
		typeComboBox.setEnabled(true);
		springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 10, SpringLayout.SOUTH, lblSelectANode);
		springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 10, SpringLayout.EAST, lblNodeType);
		this.add(typeComboBox);
		typeComboBox.setSelectedIndex(0);
		typeComboBox.addActionListener(				
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setNodesList(typeComboBox.getSelectedIndex());
					}
				});

	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	int closeIt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub

	}
}
