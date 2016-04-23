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
		System.out.println("ei");

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
		System.out.println("ei");
		return null;
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
	private static Graf generateGraf() {
		Random rand = new Random();
		Graf g = new Graf();
		for (int i = 0; i < 10; ++i) {
			g.addNode(Node.Type.Paper, i, "");
		}
		for (int i = 0; i < 10; ++i) {
			g.addNode(Node.Type.Autor, i, "");
		}
		
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				if (rand.nextInt()%100 == 0) {
					g.setArc(j, i, Node.Type.Autor);
				}
			}
		}
		Matrix mauthor = g.getMatrixAuthor();
		Matrix mterm = g.getMatrixTerm();
		Matrix mconf = g.getMatrixConf();
		
		printMatrix(mauthor);
		return g;
	}
	private static Graf generateGraff() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.println("Nombre del grafo: ");
		String nom = (scanner.nextLine());
		System.out.println("Id grafo (Entero): ");
		int id = Integer.parseInt(scanner.nextLine());

		Graf g = new Graf(nom,id);
		System.out.println("Primero vamos a añadir unos cuantos nodos de tipo Paper al grafo");
		System.out.println("-Introduce el número de nodos de tipo Paper a añadir:");
		int numNodesP = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesP; ++i) {
			g.addNode(Node.Type.Paper, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Autor al grafo");
		System.out.println("-Introduce el número de nodos de tipo Autor a añadir:");
		int numNodesA = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesA; ++i) {
			g.addNode(Node.Type.Autor, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Terme al grafo");
		System.out.println("-Introduce el número de nodos de tipo Terne a añadir:");
		int numNodesT = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numNodesT; ++i) {
			g.addNode(Node.Type.Terme, i, "");
		}
		System.out.println("Vamos a añadir unos cuantos nodos de tipo Conferencia al grafo");
		System.out.println("-Introduce el número de nodos de tipo Conferencia a añadir:");
		int numNodesC = Integer.parseInt(scanner.nextLine());
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
		Matrix mauthor = g.getMatrixAuthor();
		Matrix mterme = g.getMatrixTerm();
		Matrix mconf = g.getMatrixConf();
		printMatrix(mauthor);
		System.out.println("LEL");
		printMatrix(mterme);
		System.out.println("LEL");
		printMatrix(mconf);
		return g;
	}
	
	
	private static ResultStub generateResultAndPrint() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.println("Introducir id del Resultado (idResult)");
		String scan = (scanner.nextLine());
		ResultStub rs = new ResultStub(scan);
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
		System.out.println("Resultado generado: ");
		System.out.println(rs.toString());
		return rs;
	}
	
	private static void modifyResultAndPrint(ResultStub rs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.println("Usamos el mismo nombre para el Resultado");
		
		Path p = new Path();
		rs.setPathUsed(p);
		
		Node n1 = new Node();
		Node n2 = new Node();
		
		System.out.println("Modificar nombre Nodo origen");
		String nom = scanner.nextLine();
		System.out.println("Modificar id Nodo origen (Entero)");
		String id = scanner.nextLine();
		System.out.println("Modificar tipo Nodo origen (Autor, Conferencia, Paper, Terme, MidElement)");
		Node.Type t = switchType(scanner.nextLine());
		System.out.println("Modificar label Nodo origen (Database, DataMining, AI, InformationRetrieval)");
		Node.Label l = switchLabel(scanner.nextLine());
		
		n1.initialize(t,Integer.parseInt(id), nom);
		n1.setLabel(l);
		
		System.out.println("Modificar nombre Nodo destino");
		String nomd = scanner.nextLine();
		System.out.println("Modificar id Nodo destino (Entero)");
		String idd = scanner.nextLine();
		System.out.println("Modificar tipo Nodo destino(Autor, Conferencia, Paper, Terme, MidElement)");
		Node.Type td = switchType(scanner.nextLine());
		System.out.println("Modificar label Nodo destino(Database, DataMining, AI, InformationRetrieval)");
		Node.Label ld = switchLabel(scanner.nextLine());
		
		n2.initialize(td, Integer.parseInt(idd), nomd);
		n2.setLabel(ld);
		
		System.out.println("Modificar Threshold del Result (Real)");
		Float f = Float.parseFloat(scanner.nextLine());
		rs.setThreshold(f);
		
		ArrayList<NodePair> res = new ArrayList<NodePair>();
		//Generamos el array de NodePair que contiene los diversos resultados de Hetesim.
		//Para no hacer el testeo muy pesado usaremos la información de los nodos origen y destino para cada NodePair.
		System.out.println("Modifiquemos ahora los valores de Hetesim asociados al result");
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
		rs.setModified(true);
		rs.setresultList(res);
		System.out.println("Restultado modificado: ");
		System.out.println(rs.toString());
	}
	
	private static void printResult(ResultStub rs) {
		System.out.println("Resultado original: ");
		System.out.println(rs.toString());
	}
	//Genera un path que será usado para luego copiarlo y modificarlo
	//Tambien imprime por pantalla los datos de los que se compone el Path generado
	private static Path generatePathAndPrint() {
		Scanner scanner = new Scanner(System.in);
		Path p = new Path();
		System.out.println("------------------------------------");
		System.out.println("Nombre del Path: ");
		String scan = (scanner.nextLine());
		p.setNom(scan);
		System.out.println("Descripcion del Path: ");
		scan = (scanner.nextLine());
		p.setDescripcio(scan);
		System.out.println("Contenido del Path: ");
		scan = (scanner.nextLine());
		p.setContingut(scan);
		ArrayList<Node.Type> contingutOriginal = p.getContingut();
		System.out.println("Nom original path:"+p.getNom());
		System.out.println("Descripció original path: "+p.getDescripcio());
		String print = "[ ";
		for (int i = 0; i < contingutOriginal.size()-1; ++i) {
			print += contingutOriginal.get(i).toString()+", ";
		}
		print+= contingutOriginal.get(contingutOriginal.size()-1).toString()+" ]";
		System.out.println("Contingut Original: "+ print);
		System.out.println("------------------------------------");
		return p;
	}
	//Modifica el Path pasado por parámetro de manera que luego se puede chequear que no cam
	//bia la copia
	private static void modifyPathAndPrint(Path p) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nou nom: ");
		String scan = (scanner.nextLine());
		p.setNom(scan);
		System.out.println("Nova descripció");
		scan = (scanner.nextLine());
		p.setDescripcio(scan);
		System.out.println("Nou contingut");
		scan = (scanner.nextLine());
		p.setContingut(scan);
		ArrayList<Node.Type> contingutModificat = p.getContingut();
		System.out.println("------------------------------------------------");
		System.out.println("Nom path modificat: "+p.getNom());
		System.out.println("Descripció path modificat: "+p.getDescripcio());
		String print = "[ ";
		for (int i = 0; i < contingutModificat.size()-1; ++i) {
			print += contingutModificat.get(i).toString()+", ";
		}
		print+= contingutModificat.get(contingutModificat.size()-1).toString()+" ]";
		System.out.println("Contingut Path modificat: "+print);
		System.out.println("------------------------------------------------");
	}

	private static void printPath(Path p) {
		ArrayList<Node.Type> contingutCopia = p.getContingut();
		System.out.println("------------------------------------------------");
		System.out.println("Nom path copia: "+p.getNom());
		System.out.println("Descripció path copia: "+p.getDescripcio());
		String print = "";
		print = "[ ";
		for (int i = 0; i < contingutCopia.size()-1; ++i) {
			print += contingutCopia.get(i).toString()+", ";
		}
		print+= contingutCopia.get(contingutCopia.size()-1).toString()+" ]";
		System.out.println("Contingut Path copia: " + print);
		System.out.println("------------------------------------------------");
		
	}
	private static void testPathDeepCopy() throws FileNotFoundException, ClassNotFoundException, IOException, CloneNotSupportedException {
		System.out.println("@@@Vamos a generar un Path@@@");
		Path p = generatePathAndPrint();
		
		
		Path copy = (Path) CtrlData.deepCopy(p);
		
		System.out.println("@@@Modificamos el Path original@@@");
		modifyPathAndPrint(p);
		
		System.out.println("@@@Copia del Path (tendria que tener los valores del path original)@@@");
		printPath(copy); //Han de sortir els valors originals
		
		System.out.println("Identificadores:\nPath original:"+ p.toString()+"\nCopiaPath: "+copy.toString());
		
	}
	
	private static void testResultDeepCopy() {		
		//Se supone que Nodo funciona
		//Se supone que NodePair funciona
		//Se quiere comprobar el funcionamiento de CtrlData.deepCopy con Resultados.
		System.out.println("@@@Vamos a generar un resultado@@@");
		ResultStub rs = generateResultAndPrint();
		
		ResultStub rscopy = (ResultStub) CtrlData.deepCopy(rs);
		
		System.out.println("@@@Modificamos el Resultado original@@@");
		modifyResultAndPrint(rs);
		
		System.out.println("@@@Copia del Resultado (tendria que tener los valores del path original)@@@");
		printResult(rscopy); //Han de sortir els valors originals
		
	}	
	
	private static void testGrafDeepCopy() {
		System.out.println("@@@Vamos a generar un grafo@@@");
		System.out.println("@@@Por simplicidad solo añadiremos nodos de 2 tipos@@@");
		Graf g = generateGraff();
	}

}
