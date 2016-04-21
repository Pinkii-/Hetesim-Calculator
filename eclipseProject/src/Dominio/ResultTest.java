//Autor: Xavier Peñalosa
package Dominio;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Xavier
 *
 */

public class ResultTest{
	
	
	public static void main(String[] args) {
		testNodePair();
		testResult1();
		testResult2();
		testResult3();
	}
	
	private static void testNodePair(){
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 1, "Node1 name");
		Node n2 = new Node();
			n2.initialize(Node.Type.Autor, 2, "Node2 name");
			
		NodePair np = new NodePair(n1,n2,0.1f);
		System.out.println(np.toString());
		np.setHetesim(np.getHetesim()+0.5f);
		System.out.println(np.toString());
	}
	
	private static void testResult1(){
		System.out.println("TEST 1 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Graf g = new Graf();
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
	}
	
	private static void testResult2(){
		System.out.println("TEST 2 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
	
	private static void testResult3(){
		System.out.println("TEST 3 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Graf g = new Graf();
		Path p = new Path();
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 30, "Node origen");
		Node n2 = new Node();
			n2.initialize(Node.Type.Paper, 31, "Node last");
		
		Result r = new Result(g,0.f,0.5f,p,n1,n2);
		System.out.println(r.toString());
	}

}