

package Dominio;

import java.util.ArrayList;
import Persistencia.LoadStorePath;
import Persistencia.LoadStoreResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	private final Path PathToGrafsAndResults = Paths.get(""); //Path del directorio donde siempre se guardan las carpetas: (nombregrafo/grafo y sus resultados)
	private final Path PathToPaths = Paths.get("");			  //Path del directorio donde siempre guardamos los Paths.
	private Pair<Graf,ArrayList<Result>> GraphAndResults;
	private ArrayList<Dominio.Path> AllPaths;
	
	
	public CtrlData() {
		//Inicializar directorios (crearlos si no existen)
		
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
		Result res = new Result(null, null, null, null); //??????????????
		res = (Result)CtrlData.deepCopy(r);
		LSR = new LoadStoreResult(p.toString());
		LSR.storeResult(res);
	}
	
	public void storePath(Dominio.Path p) throws FileNotFoundException, CloneNotSupportedException, IOException {
		Dominio.Path pa = new Dominio.Path();
		pa = (Dominio.Path) CtrlData.deepCopy(p);
		LSP = new LoadStorePath(PathToPaths.toString());
		LSP.storePath(pa);
	}
	
	public void storeGraf(Graf g) {
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(g.id));
		CG = new CtrlDataGraph(p.toString());
		CG.saveGraph(g, g.nom);
	}
	
	public Result loadResult(String idResult, Graf g) throws FileNotFoundException, ClassNotFoundException, IOException {
		Result r = new Result(null, null, null, null);
		Path p = Paths.get(PathToGrafsAndResults.toString());
		p = p.resolve(String.valueOf(g.id));
		LSR = new LoadStoreResult(p.toString());
		r = LSR.loadResult(idResult);
		return r;
	}
	
	public Dominio.Path loadPath(String nomPath) throws FileNotFoundException, ClassNotFoundException, IOException {
		Dominio.Path p = new Dominio.Path();
		LSP = new LoadStorePath(PathToPaths.toString());
		p = LSP.loadPath(nomPath);
		return p;
	}
}
