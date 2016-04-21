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

	private Path pathsDirectory; 

	public LoadStorePath(String pathsDirectory) throws NotDirectoryException {
		this.setpathsDirectory(pathsDirectory);
	}
	public String getpathsDirectory() {
		return pathsDirectory.toString();
	}
	public void setpathsDirectory(String pathsDirectory) throws NotDirectoryException {
		Path p = Paths.get(pathsDirectory);
		if (Files.exists(p))
				this.pathsDirectory = Paths.get(pathsDirectory);
		else throw new NotDirectoryException("No existe el directorio");
	}
	
	public void storePath(Dominio.Path p) throws FileNotFoundException, IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(pathsDirectory.resolve(p.getNom()+".ser").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			ObjectOutput.writeObject(p);
			ObjectOutput.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("Path absoluto del path:"+pathsDirectory.resolve(p.getNom()+".ser").toString());
			System.out.println("No se puede guardar el Path");
		}

	}
	
	public void deletePath(String nomPath) throws ClassNotFoundException, IOException {
		/*Dominio.Path p = new Dominio.Path();
		p = loadPath(nomPath);*/
		File file = new File(pathsDirectory.resolve(nomPath+".ser").toString());
		if(!file.delete())
			System.out.println("No se ha podido borrar el Path");
		//...
	}

	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(pathsDirectory.resolve(nomPath+".ser").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Dominio.Path)aux;
		}
		catch(FileNotFoundException fnfe){
			if (Files.notExists(pathsDirectory.resolve(nomPath+".ser"))) {
				System.out.println(pathsDirectory.resolve(nomPath+".ser").toString());
				System.out.println("No se ha podido cargar el Path");
			}
			return null;
		}

	}
	
	public ArrayList<Dominio.Path> loadAllPaths () throws ClassNotFoundException {
		ArrayList<Dominio.Path> Paths = new ArrayList<Dominio.Path>();   
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathsDirectory)) {
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
