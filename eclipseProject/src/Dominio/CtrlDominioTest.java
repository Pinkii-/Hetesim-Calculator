/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CtrlDominioTest {
	static CtrlDominio ctrlDominio;
	static CtrlGraph ctrlGraph;
	static CtrlPaths ctrlPaths;
	static CtrlResults ctrlResults;

	static ArrayList<String> pathNames;

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			quickTest();
			return;
		}
		initControllers();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Que numero de instruccion quieres ejecutar?");
			System.out.println("0 - Hablar con CtrlDominio.");
			System.out.println("1 - Hablar con CtrlGraph.");
			System.out.println("2 - Hablar con CtrlPaths.");
			System.out.println("3 - Hablar con CtrlResults.");
			System.out.println("4 - Salir del progrma.");
			System.out.print("> ");
			Integer index;
			try {
				index = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				continue;
			}
			switch (index) {
			case 0:
				talkToCtrlDominio();
				break;
			case 1:
				talkToCtrlGraph();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				break;
			}
		}
	}

	private static String askForPathName() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Que path quieres utilizar? (Gestionado por ctrlPath");
		ArrayList<String> pathNames = new ArrayList<String>();
		pathNames = ctrlPaths.getPathNames();
		Integer index = 0;
		for (String s : pathNames) {
			System.out.println(index + ": " + s);
			++index;
		}
		index = Integer.parseInt(br.readLine());
		return pathNames.get(index);
	}

	private static void talkToCtrlDominio() throws IOException {
		boolean finished = false;

		while (!finished) {
			String pathName;
			String node1Index;
			String node1Type;
			String node2Index;
			String node2Type;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(
					"Hola, soy CtrlDominio y me encargo de gestionar las busquedas y el I/O. Que quieres que haga?");
			System.out.println("0 - Importa el grafo de pruebas (necesario si no has inicializado el grafo antes)");
			System.out.println("1 - Realiza una búsqueda con un path.");
			System.out.println("2 - Realiza una búsqueda con un una entidad y un path.");
			System.out.println("3 - Realiza una búsqueda con dos entidades y un path");
			System.out.println("4 - Guarda el grafo actual.");
			System.out.println("5 - Carga un grafo ya guardado.");
			System.out.println(
					"6 - Imprime el grafo (lo hace CtrlGrafo, pero yo te dejo hacerlo aquí por conveniencia :D )");
			System.out.println("7 - Salir.");
			System.out.print("> ");
			Integer index;
			try {
				index = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				continue;
			}
			switch (index) {
			case 0:
				ctrlDominio.importGraph("C:\\Users\\Usuari\\Desktop\\PROP\\GraphForTesting");
				break;
			case 1:
				pathName = askForPathName();				
				ctrlDominio.searchPath(pathName);
				break;
			case 2:
				pathName = askForPathName();
				System.out.println("Indica el indice y tipo del nodo sobre el que deseas hacer la busqueda (cada uno en una linea):");
				System.out.print("> ");			
				node1Index = br.readLine();
				System.out.print("> ");
				node1Type = br.readLine();
				ctrlDominio.searchPathNode(pathName, Integer.parseInt(node1Index), node1Type);
				break;
			case 3:
				pathName = askForPathName();
				System.out.println("Indica el indice y tipo del primer nodo sobre el que deseas hacer la busqueda (cada uno en una linea):");
				System.out.print("> ");			
				node1Index = br.readLine();
				System.out.print("> ");
				node1Type = br.readLine();
				System.out.println("Indica el indice y tipo del segundo nodo sobre el que deseas hacer la busqueda (cada uno en una linea):");
				System.out.print("> ");			
				node2Index = br.readLine();
				System.out.print("> ");
				node2Type = br.readLine();
				ctrlDominio.searchPathNodeNode(pathName, Integer.parseInt(node1Index), node1Type, Integer.parseInt(node2Index), node2Type);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				Utils.printGraf(ctrlGraph.getGraph());
				break;
			case 7:
				break;
			}
		}
	}

	private static void talkToCtrlGraph() throws IOException {
		boolean finished = false;
		String nodeType;
		String nodeName;
		String nodeIndex;
		String paperIndex;
		while (!finished) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Hola, soy CtrlGraph y me encargo de gestionar el grafo. Que quieres que haga?");
			System.out.println("0 - Anade un nodo nuevo.");
			System.out.println("1 - Modifica un nodo existente.");
			System.out.println("2 - Borra un nodo existente.");
			System.out.println("3 - Anade una relacion entre dos nodos existentes.");
			System.out.println("4 - Elimina una relacion existente entre dos nodos.");
			System.out.println("5 - Imprime el grafo.");
			System.out.println("6 - Salir.");
			System.out.print("> ");
			Integer index;
			try {
				index = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				continue;
			}
			switch (index) {
			case 0:
				System.out.println("Indica el tipo y nombre del nodo que deseas anadir (cada uno en una linea):");
				System.out.print("> ");
				nodeType = br.readLine();
				System.out.print("> ");
				nodeName = br.readLine();
				ctrlGraph.addNode(nodeType, nodeName);
				break;
			case 1:
				System.out.println("Indica el indice y tipo del nodo que deseas modificar (cada uno en una linea):");
				System.out.print("> ");
				nodeIndex = br.readLine();
				System.out.print("> ");
				nodeType = br.readLine();
				System.out.println("Indica el nuevo nombre del nodo que deseas modificar:");
				System.out.print("> ");
				nodeName = br.readLine();
				ctrlGraph.modifyNode(Integer.parseInt(nodeIndex), nodeType, nodeName);
				break;
			case 2:
				System.out.println("Indica el index y tipo del nodo que deseas borrar (cada uno en una linea):");
				System.out.print("> ");
				nodeIndex = br.readLine();
				System.out.print("> ");
				nodeType = br.readLine();
				ctrlGraph.eraseNode(Integer.parseInt(nodeIndex), nodeType);
				break;
			case 3:
				System.out.println("Indica el indice del paper que deseas relacionar:");
				System.out.print("> ");
				paperIndex = br.readLine();
				System.out.println(
						"Indica el indice y tipo del nodo con el que deseas relacionarlo (cada uno en una linea):");
				System.out.print("> ");
				nodeIndex = br.readLine();
				System.out.print("> ");
				nodeType = br.readLine();
				ctrlGraph.addNodeRelation(Integer.parseInt(paperIndex), Integer.parseInt(nodeIndex), nodeType);
				break;
			case 4:
				System.out.println("Indica el indice del paper del cual deseas borrar su relacion:");
				System.out.print("> ");
				paperIndex = br.readLine();
				System.out.println(
						"Indica el indice y tipo del nodo del otro extremo de la relacion (cada uno en una linea):");
				System.out.print("> ");
				nodeIndex = br.readLine();
				System.out.print("> ");
				nodeType = br.readLine();
				ctrlGraph.eraseNodeRelation(Integer.parseInt(paperIndex), Integer.parseInt(nodeIndex), nodeType);
				break;
			case 5:
				Utils.printGraf(ctrlGraph.getGraph());
				break;
			case 6:
				finished = true;
				break;
			}
		}
	}

	private static void quickTest() {
		System.out.println("Running QuickTest");
		initControllers();
		ctrlGraph.addNode("conference", "Conf");
		ctrlGraph.addNode("author", "pep");
		ctrlGraph.addNode("paper", "AwesomePaper");
		ctrlGraph.addNodeRelation(0, 0, "conference");
		ctrlGraph.addNodeRelation(0, 0, "author");
		Utils.printGraf(ctrlGraph.getGraph());
	}

	private static void initControllers() {
		// ctrlDominio instantiates all the remaining controllers, then we get a
		// reference to each of them.
		ctrlDominio = new CtrlDominio();
		ctrlGraph = ctrlDominio.getCtrlGraph();
		ctrlPaths = ctrlDominio.getCtrlPaths();
		ctrlResults = ctrlDominio.getCtrlResults();
	}
}
