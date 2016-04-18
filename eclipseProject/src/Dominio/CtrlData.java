

package Dominio;

import java.util.ArrayList;
import Persistencia.CargarGuardarPath;
import Persistencia.CargarGuardarResultado;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
*
* @author Albert
*/

public class CtrlData {
	
	private CtrlDataGraph CG;
	private CargarGuardarResultado CGR;
	private CargarGuardarPath CGP;
	
	private final Path PathToGrafsAndResults = Paths.get(""); //Path del directorio donde siempre se guardan las carpetas: (nombregrafo/grafo y sus resultados)
	private final Path PathToPaths = Paths.get("");			  //Path del directorio donde siempre guardamos los Paths.
	private Pair<Graf,ArrayList<Result>> GraphAndResults;
	private ArrayList<Dominio.Path> AllPaths;
	
	private static Object deepCopy(Object o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	private void grafAndResultsWalkin (Path dir, String idGraf) throws ClassNotFoundException {
		 try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            		for (Path path : directoryStream) {
            			if (path.getFileName().toString() != idGraf) 
            			GraphAndResults.second.add(CGR.cargaResultado(path.getFileName().toString()));
            		}
        	} catch (IOException ex) {/*ayy ayy lemao lemao*/}
	}
	
	private void pathsWalkin() throws IOException, ClassNotFoundException {
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(PathToPaths)) {
			for (Path path : directoryStream) {
				AllPaths.add(CGP.cargaPath(path.getFileName().toString()));
			}
		}
	}

	public  Pair<Graf,ArrayList<Result>> loadGraphAndResults(String idGraf) throws ClassNotFoundException {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(idGraf); //p ahora tendria que ser el directorio idGraf que contiene su graf y sus results.
		
		CG = new CtrlDataGraph(p.toString());
		CGR = new CargarGuardarResultado(p.toString());
		
		Graf g = new Graf();
		ArrayList<Result> results = new ArrayList<Result>();
		GraphAndResults = new Pair<Graf,ArrayList<Result>>(g, results);
		
		grafAndResultsWalkin(p,idGraf);
		GraphAndResults.first = CG.loadGraph(idGraf);
		return GraphAndResults;
	}
	
	public ArrayList<Dominio.Path> loadAllPaths() throws ClassNotFoundException, IOException {
		AllPaths = new ArrayList<Dominio.Path>();
		pathsWalkin();
		return AllPaths;
	}
	
	public void storeResult(Result r) throws FileNotFoundException, CloneNotSupportedException, IOException{
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(r.getIdGraph());
		Result res = new Result(null, null, null, null); //??????????????
		res = (Result)CtrlData.deepCopy(r);
		CGR = new CargarGuardarResultado(p.toString());
		CGR.guardaResultado(res);
	}
	
	public void storePath(Dominio.Path p) throws FileNotFoundException, CloneNotSupportedException, IOException {
		Dominio.Path pa = new Dominio.Path();
		pa = (Dominio.Path) CtrlData.deepCopy(p);
		CGP.guardaPath(pa);
	}
	
	public Result loadResult(String idResultado) {
		return null;
	}
	
	public Path loadPath(String nomPath) {
		return null;
	}
}
