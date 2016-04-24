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
	
	public void printLastResult(){
		System.out.println(lastResult.toString());
	}

	public void printResults() {
		System.out.println(this.toString());
	}

}
