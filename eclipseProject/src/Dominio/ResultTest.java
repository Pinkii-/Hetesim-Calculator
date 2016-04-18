//Autor: Xavier Peñalosa
package Dominio;

public class ResultTest{
	
	public static void main(String[] args) {
		testNodePair();
		testResult1();
		testResult2();
		testResult3();
	}


	
	
	private static void testNodePair(){
		Node n1 = new Node();
			Node.Type Autor = null;
			Integer id = 1;
			n1.initialize(Autor, id, "Node1 name");
		Node n2 = new Node();
			Node.Type Autor2 = null;
			Integer id2 = 2;
			n2.initialize(Autor2, id2, "Node2 name");
			
		NodePair np = new NodePair(n1,n2,0.1f);
		System.out.println(np.toString());
		np.setHetesim(np.getHetesim()+0.5f);
		System.out.println(np.toString());
	}
	
	private static void testResult1(){
		Graf g = new Graf();
		Path p = new Path();
		Matrix mat = new Matrix();
		
		//Result r = new Result(g, 0.f, mat, p);
		//System.out.println(r.toString);
		//System.out.println(r.toString(g.id+1));
	}
	
	private static void testResult2(){
		
	}
	private static void testResult3(){
		
	}

}