package Presentacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Dominio.CtrlDominio;
import Dominio.CtrlResults;
import Dominio.Pair;

	
public class FormattedResult extends ArrayList<ArrayList<String>> {
	
	private static final long serialVersionUID = 1L;
	//Insertar mas pairs innecesarios aqui
	public static final Pair<Integer,Integer> resultIdPosition = new Pair<Integer,Integer>(0,0);
	public static final Pair<Integer,Integer> resultTypePosition = new Pair<Integer,Integer>(0,1);
	public static final Pair<Integer,Integer> searchPathPosition = new Pair<Integer,Integer>(0,2);
	public static final Pair<Integer,Integer> searchGraphIdPosition = new Pair<Integer,Integer>(0,3);
	
	private String listedResult;
	private String idResult;
	private String resultType;
	private String searchPath;
	private String searchGraphId;
	private Integer numberOfValues;
	private Integer numberOfColumns;
	private Object[][] resultData;
	private HashMap<Integer,Float> oldValues;
	private HashMap<Integer,Float> newValues;
	private CtrlResults cr;
	
	private ArrayList<Boolean> modifiedResultValues;
	
	public FormattedResult(ArrayList<ArrayList<String>> res, CtrlResults cr) {
		for (int i = 0; i < res.size(); ++i) {
			for (int j = 0; j < res.get(i).size(); ++j) {
				System.out.println(res.get(i).get(j));
			}
		}
		System.out.println("Impreso");
		this.addAll(res);
		System.out.println(get(0));
		this.cr = cr;
		resultType = get(resultTypePosition.first).get(resultTypePosition.second);
		searchPath = get(searchPathPosition.first).get(searchPathPosition.second);
		searchGraphId = get(searchGraphIdPosition.first).get(searchGraphIdPosition.second);
		idResult = get(resultIdPosition.first).get(resultIdPosition.second);
		numberOfValues = size()-1;
		numberOfColumns = get(1).size();
		oldValues = new HashMap<Integer,Float>();
		newValues = new HashMap<Integer,Float>();
		
		modifiedResultValues = new ArrayList<Boolean>(numberOfValues);
		Collections.fill(modifiedResultValues, false);
		
		generateData();			
	}
	
	private void generateData() {
		
		resultData = new Object[numberOfValues][numberOfColumns];
		
		for (int i = 1; i <= numberOfValues; ++i) {
			for (int j = 0; j < numberOfColumns; ++j) {
				if (j == numberOfColumns-1) resultData[i-1][j] = new Float(Float.parseFloat(get(i).get(j)));
				else resultData[i-1][j] = get(i).get(j);
			}
		}
	}
	
	public void setOldValue(int nValue, Float value) {
		oldValues.put(nValue,value);
	}
	
	public Float getOldValue(int nValue) {
		return oldValues.get(nValue);
	}
	
	public void setNewValue(int nValue, Float value) {
		newValues.put(nValue, value);
	}
	
	public Float getNewValue(int nValue) {
		return newValues.get(nValue);
	}
	
	public void clearChanges() {
		newValues.clear();
		oldValues.clear();
	}
	
	public String getIdResult() {
		return idResult;
	}
	
	public String getResultType() {
		return resultType;
	}
	
	public String getSearchPath() {
		return searchPath;
	}
	
	public String getSearchGraphId() {
		return searchGraphId;
	}
	
	public int getNumberOfValues() {
		return numberOfValues;
	}
	
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	
	public Object[][] getResultData() {
		return resultData;
	}
	//Insertar más getters innecesarios aquí.
	
	public void commitChanges() {
		for (Integer index: newValues.keySet()) {
			cr.modifyResult(idResult, index, newValues.get(index));
		}
		
	}
	
	public String getAllInfo() {
		listedResult = "Search Type: " + resultType + ", Search Path: ";
	    listedResult += searchPath + ", Associated Graph: " + searchGraphId;
	    return listedResult;
	}
	
}
