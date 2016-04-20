import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Dominio.*;
import Persistencia.GraphImporter;

public class Propject {

	public static void main(String[] args) throws IOException, PathException {
		System.out.println("HelloOoOoO PROP");
		Graf g = GraphImporter.leMagicGoesOn("/home/pinkii/Documents/PROP/DBLP_four_area/");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		System.out.println("seting grafh hetesim");
		
		Matrix m = g.getMatrixAuthor();
		
		GraphImporter.g = new Graf();
		g = new Graf();

		System.out.println("This matrix alone");
		br.readLine();
		
		SparseMatrix sm = new SparseMatrix(m);
		
		System.out.println("SparseMatrix done");
		br.readLine();
		
//		
//		HeteSanic h = new HeteSanic();
//		h.setGraph(g);
//		
//		br.readLine();
//		System.out.println("Starting Hetesim");
//		
//		Path p = new Path();
//		p.setContingut("APA");
//		
//		Matrix m = h.getHeteSim(p);
//		
//		System.out.println("Hetsim done");
//		br.readLine();
		
	}

}
