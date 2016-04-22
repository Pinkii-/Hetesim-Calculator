package Dominio;

import java.util.ArrayList;
import java.util.Map;

public class CtrlPaths {
	Map<String, Path> paths;

	public CtrlPaths(ArrayList<Path> pathArray) {
		for (Path p : pathArray) {
			paths.put(p.getNom(), p);
		}
	}

	public Path getPath(String pathName) {
		return paths.get(pathName);
	}

	public void setPath(String pathName, Path path) {
		if (paths.containsKey(pathName))
			paths.replace(pathName, path);
	}

	public void modifyPath(String name, String newPathContent) {

		Path oldPath = paths.get(name);
		Path modifiedPath = new Path();
		modifiedPath.setContingut(newPathContent);
		modifiedPath.setDescripcio(oldPath.getDescripcio());
		modifiedPath.setNom(oldPath.getNom());
		paths.replace(name, modifiedPath);
	}

	public void addPath(String newPath, String name, String description) {
		Path p = new Path();
		p.setContingut(newPath);
		p.setNom(name);
		p.setDescripcio(description);
		paths.put(name, p);
	}

	public void erasePath() {
		// TODO
	}

}
