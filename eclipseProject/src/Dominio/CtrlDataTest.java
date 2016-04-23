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
	

	private static Graf generateGraff(int numNodesA, int numNodesT, int numNodesC, int numNodesP) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------");
		System.out.println("Nombre del grafo: ");
		String nom = (scanner.nextLine());
		System.out.println("Id grafo (Entero): ");
		int id = Integer.parseInt(scanner.nextLine());

		Graf g = new Graf(nom,id);
		System.out.println("Primero vamos a añadir unos cuantos nodos de tipo Paper al grafo");
		System.out.println("-Introduce el número de nodos de tipo Paper a añadir:");
		numNodesP = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesP; ++i) {
			g.addNode(Node.Type.Paper, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Autor al grafo");
		System.out.println("-Introduce el número de nodos de tipo Autor a añadir:");
		numNodesA = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesA; ++i) {
			g.addNode(Node.Type.Autor, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Terme al grafo");
		System.out.println("-Introduce el número de nodos de tipo Terne a añadir:");
		numNodesT = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesT; ++i) {
			g.addNode(Node.Type.Terme, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Conferencia al grafo");
		System.out.println("-Introduce el número de nodos de tipo Conferencia a añadir:");
		numNodesC = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesC; ++i) {
			g.addNode(Node.Type.Conferencia, i, "");
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
		for (int i = 0; i < numNodesA; ++i) {
			for (int j = 0; j < numNodesP; ++j) {
					g.setArc(j, i, Node.Type.Autor);
			}
		}
		for (int i = 0; i < numNodesT; ++i) {
			for (int j = 0; j < numNodesP; ++j) {
					g.setArc(j, i, Node.Type.Terme);
			}
		}
		for (int i = 0; i < numNodesC; ++i) {
			for (int j = 0; j < numNodesP; ++j) {
					g.setArc(j, i, Node.Type.Conferencia);
			}
		}
		return g;
		
	}
	
	private static void printGraf(Graf g, int numNodesA, int numNodesT, int numNodesC, int numNodesP) {
		System.out.println("------------------------------------------------");
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
		for (int i = 0; i < numNodesA; ++i) {
			printNode(g.getNode(i, Node.Type.Autor));
		}
		System.out.println("|||Nodos Terme|||");
		for (int i = 0; i < numNodesT; ++i) {
			printNode(g.getNode(i, Node.Type.Terme));
		}
		System.out.println("|||Nodos Conferencia|||");
		for (int i = 0; i < numNodesC; ++i) {
			printNode(g.getNode(i, Node.Type.Conferencia));
		}
		System.out.println("|||Nodos Paper|||");
		for (int i = 0; i < numNodesC; ++i) {
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
		retStr += "    N1) nom: " + n.getNom() +" Id: "+n.getId();
		retStr += "  Type: "+n.getTipus().toString() + "  Label: "+ n.getLabel().toString();
		System.out.println("[ "+retStr+" ]");
	}
	
	private static void enterDataResultAndPrint(ResultStub rs) {
		Scanner scanner = new Scanner(System.in);
		Path p = new Path();
		rs.setPathUsed(p);
		
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
		
		System.out.println("Introducir Threshold del Result (Real)");
		Float f = Float.parseFloat(scanner.nextLine());
		rs.setThreshold(f);
		
		ArrayList<NodePair> res = new ArrayList<NodePair>();
		//Generamos el array de NodePair que contiene los diversos resultados de Hetesim.
		//Para no hacer el testeo muy pesado usaremos la información de los nodos origen y destino para cada NodePair.
		//Posteriormente modificaremos el array.
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
		enterDataResultAndPrint(rs);
		System.out.println("-Resultado original:");
		printResult(rs);
		
		ResultStub rscopy = (ResultStub) CtrlData.deepCopy(rs);
		System.out.println("RESULT COPIADO");
		System.out.println("@@@Modificamos el Resultado original@@@");
		System.out.println("------------------------------------------------");
		System.out.println("Usamos el mismo id para el Resultado");
		enterDataResultAndPrint(rs);
		System.out.println("-Resultado modificado:");
		printResult(rs);
		
		System.out.println("-Copia del Resultado (tendria que tener los valores del path original)@@@");
		printResult(rscopy);
		
	}	
	
	private static void testGrafDeepCopy() {
		System.out.println("@@@Vamos a generar un grafo@@@");
		System.out.println("@@@Por simplicidad relacionaremos todas las entidades posibles@@@");
		System.out.println("@@@Así mismo, repetiremos los datos de los nodos de cada tipo@@@");
		int numNodesA = 0,numNodesT = 0,numNodesC = 0,numNodesP = 0;
		Graf g = generateGraff(numNodesA,numNodesT,numNodesC,numNodesP);
		printGraf(g, numNodesA, numNodesT, numNodesC, numNodesP);
	}

}
