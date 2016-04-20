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

import Dominio.Result;

/**
*
* @author Albert
*/

@SuppressWarnings("serial")
public class LoadStoreResult implements Serializable{
	
	private Path fileDirectory;
	
	public LoadStoreResult(String path) throws NotDirectoryException {
		this.setFileDirectory(path);
	}
	
	public void setFileDirectory(String fileDirectory) throws NotDirectoryException {
		Path p = Paths.get(fileDirectory);
		if (Files.exists(p))
				this.fileDirectory = Paths.get(fileDirectory);
		else throw new NotDirectoryException("No existe el directorio");
	}
	
	public String getFileDirectory() {
		return fileDirectory.toString();
	}
	
	public void storeResult(Result r) throws FileNotFoundException, IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(fileDirectory.resolve(r.getIdResult()+".Result").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			ObjectOutput.writeObject(r);
			ObjectOutput.close();
		}
		//No existe el directorio: ...
		//No existe el Resultado: lo guarda.
		//El Resultado r ya ha sido guardado: Sobreescribe.
		catch(FileNotFoundException fnfe){
			System.out.println("Path absoluto fichero:"+fileDirectory.resolve(r.getIdResult()+".Result").toString());
		}
		
	}
	
	public Result loadResult(String idResult) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(fileDirectory.resolve(idResult+".Result").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Result)aux;
		}
		
		catch(FileNotFoundException fnfe){
			if (Files.notExists(fileDirectory.resolve(idResult+".Result"))) {
				System.out.println(fileDirectory.resolve(idResult+".Result").toString());
			}
			return null;
		}
		
	}
	public ArrayList<Result> LoadAllResults () throws ClassNotFoundException {
		ArrayList<Result> Results = new ArrayList<Result>();   
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(fileDirectory)) {
	            for (Path path : directoryStream) {
	            	if (path.toString().indexOf(".Result") != -1) {
	            		String s = path.getFileName().toString();
	            		s.replace(".Result", "");
	            		Results.add(loadResult(s));
	            	}
	            }
	            return Results;
	        } catch (IOException ex) {
	        	return null;
	        }
	}

}
