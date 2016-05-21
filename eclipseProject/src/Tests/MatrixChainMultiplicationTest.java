package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Dominio.MatrixChainMultiplication;
import Dominio.SparseMatrix;

public class MatrixChainMultiplicationTest {

	@Test
	public void testCompute() {
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
	

}
