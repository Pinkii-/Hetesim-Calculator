package Persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
*
* @author Albert
*/

@SuppressWarnings("serial")
public class LoadStorePath implements Serializable{

	private Path PathsDirectory; //Carpeta donde estan todos los paths.

	public LoadStorePath() {}

	public LoadStorePath(String PathsDirectory) throws NotDirectoryException {
		this.setPathsDirectory(PathsDirectory);
	}
	public String getPathsDirectory() {
		return PathsDirectory.toString();
	}
	public void setPathsDirectory(String PathsDirectory) throws NotDirectoryException {
		Path p = Paths.get(PathsDirectory);
		if (Files.exists(p))
				this.PathsDirectory = Paths.get(PathsDirectory);
		else throw new NotDirectoryException("No existe el directorio");
	}

	public void storePath(Dominio.Path p) throws FileNotFoundException, IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(PathsDirectory.resolve(p.getNom()+".ser").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			ObjectOutput.writeObject(p);
			ObjectOutput.close();
			System.out.println("Path absoluto del path:"+PathsDirectory.resolve(p.getNom()+".ser").toString());

		}
		//No existe el Path: lo guarda.
		//El Path r ya ha sido guardado: Sobreescribe.
		catch(FileNotFoundException fnfe){
		}

	}

	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(PathsDirectory.resolve(nomPath+".ser").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Dominio.Path)aux;
		}
		//No existe el directorio: ...
		//No existe el Resultado: Error.
		catch(FileNotFoundException fnfe){
			if (Files.notExists(PathsDirectory.resolve(nomPath+".ser"))) {
				System.out.println(PathsDirectory.resolve(nomPath+".ser").toString());
				System.out.println("El Path no existe o algo pasa con los permisos ekisde");
			}
			return null;
		}

	}
	
	public ArrayList<Dominio.Path> loadAllPaths () throws ClassNotFoundException {
		ArrayList<Dominio.Path> Paths = new ArrayList<Dominio.Path>();   
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(PathsDirectory)) {
	            for (Path path : directoryStream) {
	            		Paths.add(loadPath(path.toString()));
	            	}
	            return Paths;
	    } 
		catch (IOException ex) {
			System.out.println("No se puede iterar por los Paths");
			return null;
		}
	}

}
