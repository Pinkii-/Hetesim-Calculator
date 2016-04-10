package Persistencia;

import java.io.Serializable;

//lel

@SuppressWarnings("serial")
public class Resultado implements Cloneable, Serializable {
		private String idResultado;
		
		public Resultado () {
			setIdResultado("999");
		}
		public Resultado clone() throws CloneNotSupportedException {
			Resultado obj = null;
			obj = (Resultado) super.clone();
			return obj;
		}
		public String getIdResultado() {
			return idResultado;
		}
		public void setIdResultado(String idResultado) {
			this.idResultado = idResultado;
		}
	}