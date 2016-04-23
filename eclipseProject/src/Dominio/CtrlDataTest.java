package Dominio;


import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*shit
 * CtrlDataTest: Driver para testear DataTest o algo:
 * Funcionalidades:
 * -1)TestDeepCopy
 * -------
 * Juego de prubas:
 * -PathDeepCopy: 
 * (IN)Se introducen datos para diversos paths. Luego datos para modificar dichos pats. (IN)
 * (OUT)Se copian los datos originales. Esto es. Han de ser los datos que muestre el programa y no los modificados (OUT)
 */
public class CtrlDataTest {
	
	public static void main (String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, CloneNotSupportedException {
		
		int select = -1; 
		System.out.println("Test CtrlData:");
		
		while(select != 0){
			try{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Introducir opción:\n"
						+"1.- TestPathDeepCopy\n" +
						"2.- TestResultDeepCopy\n" +
						"3.- TestGrafDeepCopy\n" +
						"4.- Dividir\n" +
						"0.- Salir");
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
					break;
				case 0: 
					break;
				default:
					break;
				}
				
				System.out.println("\n");
				
			}catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Uoop! Error!");
			}
		}
		
		
	}
	//Database, DataMining, AI, InformationRetrieval
	private static Node.Label switchLabel(String label) {
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
		System.out.println("Error introduciendo el Label");

		return null;
	}
	// Autor, Conferencia, Paper, Terme, MidElement
	private static Node.Type switchType(String type) {
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
		System.out.println("Error introduciendo el tipo");
		return null;
	}
	/*
	private static Node generateNode() {
		Scanner scanner = new Scanner(System.in);
		Node n1 = new Node();		
		System.out.println("Introducir nombre Nodo");
		String nom = scanner.nextLine();
		System.out.println("Introducir id Nodo (Entero)");
		String id = scanner.nextLine();
		System.out.println("Introducir tipo Nodo (Autor, Conferencia, Paper, Terme, MidElement)");
		Node.Type t = switchType(scanner.nextLine());
		System.out.println("Introducir label Nodo (Database, DataMining, AI, InformationRetrieval)");
		Node.Label l = switchLabel(scanner.nextLine());
		n1.initialize(t,Integer.parseInt(id), nom);
		n1.setLabel(l);
		return n1;
	}*/

	private static ArrayList<Integer> enterDataGraf(Graf g) {
		ArrayList<Integer> numNodes = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------");
		System.out.println("Primero vamos a añadir unos cuantos nodos de tipo Paper al grafo");
		System.out.println("Introduce un nombre para los nodos de tipo Paper");
		String name = scanner.nextLine();
		System.out.println("-Introduce el número de nodos de tipo Paper a añadir:");
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
		
		
		/*
		 *  public void setArc(int indexpaper, int index2, Node.Type t) {
        switch(t) {
            case Autor: autorPaper.afegirArc(index2, indexpaper);
                break;
            case Conferencia: confPaper.afegirArc(index2, indexpaper); 
                break;
            case Terme: temaPaper.afegirArc(index2, indexpaper);
                break;
            default: break;
        }
    }*/
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
	
	private static void enterDataResultAndPrint(ResultStub rs, boolean modification) {
		Scanner scanner = new Scanner(System.in);
		Path p = new Path();
		rs.setPathUsed(p);
		Node n1;
		if (!modification)
			n1 = new Node();
		else n1 = rs.getFirstN();
		Node n2;
		if (!modification)
			n2 = new Node();
		else n2 = rs.getLastN();
		
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
		
		System.out.println("Introducir Threshold del Result (Real)");
		Float f = Float.parseFloat(scanner.nextLine());
		rs.setThreshold(f);
		ArrayList<NodePair> res;
		
		if (!modification)res = new ArrayList<NodePair>();
		else {
			res = rs.getresultList();
			res.clear();
		}
		
		System.out.println("Introducir tantos valores de Hetesim como resultados se quieran añadir");
		System.out.println("Introducir 'end' para finalizar");
		String c = scanner.nextLine();
		while(!c.equals("end")) {
			Node np1 = (Node) CtrlData.deepCopy(n1);
			Node np2 = (Node) CtrlData.deepCopy(n2);
			f = Float.parseFloat(c);
			NodePair np = new NodePair(np1,np2,f);
			res.add(np);
			c = scanner.nextLine();
			
		}
		rs.setOriginDest(n1, n2);
		rs.setModified(false);
		rs.setresultList(res);
		rs.setidGraph("id");
	}
	

	private static void printResult(ResultStub rs) {
		System.out.println("------------------------------------------------");
		System.out.println("Resultado: ");
		System.out.println(rs.toString());
	}
	//Genera un path que será usado para luego copiarlo y modificarlo
	//Tambien imprime por pantalla los datos de los que se compone el Path generado
	private static void enterDataPathAndPrint(Path p) {
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
		enterDataPathAndPrint(p);
		System.out.println("-Path original:");
		printPath(p);
		
		Path copy = (Path) CtrlData.deepCopy(p);
		System.out.println("PATH COPIADO");
		System.out.println("@@@Modificamos el Path original@@@");
		enterDataPathAndPrint(p);
		System.out.println("-Path modificado:");
		printPath(p);
		
		System.out.println("-Copia del Path (tendria que tener los valores del path original)@@@");
		printPath(copy); //Han de sortir els valors originals
		
		System.out.println("Identificadores:\nPath original:"+ p.toString()+"\nCopiaPath: "+copy.toString());
		
	}
	
	private static void testResultDeepCopy() {		
		//Se supone que Nodo funciona
		//Se supone que NodePair funciona
		//Se quiere comprobar el funcionamiento de CtrlData.deepCopy con Resultados.
		Scanner scanner = new Scanner(System.in);
		System.out.println("@@@Vamos a generar un resultado@@@");
		System.out.println("------------------------------------------------");
		System.out.println("Introducir id Resultado");
		ResultStub rs = new ResultStub(scanner.nextLine());
		enterDataResultAndPrint(rs,false);
		System.out.println("-Resultado original:");
		printResult(rs);
		
		ResultStub rscopy = (ResultStub) CtrlData.deepCopy(rs);
		System.out.println("RESULT COPIADO");
		System.out.println("@@@Modificamos el Resultado original@@@");
		System.out.println("------------------------------------------------");
		System.out.println("Usamos el mismo id para el Resultado");
		enterDataResultAndPrint(rs,true);
		System.out.println("-Resultado modificado:");
		printResult(rs);
		
		System.out.println("-Copia del Resultado (tendria que tener los valores del path original)@@@");
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
		Graf gcopy = (Graf) CtrlData.deepCopy(g);
		System.out.println("GRAFO COPIADO");
		System.out.println("@@@Modificaremos el grafo original(Añadir el mismo número de nodos de cada tipo)@@@");
		System.out.println("------------------------------------------------");
		clearGraf(g);
		enterDataGraf(g);
		System.out.println("-Grafo modificado: ");
		printGraf(g);
		System.out.println("-Copia del grafo: ");
		printGraf(gcopy);
		
		
	}

}
