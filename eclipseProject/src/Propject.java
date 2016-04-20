import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Dominio.*;
import Persistencia.GraphImporter;

public class Propject {

	public static void main(String[] args) throws IOException, PathException {
		System.out.println("HelloOoOoO PROP");
		Graf g = GraphImporter.leMagicGoesOn("/home/pinkii/Documents/PROP/DBLP_four_area/");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		CtrlSearch cs = new CtrlSearch();

		System.out.println("Start Hetesim ?");
		br.readLine();
		System.out.println("Starting Hetesim");
		
		Path p = new Path();
		p.setContingut("APA");
		
		System.out.println(g.getNode(7695, Node.Type.Autor).getNom() + " " + g.getNode(416, Node.Type.Terme).getNom());
		
//		g.getNode(7696, Node.Type.Autor),g.getNode(417, Node.Type.Terme)
		
//		SparseMatrix ssm = h.getHeteSim(p);
		
//		System.out.println("Hetsim done " + ssm.getValue(7695,416));
		
//		System.out.println(g.getMatrixTerm().getRow(416));
//		g.getMatrixTerm().transpose();
		
		cs.setGraph(g);
		
		System.out.println("hetesim " + cs.searchPathNodeThreshhold(g, 0.05f, p, g.getNode(7695, Node.Type.Autor)));
		
		br.readLine();
		
//		System.out.println(ssm);
	}

}
