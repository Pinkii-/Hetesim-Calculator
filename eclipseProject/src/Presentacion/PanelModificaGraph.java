package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class PanelModificaGraph extends AbstractPanel {

	private JButton buttonAddNode = new JButton("Add New Node");
	
	private JPanel displayNode;
	private JComboBox<String> comboBoxTypeOfNode;
	private JComboBox<String> findingNode; // JComboBox que te muestra solo los nodos posibles segun lo que has escrito.
	
	public PanelModificaGraph(VistaAbstracta vp) {
		super(vp);
		setBackground(Color.green);
		
		initComponents();
		asignListeners();
		
		
	}
	
	private void initComponents() {
		// DisplayNodes
		String[] typesOfNodes = {"Paper", "Conferencia", "Terme", "Autor"};
		comboBoxTypeOfNode = new JComboBox<String>(typesOfNodes);
		comboBoxTypeOfNode.setSelectedIndex(-1);
		comboBoxTypeOfNode.setMaximumSize(buttonAddNode.getPreferredSize());
		
		findingNode = new JComboBox<String>(typesOfNodes);
			//equisde no se como hacer lo que quiero que haga
		findingNode.setMaximumSize(new Dimension(buttonAddNode.getMinimumSize().width*2, buttonAddNode.getMinimumSize().height));
		findingNode.setMinimumSize(new Dimension(buttonAddNode.getMinimumSize().width*2, buttonAddNode.getMinimumSize().height));
		
		displayNode = new JPanel();
		displayNode.setLayout(new BoxLayout(displayNode, BoxLayout.X_AXIS));
		displayNode.add(Box.createHorizontalGlue());
		displayNode.add(comboBoxTypeOfNode);
		displayNode.add(Box.createRigidArea(new Dimension(5,0)));
		displayNode.add(findingNode);
		displayNode.add(Box.createHorizontalGlue());
		
		// next
		
		
		
		BoxLayout main = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(main);
		add(buttonAddNode);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(displayNode);
	}
	
	private void asignListeners() {
		PanelModificaGraph aux = this;
		buttonAddNode.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aux.addVista(PanelModificaGraph.class, true); // Cambiar al panel que agrega nodos
			}
		});
		comboBoxTypeOfNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				cb.getSelectedIndex(); // Get selected index to call to the Dominio and get the information of the node
				
			}
		});
	}

	@Override
	public int closeIt() {
		// Preguntar si estas seguro de que te pueden cerrar desde un sitio externo
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		buttonAddNode.setEnabled(b);
		try { // Solo para el test. Luego lo tengo que quitar cuando ya no sea una vista abstracta y sea una vista principal
			VistaPrincipal v = (VistaPrincipal) vp;
			v.setEnabledPrincipal(b);
		}
		catch (ClassCastException e) {
			
		}
	}

}
