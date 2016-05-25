package Presentacion;

import javax.swing.JFrame;

import Dominio.CtrlDominio;

@SuppressWarnings("serial")
abstract public class VistaAbstracta extends JFrame {
	protected CtrlDominio cd;
	
	VistaAbstracta(CtrlDominio cd) {
		this.cd = cd;
	}
	
	abstract void continueAction();
//	abstract public void setEnabledEverything(Boolean b);
	public CtrlDominio getCtrlDominio() {
		return cd;
	}
}
