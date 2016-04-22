

package Dominio;

import java.util.ArrayList;

import Persistencia.LoadStorePath;
import Persistencia.LoadStoreResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
*
* @author Albert Lopez Alcacer
*/

public class CtrlData {
	
	private CtrlDataGraph cg;
	private LoadStoreResult lsr;
	private LoadStorePath lsp;
	
	private Path pathToGrafsAndResults; //Path del directorio donde siempre se guardan las carpetas: (nombregrafo/grafo y sus resultados)
	private Path pathToPaths;		  //Path del directorio donde siempre guardamos los Paths.
	private Pair<Graf,ArrayList<Result>> graphAndResults;
	private ArrayList<Dominio.Path> allPaths;
	public CtrlData(String s) {}
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
		
		this.pathToGrafsAndResults = GrafsAndResults;
		this.pathToPaths = Paths;	

	}
	static Object deepCopy(Object o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}catch (NotSerializableException theProblem) {
			System.out.println("El objeto no es serializable");
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} 
	}


	public  Pair<Graf,ArrayList<Result>> loadgraphAndResults(String idGraf) throws ClassNotFoundException, NotDirectoryException {
		Path p = Paths.get(pathToGrafsAndResults.toString());
		p = p.resolve(idGraf); //p ahora tendria que ser el directorio nomgraf que contiene su graf y sus results.
		
		cg = new CtrlDataGraph(p.toString());
		lsr = new LoadStoreResult(p.toString());
		
		Graf g = new Graf();
		ArrayList<Result> results = new ArrayList<Result>();
		graphAndResults = new Pair<Graf,ArrayList<Result>>(g, results);
		
		graphAndResults.second = lsr.LoadAllResults();
		graphAndResults.first = cg.loadGraph(idGraf);
		return graphAndResults;
	}
	
	public ArrayList<Dominio.Path> loadallPaths() throws ClassNotFoundException, IOException {
		allPaths = new ArrayList<Dominio.Path>();
		lsp = new LoadStorePath(pathToPaths.toString());
		allPaths = lsp.loadAllPaths();
		return allPaths;
	}
	
	public void storeResult(Result r) throws FileNotFoundException, CloneNotSupportedException, IOException{
		Path p = Paths.get(pathToGrafsAndResults.toString());
		p = p.resolve(r.getIdGraf());
		Result res; 
		res = (Result)CtrlData.deepCopy(r);
		lsr = new LoadStoreResult(p.toString());
		lsr.storeResult(res);
	}
	
	public void storePath(Dominio.Path p) throws FileNotFoundException, CloneNotSupportedException, IOException {
		Dominio.Path pa;
		pa = (Dominio.Path) CtrlData.deepCopy(p);
		lsp = new LoadStorePath(pathToPaths.toString());
		if (pa == null) System.out.println("subnulo");
		lsp.storePath(pa);
	}
	
	public void storeGraf(Graf g) {
		Path p = Paths.get(pathToGrafsAndResults.toString());
		Graf gc = (Graf) CtrlData.deepCopy(g);
		p = p.resolve(String.valueOf(g.id));
		cg = new CtrlDataGraph(p.toString());
		cg.saveGraph(gc, gc.nom);
	}
	
	public Result loadResult(String idResult, Integer idGraf) throws FileNotFoundException, ClassNotFoundException, IOException {
		Path p = Paths.get(pathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(idGraf));
		lsr = new LoadStoreResult(p.toString());
		Result r = lsr.loadResult(idResult);
		return r;
	}
	
	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, ClassNotFoundException, IOException {
		Dominio.Path p = new Dominio.Path();
		lsp = new LoadStorePath(pathToPaths.toString());
		p = lsp.loadPath(nomPath);
		return p;
	}
	
	public void deletePath(String nomPath) throws ClassNotFoundException, IOException {
		lsp = new LoadStorePath(pathToPaths.toString());
		lsp.deletePath(nomPath);
	}
	
	public void deleteResult(String idResult, Integer idGraf) throws FileNotFoundException, ClassNotFoundException, IOException {
		Path p = Paths.get(pathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(idGraf));
		lsr = new LoadStoreResult(p.toString());
		lsr.deleteResult(idResult);
	}
}
