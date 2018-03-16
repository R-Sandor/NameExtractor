package edu.odu.cs.cs350.namex.features;

import java.util.List;

public class Gazetteer implements Feature {
	
	@Override
	public String doesApply(String word) {	
		return "";
	}
	
	public void performDatabaseFetching() {
	}
	
	private boolean isCommonFirstName(String word) {
		return false;
	}
	
	private boolean isCommonLastName(String word) {
		return false;
	}
	
	private boolean isDictionaryWord(String word) {
		return false;
	}
	
	private boolean hasSuffixes(String word) {
		return false;
	}
	
	private boolean isUsaCity(String word) {
		return false;
	}
	
	private boolean isUsaState(String word) {
		return false;
	}
	
	private boolean isTerritory(String word) {
		return false;
	}
	
	private boolean isCountry(String word) {
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
