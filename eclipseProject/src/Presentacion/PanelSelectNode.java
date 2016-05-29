package Presentacion;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Dominio.CtrlDominio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class PanelSelectNode extends AbstractPanel{

	DefaultListModel<String> relationsListModel = new DefaultListModel<String>();
	JComboBox<String> typeComboBox = new JComboBox<String>();


	public PanelSelectNode(VistaAbstracta vp) {
		super(vp);
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);

		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, this);
		this.add(btnCancel);

		JButton btnGetNode = new JButton("Get node");
		springLayout.putConstraint(SpringLayout.NORTH, btnGetNode, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnGetNode, -10, SpringLayout.WEST, btnCancel);
		this.add(btnGetNode);

		JLabel lblSelectANode = new JLabel("Select a Node:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectANode, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectANode, 10, SpringLayout.WEST, this);
		this.add(lblSelectANode);

		JLabel lblNodeType = new JLabel("Node Type:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNodeType, 10, SpringLayout.SOUTH, lblSelectANode);
		springLayout.putConstraint(SpringLayout.WEST, lblNodeType, 10, SpringLayout.WEST, lblSelectANode);
		this.add(lblNodeType);

		JLabel lblNodeList = new JLabel("All nodes of the selected type: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNodeList, 10, SpringLayout.SOUTH, lblNodeType);
		springLayout.putConstraint(SpringLayout.WEST, lblNodeList, 0, SpringLayout.WEST, lblNodeType);
		this.add(lblNodeList);
		
		
		//Initializing the type combo box
		ArrayList<String> typeData = new ArrayList<String>();
		typeData.addAll(CtrlDominio.getTypes());
		String[] typeDataArray = typeData.toArray(new String[typeData.size()]); 
		typeComboBox = new JComboBox<String>(typeDataArray);
		typeComboBox.setEnabled(true);
		
		typeComboBox.addActionListener(				
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						setNodesList(CtrlDominio.getNodeTypeOfIndex(typeComboBox.getSelectedIndex()));
					}
				});
		
		springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 10, SpringLayout.SOUTH, lblSelectANode);
		springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 10, SpringLayout.EAST, lblNodeType);
		this.add(typeComboBox);

		JList<String> list = new JList<String>(relationsListModel);
		springLayout.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.SOUTH, lblNodeList);
		springLayout.putConstraint(SpringLayout.WEST, list, 0, SpringLayout.WEST, lblNodeList);
		springLayout.putConstraint(SpringLayout.SOUTH, list, -10, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, list, -10, SpringLayout.EAST, this);
		this.add(list);
		
		//Initializing the node's list
		setNodesList(CtrlDominio.getNodeTypeOfIndex(typeComboBox.getSelectedIndex()));
	}
	
	private void setNodesList(String nodeType){
		relationsListModel.clear();
		ArrayList<ArrayList<String>> nodesInfo = cd.getCtrlGraph().getformattedNodesOfType(nodeType);
		for (int i = 0; i < nodesInfo.size(); i++) {
			ArrayList<String> nodesInfoRow = nodesInfo.get(i);
			String listString = new String();
			listString = nodesInfoRow.get(1);
			relationsListModel.addElement(listString);
		}
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
