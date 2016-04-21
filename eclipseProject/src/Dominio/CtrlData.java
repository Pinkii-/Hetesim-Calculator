

package Dominio;

import java.util.ArrayList;
import Persistencia.LoadStorePath;
import Persistencia.LoadStoreResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
*
* @author Albert
*/

public class CtrlData {
	
	private CtrlDataGraph CG;
	private LoadStoreResult LSR;
	private LoadStorePath LSP;
	
	private Path PathToGrafsAndResults; //Path del directorio donde siempre se guardan las carpetas: (nombregrafo/grafo y sus resultados)
	private Path PathToPaths;		  //Path del directorio donde siempre guardamos los Paths.
	private Pair<Graf,ArrayList<Result>> GraphAndResults;
	private ArrayList<Dominio.Path> AllPaths;
	
	//No se si tendria que hacer esto o por parámetro. 
	public CtrlData() {
		//generar directorio GrafsAndResults
		//generar directorio Paths
		Path cwd = Paths.get(System.getProperty("user.dir"));
		Path Paths = cwd.resolve("Paths");
		File Pathsf = new File(Paths.toString());
		Path GrafsAndResults = cwd.resolve("GrafsAndResults");
		File GrafsAndResultsf = new File(GrafsAndResults.toString());
		if (!Pathsf.exists()) {
			try {
				Pathsf.mkdirs();
			}
			catch(SecurityException se) {
				System.out.println("No se puede crear el directorio con los Paths");
			}
		}
		if (!GrafsAndResultsf.exists()) {
			try {
				GrafsAndResultsf.mkdirs();
			}
			catch(SecurityException se) {
				System.out.println("No se puede crear el directorio GrafAndResults");
			}
		}
		this.PathToGrafsAndResults = GrafsAndResults;
		this.PathToPaths = Paths;		
}
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


	public  Pair<Graf,ArrayList<Result>> loadGraphAndResults(String idGraf) throws ClassNotFoundException, NotDirectoryException {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(idGraf); //p ahora tendria que ser el directorio nomgraf que contiene su graf y sus results.
		
		CG = new CtrlDataGraph(p.toString());
		LSR = new LoadStoreResult(p.toString());
		
		Graf g = new Graf();
		ArrayList<Result> results = new ArrayList<Result>();
		GraphAndResults = new Pair<Graf,ArrayList<Result>>(g, results);
		
		GraphAndResults.second = LSR.LoadAllResults();
		GraphAndResults.first = CG.loadGraph(idGraf);
		return GraphAndResults;
	}
	
	public ArrayList<Dominio.Path> loadAllPaths() throws ClassNotFoundException, IOException {
		AllPaths = new ArrayList<Dominio.Path>();
		LSP = new LoadStorePath(PathToPaths.toString());
		AllPaths = LSP.loadAllPaths();
		return AllPaths;
	}
	
	public void storeResult(Result r) throws FileNotFoundException, CloneNotSupportedException, IOException{
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(r.getIdGraf());
		Result res; 
		res = (Result)CtrlData.deepCopy(r);
		LSR = new LoadStoreResult(p.toString());
		LSR.storeResult(res);
	}
	
	public void storePath(Dominio.Path p) throws FileNotFoundException, CloneNotSupportedException, IOException {
		Dominio.Path pa;
		pa = (Dominio.Path) CtrlData.deepCopy(p);
		LSP = new LoadStorePath(PathToPaths.toString());
		LSP.storePath(pa);
	}
	
	public void storeGraf(Graf g) {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		Graf gc = (Graf) CtrlData.deepCopy(g);
		p = p.resolve(String.valueOf(g.id));
		CG = new CtrlDataGraph(p.toString());
		CG.saveGraph(gc, gc.nom);
	}
	
	public Result loadResult(String idResult, Integer idGraf) throws FileNotFoundException, ClassNotFoundException, IOException {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(idGraf));
		LSR = new LoadStoreResult(p.toString());
		Result r = LSR.loadResult(idResult);
		return r;
	}
	
	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, ClassNotFoundException, IOException {
		Dominio.Path p = new Dominio.Path();
		LSP = new LoadStorePath(PathToPaths.toString());
		p = LSP.loadPath(nomPath);
		return p;
	}
	
	public void deletePath(String nomPath) throws ClassNotFoundException, IOException {
		LSP = new LoadStorePath(PathToPaths.toString());
		LSP.deletePath(nomPath);
	}
	
	public void deleteResult(String idResult, Integer idGraf) throws FileNotFoundException, ClassNotFoundException, IOException {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(idGraf));
		LSR = new LoadStoreResult(p.toString());
		LSR.deleteResult(idResult);
	}
}
