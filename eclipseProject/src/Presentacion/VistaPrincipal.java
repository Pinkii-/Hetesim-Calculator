package Presentacion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.CtrlDominio;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VistaPrincipal extends VistaAbstracta{
	
	private String APPLICATION_NAME = "ola k ase lol";
	
	// Componentes UI
//	private JFrame this = new JFrame(APPLICATION_NAME);
	
	// Panels;
	enum Panels {ModificaGraph, LoadResult, PanelMostrarResultado, NuevaBusqueda, Test, Exit};
	private Panels nextPanel, currentPanel;
	
	private JPanel content = new JPanel();
	PanelModificaGraph modificaGraph = new PanelModificaGraph(this);
	PanelLoadResult loadResult = new PanelLoadResult(this);
	PanelNuevaBusqueda nuevaBusqueda = new PanelNuevaBusqueda(this);
	PanelMostrarResultado panelMostrarResultado = new PanelMostrarResultado(this);

	// Menus
	private JMenuBar menuBar = new JMenuBar();
	
	  	// File
	
	// Graph
	private JMenu menuGraph = new JMenu("Graph");	
	private JMenuItem menuitemGraphNew = new JMenuItem("New");
	private JMenuItem menuitemGraphImport = new JMenuItem("Import");
	private JMenuItem menuitemGraphLoad = new JMenuItem("Load");
	private JMenuItem menuitemGraphModify = new JMenuItem("Modify Current");
	
	// Paths
	private JMenu menuPath = new JMenu("Path");
	private JMenuItem menuitemPathNew = new JMenuItem("New");
	private JMenuItem menuitemPathShow = new JMenuItem("Show and Modify"); // Hacer vista (listar, modificar, eliminar)
	
	// Search and Results
	private JMenu menuSearch = new JMenu("Search and Results");
	private JMenuItem menuitemSearchNew = new JMenuItem("New Search");
	private JMenuItem menuitemResultShow = new JMenuItem("Show Results");
	
	private JMenu menuFile = new JMenu("File");
	
	private JMenuItem menuitemFileExit = new JMenuItem("Exit");
	
	
	// Constructor and public stuff
	
	public VistaPrincipal(CtrlDominio cdd) {
		super(cdd);
		if (cdd == null) System.out.println("mal");
		else System.out.println("bien");
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
		
		VistaPrincipal p = this;
	
		menuitemFileExit.addActionListener
		( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changePanel(Panels.Exit);
				
			}
		});
		
		menuitemGraphModify.addActionListener
	    (new ActionListener() {
	      public void actionPerformed (ActionEvent event) {
	        changePanel(Panels.ModificaGraph);
	      }
	    });
		
		menuitemResultShow.addActionListener
		(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				changePanel(Panels.LoadResult);
			}
		});
		
		menuitemSearchNew.addActionListener
		(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				changePanel(Panels.NuevaBusqueda);
			}
		});
		
		menuitemGraphImport.addActionListener
		(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				try {
					cd.importGraph(System.getProperty("user.dir")+"/../DBLP_four_area/");
					VistaDialog.setDialog("", "Grafo importado correctamente", new String[] {"Continue"}, VistaDialog.DialogType.INFORMATION_MESSAGE);
					if (currentPanel != null) p.changePanel(currentPanel);
				}
				catch (Exception e) {
					VistaDialog.setDialog("", "No se ha podido importar el grafo.\n(El diretorio de la abse de datos no está en su sitio) ", new String[]{"Continue"}, VistaDialog.DialogType.ERROR_MESSAGE);
				}
//				VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
			}
		});
		
		menuitemGraphLoad.addActionListener
		(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f  = new JFileChooser();
				f.setCurrentDirectory(new File(System.getProperty("user.dir")));
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				f.showOpenDialog(f);
				try {
					String s = f.getSelectedFile().getAbsolutePath();
					cd.loadGraph(s);
				}
				catch (NullPointerException exception) {}
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
		
		menuFile.add(this.menuitemFileExit);

		menuGraph.add(this.menuitemGraphNew);
		menuGraph.add(this.menuitemGraphImport);
		menuGraph.add(this.menuitemGraphLoad);
		menuGraph.add(this.menuitemGraphModify);
		
		menuPath.add(this.menuitemPathNew);
		menuPath.add(this.menuitemPathShow);
		
		menuSearch.add(this.menuitemSearchNew);
		menuSearch.add(this.menuitemResultShow);
		
		menuBar.add(this.menuFile);
		menuBar.add(this.menuGraph);
		menuBar.add(this.menuPath);
		menuBar.add(this.menuSearch);

		this.setJMenuBar(menuBar);
	}
	
	// Stuff
	
	void changePanel(Panels p) {
		System.out.println("Cambiando a panel " + p.toString());
		nextPanel = p;
		try {
			AbstractPanel currentPanel = (AbstractPanel) this.getContentPane().getComponent(0);
			if (0 == currentPanel.close()) {
				continueAction();
			}
		}
		catch (Exception e) {
			continueAction();
		}
	}
	
	/**
	 * No llameis a esto pl0x lo llama el AbstractPanel.
	 * @see Presentacion.VistaAbstracta#continueAction()
	 */
	
	@Deprecated
	void continueAction() {
		this.getContentPane().removeAll();
		currentPanel = nextPanel;
		switch (nextPanel) {
			case ModificaGraph:
				modificaGraph.init();
				this.getContentPane().add(modificaGraph);
				break;
			case Test:
				this.getContentPane().add(content);
				break;
			case LoadResult:
				loadResult.init();
				this.getContentPane().add(loadResult);
				break;
			case NuevaBusqueda:
				nuevaBusqueda.init();
				this.getContentPane().add(nuevaBusqueda);
				break;
			case PanelMostrarResultado:
				panelMostrarResultado.init();
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
//		menuFile.setEnabled(b);
//		menuEdit.setEnabled(b);
	}

}
