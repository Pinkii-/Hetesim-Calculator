/**
 * @author Victor Alcazar Lopez & Albert Lopez
 * 
**/

package Dominio;

import java.util.ArrayList;

public class Utils {
	public static Node.Type getNodeType(Integer i) {
		return Node.Type.values()[i];
	}

	public static Node.Type getNodeType(String s) {
		s = s.toLowerCase();
		if (s.equals("autor"))
			return Node.Type.Autor;
		if (s.equals("conferencia"))
			return Node.Type.Conferencia;
		if (s.equals("paper"))
			return Node.Type.Paper;
		if (s.equals("terme"))
			return Node.Type.Terme;
		else
			return Node.Type.MidElement;
	}

	public static Node.Label getNodeLabel(Integer i) {
		return Node.Label.values()[i];
	}

	// Printing functions
	
	private static void printMatrixNodes(Graf g, Matrix m, Node.Type t){
		for (int i = 0; i < m.getNRows(); ++i) {
			System.out.print(i + ": ");
			printNode(g.getNode(i, t));
		}
	}
	
	private static void printPaperNodes(Graf g, Matrix m){
		try{
			for (int i = 0; i < m.getNCols(); ++i) {
				System.out.print(i + ": ");
				printNode(g.getNode(i, Node.Type.Paper));
			}
		}catch(Exception e){
			//Resulta que si no hay papers peta el programa :)
		}

	}
	
	public static void printNodesOfType(Graf g, Node.Type t){
		Matrix mauthor = g.getMatrixAuthor();
		Matrix mterme = g.getMatrixTerm();
		Matrix mconf = g.getMatrixConf();
		if(t == Node.Type.Autor){
			printMatrixNodes(g, mauthor, t);			
		}else if(t == Node.Type.Conferencia){
			printMatrixNodes(g, mconf, t);
		}else if(t == Node.Type.Terme){
			printMatrixNodes(g, mterme, t);
		}else if(t == Node.Type.Paper){
			printPaperNodes(g, mauthor);
		}else{
			System.out.println("Node type not found " + t);
		}
	}

	public static void printGraf(Graf g) {
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
		System.out.println("Nodos Autor:\n");
		printMatrixNodes(g, mauthor, Node.Type.Autor);
		System.out.println("Nodos Terme:\n");
		printMatrixNodes(g, mterme, Node.Type.Terme);
		System.out.println("Nodos Conferencia:\n");
		printMatrixNodes(g, mconf, Node.Type.Conferencia);
		System.out.println("Nodos Paper:\n");
		printPaperNodes(g, mauthor);

	}

	public static void printMatrix(Matrix m) {
		for (int i = 0; i < m.getNRows(); ++i) {
			ArrayList<Float> fila = new ArrayList<Float>();
			fila = m.getRow(i);
			String conc = "";
			for (int j = 0; j < m.getNCols(); ++j) {
				conc += Float.toString(fila.get(j)) + " ";
			}
			System.out.println(conc);
		}
	}

	public static void printNode(Node n) {
		String retStr = "";
		retStr += "Name: " + n.getNom() + "  Type: " + n.getTipus().toString();
		System.out.println("(" + retStr + ")");
	}
	
	public static void printPath(Path p){
		String retStr = "";
		retStr += "Name: " + p.getNom() + "  Content: " + p.getContingut() + " Description: " + p.getDescripcio();
		System.out.println("(" + retStr + ")");
	}
}
