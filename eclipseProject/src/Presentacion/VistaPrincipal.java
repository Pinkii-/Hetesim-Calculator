package Presentacion;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VistaPrincipal {

	private String APPLICATION_NAME = "ola k ase lol";
	
	// Componentes UI
	private JFrame jFrame = new JFrame(APPLICATION_NAME);
	
	// Panels;
	enum Panels {ModificaGraph, Test, Exit};
	private Panels nextPanel;
	
	private JPanel content = new JPanel();
	private AbstractPanel modificaGraph = new PanelModificaGraph(this);

	// Menus
	private JMenuBar menuBar = new JMenuBar();
	
	  	// File
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuitemFileNewGraph = new JMenuItem("New Graph");
	private JMenuItem menuitemFileLoadGraf = new JMenuItem("Load Graph");
	private JMenuItem menuitemFileImportGraf = new JMenuItem("Import Graph");

	private JMenuItem menuitemFileExit = new JMenuItem("Exit");
		// Edit
	private JMenu menuEdit = new JMenu("Edit");
	private JMenu menuEditModify = new JMenu("Modify");
	private JMenuItem menuitemEditGraph = new JMenuItem("Graph");
	private JMenuItem menuitemEditPath = new JMenuItem("Paths");
	private JMenuItem menuitemEditResult = new JMenuItem("Result");
	
	
	// Constructor and public stuff
	
	public VistaPrincipal() {
		initComponents();
		jFrame.setEnabled(true);
		jFrame.pack();
		jFrame.setVisible(true);
	}
	
	// listeners de los menus
	
	private void asign_listeners() {
	
		menuitemFileExit.addActionListener
		( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changePanel(Panels.Exit);
				
			}
		});
		
		menuitemEditGraph.addActionListener
	    (new ActionListener() {
	      public void actionPerformed (ActionEvent event) {
	        changePanel(Panels.ModificaGraph);
	      }
	    });
		
	}
	
	// private metods
	
	private void initComponents() {
		initFrame();
		initMenuBar();
		asign_listeners();
	}
	
	private void initFrame() {
		jFrame.setSize(600, 400);
		jFrame.setMinimumSize(jFrame.getSize());
		jFrame.setResizable(false);
		
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set the first contentPanel
		JPanel contentPane = (JPanel) jFrame.getContentPane();
		contentPane.add(content);
		contentPane.setBackground(Color.blue);
	}
	
	private void initMenuBar() {
		
		menuFile.add(menuitemFileNewGraph);
		menuFile.add(menuitemFileLoadGraf);
		menuFile.add(menuitemFileImportGraf);
		menuFile.add(menuitemFileExit);
		
		menuEditModify.add(menuitemEditGraph);
		menuEditModify.add(menuitemEditPath);
		menuEditModify.add(menuitemEditResult);
		
		menuEdit.add(menuEditModify);
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		
		jFrame.setJMenuBar(menuBar);
	}
	
	void changePanel(Panels p) {
		System.out.println("Cambiando a panel " + p.toString());
		nextPanel = p;
		try {
			AbstractPanel currentPanel = (AbstractPanel) jFrame.getContentPane().getComponent(0);
			currentPanel.closeIt();
		}
		catch (Exception e) {
			continueAction();
		}
	}
	
	void continueAction() {
		jFrame.getContentPane().removeAll();
		switch (nextPanel) {
			case ModificaGraph:
				jFrame.getContentPane().add(modificaGraph);
				break;
			case Test:
				jFrame.getContentPane().add(content);
				break;
			case Exit:
				System.exit(0);
			default:
				throw new RuntimeException("Ese panel no existe h3h3h3h3h3h3h3h3h3h3h3h3h3h3h3");
		}
		jFrame.pack();
		jFrame.repaint();
	}

}
