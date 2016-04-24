/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CtrlPaths {
	private Map<String, Path> paths;
	private Map<String, Boolean> modifiedPaths;
	


	public CtrlPaths() {
		paths = new HashMap<String, Path>();
		modifiedPaths = new HashMap<String, Boolean>();
		initDefaultPaths();
	}

	public CtrlPaths(ArrayList<Path> pathArray) {
		paths = new HashMap<String, Path>();
		modifiedPaths = new HashMap<String, Boolean>();
		initDefaultPaths();
		for (Path p : pathArray) {
			paths.put(p.getNom(), p);
			modifiedPaths.put(p.getNom(), false);
		}
	}

	public Path getPath(String pathName) {
		if (paths.containsKey(pathName))
			return paths.get(pathName);
		else
			System.out.println("Path not found");
		return new Path();
	}

	public void setPath(String pathName, Path path) {
		if (paths.containsKey(pathName)) {
			paths.replace(pathName, path);
			modifiedPaths.replace(pathName, true);
		}

		else {
			System.out.println("Path not found");
		}
	}

	public void modifyPath(String pathName, String newPathContent) {
		if (paths.containsKey(pathName)) {
			Path oldPath = paths.get(pathName);
			Path modifiedPath = new Path();
			modifiedPath.setContingut(newPathContent);
			modifiedPath.setDescripcio(oldPath.getDescripcio());
			modifiedPath.setNom(oldPath.getNom());
			paths.replace(pathName, modifiedPath);
			modifiedPaths.replace(pathName, true);
		} else {
			System.out.println("Path not found");
		}
	}

	public void addPath(String pathContent, String pathName, String description) {
		if (!paths.containsKey(pathName)) {
			Path p = new Path();
			p.setContingut(pathContent);
			p.setNom(pathName);
			p.setDescripcio(description);
			paths.put(pathName, p);
			modifiedPaths.put(pathName, true);
		} else {
			System.out.println("Path already exists");
		}
	}

	public void erasePath(String pathName) {
		if (paths.containsKey(pathName)) {
			// TODO this'll probably fuck something up
			paths.replace(pathName, null);
			modifiedPaths.replace(pathName, true);
		} else {
			System.out.println("Path not found");
		}
	}
	
	public ArrayList<String> getPathNames(){
		ArrayList<String> pathNames = new ArrayList<String>();
		for (Map.Entry<String, Path> entry : paths.entrySet()) {
			pathNames.add(entry.getKey());
		}
		return pathNames;
	}

	public String toString() {
		String ret = new String();
		for (Map.Entry<String, Path> entry : paths.entrySet()) {
			ret += entry.getValue().toString();
		}
		return ret;
	}

	public ArrayList<Path> getModifiedPaths() {
		ArrayList<Path> ret = new ArrayList<Path>();
		for (Map.Entry<String, Boolean> entry : modifiedPaths.entrySet()) {
			if (entry.getValue()) {
				ret.add(paths.get(entry.getKey()));
			}
		}
		return ret;
	}
	
	private void initDefaultPaths(){
		Path p = new Path();
		p.setNom("APA");
		p.setContingut("APA");
		paths.put("APA", p);
		p = new Path();
		p.setNom("APC");
		p.setContingut("APC");
		paths.put("APC", p);
		p = new Path();
		p.setNom("APT");
		p.setContingut("APT");
		paths.put("APT", p);
	}

}
