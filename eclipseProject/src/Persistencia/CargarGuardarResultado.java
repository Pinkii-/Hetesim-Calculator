
package Persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Clase CargarGuardarResultado:


@SuppressWarnings("serial")
public class CargarGuardarResultado implements Serializable{
	
	private Path fileDirectory;
	
	public CargarGuardarResultado() {}
	
	public CargarGuardarResultado(String path) {
		this.setFileDirectory(path);
	}
	public String getFileDirectory() {
		return fileDirectory.toString();
	}
	public void setFileDirectory(String fileDirectory) {
		/*Chequiar si existe*/
		this.fileDirectory = Paths.get(fileDirectory);
	}
	
	public void guardaResultado(Resultado r) throws FileNotFoundException, CloneNotSupportedException, IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(fileDirectory.resolve(r.getIdResultado()+".ser").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			Resultado res = new Resultado();
			res = r.deepClone(); 
			ObjectOutput.writeObject(res);
			ObjectOutput.close();
			System.out.println("Path absoluto fichero:"+fileDirectory.resolve(r.getIdResultado()+".ser").toString());

		}
		//No existe el directorio: ...
		//No existe el Resultado: lo guarda.
		//El Resultado r ya ha sido guardado: Sobreescribe.
		catch(FileNotFoundException fnfe){
			/*if (Files.notExists(fileDirectory)) {
				System.out.println(fileDirectory.toString());
				System.out.println("El Directorio no existe");
			}
			Mirar en el constructor/setter.
			*/
		}
		
	}
	
	public Resultado cargaResultado(String idResultado) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(fileDirectory.resolve(idResultado+".ser").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Resultado)aux;
		}
		//No existe el directorio: ...
		//No existe el Resultado: Error.
		catch(FileNotFoundException fnfe){
			/*
			if (Files.notExists(fileDirectory)) {
				System.out.println(fileDirectory.toString());
				System.out.println("El Directorio no existe");
			}
			Mirar en el constructor/setter*/
			if (Files.notExists(fileDirectory.resolve(idResultado+".ser"))) {
				System.out.println(fileDirectory.resolve(idResultado+".ser").toString());
				System.out.println("El Resultado no existe o algo pasa con los permisos ekisde");
			}
			return null;
		}
		
	}

}
