package Dominio;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * CtrlDataTest: Driver para testear DataTest.
 */
public class CtrlDataTest {
	
	static CtrlData cd = new CtrlData();
	
	public static void main (String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, CloneNotSupportedException {
		
		int select = -1; 
		System.out.println("Test CtrlData:");
		
		while(select != 0){
			try{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Introducir opción:\n"
						+"1.- Test Path deep copy\n" +
						"2.- Test Result deep copy\n" +
						"3.- Test Graf deep copy\n" +
						"4.- Test constructor\n" +
						"5.- Test store and load Paths\n"+
						"6.- Test load all Paths\n"+
						"7.- Test store and load Results\n"+
						"8.- Test store Graf\n"+
						"9.- Test load Graf and associated Results");
				select = Integer.parseInt(scanner.nextLine()); 
	
				switch(select){
				case 1: 
					testPathDeepCopy();
					break;
				case 2: 
					testResultDeepCopy();
					break;
				case 3: 
					testGrafDeepCopy();
					break;
				case 4:
					testConstructor();
					break;
				case 5:	
					testStoreAndLoadPaths();
					break;
				case 6:
					testLoadAllPaths();
					break;
				case 7:
					testStoreAndLoadResults();
					break;
				case 8:
					testStoreGraf();
					break;
				case 9:
					testLoadGrafAndResults();
					break;
				default:
					break;
				}
				
				System.out.println("\n");
				
			}catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Uoop! Error! Esto no tendría que pasar!");
			}
		}
		
		
	}
	//Database, DataMining, AI, InformationRetrieval
	private static Node.Label switchLabel(String label) throws Exception {
		switch(label) {
		case "Database":
			return Node.Label.Database;
		case "DataMining":
			return Node.Label.DataMining;
		case "AI":
			return Node.Label.AI;
		case "InformationRetrieval":
			return Node.Label.InformationRetrieval;
		default:
			break;
		}
		throw new Exception("Error introduciendo el Label");

	}
	// Autor, Conferencia, Paper, Terme, MidElement
	private static Node.Type switchType(String type) throws Exception {
		switch(type) {
		case "Autor":
			return  Node.Type.Autor;
		case "Conferencia":
			return Node.Type.Conferencia;
		case "Paper":
			return Node.Type.Paper;
		case "Terme":
			return Node.Type.Terme;
		case "MidElement":
			return Node.Type.MidElement;
		default:
			break;
		}
		throw new Exception("Error introduciendo el tipo");
	}

	private static ArrayList<Integer> enterDataGraf(Graf g) {
		ArrayList<Integer> numNodes = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------");
		System.out.println("Primero vamos a añadir unos cuantos nodos de tipo Paper al grafo");
		System.out.println("Introduce un nombre para los nodos de tipo Paper");
		String name = scanner.nextLine();
		System.out.println("-Introduce el número de nodos de tipo Paper a añadir (:");
		numNodes.add(Integer.parseInt(scanner.nextLine()));
		for (int i = 0; i < numNodes.get(0); ++i) {
			g.addNode(Node.Type.Paper, i, name);
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Autor al grafo");
		System.out.println("Introduce un nombre para los nodos de tipo Autor");
		name = scanner.nextLine();
		System.out.println("-Introduce el número de nodos de tipo Autor a añadir:");
		numNodes.add(Integer.parseInt(scanner.nextLine()));
		
		for (int i = 0; i < numNodes.get(1); ++i) {
			g.addNode(Node.Type.Autor, i, name);
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Terme al grafo");
		System.out.println("Introduce un nombre para los nodos de tipo Terme");
		name = scanner.nextLine();
		System.out.println("-Introduce el número de nodos de tipo Terne a añadir:");
		numNodes.add(Integer.parseInt(scanner.nextLine()));
		for (int i = 0; i < numNodes.get(2); ++i) {
			g.addNode(Node.Type.Terme, i, name);
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Conferencia al grafo");
		System.out.println("Introduce un nombre para los nodos de tipo Conferencia");
		name = scanner.nextLine();;
		System.out.println("-Introduce el número de nodos de tipo Conferencia a añadir:");
		numNodes.add(Integer.parseInt(scanner.nextLine()));
		for (int i = 0; i < numNodes.get(3); ++i) {
			g.addNode(Node.Type.Conferencia, i, name);
		}
	
		for (int i = 0; i < numNodes.get(1); ++i) {
			for (int j = 0; j < numNodes.get(0); ++j) {
					g.setArc(j, i, Node.Type.Autor);
			}
		}
		for (int i = 0; i < numNodes.get(2); ++i) {
			for (int j = 0; j < numNodes.get(0); ++j) {
					g.setArc(j, i, Node.Type.Terme);
			}
		}
		for (int i = 0; i < numNodes.get(3); ++i) {
			for (int j = 0; j < numNodes.get(0); ++j) {
					g.setArc(j, i, Node.Type.Conferencia);
			}
		}
		return numNodes;
		
	}
	private static void clearGraf(Graf g) {
		ArrayList<Node> nodesAutor = new ArrayList<Node>();
		for (int i = 0; i < g.getMatrixAuthor().getNRows(); ++i) {
			nodesAutor.add(g.getNode(i, Node.Type.Autor));
		}
		ArrayList<Node> nodesTerme = new ArrayList<Node>();
		for (int i = 0; i < g.getMatrixTerm().getNRows(); ++i) {
			nodesTerme.add(g.getNode(i, Node.Type.Terme));
		}
		ArrayList<Node> nodesConferencia = new ArrayList<Node>();
		for (int i = 0; i < g.getMatrixConf().getNRows(); ++i) {
			nodesConferencia.add(g.getNode(i, Node.Type.Conferencia));
		}
		ArrayList<Node> nodesPaper = new ArrayList<Node>();
		for (int i = 0; i < g.getMatrixAuthor().getNCols(); ++i) {
			nodesPaper.add(g.getNode(i, Node.Type.Paper));
		}
		
		
		for (int i = 0; i < g.getMatrixAuthor().getNRows(); ++i) {
			for (int j = 0; j < g.getMatrixAuthor().getNCols(); ++j) {
					g.deleteArc(nodesAutor.get(i),nodesPaper.get(j));
			}
		}
		for (int i = 0; i < g.getMatrixTerm().getNRows(); ++i) {
			for (int j = 0; j < g.getMatrixAuthor().getNCols(); ++j) {
					g.deleteArc(nodesTerme.get(i),nodesPaper.get(j));
			}
		}
		for (int i = 0; i < g.getMatrixConf().getNRows(); ++i) {
			for (int j = 0; j < g.getMatrixAuthor().getNCols(); ++j) {
					g.deleteArc(nodesConferencia.get(i),nodesPaper.get(j));
			}
		}
		for (int i = 0; i < g.getMatrixAuthor().getNRows(); ++i) {
			g.deleteNode(nodesAutor.get(i));
		}
		for (int i = 0; i < g.getMatrixTerm().getNRows(); ++i) {
			g.deleteNode(nodesTerme.get(i));
		}
		for (int i = 0; i < g.getMatrixConf().getNRows(); ++i) {
			g.deleteNode(nodesConferencia.get(i));
		}
		for (int i = 0; i < g.getMatrixAuthor().getNCols(); ++i) {
			g.deleteNode(nodesPaper.get(i));
		}
		g.getMatrixAuthor().setTamany(0, 0);
		g.getMatrixConf().setTamany(0, 0);
		g.getMatrixTerm().setTamany(0, 0);
	}
	private static void printGraf(Graf g) {
		System.out.println("------------------------------------------------");
		System.out.println("-Nom Graf: " + g.getNom());
		Matrix mauthor = g.getMatrixAuthor();
		Matrix mterme = g.getMatrixTerm();
		Matrix mconf = g.getMatrixConf();
		System.out.println("|||Matriz Adyaciencia AutorPaper|||");
		printMatrix(mauthor);
		System.out.println("|||Matriz Adyaciencia TemaPaper|||");
		printMatrix(mterme);
		System.out.println("|||Matriz Adyaciencia ConferenciaPaper|||");
		printMatrix(mconf);
		System.out.println("|||Nodos Autor|||:");
		for (int i = 0; i < mauthor.getNRows(); ++i) {
			printNode(g.getNode(i, Node.Type.Autor));
		}
		System.out.println("|||Nodos Terme|||");
		for (int i = 0; i < mterme.getNRows(); ++i) {
			printNode(g.getNode(i, Node.Type.Terme));
		}
		System.out.println("|||Nodos Conferencia|||");
		for (int i = 0; i < mconf.getNRows(); ++i) {
			printNode(g.getNode(i, Node.Type.Conferencia));
		}
		System.out.println("|||Nodos Paper|||");
		for (int i = 0; i < mauthor.getNCols(); ++i) {
			printNode(g.getNode(i, Node.Type.Paper));
		}
		
	}
	
	private static void printMatrix(Matrix m) {
		for (int i = 0; i < m.getNRows(); ++i) {
			ArrayList<Float> fila = new ArrayList<Float>();
			fila = m.getRow(i);
			String conc = "";
			for (int j = 0; j < m.getNCols(); ++j) {
				conc += Float.toString(fila.get(j))+" ";
			}
			System.out.println(conc);
		}
	}
	
	private static void printNode(Node n) {
		String retStr = "";
		retStr += "nom: " + n.getNom() +" Id: "+n.getId();
		retStr += "  Type: "+n.getTipus().toString();
		System.out.println("[ "+retStr+" ]");
	}
	
	private static Result enterDataResult() throws Exception {
		Scanner scanner = new Scanner(System.in);
		Path p = new Path();
		System.out.println("Creamos path usado del Resultado");
		enterDataPath(p);
		Node n1 = new Node();
		Node n2 = new Node();
		System.out.println("Introducir nombre Nodo origen");
		String nom = scanner.nextLine();
		System.out.println("Introducir id Nodo origen (Entero)");
		String id = scanner.nextLine();
		System.out.println("Introducir tipo Nodo origen (Autor, Conferencia, Paper, Terme, MidElement)");
		Node.Type t = switchType(scanner.nextLine());
		System.out.println("Introducir label Nodo origen (Database, DataMining, AI, InformationRetrieval)");
		Node.Label l = switchLabel(scanner.nextLine());
		
		n1.initialize(t,Integer.parseInt(id), nom);
		n1.setLabel(l);
		
		System.out.println("Introducir nombre Nodo destino");
		String nomd = scanner.nextLine();
		System.out.println("Introducir id Nodo destino (Entero)");
		String idd = scanner.nextLine();
		System.out.println("Introducir tipo Nodo destino(Autor, Conferencia, Paper, Terme, MidElement)");
		Node.Type td = switchType(scanner.nextLine());
		System.out.println("Introducir label Nodo destino(Database, DataMining, AI, InformationRetrieval)");
		Node.Label ld = switchLabel(scanner.nextLine());
		
		n2.initialize(td, Integer.parseInt(idd), nomd);
		n2.setLabel(ld);
		
		System.out.println("Introducir Threshold del resultado(Real)");
		Float Threshold = Float.parseFloat(scanner.nextLine());
		System.out.println("Introducir valor hetesim del resultado (>= Threshold)");
		Float valhete = Float.parseFloat(scanner.nextLine());
		System.out.println("Introducir nombre grafo asociado: ");
		String nomg = scanner.nextLine();
		System.out.println("Introducir id grafo asociado (Entero): ");
		int idg = Integer.parseInt(scanner.nextLine());
		Graf g = new Graf(nomg,idg);
		Result rs = new Result(g,Threshold,valhete,p,n1,n2);
		return rs;
	}
	
	private static void modifyResult(Result rs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introducir nuevo id de grafo: ");
		rs.setIdGraf(scanner.nextLine());
		System.out.println("Introducir nuevo id de resutado: ");
		rs.setIdResult(scanner.nextLine());
		System.out.println("Introducir nuevo Threshold: ");
		rs.setThreshold(Float.parseFloat(scanner.nextLine()));
		System.out.println("Introducir nuevo valor de HeteSim para el resultado: ");
		rs.modifLine(0, Float.parseFloat(scanner.nextLine()));
	}

	private static void printResult(Result rs) {
		System.out.println("------------------------------------------------");
		System.out.println("Resultado: ");
		System.out.println(rs.toString());
	}
	//Genera un path que será usado para luego copiarlo y modificarlo
	//Tambien imprime por pantalla los datos de los que se compone el Path generado
	private static void enterDataPath(Path p) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------");
		System.out.println("Nombre del Path: ");
		String scan = (scanner.nextLine());
		p.setNom(scan);
		System.out.println("Descripcion del Path: ");
		scan = (scanner.nextLine());
		p.setDescripcio(scan);
		System.out.println("Contenido del Path: ");
		scan = (scanner.nextLine());
		p.setContingut(scan);
		
	}

	private static void printPath(Path p) {
		ArrayList<Node.Type> contingut = p.getContingut();
		System.out.println("------------------------------------------------");
		System.out.println("Nom path: "+p.getNom());
		System.out.println("Descripció path: "+p.getDescripcio());
		String print = "";
		print = "[ ";
		for (int i = 0; i < contingut.size()-1; ++i) {
			print += contingut.get(i).toString()+", ";
		}
		print+= contingut.get(contingut.size()-1).toString()+" ]";
		System.out.println("Contingut Path: " + print);
		System.out.println("------------------------------------------------");
		
	}
	private static void testPathDeepCopy() throws FileNotFoundException, ClassNotFoundException, IOException, CloneNotSupportedException {
		System.out.println("@@@Vamos a generar un Path@@@");
		Path p = new Path();
		enterDataPath(p);
		System.out.println("-Path original:");
		printPath(p);
		Path copy = null;
		copy = (Path) CtrlData.deepCopy(p);
		System.out.println("PATH COPIADO");
		System.out.println("@@@Modificamos el Path original@@@");
		enterDataPath(p);
		System.out.println("-Path modificado:");
		printPath(p);
		
		System.out.println("-Copia del Path (tendria que tener los valores del path original)@@@");
		printPath(copy); //Han de sortir els valors originals
				
	}
	
	private static void testResultDeepCopy() throws Exception {		
		//Se supone que Nodo funciona
		//Se supone que NodePair funciona
		//Se quiere comprobar el funcionamiento de CtrlData.deepCopy con Resultados.
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar un resultado@@@");
		System.out.println("------------------------------------------------");
		Result rs = enterDataResult();
		System.out.println("-Resultado original:");
		printResult(rs);
		Result rscopy = null;
		try {
			rscopy = (Result) CtrlData.deepCopy(rs);
		}
		catch (Exception e) {
			System.out.println("Ha habido algun problema con la funcion de deepCopy");
		}
		System.out.println("RESULT COPIADO");
		System.out.println("@@@Modificamos el Resultado original@@@");
		System.out.println("------------------------------------------------");
		modifyResult(rs);
		System.out.println("-Resultado modificado:");
		printResult(rs);
		
		System.out.println("-Copia del Resultado (tendria que tener los valores del resultado original)@@@");
		printResult(rscopy);
		
	}	
	
	private static void testGrafDeepCopy() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar un grafo@@@");
		System.out.println("@@@Por simplicidad relacionaremos todas las entidades posibles@@@");
		System.out.println("@@@Así mismo, repetiremos los datos de los nodos de cada tipo@@@");
		System.out.println("Nombre del grafo: ");
		String nom = (scanner.nextLine());
		System.out.println("Id grafo (Entero): ");
		int id = Integer.parseInt(scanner.nextLine());
		Graf g = new Graf(nom,id);
		enterDataGraf(g);
		printGraf(g);
		Graf gcopy = null;
		try {
			gcopy = (Graf) CtrlData.deepCopy(g);
		}
		catch(Exception e) {
			System.out.println("Ha habido algún problema con la función deepCopy");
		}
		System.out.println("GRAFO COPIADO");
		System.out.println("@@@Modificaremos el grafo original@@@");
		System.out.println("------------------------------------------------");
		clearGraf(g);
		enterDataGraf(g);
		System.out.println("-Grafo modificado: ");
		printGraf(g);
		System.out.println("-Copia del grafo: ");
		printGraf(gcopy);
		
	}
	
	private static void testConstructor() throws Exception {
		System.out.println("@@@Hay que comprovar que las carpetas: Paths y GrafsAndResults han sido creadas@@@");
		java.io.File Pathsf = new File(cd.getPathtoPahts());
		java.io.File GrafsAndResultsf = new File(cd.getPathtoGraphsAndResult());
		if (Pathsf.exists()) System.out.println("La carpeta Paths ha sido creada con éxito o ya existia");
		else throw new Exception("Error. No se ha podido crear la carpeta Paths");
		if (GrafsAndResultsf.exists()) System.out.println("La carpeta GrafsAndResults ha sido creada con éxito o ya existia");
		else System.out.println("Error. No se ha podido crear la carpeta GrafsAndResults");
		
	}
	
	private static void testStoreAndLoadPaths() throws FileNotFoundException, CloneNotSupportedException, IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar una serie de Paths@@@");
		System.out.println("[cont],[end]");
		String res = "";
		res = scanner.nextLine();
		ArrayList<Path> paths = new ArrayList<Path>();
		while (!res.equals("end")) {
			Path p = new Path();
			enterDataPath(p);
			paths.add(p);
			cd.storePath(p);
			System.out.println("[cont],[end]");
			res = scanner.nextLine();
		}
		System.out.println("Paths creados:");
		java.io.File PathtoPath;
		for (int i = 0; i < paths.size(); ++i) {
			Path pa = cd.loadPath(paths.get(i).getNom());
			printPath(pa);
			PathtoPath = new File(cd.getPathtoPahts()+"/"+pa.getNom()+".ser");
			if (PathtoPath.exists()) System.out.println(PathtoPath);
			else throw new FileNotFoundException("Error! No se ha guardado el Path");
			System.out.println("------------------------------------------------");

		}
	}
	
	private static void testLoadAllPaths() throws FileNotFoundException, CloneNotSupportedException, IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a cargar todos los Paths anteriormente guardados@@@");
		try {
		ArrayList<Path> pathsCargados = cd.loadallPaths();
		File PathtoPath;
		for (int i = 0; i < pathsCargados.size(); ++i) {
			printPath(pathsCargados.get(i));
			PathtoPath = new File(cd.getPathtoPahts()+"/"+pathsCargados.get(i).getNom()+".ser");
			System.out.println(PathtoPath);
			System.out.println("------------------------------------------------");
		}
		}
		catch(Exception e) {
			System.out.println("Ha habido algun problema al intentar cargar todos los Paths");
		}
	}

	
	private static void testStoreAndLoadResults() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar una serie de Resultados@@@");
		System.out.println("[cont],[end]");
		String res = "";
		res = scanner.nextLine();
		ArrayList<Result> results = new ArrayList<Result>();
		while (!res.equals("end")) {
			Result rs = enterDataResult();
			results.add(rs);
			cd.storeResult(rs);
			System.out.println("[cont],[end]");
			res = scanner.nextLine();
		}
		System.out.println("Resultados:");
		java.io.File PathtoResult;
		for (int i = 0; i < results.size(); ++i) {
			Result rs = cd.loadResult(results.get(i).getIdResult(),Integer.parseInt(results.get(i).getIdGraf()));
			printResult(rs);
			PathtoResult = new File(cd.getPathtoGraphsAndResult()+"/"+rs.getIdGraf()+"/"+rs.getIdResult()+".Result");
			if (PathtoResult.exists()) System.out.println(PathtoResult);
			else throw new FileNotFoundException("Error! No se ha guardado el Resultado");
			System.out.println("------------------------------------------------");

		}
	}
	
	private static void testStoreGraf() throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar un grafo@@@");
		System.out.println("@@@Por simplicidad relacionaremos todas las entidades posibles@@@");
		System.out.println("@@@Así mismo, repetiremos los datos de los nodos de cada tipo@@@");
		System.out.println("Nombre del grafo: ");
		String nom = (scanner.nextLine());
		System.out.println("Id grafo (Entero): ");
		int id = Integer.parseInt(scanner.nextLine());
		Graf g = new Graf(nom,id);
		enterDataGraf(g);
		cd.storeGraf(g);
		java.nio.file.Path PathtoGraf = Paths.get(cd.getPathtoGraphsAndResult(), String.valueOf(g.id),String.valueOf(g.id));
		if (cd.checkGraphFile(PathtoGraf.toString())) {
			System.out.println("Grafo guardado correctamente en:");
			System.out.println(PathtoGraf.toString());
		}
		else throw new FileNotFoundException("Error! No se ha guardado el Grafo");

	}
	
	public static void testLoadGrafAndResults() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a cargar un grafo anteriormente guardado, y sus resultados asociados@@@");
		Graf g = new Graf();
		ArrayList<Result> results = new ArrayList<Result>();
		Pair<Graf,ArrayList<Result>> GrafAndResults = new Pair<Graf,ArrayList<Result>>(g,results);
		System.out.println("Selecciona uno de los grafos almacenados anteriormente: ");
		String in = scanner.nextLine();
		GrafAndResults = cd.loadgraphAndResults(in);
		System.out.println("-Grafo cargado:");
		printGraf(GrafAndResults.first);
		System.out.println("-Resultados asociados:");
		for (int i = 0; i < GrafAndResults.second.size(); ++i) {
			printResult(GrafAndResults.second.get(i));
		}
		
	}

}
