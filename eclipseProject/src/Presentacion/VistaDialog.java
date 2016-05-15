package Presentacion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaDialog {
	
	public enum DialogType {
		ERROR_MESSAGE(JOptionPane.ERROR_MESSAGE),
	    INFORMATION_MESSAGE(JOptionPane.INFORMATION_MESSAGE),
	    WARNING_MESSAGE(JOptionPane.WARNING_MESSAGE),
	    QUESTION_MESSAGE(JOptionPane.QUESTION_MESSAGE), 
	    PLAIN_MESSAGE(JOptionPane.PLAIN_MESSAGE);
		
		private int value;
		private DialogType(int value) {
			this.value = value;
		}
	}

	public static int setDialog(String strTitulo, String strTexto, String[] strBotones, DialogType iTipo) {

		// Crea y viisualiza el dialogo
		JOptionPane optionPane = new JOptionPane(strTexto,iTipo.value);
		optionPane.setOptions(strBotones);
		JDialog dialogOptionPane = optionPane.createDialog(new JFrame(),strTitulo);
		dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialogOptionPane.pack();
		dialogOptionPane.setVisible(true);

		// Captura la opcion elegida
		String vsel = (String) optionPane.getValue();
		int isel;
		for (isel = 0;
				isel < strBotones.length && !strBotones[isel].equals(vsel); isel++);
		return isel;
	}
}
