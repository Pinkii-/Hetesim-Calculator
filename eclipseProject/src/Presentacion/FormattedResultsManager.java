package Presentacion;

import java.util.ArrayList;
import java.util.HashMap;

import Dominio.CtrlDominio;
import Dominio.CtrlResults;
import Dominio.Pair;
import Dominio.formatedNodeComparator;


/*
 * Funcion:
 * -Guarda los diferentes resultados formateados que necesita panelLoadResult
 * -Permite a panelLoadResults marcar los resultados a borrar, revertir luego si no se quieren borrar .
 * ------------------------------
 * -Permite a panelMostrarResultado mantener los cambios hechos, revertir algunos, y finalmente guardarlos
 * -Para guardarlos, de cada resultado modificado, sacar que linea tiene su valor Hetesim modificado
 * 
 * */
public class FormattedResultsManager {
	private CtrlDominio cd;
	private CtrlResults cr;
	private ArrayList<String> idResults;
	private ArrayList<FormattedResult> results;
	private HashMap<Integer,String> toDelete;
	
	
	public FormattedResultsManager(CtrlDominio cd) {
		this.cd = cd;
		idResults = new ArrayList<String>();
		results = new ArrayList<FormattedResult>();
		toDelete = new HashMap<Integer,String>();
		cr = this.cd.getCtrlResults();
		//obtainFormattedResults();
	}
	
	private void obtainFormattedResults() {
		idResults = cr.getAllResultIds();
		for (int i = 0; i < idResults.size(); ++i) {
			FormattedResult fr = new FormattedResult(cr.getFormatted(idResults.get(i)));
			results.add(fr);
		}
	}
	
	public ArrayList<FormattedResult> getFormattedResults() {
		obtainFormattedResults();
		return results;
	}
	
	public void addToDelete (int index) {
		if (!toDelete.containsKey(index)) {
			FormattedResult aux = results.get(index);
			toDelete.put(index, aux.getIdResult());
		}
	}
	
	public void revertDeletions() {
		toDelete.clear();
	}
	
	public void commitDeletions() {
	}
	
	public static class FormattedResult  {
		private ArrayList<ArrayList<String>> formattedResult;
		private String listedResult;
		private String resultType;
		private String searchPath;
		private String searchGraphId;
		private String searchGraphName;
		
		public FormattedResult(ArrayList<ArrayList<String>> res) {
			this.formattedResult = res;
			resultType = res.get(resultTypePosition.first).get(resultTypePosition.second);
			searchPath = res.get(searchPathPosition.first).get(searchPathPosition.second);
			searchGraphId = res.get(searchGraphIdPosition.first).get(searchGraphIdPosition.second);
			//searchGraphName = res.get(searchGraphNamePosition.first).get(searchGraphNamePosition.second);
			
		}
		
		public ArrayList<ArrayList<String>> getFormattedResult() {
			return formattedResult;
		}
		
		public String getIdResult() {
			return formattedResult.get(resultIdPosition.first).get(resultIdPosition.second);
		}
		
		//Insertar más getters innecesarios aquí.
		public void commitChanges() {
			
		}
		
		public void revertChanges() {
			
		}
		
		public String getListedResult() {
			listedResult = "Search Type: " + resultType + ", Search Path: ";
		    listedResult += searchPath + ", Associated Graph: " + searchGraphId;
		    return listedResult;
		}
		
		//Insertar mas pairs innecesarios aqui
		public static final Pair<Integer,Integer> resultIdPosition = new Pair<Integer,Integer>(0,0);
		public static final Pair<Integer,Integer> resultTypePosition = new Pair<Integer,Integer>(0,1);
		public static final Pair<Integer,Integer> searchPathPosition = new Pair<Integer,Integer>(0,2);
		public static final Pair<Integer,Integer> searchGraphIdPosition = new Pair<Integer,Integer>(0,3);
		//public static final Pair<Integer,Integer> searchGraphNamePosition = new Pair<Integer, Integer>(0,3);
		
		/*
		controlar las modificaciones que se hacen a un resultado en concreto: devolver que indices se 
		han modificado y su nuevo valor de HeteSim*/
		
	}
}
