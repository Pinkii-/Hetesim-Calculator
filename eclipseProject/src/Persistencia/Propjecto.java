package Persistencia;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Propjecto {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException  {
		String idResultado;
		
		//Path directorio. 
		//ASIN como está lo guardaría en la carpeta del proyecto.
		//De momento solo puedo guardar en /home/usuario o la carpeta del proyecto por permisos o algo xd.
		CargarGuardarResultado cgr = new CargarGuardarResultado(""); 
		Resultado r = new Resultado();
		
		
		r.setIdResultado("Resultado1");
		idResultado = r.getIdResultado();
		System.out.println("Resultado antes de guardar:"+r.getIdResultado());
		
		cgr.guardaResultado(r);
		r = cgr.cargaResultado(idResultado);
		
		System.out.println("Resultado despues de guardar:"+r.getIdResultado());
		
	}
}

