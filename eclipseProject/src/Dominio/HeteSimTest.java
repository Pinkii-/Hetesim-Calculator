package Dominio;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Persistencia.GraphImporter;

public class HeteSimTest {

	static HeteSim hetesim = new HeteSim();
	static Graf graph;
	static Path path;
	
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Necesitas pasarle el path de la carpeta de donde va a cargar los juegos de prueba");
			System.exit(-1);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Que numero de instruccion quieres ejecutar?");
			System.out.println("0 - Cargar el grafo a traves del path recibido");
			System.out.println("1 - Setear el grafo nuevo en el hetesim (Cada vez que hay un grafo nuevo, se tiene que setear en el hetesim)");
			System.out.println("2 - Busqueda que te devuelve la matriz de hetesims para cierto path");
			System.out.println("3 - Busqueda que te devuleve para cierto path, para cierto nodo, el hetesim de ese nodo a todos los nodos de final del path, desordenadamente");
			System.out.println("4 - Busqueda que te devuelve el valor hetesim entre los dos nodos");
			System.out.print("> ");
			Integer index;
			try {
				index = Integer.parseInt(br.readLine());
			}
			catch (NumberFormatException e) {
				continue;
			}
			switch (index) {
			case 0:
				graph = GraphImporter.leMagicGoesOn(args[0]);
				System.out.println("    Cargado correctamente :D");
				break;
			
			case 1:
				hetesim.setGraph(graph);
				System.out.println("    Seteado correctamente :D");
				break;
			
			case 2:{
				System.out.println("Que path quieres utilizar? 'A' autor, 'T' term, 'P' paper, 'C' conf ");
				System.out.println("APA autor -> paper -> autor, TPCPA term->paper->conf->...");
				String contenidoPath = br.readLine().toUpperCase();
				if (contenidoPath.matches("[APTC]*")){
					path = new Path();
					path.setContingut(contenidoPath);
					try {
						Matrix m = hetesim.getHeteSim(path);
						for (int i = 0; i < m.getNRows(); ++i) {
							for (int j = 0; j < m.getNCols(); ++j) {
								System.out.print(m.getValue(i, j) + " ");;
							}
							System.out.println();
						}
					} catch (PathException e) {
						System.out.println("El path era incorrecto: " + e.getMessage());
					}					
				}
				else {
					System.out.println("El path es incorrecto :(");
				}
				break;}
			case 3:{
				System.out.println("Que path quieres utilizar? 'A' autor, 'T' term, 'P' paper, 'C' conf ");
				System.out.println("APA autor -> paper -> autor, TPCPA term->paper->conf->...");
				String contenidoPath = br.readLine().toUpperCase();
				if (contenidoPath.matches("[APTC]*")){
					path = new Path();
					path.setContingut(contenidoPath);
					
					System.out.println("Que nodo quieres poner como principio de busqueda?");
					for (Node n : getNodes(contenidoPath.charAt(0))) {
						System.out.println(n.id + " -> " + n.nom);
					}
					
					try {
						Matrix m = hetesim.getHeteSim(path);
						for (int i = 0; i < m.getNRows(); ++i) {
							for (int j = 0; j < m.getNCols(); ++j) {
								System.out.print(m.getValue(i, j) + " ");;
							}
							System.out.println();
						}
					} catch (PathException e) {
						System.out.println("El path era incorrecto: " + e.getMessage());
					}					
				}
				else {
					System.out.println("El path es incorrecto :(");
				}
				break;}
			
			}
		}
	}

	private static ArrayList<Node> getNodes(char charAt) {
		ArrayList<Node> ret = new ArrayList<Node>();
		int size;
		Node.Type type;
		switch (charAt) {
		case 'P':
			size = graph.getMatrixTerm().getNCols();
			type = Node.Type.Paper;
			break;
		case 'T':
			size = graph.getMatrixTerm().getNRows();
			type = Node.Type.Terme;
			break;
		case 'A':
			size = graph.getMatrixAuthor().getNRows();
			type = Node.Type.Autor;
			break;
		case 'C':
			size = graph.getMatrixConf().getNRows();
			type = Node.Type.Conferencia;
		default:
			return null;
		}
		
		for (int i = 0; i < size; ++i) {
			ret.add(graph.getNode(i, type));
		}
		
		return ret;
	}
}
