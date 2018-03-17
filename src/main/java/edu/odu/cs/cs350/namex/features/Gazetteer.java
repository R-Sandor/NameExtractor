package edu.odu.cs.cs350.namex.features;

import java.util.List;

public class Gazetteer implements Feature {
	
	@Override
	public String doesApply(String text) {	
		return "";
	}
	
	public void performDatabaseFetching() {
	}
	
	private boolean isCommonFirstName(String text) {
		return false;
	}
	
	private boolean isCommonLastName(String text) {
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
