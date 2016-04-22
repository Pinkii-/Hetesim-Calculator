package Dominio;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import Persistencia.GraphImporter;

public class HeteSanicTest {

	@Test
	public void testGetHeteSimPath() {
		Graf g = GraphImporter.leMagicGoesOn("/home/pinkii/Documents/PROP/DBLP_four_area/");
//		Random rand = new Random();
//		for (int i = 0; i < 1000; ++i) {
//			g.addNode(Node.Type.Paper, i, "");
//		}
//		for (int i = 0; i < 1000; ++i) {
//			g.addNode(Node.Type.Autor, i, "");
//		}
//		
//		for (int i = 0; i < 1000; ++i) {
//			for (int j = 0; j < 1000; ++j) {
//				if (rand.nextInt()%100 == 0) {
//					g.setArc(j, i, Node.Type.Autor);
//				}
//			}
//		}
		
//		HeteSanic h = new HeteSanic();
//		h.setGraph(g);
//		
//		Path p =  new Path();
//		p.setContingut("APAP");
//		
//		try {
//			Matrix m = h.getHeteSim(p);
//		} catch (PathException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Test
	public void testGetHeteSimPathNode() {
		Graf g = new Graf();
		Random rand = new Random();
		for (int i = 0; i < 1000; ++i) {
			g.addNode(Node.Type.Paper, i, "");
		}
		for (int i = 0; i < 1000; ++i) {
			g.addNode(Node.Type.Autor, i, "");
		}
		
		for (int i = 0; i < 1000; ++i) {
			for (int j = 0; j < 1000; ++j) {
				if (rand.nextInt()%100 == 0) {
					g.setArc(j, i, Node.Type.Autor);
				}
			}
		}
		HeteSim h = new HeteSim();
		h.setGraph(g);
		
		Path p =  new Path();
		p.setContingut("APAP");
		
		try {
			Matrix m = h.getHeteSim(p);
		} catch (PathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetHeteSimPathNodeNode() {
		Graf g = new Graf();
		
		g.addNode(Node.Type.Autor, 0, "a1");
		g.addNode(Node.Type.Autor, 1, "a2");
		g.addNode(Node.Type.Autor, 2, "a3");
		
		g.addNode(Node.Type.Paper, 0, "b1");
		g.addNode(Node.Type.Paper, 1, "b2");
		g.addNode(Node.Type.Paper, 2, "b3");
		g.addNode(Node.Type.Paper, 3, "b4");
		
		g.setArc(0, 0, Node.Type.Autor);
		g.setArc(1, 0, Node.Type.Autor);
		g.setArc(1, 1, Node.Type.Autor);
		g.setArc(2, 1, Node.Type.Autor);
		g.setArc(3, 1, Node.Type.Autor);
		g.setArc(3, 2, Node.Type.Autor);
		
		Matrix m = g.getMatrixAuthor();
		for (int i = 0; i < m.getNRows(); ++i) {
			for (int j = 0; j < m.getNCols(); ++j) {
				System.out.print(m.getValue(i, j) + " ");
			}
			System.out.println();
		}
		
		HeteSim h = new HeteSim();
		h.setGraph(g);
		
		Path p =  new Path();
		p.setContingut("APAPAPAPA");
	
		
		try {
			m = h.getHeteSim(p);
		} catch (PathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < m.getNRows(); ++i) {
			for (int j = 0; j < m.getNCols(); ++j) {
				System.out.print(m.getValue(i, j) + " ");
			}
			System.out.println();
		}
		
//		HeteSanic h2 = new HeteSanic();
//		h2.setGraph(g);
//		
//		try {
//			m = h2.getHeteSim(p);
//		} catch (PathException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (int i = 0; i < m.getNRows(); ++i) {
//			for (int j = 0; j < m.getNCols(); ++j) {
//				System.out.print(m.getValue(i, j) + " ");
//			}
//			System.out.println();
//		}
		
	}

}
