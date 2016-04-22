package Dominio;


import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
		
		Scanner scanner = new Scanner(System.in); 
		int select = -1; 
		System.out.println("Test CtrlData:");
		
		while(select != 0){
			try{
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
					break;
				case 4: 
					break;
				case 0: 
					break;
				default:
					break;
				}
				
				System.out.println("\n"); //Mostrar un salto de línea en Java
				
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
		return null;
	}
	// Autor, Conferencia, Paper, Terme, MidElement
	private static Node.Type switchType(String type) {
		switch(type) {
		case "Autor":
			return Node.Type.Autor;
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
		return null;
	}
	private static Graf generateGraf() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vamos a generar un Graf");
		System.out.println("Nombre grafo: ");
		String nom = (scanner.nextLine());
		System.out.println("Id grafo: ");
		int id = Integer.parseInt(scanner.nextLine());
		Graf g = new Graf();
		
	}
	
	private static ResultStub generateResultAndPrint() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------");
		System.out.println("Vamos a generar un Resultado");
		System.out.println("Introducir id del Resultado (idResult)");
		String scan = (scanner.nextLine());
		ResultStub rs = new ResultStub(scan);
		
		Path p = new Path();
		rs.setPathUsed(p);
		
		Node n1 = new Node();
		Node n2 = new Node();		
		
		System.out.println("Introducir nombre Nodo origen");
		String nom = scanner.nextLine();
		System.out.println("Introducir id Nodo origen");
		String id = scanner.nextLine();
		System.out.println("Introducir tipo Nodo origen");
		Node.Type t = switchType(scanner.nextLine());
		System.out.println("Introducir label Nodo origen ");
		Node.Label l = switchLabel(scanner.nextLine());
		
		n1.initialize(t,Integer.parseInt(id), nom);
		n1.setLabel(l);
		
		System.out.println("Introducir nombre Nodo destino");
		String nomd = scanner.nextLine();
		System.out.println("Introducir id Nodo destino");
		String idd = scanner.nextLine();
		System.out.println("Introducir tipo Nodo destino");
		Node.Type td = switchType(scanner.nextLine());
		System.out.println("Introducir label Nodo destino");
		Node.Label ld = switchLabel(scanner.nextLine());
		
		n2.initialize(td, Integer.parseInt(idd), nomd);
		n2.setLabel(ld);
		
		System.out.println("Introducir Threshold del Result");
		Float f = Float.parseFloat(scanner.nextLine());
		rs.setThreshold(f);
		
		ArrayList<NodePair> res = new ArrayList<NodePair>();
		//Generamos el array de NodePair que contiene los diversos resultados de Hetesim.
		//Para no hacer el testeo muy pesado usaremos la información de los nodos origen y destino.
		//Posteriormente modificaremos el array.
		while(scanner.nextLine() != "end") {
			Node np1 = (Node) CtrlData.deepCopy(n1);
			Node np2 = (Node) CtrlData.deepCopy(n2);
			NodePair np = new NodePair(np1,np2,f);
			res.add(np);
		}
		rs.setresultList(res);
		return rs;
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
		
		CtrlData cd = new CtrlData();
		cd.storePath(p);
		
		Path copy = new Path();
		copy = cd.loadPath(p.getNom());
		
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
		
		
	}	
	
	public void testGrafDeepCopy() {
		/*asumimos que Matrix funciona*/
		/*asumimos que Node funciona*/
		/*Graf g = new Graf("g",1);
		Node.Type tipo;
		g.addNode(tipo, , nom)
		*/
	}

}
