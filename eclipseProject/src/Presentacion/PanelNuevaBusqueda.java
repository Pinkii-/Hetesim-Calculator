package Presentacion;


import javax.swing.SpringLayout;

import Presentacion.VistaPrincipal.Panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Panel para el caso de uso de nueva Búsqueda
 * 
 * @author Xavier Peñalosa
 *
 */
public class PanelNuevaBusqueda extends AbstractPanel implements ActionListener{

	private JComboBox<String> pathSelect;
	private MyComboBox node1Select, node2Select;
	private JLabel pathLabel, node1Label, node2Label, thresholdLabel;
	private JCheckBox checkbox;
	
	private JList resultList;
	private MyResultTable resultTable;
	private JSpinner threshold;
	
	private JButton calcHete, reuseSearch, saveResult, editResult;
	
	private VistaPrincipal vp;
	private boolean hasResult = false;
	private String idResult = "";
	private static final long serialVersionUID = 1L;
	

	PanelNuevaBusqueda(VistaPrincipal vp) {
		super(vp);
		
		this.vp = vp;
	}
	
	public void init(){
		this.removeAll();
		hasResult = false;
		
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
		node1Label.setForeground(Color.gray);
		add(node1Label);
			node1Select = new MyComboBox();
			node1Select.loadNodesToLists(this.cd);
			add(node1Select);
		
		//Node 2
		node2Label = new JLabel();
		node2Label.setPreferredSize(new Dimension(168,20));
		node2Label.setText("Node type:");
		node2Label.setForeground(Color.gray);
		add(node2Label);
			node2Select = new MyComboBox();
			node2Select.loadNodesToLists(this.cd);
			add(node2Select);
		
		//Path label
		pathLabel = new JLabel();
		pathLabel.setPreferredSize(new Dimension(168,20));
		pathLabel.setText("Path");
		add(pathLabel);
		
		//ResultList
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp1 = new ArrayList<String>();
				String temp2 = "";
			temp1.add(temp2);
		temp.add(temp1);
		resultTable = new MyResultTable();
		resultTable.setBorder(BorderFactory.createLineBorder(Color.gray));
		resultTable.setPreferredSize(new Dimension(350,200));
		add(resultTable);
		
		//resultList = new JList<String>();
		//resultList.setPreferredSize(new Dimension(350,200));
		//resultList.setBorder(BorderFactory.createLineBorder(Color.gray));
		//add(resultList);
		
		
		
		//Threshold value
		checkbox = new JCheckBox();
		checkbox.setSelected(false);
		checkbox.setEnabled(false);
		add(checkbox);
		
		thresholdLabel = new JLabel();
		thresholdLabel.setPreferredSize(new Dimension(75,20));
		thresholdLabel.setText("Threshold");
		thresholdLabel.setForeground(Color.gray);
		add(thresholdLabel);
			threshold = new JSpinner();
			SpinnerModel sm = new SpinnerNumberModel(0.5,0,1,0.05);
			threshold.setModel(sm);
			JComponent editor = threshold.getEditor();
			((JSpinner.DefaultEditor) editor).getTextField().setColumns(4);
			threshold.setEnabled(false);
			add(threshold);
		
		//Hetesim button
		Icon icon = null;
		try {
			icon = new ImageIcon(new URL("http://i.imgur.com/q9dfxIe.png"));
		} catch (MalformedURLException e) {
			
		}
		calcHete = new JButton("Calculate Hetesim", icon);
		calcHete.setPreferredSize(new Dimension(200,50));
		calcHete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calcHete.setFocusable(false);
		calcHete.setEnabled(false);
		add(calcHete);
		
		Icon icon4 = null;
		try {
			icon4 = new ImageIcon(new URL("http://i.imgur.com/q9dfxIe.png"));
		} catch (MalformedURLException e) {
			
		}
		reuseSearch = new JButton("Use fields", icon4);
		reuseSearch.setPreferredSize(new Dimension(200,50));
		reuseSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reuseSearch.setFocusable(false);
		reuseSearch.setEnabled(false);
		add(reuseSearch);
		
		//Save result button
		Icon icon2 = null;
		try {
			icon2 = new ImageIcon(new URL("http://i.imgur.com/RTzXRY4.png"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		saveResult = new JButton("Save", icon2);
		saveResult.setPreferredSize(new Dimension(92,30));
		saveResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveResult.setFocusable(false);
		saveResult.setEnabled(false);
		add(saveResult);
		
		//Edit result button
		Icon icon3 = null;
		try {
			icon3 = new ImageIcon(new URL("http://i.imgur.com/iA8cG7Q.jpg"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		editResult = new JButton("Edit", icon3);
		editResult.setPreferredSize(new Dimension(92,30));
		editResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editResult.setToolTipText("You must save the result before editing");
		editResult.setFocusable(false);
		editResult.setEnabled(false);
		add(editResult);
		
		
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
		sl.putConstraint(SpringLayout.NORTH, resultTable, 15, SpringLayout.SOUTH, node2Label);
		sl.putConstraint(SpringLayout.SOUTH, resultTable, -35, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.WEST, resultTable, 20, SpringLayout.WEST, this);
		sl.putConstraint(SpringLayout.EAST, resultTable, -15, SpringLayout.WEST, calcHete);
		//sl.putConstraint(SpringLayout.NORTH, resultList, 15, SpringLayout.SOUTH, node2Label);
		//sl.putConstraint(SpringLayout.SOUTH, resultList, -35, SpringLayout.SOUTH, this);
		//sl.putConstraint(SpringLayout.WEST, resultList, 20, SpringLayout.WEST, this);
		
		//Threshold
		sl.putConstraint(SpringLayout.NORTH, checkbox, 5, SpringLayout.SOUTH, resultTable);
		sl.putConstraint(SpringLayout.EAST, checkbox, 0, SpringLayout.WEST, thresholdLabel);
		
		sl.putConstraint(SpringLayout.NORTH, threshold, 5, SpringLayout.SOUTH, resultTable);
		sl.putConstraint(SpringLayout.EAST, threshold, 0, SpringLayout.EAST, resultTable);
		
		sl.putConstraint(SpringLayout.NORTH, thresholdLabel, 5, SpringLayout.SOUTH, resultTable);
		sl.putConstraint(SpringLayout.EAST, thresholdLabel, 0, SpringLayout.WEST, threshold);
		//sl.putConstraint(SpringLayout.NORTH, checkbox, 5, SpringLayout.SOUTH, resultList);
		//sl.putConstraint(SpringLayout.EAST, checkbox, 0, SpringLayout.WEST, thresholdLabel);
		
		//sl.putConstraint(SpringLayout.NORTH, threshold, 5, SpringLayout.SOUTH, resultList);
		//sl.putConstraint(SpringLayout.EAST, threshold, 0, SpringLayout.EAST, resultList);
		
		//sl.putConstraint(SpringLayout.NORTH, thresholdLabel, 5, SpringLayout.SOUTH, resultList);
		//sl.putConstraint(SpringLayout.EAST, thresholdLabel, 0, SpringLayout.WEST, threshold);
		
		//Calc hetesim
		sl.putConstraint(SpringLayout.NORTH, calcHete, 0, SpringLayout.NORTH, resultTable);
		sl.putConstraint(SpringLayout.EAST, calcHete, -15, SpringLayout.EAST, this);
		//sl.putConstraint(SpringLayout.NORTH, calcHete, 0, SpringLayout.NORTH, resultList);
		//sl.putConstraint(SpringLayout.WEST, calcHete, 15, SpringLayout.EAST, resultList);
		
		//Reuse search
		sl.putConstraint(SpringLayout.NORTH, reuseSearch, 15, SpringLayout.SOUTH, calcHete);
		sl.putConstraint(SpringLayout.WEST, reuseSearch, 15, SpringLayout.EAST, resultTable);
		sl.putConstraint(SpringLayout.EAST, reuseSearch, -15, SpringLayout.EAST, this);
		
		
		//Edit result
		//sl.putConstraint(SpringLayout.NORTH, editResult, 1000, SpringLayout.SOUTH, calcHete);
		sl.putConstraint(SpringLayout.SOUTH, editResult, -35, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.WEST, editResult, 15, SpringLayout.EAST, resultTable);
		//sl.putConstraint(SpringLayout.SOUTH, editResult, -35, SpringLayout.SOUTH, this);
		//sl.putConstraint(SpringLayout.WEST, editResult, 15, SpringLayout.EAST, resultList);
		
		//Save result
		//sl.putConstraint(SpringLayout.NORTH, saveResult, 50, SpringLayout.SOUTH, calcHete);
		sl.putConstraint(SpringLayout.SOUTH, saveResult, -35, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.WEST, saveResult, 15, SpringLayout.EAST, editResult);
		
		
		
	}
	
	private void assignListeners(){
		
		pathSelect.addActionListener(this);
		calcHete.addActionListener(this);
		saveResult.addActionListener(this);
		editResult.addActionListener(this);
		checkbox.addActionListener(this);
		
	}

	private String[] arrayListToArray(ArrayList<String> alist){
		String[] temp = new String[alist.size()];
		return alist.toArray(temp);
	}
	
	
	@Override
	public int closeIt() {
		if (hasResult){
			String[] buttons = {"Salir", "Cancelar"};
			int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todos los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
			return result;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource().equals(calcHete)){
			String path = pathSelect.getSelectedItem().toString();
			int n1 = -1, n2 = -1;
			if (!node1Select.getEditor().getItem().toString().equals(" - Select all -")) n1 = node1Select.getSelectedNodeIndex();
			if (!node2Select.getEditor().getItem().toString().equals(" - Select all -")) n2 = node2Select.getSelectedNodeIndex();
			if (n1 == -1 && n2 == -1){
				//No nodes
				System.out.println("Searching");
				if (checkbox.isSelected()){
					System.out.println("P threshold");
					idResult = cd.searchPathThreshhold((float)((double)threshold.getValue()), path);
					resultTable = new MyResultTable(cd.getCtrlResults().getLastResultFormatted(idResult));
				}
				else {
					System.out.println("P");
					idResult = cd.searchPath(path);
					resultTable = new MyResultTable(cd.getCtrlResults().getLastResultFormatted(idResult));
				}
				System.out.println("Done");
			}
			else if (n1 == -1 && n2 >= 0){
				//One node
				if (checkbox.isSelected()){
					System.out.println("PN1 threshold");
					idResult = cd.searchPathNodeThreshhold((float)((double)threshold.getValue()), path, n1);
					resultTable = new MyResultTable(cd.getCtrlResults().getLastResultFormatted(idResult));
				}
				else {
					System.out.println("PN1");
					idResult = cd.searchPathNode(path,n1);
				}
				System.out.println("Done");
				
			}
			else if (n2 == -1 && n1 >= 0){
				//One node, reverse
				if (checkbox.isSelected()){
					System.out.println("PN2 threshold");
					path = new StringBuilder(path).reverse().toString();
					idResult = cd.searchPathNodeThreshhold((float)((double)threshold.getValue()), path, n2);
				}
				else {
					System.out.println("PN2");
					path = new StringBuilder(path).reverse().toString();
					idResult = cd.searchPathNode(path,n2);
				}
				System.out.println("Done");
			}
			else if (n1 >= 0 && n2 >= 0){
				//Two
				if (checkbox.isSelected()){
					System.out.println("PNN threshold");
					idResult = cd.searchPathNodeNodeThreshhold((float)((double)threshold.getValue()), path, n1, n2);
				}
				else {
					System.out.println("PNN");
					idResult = cd.searchPathNodeNode(path,n1,n2);
				}
				System.out.println("Done");
			}
			else {
				System.out.println("Either node is invalid");
			}
			
			//if (result.exists()){
				//Cargar resultado ya existente
				
			//}
			//else {
			System.out.println(idResult);
			
			if (cd.getCtrlResults().getResult(idResult) != null){
				try {
					resultTable = new MyResultTable(cd.getCtrlResults().getLastResultFormatted(idResult));
					resultTable.setPreferredSize(new Dimension(350,200));
					resultTable.setBorder(BorderFactory.createLineBorder(Color.black));
					threshold.setEnabled(true);
					thresholdLabel.setForeground(Color.black);
					saveResult.setEnabled(true);
					hasResult = true;
				}
				catch (NullPointerException p){
					VistaDialog.setDialog("Error", "No se ha podido acceder al resultado\n"
											     + "@Victor fix your shit", new String[]{"Continue"}, VistaDialog.DialogType.ERROR_MESSAGE);
				}
			}
			
			//}
		}
		else if (e.getSource().equals(reuseSearch)){
			if (resultTable.getSelectedRowCount() == 1){
				node1Select.setSelectedItem(resultTable.getModel().getValueAt(resultTable.getSelectedRow(),0));
				node2Select.setSelectedItem(resultTable.getModel().getValueAt(resultTable.getSelectedRow(),2));
			}	
		}
		else if (e.getSource().equals(saveResult)){
			idResult = cd.getCtrlResults().addLastResult();
			resultTable = new MyResultTable(cd.getCtrlResults().getFormatted(idResult));
			editResult.setEnabled(true);
		}
		else if (e.getSource().equals(editResult)){
			this.vp.panelMostrarResultado.setShowedResult(cd.getCtrlResults().getFormatted(idResult));
			this.vp.changePanel(Panels.PanelMostrarResultado);
		}
		else if (e.getSource().equals(checkbox)){
			if (checkbox.isSelected()){
				threshold.setEnabled(true);
				thresholdLabel.setForeground(Color.black);
			}
			else {
				threshold.setValue(0.5);
				threshold.setEnabled(false);
				thresholdLabel.setForeground(Color.gray);
			}
		}
		else if (e.getSource().equals(pathSelect)){
			calcHete.setEnabled(true);
			checkbox.setEnabled(true);
			String text = pathSelect.getItemAt(pathSelect.getSelectedIndex()).toString();
			boolean n1 = false, n2 = false;
			switch (text.charAt(0)){
				case 'P':
					node1Label.setText("Node type: Paper");
					node1Label.setForeground(Color.black);
					node1Select.setList(0);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'A':
					node1Label.setText("Node type: Author");
					node1Label.setForeground(Color.black);
					node1Select.setList(1);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'C':
					node1Label.setText("Node type: Conference");
					node1Label.setForeground(Color.black);
					node1Select.setList(2);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				case 'T':
					node1Label.setText("Node type: Term");
					node1Label.setForeground(Color.black);
					node1Select.setList(3);
					node1Select.setEnabled(true);
					n1 = true;
					break;
				default:
					node1Label.setText("INVALID PATH");
					node1Label.setForeground(Color.gray);
					node1Select.setEnabled(false);
					break;
			}
			switch (text.charAt(text.length()-1)){
				case 'P':
					node2Label.setText("Node type: Paper");
					node2Label.setForeground(Color.black);
					node2Select.setList(0);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'A':
					node2Label.setText("Node type: Author");
					node2Label.setForeground(Color.black);
					node2Select.setList(1);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'C':
					node2Label.setText("Node type: Conference");
					node2Label.setForeground(Color.black);
					node2Select.setList(2);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				case 'T':
					node2Label.setText("Node type: Term");
					node2Label.setForeground(Color.black);
					node2Select.setList(3);
					node2Select.setEnabled(true);
					n2 = true;
					break;
				default:
					node2Label.setText("INVALID PATH");
					node2Label.setForeground(Color.gray);
					node2Select.setEnabled(false);
					break;
			}
			if (node1Select.getEditor().getItem().toString().equals("None found!") ||
				node2Select.getEditor().getItem().toString().equals("None found!") ||
				!(n1 && n2)){
					
					calcHete.setEnabled(false);
					checkbox.setEnabled(false);
					threshold.setEnabled(false);
					thresholdLabel.setForeground(Color.gray);
				
			}
		}
		
	}
}