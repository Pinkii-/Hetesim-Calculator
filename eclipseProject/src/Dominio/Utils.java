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
		if (s.equals("author"))
			return Node.Type.Autor;
		if (s.equals("conference"))
			return Node.Type.Conferencia;
		if (s.equals("paper"))
			return Node.Type.Paper;
		if (s.equals("term"))
			return Node.Type.Terme;
		else
			return Node.Type.MidElement;
	}

	public static Node.Label getNodeLabel(Integer i) {
		return Node.Label.values()[i];
	}

	// Printing functions

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
		for (int i = 0; i < mauthor.getNRows(); ++i) {
			System.out.print(i + ": ");
			printNode(g.getNode(i, Node.Type.Autor));
		}
		System.out.println("Nodos Terme:\n");
		for (int i = 0; i < mterme.getNRows(); ++i) {
			System.out.print(i + ": ");
			printNode(g.getNode(i, Node.Type.Terme));
		}
		System.out.println("Nodos Conferencia:\n");
		for (int i = 0; i < mconf.getNRows(); ++i) {
			System.out.print(i + ": ");
			printNode(g.getNode(i, Node.Type.Conferencia));
		}
		System.out.println("Nodos Paper:\n");
		try{
			for (int i = 0; i < mauthor.getNCols(); ++i) {
				System.out.print(i + ": ");
				printNode(g.getNode(i, Node.Type.Paper));
			}
		}catch(Exception e){
			//Resulta que si no hay papers peta el programa :)
		}


	}

	private static void printMatrix(Matrix m) {
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

	private static void printNode(Node n) {
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
