package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//import gnu.trove.map.hash.THashMap;

/**
 * 
 * @author Gonzalo Diez
 * 
 */

@SuppressWarnings("serial")
public class SparseVector extends /*T*/HashMap<Integer,Float> {
	
//	float total = 0;
	
//	public Float put(Integer key, Float value) {
//		total += Math.pow(value, 2);
//		return super.put(key, value);
//	}
	
	static Float multiply(SparseVector sv1, SparseVector sv2) {
		Float ret = 0.f;
//		Set<Integer> aux = new HashSet<Integer>(sv1.keySet());
//		System.out.println("first " + sv1.keySet() + " " + sv2.keySet());
//		aux.retainAll(sv2.keySet());
//		System.out.println(aux);
		
		if (sv1.keySet().size() < sv2.keySet().size()) {		
			for (Integer k : sv1.keySet()) if (sv2.containsKey(k)) ret += sv1.get(k) * sv2.get(k);
		}
		else {
			for (Integer k : sv2.keySet()) if (sv1.containsKey(k)) ret += sv1.get(k) * sv2.get(k);
		}
		
		return ret;
	}
	
	static Float multiply(SparseVector sv1, ArrayList<Float> sv2) {
		Float ret = 0.f;
		Set<Integer> aux = new HashSet<Integer>(sv1.keySet());
		
		try {
			for (Integer k : aux) ret += sv1.get(k) * sv2.get(k);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("The vector cant be smaller than the sparseVector");
			throw e;
		}
		return ret;
	}
	
	static Float multiply(ArrayList<Float> v1, SparseVector sv2) {		
		return SparseVector.multiply(sv2, v1);
	}
	
	
	float norm() {
		double total = 0.0;
		for (Integer i : keySet()) {
			total += Math.pow(get(i), 2);
		}
		return (float) Math.sqrt(total);
	}

}