package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
class SparseVector extends HashMap<Integer,Float> {
	
	static Float multiply(SparseVector sv1, SparseVector sv2) {
		Float ret = 0.f;
		Set<Integer> aux = new HashSet<Integer>(sv1.keySet());
		aux.retainAll(sv2.keySet());
		
		for (Integer k : aux) ret += sv1.get(k) * sv2.get(k);
		
		return ret;
	}
	
	static Float multiply(SparseVector sv1, ArrayList<Float> sv2) {
		Float ret = 0.f;
		Set<Integer> aux = new HashSet<Integer>(sv1.keySet());
		
		for (Integer k : aux) ret += sv1.get(k) * sv2.get(k);
		
		return ret;
	}
	
	static Float multiply(ArrayList<Float> v1, SparseVector sv2) {		
		return SparseVector.multiply(sv2, v1);
	}
	
	
	float norm() {
		Float total = 0.f;
		for (Integer i : keySet()) {
			total += (float) Math.pow(get(i), 2);
		}
		
		return (float) Math.sqrt(total);
	}
}