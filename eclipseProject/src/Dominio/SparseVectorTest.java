package Dominio;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class SparseVectorTest {

	@Test
	public void testMultiplySparseVectorSparseVector() {
		SparseVector sv1 = new SparseVector(); // {2, 0, 1}
		SparseVector sv2 = new SparseVector(); // {2, 1, 0}
		
		sv1.put(0, 2.f);
		sv1.put(2, 1.f);
		
		sv2.put(0, 2.f);
		sv2.put(1, 1.f);
		float result = SparseVector.multiply(sv1, sv2);
		assert(4.f == result);
		
		sv1 = new SparseVector(); // {}
		result = SparseVector.multiply(sv1, sv2);
		assert(0.f == result);
		
		sv1 = sv2;
		sv2 = new SparseVector(); // {}
		result = SparseVector.multiply(sv1, sv2);
		assert(0.f == result);
		
		sv1 = new SparseVector();
		result = SparseVector.multiply(sv1, sv2);
		assert(0.f == result);
		
		sv1 = new SparseVector(); // {2, 0, 1}
		sv2 = new SparseVector(); // {2, 1, 0}
		
		sv1.put(0, 1.f);
		sv1.put(1, 2.f);
		sv1.put(2, 3.f);
		
		sv2.put(0, 4.f);
		sv2.put(1, 5.f);
		sv2.put(2, 6.f);
		
		result = SparseVector.multiply(sv1, sv2);
		assert(24.f == result);
	}

	@Test
	public void testMultiplySparseVectorArrayListOfFloat() {
		SparseVector sv2 = new SparseVector(); // {2, 0, 1}
		ArrayList<Float> v1 = new ArrayList<Float>(); // {2, 1, 0}
		
		sv2.put(0, 2.f);
		sv2.put(2, 1.f);
		
		v1.add(2.5f);
		v1.add(0.f);
		v1.add(1.f);
		float result = SparseVector.multiply(v1, sv2);
		assert(5.f == result);
		
		sv2 = new SparseVector(); // {}
		result = SparseVector.multiply(v1, sv2);
		assert(0.f == result);
		
		
		sv2 = new SparseVector();
		result = SparseVector.multiply(v1, sv2);
		assert(0.f == result);
		
		sv2 = new SparseVector(); // {2, 0, 1}
		v1 = new ArrayList<Float>(); // {2, 1, 0}
		
		sv2.put(0, 1.f);
		sv2.put(1, 2.f);
		sv2.put(2, 3.f);
		
		v1.add(4.f);
		v1.add(5.f);
		v1.add(6.f);
		
		result = SparseVector.multiply(v1, sv2);
		assert(24.f == result);
	}

	@Test
	public void testMultiplyArrayListOfFloatSparseVector() {
		SparseVector sv1 = new SparseVector(); // {2, 0, 1}
		ArrayList<Float> v2 = new ArrayList<Float>(); // {2, 1, 0}
		
		sv1.put(0, 2.f);
		sv1.put(2, 1.f);
		
		v2.add(2.5f);
		v2.add(0.f);
		v2.add(1.f);
		float result = SparseVector.multiply(sv1, v2);
		assert(5.f == result);
		
		sv1 = new SparseVector(); // {}
		result = SparseVector.multiply(sv1, v2);
		assert(0.f == result);
		
		
		sv1 = new SparseVector();
		result = SparseVector.multiply(sv1, v2);
		assert(0.f == result);
		
		sv1 = new SparseVector(); // {2, 0, 1}
		v2 = new ArrayList<Float>(); // {2, 1, 0}
		
		sv1.put(0, 1.f);
		sv1.put(1, 2.f);
		sv1.put(2, 3.f);
		
		v2.add(4.f);
		v2.add(5.f);
		v2.add(6.f);
		
		result = SparseVector.multiply(sv1, v2);
		assert(24.f == result);
	}

	@Test
	public void testNorm() {
		SparseVector sv = new SparseVector();
		sv.put(0, 1.f);
		sv.put(1, 2.f);
		sv.put(4, 3.f);
		sv.put(150, 4.f);
		
		float result = sv.norm();
		assert(result == (float) Math.sqrt(1 + 4 + 9 + 16));
		
		sv = new SparseVector();
		assert(sv.norm() == 0);
	}

}
