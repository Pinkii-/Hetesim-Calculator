package Presentacion;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dominio.CtrlGraph;

public class PanelEditNode extends AbstractPanel{
	
	JPanel infoRelationsPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel relationsPanel = new JPanel();
	JPanel optionsPanel = new JPanel();
	
	JTable relationsTable = new JTable();
	
	JLabel nameLabel = new JLabel("Name: ");
	JTextField nameTextField = new JTextField();
	JPanel namePanel = new JPanel();
	
	JLabel typeLabel = new JLabel("Type: ");
	MyComboBox typeComboBox = new MyComboBox();
	JPanel typePanel = new JPanel();

	JLabel labelLabel = new JLabel("Label: ");
	MyComboBox labelComboBox = new MyComboBox();
	JPanel labelPanel = new JPanel();

	JLabel relationsLabel = new JLabel("Relations: ");
	
	JButton addRelationButton = new JButton("Add new relation");
	JButton saveButton = new JButton("Save");
	JButton exitButton = new JButton("Exit");

	private static final long serialVersionUID = 1L;
	CtrlGraph ctrlGraph;
	ArrayList<String> nodeInfo;
	@Override
	public int closeIt() {
		vp.continueAction();
		return 0;
	}


	@Override
	public void setEnabledEverything(Boolean b) {
		
	}
	
	public PanelEditNode(VistaAbstracta vp) {
		super(vp);
		ctrlGraph = cd.getCtrlGraph();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		infoRelationsPanel.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		infoPanel.setLayout(new BoxLayout(infoRelationsPanel, BoxLayout.Y_AXIS));
		namePanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		infoPanel.add(namePanel);
		typePanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		typePanel.add(typeLabel);
		typePanel.add(typeComboBox);
		infoPanel.add(typePanel);
		labelPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		labelPanel.add(labelLabel);
		labelPanel.add(labelComboBox);
		infoPanel.add(labelPanel);
		infoRelationsPanel.add(infoPanel);
		relationsPanel.setLayout(new BoxLayout(infoRelationsPanel, BoxLayout.Y_AXIS));
		relationsPanel.add(relationsLabel);
		relationsPanel.add(relationsTable);
		relationsPanel.add(addRelationButton);
		infoRelationsPanel.add(relationsPanel);
		optionsPanel.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		optionsPanel.add(saveButton);
		optionsPanel.add(exitButton);		
		
//		JPanel infoPanel = new JPanel();
//		JPanel relationsPanel = new JPanel();
//		JPanel optionsPanel = new JPanel();
//		
//		JTable relationsTable = new JTable();
//		
//		JLabel nameLabel = new JLabel("Name: ");
//		JTextField nameTextField = new JTextField();
//		JPanel namePanel = new JPanel();
//		
//		JLabel typeLabel = new JLabel("Type: ");
//		MyComboBox typeComboBox = new MyComboBox();
//		JPanel typePanel = new JPanel();
//
//		JLabel labelLabel = new JLabel("Label: ");
//		MyComboBox labelComboBox = new MyComboBox();
//		JPanel labelPanel = new JPanel();
//
//		JLabel relationsLabel = new JLabel("Relations: ");
//		
//		JButton addRelationButton = new JButton("Add new relation");
//		JButton saveButton = new JButton("Save");
//		JButton exitButton = new JButton("Exit");
		
	}
	
	public void setNodeToEdit(Integer index, String nodeType){
		nodeInfo = ctrlGraph.getNodeFormatted(index, nodeType);
		updatePanel();
	}
	
	private void updatePanel(){
		
	}


}
