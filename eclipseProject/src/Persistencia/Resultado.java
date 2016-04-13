package Persistencia;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//lel

@SuppressWarnings("serial")
public class Resultado implements Cloneable, Serializable {
		private String idResultado;
		
		
		/*Necesitaria estos métodos en Result.java
		 * y que implementara Cloneable, Serializable
		 */
		//---------------------------------------------------------------------------------------
		public Resultado () {}
		
		public Resultado deepClone() {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(this);

				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);
				return (Resultado) ois.readObject();
			} catch (IOException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}
		}
		public String getIdResultado() {
			return idResultado;
		}
		//----------------------------------------------------------------------------------------
		/*
		public Resultado clone() throws CloneNotSupportedException {
			Resultado obj = null;
			obj = (Resultado) super.clone();
			return obj;
		}
		*/
		
		public void setIdResultado(String idResultado) {
			this.idResultado = idResultado;
		}
	}