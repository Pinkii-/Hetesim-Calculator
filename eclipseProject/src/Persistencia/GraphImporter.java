package Persistencia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import Dominio.Graf;
import Dominio.Node;

/**
 * 
 * @author Gonzalo Diez
 * 
 */

public class GraphImporter {
	static public Graf g;
	static HashMap <Integer,Integer> authors = new HashMap <Integer,Integer>();
	static HashMap <Integer,Integer> papers = new HashMap <Integer,Integer>();
	static HashMap <Integer,Integer> terms = new HashMap <Integer,Integer>();
	static HashMap <Integer,Integer> confs = new HashMap <Integer,Integer>();
	
	static String directoryPath = "/home/pinkii/Documents/PROP/DBLP_four_area/";
	
	static public Graf leMagicGoesOn(String path) {
		directoryPath = path;
		g = new Graf();
		authors = new HashMap <Integer,Integer>();
		papers = new HashMap <Integer,Integer>();
		terms = new HashMap <Integer,Integer>();
		confs = new HashMap <Integer,Integer>();
		

		importElements("paper.txt",papers,Node.Type.Paper);
		System.out.println("Papers Done");
		
		importElements("author.txt",authors,Node.Type.Autor);
		System.out.println("Authors Done");
		importRelations("paper_author.txt",authors,Node.Type.Autor);
		System.out.println("relaciones autor Done");
		
		authors = null;
		
		importElements("conf.txt",confs,Node.Type.Conferencia);
		System.out.println("Conferencias Done");
		importRelations("paper_conf.txt",confs,Node.Type.Conferencia);
		System.out.println("relaciones conf Done");
		
		confs = null;
		
		importElements("term.txt",terms,Node.Type.Terme);
		System.out.println("Terme Done");
		importRelations("paper_term.txt",terms,Node.Type.Terme);
		System.out.println("relaciones term Done");
		
		terms = null;
		papers = null;
		
		return g;
	}

	static private void importRelations(String path, HashMap<Integer, Integer> map, Node.Type type) {
		try {
			for (String line: Files.readAllLines(Paths.get(directoryPath).resolve(path),StandardCharsets.ISO_8859_1)) {
				String[] sings = line.split("\\s+");
				Integer paper = Integer.parseInt(sings[0]);
				Integer other = Integer.parseInt(sings[1]);
//				System.out.println(paper + " " + papers.get(paper) + " " + other + " " + map.get(other));
				g.setArc(papers.get(paper), map.get(other), type);
			}
		} catch (IOException e) {
			System.out.println("I'm so sorry, but I couldn't read your file :(");
			e.printStackTrace();
			System.exit(0);
		}
		
	}

	static private void importElements(String path, HashMap<Integer, Integer> map, Node.Type type) {
		
		try {
			for (String line: Files.readAllLines(Paths.get(directoryPath).resolve(path),StandardCharsets.ISO_8859_1)) {
				String[] sings = line.split("\\t");
				Integer id = Integer.parseInt(sings[0]);
				String name = sings[1];
				map.put(id, g.addNode(type, id, name));
			}
		} catch (IOException e) {
			System.out.println("I'm so sorry, but I couldn't read your file :( " + Paths.get(directoryPath).resolve(path));
			e.printStackTrace();
			System.exit(0);
		}
		
	}

}
