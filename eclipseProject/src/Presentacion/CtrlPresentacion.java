package Presentacion;

import Dominio.CtrlDominio;

public class CtrlPresentacion {
	
	CtrlDominio ctrlDominio = new CtrlDominio();
	VistaPrincipal vp = new VistaPrincipal(ctrlDominio);
}
