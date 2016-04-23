package Dominio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

/**
 * 
 * @author Gonzalo Diez
 * 
 */

public class SparseMatrixTest {

	@Test
	public void testSparseMatrixMatrix() {
		Matrix m = new Matrix();
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		m.setTamany(rows, cols);
		System.out.println("Original Matrix:");
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if (rand.nextInt(2)%2 == 0) m.afegirArc(i, j);
				System.out.print(" " + m.getValue(i, j));
			}
			System.out.println();
		}
		
		SparseMatrix sm = new SparseMatrix(m);
		
		System.out.println("\n SparseMatrix:");
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				System.out.print(" " + sm.getValue(i, j));
				assertEquals(m.getValue(i, j),sm.getValue(i, j));
			}
			System.out.println();
		}		
	}

	@Test
	public void testSparseMatrixIntInt() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		assertEquals(sm.getNCols(),cols);
		assertEquals(sm.getNRows(),rows);
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				assertEquals(new Float(0.f), sm.getValue(i, j));
			}
		}
		
	}

	@Test
	public void testSparseMatrixSparseMatrix() {
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
		
		SparseMatrix sm1 = new SparseMatrix(m);
		SparseMatrix sm2 = new SparseMatrix(sm1);
		
		assertEquals(sm1.getNRows(), sm2.getNRows());
		assertEquals(sm1.getNCols(), sm2.getNCols());
		assertEquals(sm1.getNRows(), rows);
		assertEquals(sm1.getNCols(), cols);
		
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				assertEquals(sm1.getValue(i, j),sm2.getValue(i, j));
			}
		}
		
		sm1.set(0, 0, sm2.getValue(0, 0)+1);
		
		assertEquals(sm1.getValue(0, 0),(Float)(sm2.getValue(0, 0)+1));
		
		sm2.set(0, 0, sm1.getValue(0, 0)+1);
		
		assertEquals(sm2.getValue(0, 0),(Float)(sm1.getValue(0, 0)+1));
	}

	@Test
	public void testSet() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = rand.nextFloat();
				sm.set(i, j, f);
				assertEquals(f, sm.getValue(i, j));
			}
		}
		// Second pass of inserts to be sure that works
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = rand.nextFloat();
				sm.set(i, j, f);
				assertEquals(f, sm.getValue(i, j));
			}
		}
		// Lets put all zeros (In the program I think will never be posible to try to insert a 0)
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = 0.f;
				sm.set(i, j, f);
				assertEquals(f, sm.getValue(i, j));
			}
		}
		
	}

	@Test
	public void testGetNRows() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		assertEquals(rows, sm.getNRows());
		Matrix m = new Matrix();
		m.setTamany(rows, cols);
		sm = new SparseMatrix(m);
		assertEquals(rows, sm.getNRows());
		sm = new SparseMatrix(sm);
		assertEquals(rows, sm.getNRows());
		
		// Matrix of 0 x 0 (?) this makes any sense? dunno
//		rows = 0;
//		cols = 0;
//		sm = new SparseMatrix(rows, cols);
//		assertEquals(cols, sm.getNRows());
//		m = new Matrix();
//		m.setTamany(rows, cols);
//		sm = new SparseMatrix(m);
//		assertEquals(cols, sm.getNRows());
	}

	@Test
	public void testGetNCols() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		assertEquals(cols, sm.getNCols());
		Matrix m = new Matrix();
		m.setTamany(rows, cols);
		sm = new SparseMatrix(m);
		assertEquals(cols, sm.getNCols());
		sm = new SparseMatrix(sm);
		assertEquals(cols, sm.getNCols());
		
		// Matrix of 0 x 0 (?) this makes any sense? dunno
