package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dominio.CtrlGraph;
import Dominio.Node;

import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

//public class PanelEditNode extends AbstractPanel{
public class PanelEditNode extends JFrame{
	JPanel relationsPanel = new JPanel();
	JPanel optionsPanel = new JPanel();
	JPanel splitPanel = new JPanel();

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
	//private final JTextPane textPane = new JTextPane();
	private final Component horizontalStrut = Box.createHorizontalStrut(40);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalGlue = Box.createVerticalGlue();
	private final Component horizontalStrut_4 = Box.createHorizontalStrut(20);
	//@Override
	public int closeIt() {
		return 0;
	}


	//@Override
	public void setEnabledEverything(Boolean b) {

	}

	//public PanelEditNode(VistaAbstracta vp) {
	//	super(vp);
	public PanelEditNode(){
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, splitPanel, 3, SpringLayout.SOUTH, verticalStrut);
		springLayout.putConstraint(SpringLayout.SOUTH, splitPanel, 202, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, verticalStrut, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, verticalStrut, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, verticalStrut, 434, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, optionsPanel, 239, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, optionsPanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, optionsPanel, 434, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, splitPanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, splitPanel, 434, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.X_AXIS));

		splitPanel.add(horizontalStrut_1);
		splitPanel.add(nameTextField);
		nameTextField.setMinimumSize(new Dimension(100, 30));
		nameTextField.setMaximumSize(new Dimension(100, 30));
		nameTextField.setAlignmentX(LEFT_ALIGNMENT);
		splitPanel.add(labelComboBox);
		labelComboBox.setMaximumSize(labelComboBox.getPreferredSize());
		splitPanel.add(typeComboBox);
		typeComboBox.setMaximumSize(typeComboBox.getPreferredSize());

		splitPanel.add(horizontalStrut);

		relationsPanel.setLayout(new BoxLayout(relationsPanel, BoxLayout.Y_AXIS));
		relationsPanel.add(relationsLabel);
		relationsPanel.add(relationsTable);
		
		relationsPanel.add(verticalGlue);

		//relationsPanel.add(textPane);
		relationsPanel.add(addRelationButton);

		splitPanel.add(relationsPanel, BorderLayout.EAST);		
		getContentPane().add(splitPanel);

		splitPanel.add(horizontalStrut_2);
		getContentPane().add(namePanel);
		namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
				namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
				namePanel.add(nameLabel);
		getContentPane().add(typePanel);
		typePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
		typePanel.add(typeLabel);
		getContentPane().add(labelPanel);
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
				labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
				labelPanel.add(labelLabel);

		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		
		optionsPanel.add(horizontalStrut_4);
		optionsPanel.add(saveButton);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		optionsPanel.add(horizontalStrut_3);
		optionsPanel.add(exitButton);

		getContentPane().add(optionsPanel);
		
		getContentPane().add(verticalStrut);
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
		relationsPanel.remove(relationsTable);
		relationsPanel.remove(addRelationButton);
		relationsPanel.add(relationsTable);
		relationsPanel.add(addRelationButton);
		//relationsTable.repaint();
		//relationsTable.revalidate();
		//this.repaint();
		//this.revalidate();
	}
}
