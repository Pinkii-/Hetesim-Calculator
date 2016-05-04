//Autor: Xavier Peñalosa
package resultTesting;

import java.util.ArrayList;
import java.util.Random;

public class ResultTest{
	
	public static void main(String[] args) {
		testNodePair();
		testResult1();
		testResult2();
		testResult3();
		testResult4();
	}


	
	/**
	 * Crea dos nodos, los pone en un Nodepair con valor de Hetesim = 0.1f
	 * Escribe el NodePair
	 * Añade 0.5f al valor del Hetesim anterior
	 * Escribe el NodePair de nuevo
	 * 
	 */
	private static void testNodePair(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ TEST 0 @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 1, "Node1 name");
		Node n2 = new Node();
			n2.initialize(Node.Type.Autor, 2, "Node2 name");
			
		NodePair np = new NodePair(n1,n2,0.1f);
		System.out.println(np.toString());
		np.setHetesim(np.getHetesim()+0.5f);
		System.out.println(np.toString());
	}
	
	/**
	 * Crea un grafo y un path por defecto
	 * Crea una nueva matrix con valores float aleatorios
	 * Crea un resultado pasando como parametros el grafo, threshold = 0 para que escriba todo, la matrix con los valores de hetesim, y el path
	 * Escribe el resultado
	 */
	private static void testResult1(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ TEST 1 @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Graf g = new Graf();
		Graf g2 = new Graf(2);
		Path p = new Path();

		Matrix m = new Matrix();
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		m.setTamany(rows, cols);
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if (rand.nextInt(2)%2 == 0) m.afegirArc(i, j);
			}
		}
		
		Result r = new Result(g, 0.f, m, p);
		System.out.println(r.toString());
		System.out.println(r.toString(g2));
	}
	
	
	/**
	 * Crea un grafo y un path por defecto
	 * Crea un nodo (origen)
	 * Crea un nuevo ArrayList con identificadores de nodo y supuestos resultados del Hetesim 
	 * Crea un resultado pasando como parametros el grafo, threshold = 0 para que escriba todo, el ArrayList, el path, y el nodo origen
	 * Escribe el resultado
	 */
	private static void testResult2(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ TEST 2 @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Graf g = new Graf();
		ArrayList<Pair<Integer,Float>> m = new ArrayList<>();
		for (int i = 0; i < 10; ++i){
			m.add(new Pair<Integer,Float>(i,i*0.1f));
		}
		Path p = new Path();
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 25, "NodeOrigin");
		
		Result r = new Result(g,0.f,m,p,n1);
		
		System.out.println(r.toString());
	}
	
	
	/**
	 * Crea un grafo y un path por defecto
	 * Crea un nodo (origen) y un nodo (destino)
	 * Crea un nuevo float, resultado de un supuesto Hetesim 
	 * Crea un resultado pasando como parametros el grafo, threshold = 0 para que escriba todo, el Float, el path, el nodo origen y el nodo destino
	 * Escribe el resultado
	 */
	private static void testResult3(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ TEST 3 @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Graf g = new Graf();
		Path p = new Path();
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 30, "NodeOrigin");
		Node n2 = new Node();
			n2.initialize(Node.Type.Paper, 31, "NodeLast");
		
		Result r = new Result(g,0.f,0.5f,p,n1,n2);
		System.out.println(r.toString());
	}

	
	/**
	 * Igual al test 2, con comprobación de threshold
	 * 
	 * Crea un grafo y un path por defecto
	 * Crea un nodo (origen)
	 * Crea un nuevo ArrayList con identificadores de nodo y supuestos resultados del Hetesim 
	 * Crea un resultado pasando como parametros el grafo, threshold = 0.3 para demostrar que funciona, el ArrayList, el path, y el nodo origen
	 * Escribe el resultado
	 */
	private static void testResult4(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ TEST 4 @@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Graf g = new Graf();
		ArrayList<Pair<Integer,Float>> m = new ArrayList<>();
		for (int i = 0; i < 10; ++i){
			m.add(new Pair<Integer,Float>(i,i*0.1f));
		}
		Path p = new Path();
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 25, "NodeOrigin");
		
		Result r = new Result(g,0.4f,m,p,n1);
		
		System.out.println(r.toString());
	}
	
}