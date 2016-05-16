package Presentacion;

import javax.swing.JFrame;

import Dominio.CtrlDominio;

abstract public class VistaAbstracta extends JFrame {
	private CtrlDominio cd;
	
	VistaAbstracta(CtrlDominio cd) {
		this.cd = cd;
	}
	
	abstract void continueAction();
//	abstract public void setEnabledEverything(Boolean b);
	public CtrlDominio getCtrlDominio() {
		return cd;
	}
}
