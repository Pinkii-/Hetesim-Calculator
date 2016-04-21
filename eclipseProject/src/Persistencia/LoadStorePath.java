package Persistencia;
import java.io.File;
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

	private Path PathsDirectory; 

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
		}
		catch(FileNotFoundException fnfe){
			System.out.println("Path absoluto del path:"+PathsDirectory.resolve(p.getNom()+".ser").toString());
			System.out.println("No se puede guardar el Path");
		}

	}
	
	public void deletePath(String nomPath) throws ClassNotFoundException, IOException {
		/*Dominio.Path p = new Dominio.Path();
		p = loadPath(nomPath);*/
		File file = new File(PathsDirectory.resolve(nomPath+".ser").toString());
		if(!file.delete())
			System.out.println("No se ha podido borrar el Path");
		//...
	}

	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(PathsDirectory.resolve(nomPath+".ser").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Dominio.Path)aux;
		}
		catch(FileNotFoundException fnfe){
			if (Files.notExists(PathsDirectory.resolve(nomPath+".ser"))) {
				System.out.println(PathsDirectory.resolve(nomPath+".ser").toString());
				System.out.println("No se ha podido cargar el Path");
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