//		rows = 0;
//		cols = 0;
//		sm = new SparseMatrix(rows, cols);
//		assertEquals(cols, sm.getNCols());
//		m = new Matrix();
//		m.setTamany(rows, cols);
//		sm = new SparseMatrix(m);
//		assertEquals(cols, sm.getNCols());
	}

	@Test
	public void testGetRow() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		ArrayList<SparseVector> svRows = new ArrayList<SparseVector>();
		ArrayList<SparseVector> svCols = new ArrayList<SparseVector>();
		for (int i = 0; i < rows; ++i) svRows.add(new SparseVector());
		for (int i = 0; i < cols; ++i) svCols.add(new SparseVector());
		
		
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = rand.nextFloat();
				sm.set(i, j, f);
				svRows.get(i).put(j,f);
				svCols.get(j).put(i, f);
			}
		}
		
		for (int i = 0; i < rows; ++i) {
			assertEquals(svRows.get(i), sm.getRow(i));
		}
	}

	@Test
	public void testGetCol() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		ArrayList<SparseVector> svRows = new ArrayList<SparseVector>();
		ArrayList<SparseVector> svCols = new ArrayList<SparseVector>();
		for (int i = 0; i < rows; ++i) svRows.add(new SparseVector());
		for (int i = 0; i < cols; ++i) svCols.add(new SparseVector());
		
		
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = rand.nextFloat();
				sm.set(i, j, f);
				svRows.get(i).put(j,f);
				svCols.get(j).put(i, f);
			}
		}
		
		for (int i = 0; i < cols; ++i) {
			assertEquals(svCols.get(i), sm.getCol(i));
		}
	}

	@Test
	public void testGetValue() {
		testSet();
	}

	@Test
	public void testTranspose() {
		Random rand = new Random();
		int rows = rand.nextInt(5) + 1;
		int cols = rand.nextInt(5) + 1;
		SparseMatrix sm = new SparseMatrix(rows, cols);
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Float f = rand.nextFloat();
				if (rand.nextBoolean()) sm.set(i, j, f);
			}
		}
		SparseMatrix transposed = new SparseMatrix(sm);
		sm.transpose();
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				assertEquals(sm.getValue(i, j), transposed.getValue(j, i));
			}
		}
		
	}

	@Test
	public void testMultiplySparseMatrixSparseMatrix() {
		SparseMatrix sm1 = new SparseMatrix(3,5), sm2 = new SparseMatrix(5,2);
		sm1.set(0, 0, 1.f);
		sm1.set(0, 1, 2.f);
		sm1.set(0, 3, 3.f);
		sm1.set(1, 0, 4.f);
		sm1.set(1, 2, 5.f);
		sm1.set(1, 4, 6.f);
		sm1.set(2, 1, 7.f);
		sm1.set(2, 3, 2.f);
		sm1.set(2, 4, 7.f);
		
		sm2.set(0, 0, 0.5f);
		sm2.set(1, 1, 0.5f);
		sm2.set(2, 0, 0.5f);
		sm2.set(3, 1, 0.5f);
		sm2.set(4, 0, 0.5f);
		
		ArrayList<SparseMatrix> sms = new ArrayList<SparseMatrix>(2);
		sms.add(sm1);
		sms.add(sm2);
		SparseMatrix resul  = MatrixChainMultiplication.compute(sms);
		SparseMatrix result = SparseMatrix.multiply(sm1, sm2);
		
		for (int i = 0; i < result.getNRows(); ++i) {
			for (int j = 0; j < result.getNCols(); ++j) {
				assertEquals(result.getValue(i, j), resul.getValue(i, j), 0.00001f);
			}
		}
		
		assertEquals(new Float(0.5f),result.getValue(0, 0));
		assertEquals(new Float(2.5f),result.getValue(0, 1));
		assertEquals(new Float(7.5f),result.getValue(1, 0));
		assertEquals(new Float(0.f),result.getValue(1, 1));
		assertEquals(new Float(3.5f),result.getValue(2, 0));
		assertEquals(new Float(4.5f),result.getValue(2, 1));
		
		SparseMatrix sm3 = new SparseMatrix(3,4), sm4 = new SparseMatrix(4,3);
		
		sm3.set(0, 0, 0.70710677f);
		sm3.set(0, 1, 0.70710677f);
		sm3.set(1, 1, 0.57735026f);
		sm3.set(1, 2, 0.57735026f);
		sm3.set(1, 3, 0.57735026f);
		sm3.set(2, 3, 1.f);
		
		sm4.set(0, 0, 1.f);
		sm4.set(1, 0, 0.70710677f);
		sm4.set(1, 1, 0.70710677f);
		sm4.set(2, 1, 1.f);
		sm4.set(3, 1, 0.70710677f);
		sm4.set(3, 2, 0.70710677f);
		
		SparseMatrix sm5 = new SparseMatrix(sm3);
		
		SparseMatrix result2 = SparseMatrix.multiply(sm5, sm4);
		
		assertEquals(1.2071067541798329f, result2.getValue(0, 0), 0.000001);
		assertEquals(0.4999999841798329f, result2.getValue(0, 1), 0.000001);
		
		SparseMatrix result3 = SparseMatrix.multiply(result2, result);
		sms = new ArrayList<SparseMatrix>(4);
		sms.add(sm5);
		sms.add(sm4);
		sms.add(sm1);
		sms.add(sm2);
		SparseMatrix result4 = MatrixChainMultiplication.compute(sms);

		assertEquals(result4.getNCols(), result3.getNCols());
		assertEquals(result4.getNRows(), result3.getNRows());
		
		for (int i = 0; i < result3.getNRows(); ++i) {
			for (int j = 0; j < result3.getNCols(); ++j) {
				assertEquals(result3.getValue(i, j), result4.getValue(i, j), 0.00001f);
			}
		}
		
		
	}

	@Test
	public void testMultiplyMatrixSparseMatrix() {
		SparseMatrix sm = new SparseMatrix(5,2), sm2 = new SparseMatrix(3,5);
		
		sm2.set(0, 0, 1.f);
		sm2.set(0, 1, 2.f);
		sm2.set(0, 3, 3.f);
		sm2.set(1, 0, 4.f);
		sm2.set(1, 2, 5.f);
		sm2.set(1, 4, 6.f);
		sm2.set(2, 1, 7.f);
		sm2.set(2, 3, 2.f);
		sm2.set(2, 4, 7.f);
		
		Matrix m = sm2.toMatrix();
		
		sm.set(0, 0, 0.5f);
		sm.set(1, 1, 0.5f);
		sm.set(2, 0, 0.5f);
		sm.set(3, 1, 0.5f);
		sm.set(4, 0, 0.5f);
		
		SparseMatrix result = SparseMatrix.multiply(m, sm);
		assertEquals(new Float(0.5f),result.getValue(0, 0));
		assertEquals(new Float(2.5f),result.getValue(0, 1));
		assertEquals(new Float(7.5f),result.getValue(1, 0));
		assertEquals(new Float(0.f),result.getValue(1, 1));
		assertEquals(new Float(3.5f),result.getValue(2, 0));
		assertEquals(new Float(4.5f),result.getValue(2, 1));
	}

	@Test
	public void testMultiplySparseMatrixMatrix() {
		SparseMatrix sm1 = new SparseMatrix(3,5), sm2 = new SparseMatrix(5,2);
		sm1.set(0, 0, 1.f);
		sm1.set(0, 1, 2.f);
		sm1.set(0, 3, 3.f);
		sm1.set(1, 0, 4.f);
		sm1.set(1, 2, 5.f);
		sm1.set(1, 4, 6.f);
		sm1.set(2, 1, 7.f);
		sm1.set(2, 3, 2.f);
		sm1.set(2, 4, 7.f);
		
		sm2.set(0, 0, 0.5f);
		sm2.set(1, 1, 0.5f);
		sm2.set(2, 0, 0.5f);
		sm2.set(3, 1, 0.5f);
		sm2.set(4, 0, 0.5f);
		
		Matrix m = sm2.toMatrix();
		
		SparseMatrix result = SparseMatrix.multiply(sm1, m);
		assertEquals(new Float(0.5f),result.getValue(0, 0));
		assertEquals(new Float(2.5f),result.getValue(0, 1));
		assertEquals(new Float(7.5f),result.getValue(1, 0));
		assertEquals(new Float(0.f),result.getValue(1, 1));
		assertEquals(new Float(3.5f),result.getValue(2, 0));
		assertEquals(new Float(4.5f),result.getValue(2, 1));
	}

	@Test
	public void testNormaliceRows() {
		SparseMatrix sm = new SparseMatrix(2,2);
		sm.set(0, 0, 1.f);
		sm.set(0, 1, 2.f);
		sm.set(1, 0, 3.f);
		sm.set(1, 1, 4.f);
		sm.normaliceRows();
		assertEquals(0.4472f,sm.getValue(0, 0),0.0001f);
		assertEquals(0.8944f,sm.getValue(0, 1),0.0001f);
		assertEquals(0.6f,   sm.getValue(1, 0),0.0001f);
		assertEquals(0.8f,   sm.getValue(1, 1),0.0001f);
		
		sm = new SparseMatrix(4,4);
		sm.set(3, 0, 1.f);
		sm.set(3, 1, 2.f);
		sm.set(1, 0, 3.f);
		sm.set(1, 1, 4.f);
		sm.normaliceRows();
		assertEquals(0.4472f,sm.getValue(3, 0),0.0001f);
		assertEquals(0.8944f,sm.getValue(3, 1),0.0001f);
		assertEquals(0.6f,   sm.getValue(1, 0),0.0001f);
		assertEquals(0.8f,   sm.getValue(1, 1),0.0001f);
		assertEquals(0.f,    sm.getValue(0, 0),0.0001f);
		assertEquals(0.f,    sm.getValue(0, 1),0.0001f);
		assertEquals(0.f,    sm.getValue(3, 2),0.0001f);
		assertEquals(0.f,    sm.getValue(3, 3),0.0001f);
		// more assertEqual 0 bla bla
	}

	@Test
	public void testNumberOfNotZeros() {
		SparseMatrix sm = new SparseMatrix(2,2);
		sm.set(0, 0, 1.f);
		sm.set(0, 1, 2.f);
		sm.set(1, 0, 3.f);
		sm.set(1, 1, 4.f);
		assertEquals(4, sm.numberOfNotZeros());
		
		sm.set(0, 0, 0.f);
		assertEquals(3, sm.numberOfNotZeros());
	}

	@Test
	public void testToMatrix() {
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
		
		SparseMatrix sm = new SparseMatrix(m);
		SparseMatrix sm2 = new SparseMatrix(sm);
		sm2.transpose();
		SparseMatrix result = SparseMatrix.multiply(sm, sm2);
		m = result.toMatrix();
		
		for (int i = 0; i < rows; ++i) {
			for(int j = 0; j < rows; ++j) {
				assertEquals(m.getValue(i, j),result.getValue(i, j));
			}
		}
		
	}
	
}
