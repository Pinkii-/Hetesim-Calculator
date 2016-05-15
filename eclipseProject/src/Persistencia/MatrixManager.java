package Persistencia;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Dominio.Matrix;

public class MatrixManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private Path fileDirectory;
	
	public static Matrix matrixDeepCopy(Matrix m) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(m);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Matrix)ois.readObject();
		}catch (NotSerializableException theProblem) {
			System.out.println("El objeto Matrix no es serializable");
			return null;
		} catch (IOException e) {
			System.out.println("Se ha producido un problema con la operación de E/S");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("No hay definición para la clase especificada");
			return null;
		} catch (Exception e) {
			System.out.println("Ha habido algun problema con la funcion de deepCopy");
			return null;
		}
	}
	
	private static void checkSubdirectory(Path p) {
		File f = new File(p.toString());
		f.setReadable(true);
		f.setWritable(true);
		if (!f.exists()) {
			try {
				f.mkdirs();
			}
			catch(SecurityException se) {
				System.out.println("No existe y no se puede crear el directorio");
			}
		}
	}
	public MatrixManager(String path) {
		Path p = Paths.get(path);
		checkSubdirectory(p);
		this.setFileDirectory(path);
	}
	
	public void setFileDirectory(String fileDirectory) {
		Path p = Paths.get(fileDirectory);
		checkSubdirectory(p);
		this.fileDirectory = Paths.get(fileDirectory);
	}
	
	public String getFileDirectory() {
		return fileDirectory.toString();
	}
	
	public void storeMatrix(Matrix m) throws IOException {
		try {
			FileOutputStream FileOutput = new FileOutputStream(fileDirectory.resolve(m.getIdResult()+".Matrix").toString());
			ObjectOutputStream ObjectOutput = new ObjectOutputStream(FileOutput);
			ObjectOutput.writeObject(matrixDeepCopy(m));
			ObjectOutput.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("Path absoluto fichero:"+fileDirectory.resolve(m.getIdResult()+".Matrix").toString());
			System.out.println("No se puede guardar la Matriz");
		}
		
	}
	
	public Matrix loadMatrix(String idMatrix ) throws IOException, ClassNotFoundException {
		try {
			FileInputStream FileInput = new FileInputStream(fileDirectory.resolve(idMatrix+".Matrix").toString());
			ObjectInputStream ObjectInput = new ObjectInputStream(FileInput);
			Object aux = ObjectInput.readObject();
			ObjectInput.close();
			return (Matrix)aux;
		}
		
		catch(FileNotFoundException fnfe){
			if (Files.notExists(fileDirectory.resolve(idMatrix+".Matrix"))) {
				System.out.println("Path absoluto fichero: "+ fileDirectory.resolve(idMatrix+".Matrix").toString());
				System.out.println("No se puede cargar la Matriz");
			}
			return null;
		}
		
	}
	
	public void deleteMatrix(String idMatrix) throws Exception {
		File file = new File(fileDirectory.resolve(idMatrix+".Matrix").toString());
		if (!file.exists()) throw new Exception("No existe la Matriz con ese id");
		if (!file.delete()) throw new Exception("No se ha podido eliminar la Matriz");
	}
	
	public ArrayList<Matrix> LoadAllMatrices () throws ClassNotFoundException {
		ArrayList<Matrix> Matrices = new ArrayList<Matrix>();   
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(fileDirectory)) {
	            for (Path path : directoryStream) {
	            	if (path.toString().indexOf(".Matrix") != -1) {
	            		String s = path.getFileName().toString();
	            		s = s.replace(".Matrix", "");
	            		Matrices.add(loadMatrix(s));
	            	}
	            }
	            return Matrices;
	        } catch (IOException ex) {
	        	System.out.println("No se puede iterar por las Matrices");
	        	return null;
	        }
	}
}
