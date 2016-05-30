package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class PanelModificaGraph extends AbstractPanel {

	private JButton buttonAddNode = new JButton("Add New Node");
	private JButton buttonEditNode = new JButton("Edit Node");
	private JButton buttonSaveGraph = new JButton("Save Graph To File");
	
	private JPanel displayNode;
	private JComboBox<String> comboBoxTypeOfNode;
//	private MyComboBox findingNode; // JComboBox que te muestra solo los nodos posibles segun lo que has escrito.
	Boolean changed = false;
	
	private JScrollPane scrollPane;
	private JList<String> list;
	private JTextField textField;
	private ArrayList<Integer> indexOfNodes;
	DefaultListModel<String> model;
	
	public PanelModificaGraph(VistaAbstracta vp) {
		super(vp);
//		setBackground(Color.green);
	}
	
	public void init() {
		initComponents();
		asignListeners();
	}
	
	private void initComponents() {
		this.removeAll();
		// DisplayNodes
		comboBoxTypeOfNode = new JComboBox<String>(cd.getTypes().toArray(new String[cd.getTypes().size()]));
		comboBoxTypeOfNode.setSelectedIndex(-1);
		comboBoxTypeOfNode.setToolTipText("Select Type of node");
		comboBoxTypeOfNode.setMaximumSize(buttonAddNode.getPreferredSize());
		
//		findingNode = new MyComboBox();
////		findingNode.linkToParentComboBox(comboBoxTypeOfNode);
//		findingNode.loadNodesToLists(cd);
////		findingNode.linkToParentComboBox(comboBoxTypeOfNode);
//		
//			//equisde no se como hacer lo que quiero que haga
//		findingNode.setMaximumSize(new Dimension(buttonAddNode.getMinimumSize().width*2, buttonAddNode.getMinimumSize().height));
//		findingNode.setMinimumSize(new Dimension(buttonAddNode.getMinimumSize().width*2, buttonAddNode.getMinimumSize().height));
		
		createList();
		
		
		displayNode = new JPanel();
		displayNode.setLayout(new BoxLayout(displayNode, BoxLayout.X_AXIS));
		displayNode.add(Box.createRigidArea(new Dimension(5,0)));
		displayNode.add(comboBoxTypeOfNode);
		displayNode.add(Box.createRigidArea(new Dimension(5,0)));
		displayNode.add(textField);
		displayNode.add(Box.createRigidArea(new Dimension(5,0)));
		displayNode.setMaximumSize(new Dimension(99000,comboBoxTypeOfNode.getHeight()));
		
		// next
		
//		JLabel l;
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.add(Box.createHorizontalGlue());
		btnPanel.add(buttonEditNode);
		btnPanel.add(Box.createRigidArea(new Dimension(5,0)));
		btnPanel.add(buttonAddNode);
		btnPanel.add(Box.createRigidArea(new Dimension(5,0)));
		btnPanel.add(buttonSaveGraph);
		btnPanel.add(Box.createRigidArea(new Dimension(5,0)));
		
		
		BoxLayout main = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(main);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(displayNode);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(btnPanel);
		add(Box.createRigidArea(new Dimension(0,5)));
		
		
	}
	
	private void createList() {
		scrollPane = new JScrollPane();
		model = new DefaultListModel<String>();

		list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		
		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				model.clear();
				ArrayList<ArrayList<String>> nodes = cd.getCtrlGraph().getformattedNodesOfType((String) comboBoxTypeOfNode.getSelectedItem());
				indexOfNodes = new ArrayList<Integer>();
				System.gc();
				if (textField.getText().length() > 0) {
					for (int i = 0; i < nodes.size(); ++i) {
						if (nodes.get(i).get(1).toLowerCase().contains(textField.getText().toLowerCase())) {
							model.addElement(nodes.get(i).get(1));
							indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
						}
					}
				}
				else {
					for (int i = 0; i < nodes.size(); ++i) {
						model.addElement(nodes.get(i).get(1));
						indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
					}
				}
			}
			
		});
		
		comboBoxTypeOfNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				model.clear();
				ArrayList<ArrayList<String>> nodes = cd.getCtrlGraph().getformattedNodesOfType((String) comboBoxTypeOfNode.getSelectedItem());
				indexOfNodes = new ArrayList<Integer>();
				for (int i = 0; i < nodes.size(); ++i) {
					model.addElement(nodes.get(i).get(1));
					indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
				}
			}
		});
	}
	
	private void asignListeners() {
		PanelModificaGraph aux = this;
		buttonAddNode.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!buttonAddNode.isEnabled()) return;
				aux.addVista(PanelEditNode.class, true); // Cambiar al panel que agrega nodos
				
			}
		});
		
		
		buttonEditNode.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				if (!buttonEditNode.isEnabled()) return;
				aux.addVista(PanelEditNode.class, true);
				((PanelEditNode) aux.childs.get(0).getContentPane().getComponent(0))
				.setNodeToEdit(indexOfNodes.get(list.getSelectedIndex()), 
						(String) comboBoxTypeOfNode.getSelectedItem());

			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		buttonSaveGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!buttonSaveGraph.isEnabled()) return;
				cd.saveGraph();
			}
		});
//		buttonEditNode.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				aux.addVista(PanelEditNode.class, true);
//				((PanelEditNode) aux.childs.get(0).getContentPane().getComponent(0))
//				.setNodeToEdit(indexOfNodes.get(list.getSelectedIndex()), 
//						(String) comboBoxTypeOfNode.getSelectedItem());
//
//				System.out.println("buttonEditNode");
//			}
//		});
	}

	@Override
	public int closeIt() {
		if (changed) {
			// Preguntar si estas seguro de que te pueden cerrar desde un sitio externo
			String[] buttons = {"Salir", "Cancelar"};
			int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.WARNING_MESSAGE);
			return result;
		}
		else return 0;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		buttonAddNode.setEnabled(b);
		
		buttonEditNode.setEnabled(b);
		buttonSaveGraph.setEnabled(b);
		
		displayNode.setEnabled(b);
		comboBoxTypeOfNode.setEnabled(b);
		
		scrollPane.setEnabled(b);
		list.setEnabled(b);
		textField.setEnabled(b);
		
		
		System.out.println("testestes");
		try { // Solo para el test. Luego lo tengo que quitar cuando ya no sea una vista abstracta y sea una vista principal
			VistaPrincipal v = (VistaPrincipal) vp;
			v.setEnabledPrincipal(b);
		}
		catch (ClassCastException e) {
			
		}
	}
	
	void updateGraph() {
		model.clear();
		ArrayList<ArrayList<String>> nodes = cd.getCtrlGraph().getformattedNodesOfType((String) comboBoxTypeOfNode.getSelectedItem());
		indexOfNodes = new ArrayList<Integer>();
		System.gc();
		if (textField.getText().length() > 0) {
			for (int i = 0; i < nodes.size(); ++i) {
				if (nodes.get(i).get(1).toLowerCase().contains(textField.getText().toLowerCase())) {
					model.addElement(nodes.get(i).get(1));
					indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
				}
			}
		}
		else {
			for (int i = 0; i < nodes.size(); ++i) {
				model.addElement(nodes.get(i).get(1));
				indexOfNodes.add(Integer.parseInt(nodes.get(i).get(0)));
			}
		}
	}

}
