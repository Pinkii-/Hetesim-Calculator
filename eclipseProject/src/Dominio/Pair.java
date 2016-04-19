package Dominio;

public class Pair<T,S> {

	public T first;
	public S second;

	//Pre: -
	//Post: first = f, second = s
	public Pair(T f, S s) {
		first = f;
		second = s;
	}
	
	public String toString() {
		return first + " " + second;
	}

}
