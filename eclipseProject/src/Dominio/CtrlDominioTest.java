/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

import java.util.ArrayList;


public class CtrlDominioTest {
	static CtrlDominio ctrlDominio;
	static CtrlGraph ctrlGraph;
	static CtrlPaths ctrlPaths;
	static CtrlResults ctrlResults;
	
	static ArrayList<String> pathNames;

	public static void main(String[] args){
		initControllers();
		ctrlDominio.importGraph("C:\\Users\\Usuari\\Desktop\\PROP\\GraphForTesting");
		ctrlPaths.addPath("APA", "Related authors", "Description left empty");
		pathNames = ctrlPaths.getPathNames();
		
		ctrlDominio.searchPath(pathNames.get(0));
		ctrlDominio.saveLastSearchResult();
		System.out.println(ctrlResults.toString());		
	}
	private static void initControllers() {
		//ctrlDominio instantiates all the remaining controllers, then we get a reference to each.
		ctrlDominio = new CtrlDominio();
		ctrlGraph = ctrlDominio.getCtrlGraph();
		ctrlPaths = ctrlDominio.getCtrlPaths();
		ctrlResults = ctrlDominio.getCtrlResults();
	}
}
