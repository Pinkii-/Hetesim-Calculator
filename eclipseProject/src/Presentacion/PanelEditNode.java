package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dominio.CtrlGraph;
import Dominio.Node;

import java.awt.Component;
import javax.swing.JTextPane;

public class PanelEditNode extends AbstractPanel{

	JPanel infoPanel = new JPanel();
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
		cd.importGraph("C:/Users/Usuari/Desktop/PROP/GraphForTesting");
		this.setLayout(new BorderLayout(20, 20));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		infoPanel.add(namePanel);
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		nameTextField.setMinimumSize(new Dimension(100, 30));
		nameTextField.setMaximumSize(new Dimension(100, 30));
		nameTextField.setAlignmentX(LEFT_ALIGNMENT);
		typePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
		typePanel.add(typeLabel);
		typeComboBox.setMaximumSize(typeComboBox.getPreferredSize());
		typePanel.add(typeComboBox);

		infoPanel.add(typePanel);
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.add(labelLabel);
		labelComboBox.setMaximumSize(labelComboBox.getPreferredSize());
		labelPanel.add(labelComboBox);

		infoPanel.add(labelPanel);
		splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.X_AXIS));

		splitPanel.add(horizontalStrut_1);
		splitPanel.add(infoPanel, BorderLayout.WEST);

		splitPanel.add(horizontalStrut);

		relationsPanel.setLayout(new BoxLayout(relationsPanel, BoxLayout.Y_AXIS));
		relationsPanel.add(relationsLabel);
		relationsPanel.add(relationsTable);

		//relationsPanel.add(textPane);
		relationsPanel.add(addRelationButton);

		splitPanel.add(relationsPanel, BorderLayout.EAST);		
		this.add(splitPanel, BorderLayout.CENTER);

		splitPanel.add(horizontalStrut_2);

		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		optionsPanel.add(saveButton);
		optionsPanel.add(exitButton);

		this.add(optionsPanel, BorderLayout.SOUTH);
		
		add(verticalStrut, BorderLayout.NORTH);
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
