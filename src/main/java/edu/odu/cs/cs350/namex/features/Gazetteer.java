package edu.odu.cs.cs350.namex.features;

import java.util.List;

public class Gazetteer implements Feature {
	
	private String[] commonFirstNames = {
		"Joseph"
	};
	
	private String[] commonLastNames = {
		"Joseph"	
	};
	
	@Override
	public String doesApply(String text) {
		String binaryString = "";
		binaryString = isCommonFirstName(text) ? "1" : "0";
		binaryString += isCommonLastName(text) ? ", 1" : ", 0";
		return binaryString;
	}
	
	public void performDatabaseFetching() {
	}
	
	private boolean isCommonFirstName(String text) {
		for (String name : commonFirstNames) {
			if (name.equals(text)) return true;
		}
		return false;
	}
	
	private boolean isCommonLastName(String text) {
		for (String name : commonLastNames) {
			if (name.equals(text)) return true;
		}
		return false;
	}
	
	private boolean isDictionaryWord(String text) {
		return false;
	}
	
	private boolean hasSuffixes(String text) {
		return false;
	}
	
	private boolean isUsaCity(String text) {
		return false;
	}
	
	private boolean isUsaState(String text) {
		return false;
	}
	
	private boolean isTerritory(String text) {
		return false;
	}
	
	private boolean isCountry(String text) {
		return false;
	}
	
	public List<String> getCommonFirstNamesDTIC() {
		return null;
	}
	
	public List<String> getCommonLastNamesDTIC() {
		return null;
	}
	
	public List<String> getCommonFirstNamesCensus() {
		return null;
	}
	
	public List<String> getCommonLastNamesCensus() {
		return null;
	}
	
	
	
}
