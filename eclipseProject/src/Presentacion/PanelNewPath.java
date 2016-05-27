package Presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelNewPath extends AbstractPanel { //Abstract
//	public PanelNewPath() {
//	}

	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JTextField textField;
	
	PanelNewPath(VistaAbstracta vp) {
		super(vp);
	}
	
	public void init() {
//	PanelNewPath() {
		this.removeAll();
		this.setSize(vp.getSize());
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
		

		Component horizontalGlue = Box.createHorizontalGlue();
		panelNameDescription.add(horizontalGlue);
		
		JLabel lblNewLabel = new JLabel("Name");
		panelNameDescription.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("Name of the path");
		panelNameDescription.add(textFieldName);
		textFieldName.setColumns(10);
		
		panelNameDescription.add(horizontalGlue);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		panelNameDescription.add(lblNewLabel_1);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setToolTipText("Info about the path");
		panelNameDescription.add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		panelNameDescription.add(horizontalGlue);
		
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2);
		
		JPanel panelContent = new JPanel();
		add(panelContent);
		panelContent.setLayout(new GridLayout(5, 3, 0, 5));
		
		JButton buttonAuthor = new RoundButton("Author");
		buttonAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+'A');
			}
		});
		buttonAuthor.setBackground(new Color(230, 230, 250));
//			buttonAuthor.setBackground(bg);
		panelContent.add(buttonAuthor);
		
		JLabel label = new JLabel("");
		panelContent.add(label);
		
		JButton buttonConf = new RoundButton("Conference");
		panelContent.add(buttonConf);
		
		
		JLabel label_1 = new JLabel("");
		panelContent.add(label_1);
		
		JButton buttonPaper = new RoundButton("Paper");
		buttonPaper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelContent.add(buttonPaper);
		
		JLabel label_2 = new JLabel("");
		panelContent.add(label_2);
		
		
		JLabel label_3 = new JLabel("");
		panelContent.add(label_3);
		
		JButton buttonTerm = new RoundButton("Term");
		buttonTerm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
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
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_10 = new JLabel("");
		panelContent.add(label_10);
		
		Component verticalGlue = Box.createVerticalGlue();
		add(verticalGlue);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		add(verticalStrut_3);
		
		JPanel panelSaveCancel = new JPanel();
		add(panelSaveCancel);
		panelSaveCancel.setLayout(new BoxLayout(panelSaveCancel, BoxLayout.X_AXIS));
		
		JButton btnSave = new JButton("Save and Return");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSaveCancel.add(btnSave);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSaveCancel.add(horizontalStrut);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSaveCancel.add(btnCancel);		
	}

//	@Override
	int closeIt() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub

	}

}
