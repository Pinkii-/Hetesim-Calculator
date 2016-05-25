package Presentacion;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.CtrlDominio;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VistaPrincipal extends VistaAbstracta{

	private CtrlDominio cd;
	
	private String APPLICATION_NAME = "ola k ase lol";
	
	// Componentes UI
//	private JFrame this = new JFrame(APPLICATION_NAME);
	
	// Panels;
	enum Panels {ModificaGraph, LoadResult, PanelMostrarResultado, NuevaBusqueda, Test, Exit};
	private Panels nextPanel;
	
	private JPanel content = new JPanel();
	AbstractPanel modificaGraph = new PanelModificaGraph(this);
	AbstractPanel loadResult = new PanelLoadResult(this);
	AbstractPanel nuevaBusqueda = new PanelNuevaBusqueda(this);
	PanelMostrarResultado panelMostrarResultado = new PanelMostrarResultado(this);

	// Menus
	private JMenuBar menuBar = new JMenuBar();
	
	  	// File
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuitemFileNewGraph = new JMenuItem("New Graph");
	private JMenuItem menuitemFileLoadGraf = new JMenuItem("Load Graph");
	private JMenuItem menuitemFileImportGraf = new JMenuItem("Import Graph");
	private JMenuItem menuitemFileLoadResult = new JMenuItem("Load Result");

	private JMenuItem menuitemFileExit = new JMenuItem("Exit");
	

	private JMenuItem menuitemNuevaBusqueda = new JMenuItem("New Search");
		// Edit
	private JMenu menuEdit = new JMenu("Edit");
	private JMenu menuEditModify = new JMenu("Modify");
	private JMenuItem menuitemEditGraph = new JMenuItem("Graph");
	private JMenuItem menuitemEditPath = new JMenuItem("Paths");
	private JMenuItem menuitemEditResult = new JMenuItem("Result");
	
	
	// Constructor and public stuff
	
	public VistaPrincipal(CtrlDominio cd) {
		super(cd);
		initComponents();
		this.setEnabled(true);
		this.pack();
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing");
				changePanel(Panels.Exit);
			}
		});
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
		
		menuitemFileLoadResult.addActionListener
		(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				changePanel(Panels.LoadResult);
			}
		});
		
		menuitemNuevaBusqueda.addActionListener
		(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				changePanel(Panels.NuevaBusqueda);
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
		this.setSize(600, 400);
		this.setMinimumSize(this.getSize());
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//set the first contentPanel
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.add(content);
		contentPane.setBackground(Color.blue);
	}
	
	private void initMenuBar() {
		
		menuFile.add(menuitemFileNewGraph);
		menuFile.add(menuitemFileLoadGraf);
		menuFile.add(menuitemFileImportGraf);
		menuFile.add(menuitemFileLoadResult);
		menuFile.add(menuitemFileExit);
		menuFile.add(menuitemNuevaBusqueda);
		
		menuEditModify.add(menuitemEditGraph);
		menuEditModify.add(menuitemEditPath);
		menuEditModify.add(menuitemEditResult);
		
		menuEdit.add(menuEditModify);
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		
		this.setJMenuBar(menuBar);
	}
	
	// Stuff
	
	void changePanel(Panels p) {
		System.out.println("Cambiando a panel " + p.toString());
		nextPanel = p;
		try {
			AbstractPanel currentPanel = (AbstractPanel) this.getContentPane().getComponent(0);
			currentPanel.close();
		}
		catch (Exception e) {
			continueAction();
		}
	}
	
	void continueAction() {
		this.getContentPane().removeAll();
		switch (nextPanel) {
			case ModificaGraph:
				this.getContentPane().add(modificaGraph);
				break;
			case Test:
				this.getContentPane().add(content);
				break;
			case LoadResult:
				this.getContentPane().add(loadResult);
				break;
			case NuevaBusqueda:
				this.getContentPane().add(nuevaBusqueda);
				break;
			case PanelMostrarResultado:
				this.getContentPane().add(panelMostrarResultado);
				break;
			case Exit:
				dispose();
				System.exit(0);
			default:
				throw new RuntimeException("Ese panel no existe h3h3h3h3h3h3h3h3h3h3h3h3h3h3h3");
		}
		this.pack();
		this.repaint();
	}
	
	void setEnabledPrincipal(Boolean b) {
		System.out.println("lalala");
		menuFile.setEnabled(b);
		menuEdit.setEnabled(b);
	}

}
