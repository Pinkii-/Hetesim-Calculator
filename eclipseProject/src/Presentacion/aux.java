package Presentacion;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class aux extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public aux() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		JLabel tittleLabel = new JLabel("New Path");
		tittleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		tittleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(tittleLabel);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1);
		
		JPanel panelNameDescription = new JPanel();
		panelNameDescription.setAlignmentY(Component.TOP_ALIGNMENT);
		add(panelNameDescription);
		panelNameDescription.setLayout(new BoxLayout(panelNameDescription, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Name");
		panelNameDescription.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("Name of the path");
		panelNameDescription.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		panelNameDescription.add(lblNewLabel_1);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setToolTipText("Info about the path");
		panelNameDescription.add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2);
		
		JPanel panelContent = new JPanel();
		add(panelContent);
		panelContent.setLayout(new GridLayout(5, 3, 0, 5));
		
		JButton buttonAuthor = new RoundButton("Author");
		buttonAuthor.setBackground(new Color(230, 230, 250));
//		buttonAuthor.setBackground(bg);
		panelContent.add(buttonAuthor);
		
		JLabel label = new JLabel("");
		panelContent.add(label);
		
		JButton buttonConf = new RoundButton("Conference");
		panelContent.add(buttonConf);
		
		
		JLabel label_1 = new JLabel("");
		panelContent.add(label_1);
		
		JButton buttonPaper = new RoundButton("Paper");
		panelContent.add(buttonPaper);
		
		JLabel label_2 = new JLabel("");
		panelContent.add(label_2);
		
		
		JLabel label_3 = new JLabel("");
		panelContent.add(label_3);
		
		JButton buttonTerm = new RoundButton("Term");
		panelContent.add(buttonTerm);
		
		JLabel label_4 = new JLabel("");
		panelContent.add(label_4);
		
		
		JLabel label_5 = new JLabel("");
		panelContent.add(label_5);
		
		JLabel label_6 = new JLabel("");
		panelContent.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panelContent.add(label_7);
		
		
		
		JLabel label_8 = new JLabel("");
		panelContent.add(label_8);
		
		JPanel panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label_9 = new JLabel("Content");
		panel.add(label_9);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_10 = new JLabel("");
		panelContent.add(label_10);
		
		
		
		

	}

}
