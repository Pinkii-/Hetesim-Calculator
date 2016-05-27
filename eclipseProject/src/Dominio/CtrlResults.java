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

	public String setLastResult(Result lastResult) {
		this.lastResult = lastResult;
		return lastResult.getIdResult();
	}
	
	public ArrayList<ArrayList<String>> getLastResultFormatted(String resultId){
		return getFormatted(resultId);
	}

	public String addLastResult() {
		//String resultId = String.valueOf(System.currentTimeMillis());
		addResult(lastResult.getIdResult(), lastResult);
		return lastResult.getIdResult();
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
	
	public ArrayList<String> getAllResultIds(){		
		ArrayList<String> ret = new ArrayList<String>();
		for (Map.Entry<String, Result> entry : results.entrySet()) {
			ret.add(entry.getKey().toString());
		}
		return ret; 
	}
	
	/**
	 * @param resultId The id of the result to be formatted.
	 * @return
	 * Returns a matrix containing the info associated with the <b><i>result</i></b> with id <b>resultId</b>
	 * formatted by the following criteria: <br><br>
	 * <i>A single row</i> composed of:
	 * <ol>
	 *		<li>The <b><i>result</i></b>'s Id</li>
	 *		<li>The used path's name</li>
	 *		<li>The used graph's Id</li>
	 * </ol>
	 * <i>Multiple rows</i> containing all the info of the node pairs associated with the <b><i>result</i></b>
	 * formatted like so:
	 * <ol>
	 * 	 <li>First Node's name</li>
	 * 	 <li>First Node's type</li>
	 * 	 <li>Second Node's name</li>
	 * 	 <li>Second Node's type</li>
	 *	 <li>HeteSim value of its relatedness</li>
	 * </ol>
	 */
	
	public ArrayList<ArrayList<String>> getFormatted(String resultId){
		Result res =  getResult(resultId);
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<String> firstCol = new ArrayList<String>();
		firstCol.add(resultId);
		firstCol.add(res.getUsedPath());
		firstCol.add(res.getIdGraf());
		ret.add(firstCol);
		ArrayList<NodePair> nodes = getResult(resultId).getResult();
		for (NodePair np: nodes){
			ArrayList<String> col = new ArrayList<String>();
			col.add(np.pairN.first.getNom());
			col.add(np.pairN.first.getTipus().toString());
			col.add(np.pairN.second.getNom());
			col.add(np.pairN.second.getTipus().toString());
			col.add(String.valueOf(np.getHetesim()));
			ret.add(col);
		}
		return ret;
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
	
	public void resultsStored(){
		for (Map.Entry<String, Boolean> entry : modifiedResults.entrySet()) {
			entry.setValue(false);
		}
	}

}
