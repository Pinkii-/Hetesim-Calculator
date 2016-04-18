package Dominio;

public class CtrlDataGraph {
	
	private String fileDirectory;
	
	public CtrlDataGraph(String filePath) {
		fileDirectory = filePath;
	}
	
	public void saveGraph(Graf g, String name) {
		
	}
	
	public Graf loadGraph(String name) {
		return new Graf();
	}

}
