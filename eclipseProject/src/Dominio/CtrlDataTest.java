package Dominio;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class CtrlDataTest {
	@Test
	
	private bool compareNode() {}
	
	//asumo que matrix funciona
	private Matrix generateRandomMatrix() {
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
		return m;
	}
	public void testPathDeepCopy() throws FileNotFoundException, ClassNotFoundException, IOException, CloneNotSupportedException {
		/*
		 * Nombre path
		 * Contenido path
		 * Descripción path
		 * No uso stub de Path (funciona -> las clases que usa Path tambien funcionan)
		 */
		String nomPathOriginal;
		String nomCopiaPath;
		String descripcióOriginal;
		String descripcióCopia;
		
		Path p =  new Path();
		p.setContingut("APAP");
		p.setNom("p1");
		p.setDescripcio("PrimerPath");
		nomPathOriginal = p.getNom();
		descripcióOriginal = p.getDescripcio();
		nomPathOriginal = p.getNom();
		
		CtrlData cd = new CtrlData();
		cd.storePath(p);
		Path copy = cd.loadPath(p.getNom());
		p.setDescripcio("CopiaPath");
		p.setContingut("NOFUNSIONA");
		p.setNom("NOFUNSIONA");
		
		nomCopiaPath = copy.getNom();
		/////////////////////////////
		
		assertTrue(p.getNom() != copy.getNom());
		assertTrue(p.getDescripcio() != copy.getDescripcio());
		assertTrue(!p.getContingut().equals(copy.getContingut()));
	}
	
	@Test
	public void testResultDeepCopy() {		
		
		assertEquals(1,1);
	}
	@Test
	public void testGrafDeepCopy() {
		/*asumimos que Matrix funciona*/
		
		
		
		assertEquals(1,1);
	}

}
