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

	static BufferedReader br;
	
	static String filePath;

	public static void main(String[] args) throws IOException {
		if(args.length < 1){
			System.out.println("Debes poner como primer parametro el camino hasta un directorio con un grafo valido");
			System.exit(0);
		}
		filePath = args[0];
		br = new BufferedReader(new InputStreamReader(System.in));
		initControllers();
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
				talkToCtrlPaths();
				break;
			case 3:
				talkToCtrlResults();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	private static String askForPathName() throws NumberFormatException, IOException {
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

	private static Float askForThreshold() throws NumberFormatException, IOException {
		System.out.println("Quieres hacer la busqueda con un valor de relacion como referencia? (S/N)");
		System.out.print("> ");
		String isThreshold = br.readLine();
		isThreshold = isThreshold.toUpperCase();
		if (isThreshold.equals("S")) {
			System.out.println("Introduce el valor [0..1]:");
			System.out.print("> ");
			return Float.parseFloat(br.readLine());
		} else
			return -1f;
	}

	private static String askForDescription() throws IOException {
		System.out.println("Anadimos una descripcion para el path? (S/N)");
		System.out.print("> ");
		String isDescription = br.readLine();
		isDescription = isDescription.toUpperCase();
		if (isDescription.equals("S")) {
			System.out.println("Descripcion:");
			System.out.print("> ");
			String desc = br.readLine();
			return desc;
		} else
			return null;
	}

	private static boolean askForStoreResult() throws IOException {
		System.out.println("CtrlResults: Guardamos el resultado? (S/N)");
		System.out.print("> ");
		String SN = br.readLine();
		SN = SN.toUpperCase();
		if (SN.equals("S"))
			return true;
		else
			return false;
	}
	
	private static void printLastResult(){
		System.out.println("CtrlResult Imprime el ultimo resultado conseguido:");
		ctrlResults.printLastResult();
	}

	private static void talkToCtrlResults() throws IOException {
		boolean finished = false;

		while (!finished) {

			System.out.println(
					"Hola, soy CtrlResults y me encargo de gestionar todos los resultados de busquedas del dominio. Que quieres que haga?");
			System.out.println("0 - Imprime todos los resultados.");
			System.out.println("1 - Guardar en persistencia todos los resultados (Lo hace CtrlDominio).");
			System.out.println("2 - Salir.");
			System.out.print("> ");
			Integer index;
			try {
				index = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				continue;
			}
			switch (index) {
			case 0:
				ctrlResults.printResults();
				break;
			case 1:
				ctrlDominio.saveResults();
				break;
			case 2:
				finished = true;
				break;
			default:
				break;
			}
		}
	}

	private static void talkToCtrlPaths() throws IOException {
		boolean finished = false;
		String pathName;
		String pathContent;
		String pathDescription;
		ArrayList<String> pathNames;

		while (!finished) {

			System.out.println(
					"Hola, soy CtrlPaths y me encargo de gestionar todos los paths del dominio. Que quieres que haga?");
			System.out.println("0 - Anade un path nuevo.");
			System.out.println("1 - Modifica un path existente.");
			System.out.println("2 - Borra un path existente.");
			System.out.println("3 - Dime la informacion sobre un path determinado.");
			System.out.println("4 - Dame el nombre de todos los paths.");
			System.out.println("5 - Guarda todos los paths. (Lo hace CtrlDominio)");
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
				System.out
						.println("Indica el nombre y el contenido del path que deseas anyadir (cada uno en una linea):");
				System.out.print("> ");
				pathName = br.readLine();
				System.out.print("> ");
				pathContent = br.readLine();
				pathDescription = askForDescription();
				if (pathDescription != null)
					ctrlPaths.addPath(pathContent, pathName, pathDescription);
				else
					ctrlPaths.addPath(pathContent, pathName, "");
				break;
			case 1:
				System.out.println("Indica el nombre del path que deseas modificar:");
				System.out.print("> ");
				pathName = br.readLine();
				System.out.println("Indica el nuevo contenido del path que deseas modificar:");
				System.out.print("> ");
				pathContent = br.readLine();
				pathDescription = askForDescription();
				if (pathDescription != null)
					ctrlPaths.modifyPath(pathName, pathContent, pathDescription);
				else
					ctrlPaths.modifyPath(pathName, pathContent);
				break;
			case 2:
				System.out.println("Indica el nombre del path que deseas eliminar:");
				System.out.print("> ");
				pathName = br.readLine();
				ctrlPaths.erasePath(pathName);
				break;
			case 3:
				System.out.println("Indica el nombre del path a consultar:");
				System.out.print("> ");
				pathName = br.readLine();
				ctrlPaths.printPath(pathName);
				break;
			case 4:
				pathNames = ctrlPaths.getPathNames();
				for (String s : pathNames) {
					System.out.println(s);
				}
				break;
			case 5:
				ctrlDominio.savePaths();
				break;
			case 6:
				finished = true;
				break;
			default:
				break;
			}
		}
	}

	private static void talkToCtrlDominio() throws IOException {
		boolean finished = false;

		while (!finished) {
			String pathName;
			String node1Index;
			String node2Index;
			Float threshold;
			String graphId;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(
					"Hola, soy CtrlDominio y me encargo de gestionar las busquedas y el I/O. Que quieres que haga?");
			System.out.println("0 - Importa el grafo de pruebas (necesario si no has inicializado el grafo antes)");
			System.out.println("1 - Realiza una busqueda con un path.");
			System.out.println("2 - Realiza una busqueda con un una entidad y un path.");
			System.out.println("3 - Realiza una busqueda con dos entidades y un path");
			System.out.println("4 - Guarda el grafo actual.");
			System.out.println("5 - Carga un grafo ya guardado.");
			System.out.println(
					"6 - Imprime el grafo (lo hace CtrlGrafo, pero yo te dejo hacerlo aqu� por conveniencia :D )");
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
				ctrlDominio.importGraph(filePath);
				break;
			case 1:
				pathName = askForPathName();
				threshold = askForThreshold();
				if (threshold.equals(-1))
					ctrlDominio.searchPath(pathName);
				else
					ctrlDominio.searchPathThreshhold(threshold, pathName);
				printLastResult();
				if (askForStoreResult())
					ctrlResults.addLastResult();
				break;
			case 2:
				pathName = askForPathName();
				System.out.println(
						"Indica el indice del nodo sobre el que deseas hacer la busqueda:");
				System.out.print("> ");
				node1Index = br.readLine();
				threshold = askForThreshold();
				if (threshold.equals(-1))
					ctrlDominio.searchPathNode(pathName, Integer.parseInt(node1Index));
				else
					ctrlDominio.searchPathNodeThreshhold(threshold, pathName, Integer.parseInt(node1Index));
				printLastResult();
				if (askForStoreResult())
					ctrlResults.addLastResult();
				break;
			case 3:
				pathName = askForPathName();
				System.out.println(
						"Indica el indice del primer nodo sobre el que deseas hacer la busqueda:");
				System.out.print("> ");
				node1Index = br.readLine();				
				System.out.println(
						"Indica el indice del segundo nodo sobre el que deseas hacer la busqueda:");
				System.out.print("> ");
				node2Index = br.readLine();				
				threshold = askForThreshold();
				if (threshold.equals(-1))
					ctrlDominio.searchPathNodeNode(pathName, Integer.parseInt(node1Index),
							Integer.parseInt(node2Index));
				else
					ctrlDominio.searchPathNodeNodeThreshhold(threshold, pathName, Integer.parseInt(node1Index),
							Integer.parseInt(node2Index));
				printLastResult();
				if (askForStoreResult())
					ctrlResults.addLastResult();
				break;
			case 4:
				ctrlDominio.saveGraph();
				break;
			case 5:
				System.out.println("Introduce la id del grafo que deseas cargar.");
				System.out.println(">");
				graphId = br.readLine();
				ctrlDominio.loadGraph(graphId);
				break;
			case 6:
				Utils.printGraf(ctrlGraph.getGraph());
				break;
			case 7:
				finished = true;
				break;
			default:
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
			default:
				break;
			}
		}
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
