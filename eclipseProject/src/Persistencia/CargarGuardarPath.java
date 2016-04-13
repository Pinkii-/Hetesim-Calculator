package Persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import Dominio.*;

//Clase CargarGuardarPath:
/*
 * Se necesitaría deepCopy en Path, o otra manera de hacer una copia bien hecha desde este clase.
 */

@SuppressWarnings("serial")
public class CargarGuardarPath implements Serializable{

	private java.nio.file.Path PathsDirectory; //Carpeta donde estan todos los paths.

	public CargarGuardarPath() {}

	public CargarGuardarPath(String PathsDirectory) {
		this.setPathsDirectory(PathsDirectory);
	}
	public String getPathsDirectory() {
		return PathsDirectory.toString();
	}
	public void setPathsDirectory(String PathsDirectory) {
		//Chequiar si existe
		this.PathsDirectory = Paths.get(PathsDirectory);
	}

	public void guardaPath(Path p) throws FileNotFoundException, CloneNotSupportedException, IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(PathsDirectory.resolve(p.getNom()+".ser").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			Path path = new Path();
			path = (Path) p.deepClone();
			ObjectOutput.writeObject(path);
			ObjectOutput.close();
			System.out.println("Path absoluto del path:"+PathsDirectory.resolve(p.getNom()+".ser").toString());

		}
		//No existe el Path: lo guarda.
		//El Path r ya ha sido guardado: Sobreescribe.
		catch(FileNotFoundException fnfe){
			//Esto no puede pasar.
			//Si el PathsDirectory no existe el error saltará en el constructor o setter
		}

	}

	public Path cargaPath(String nomPath) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(PathsDirectory.resolve(nomPath+".ser").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Path)aux;
		}
		//No existe el directorio: ...
		//No existe el Resultado: Error.
		catch(FileNotFoundException fnfe){
			/*
			if (Files.notExists(fileDirectory)) {
				System.out.println(fileDirectory.toString());
				System.out.println("El Directorio no existe");
			}Mirar en el constructor/setter*/
			if (Files.notExists(PathsDirectory.resolve(nomPath+".ser"))) {
				System.out.println(PathsDirectory.resolve(nomPath+".ser").toString());
				System.out.println("El Path no existe o algo pasa con los permisos ekisde");
			}
			return null;
		}

	}

}
