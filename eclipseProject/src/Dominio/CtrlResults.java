/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CtrlResults {
	private Map<String, Result> results;
	private Result lastResult;
	private Map<String, Boolean> modifiedResults;

	public CtrlResults() {
		lastResult = null;
		results = new HashMap<String, Result>();
		modifiedResults = new HashMap<String, Boolean>();
	}

	public CtrlResults(ArrayList<Result> pathArray) {
		lastResult = null;
		results = new HashMap<String, Result>();
		modifiedResults = new HashMap<String, Boolean>();
		for (Result r : pathArray) {
			results.put(r.getIdResult(), r);
		}
	}

	public Result getResult(String resultId) {
		if (results.containsKey(resultId))
			return results.get(resultId);
		else {
			System.out.println("Result not found");
			return null;
		}
	}

	public void setResult(String resultId, Result result) {
		if (results.containsKey(resultId)) {
			results.replace(resultId, result);
			modifiedResults.put(resultId, true);
		} else {
			System.out.println("Result not found");
		}
	}

	public void addResult(String resultId, Result result) {
		if (!results.containsKey(resultId)) {
			results.put(resultId, result);
			modifiedResults.put(resultId, true);
		} else
			System.out.println("Result already exists");
	}

	public String toString() {
		String ret = new String();
		for (Map.Entry<String, Result> entry : results.entrySet()) {
			ret += entry.getValue().toString();
		}
		return ret;
	}

	public void setLastResult(Result lastResult) {
		this.lastResult = lastResult;
	}

	public void addLastResult() {
		String nodeId = String.valueOf(System.currentTimeMillis());
		addResult(nodeId, lastResult);
	}

	public ArrayList<Result> getModifiedResults() {
		ArrayList<Result> ret = new ArrayList<Result>();
		for (Map.Entry<String, Boolean> entry : modifiedResults.entrySet()) {
			if (entry.getValue()) {
				ret.add(results.get(entry.getKey()));
			}
		}
		return ret;
	}
	
	//Returns an arrayList of strings formatted by the following criteria:
	/*
	 * 0) 0) Result's Id id 1)Name of the path used 2) GraphId
	 * 1) 0) First Node's name 1) First Node's type 2) Second Node's name 3) Second Node's type
	 * 1) 4) HeteSim value
	 * Number of rows is of variable size, depends on the size of the result
	 */
	
	public ArrayList<ArrayList<String>> getFormatted(String resultId){
		ArrayList<NodePair> nodes = getResult(resultId).getResult();
		//for(NodePair np: )
		return null;
	}
	
	public boolean isModified(String resultId){
		if(modifiedResults.containsKey(resultId))
			return modifiedResults.get(resultId);
		System.out.println("Result does not exist!");
		return false;
	}
	
	public void printLastResult(){
		System.out.println(lastResult.toString());
	}

	public void printResults() {
		System.out.println(this.toString());
	}

}
