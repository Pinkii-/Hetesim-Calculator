package Presentacion;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelNuevaBusqueda() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		//KOMO AGO ESTO XDXD
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setSelectedItem("LEMAO");
		comboBox.setSize(new Dimension(30,30));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 50, SpringLayout.WEST, this);
		add(comboBox);

	}
}