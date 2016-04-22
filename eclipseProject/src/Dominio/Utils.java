package Dominio;

public class Utils {
	public static Node.Type getNodeType(Integer i) {
		return Node.Type.values()[i];
	}

	public static Node.Label getNodeLabel(Integer i) {
		return Node.Label.values()[i];
	}
}
