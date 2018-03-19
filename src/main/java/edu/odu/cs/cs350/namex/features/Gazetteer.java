package edu.odu.cs.cs350.namex.features;

import java.util.List;

/*
 * Determines whether a given piece of text is a common first name or last name based on a reference list
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is a common first name or last name
 */
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
	
	@SuppressWarnings("unused")
	private boolean isDictionaryWord(String text) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean hasSuffixes(String text) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean isUsaCity(String text) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean isUsaState(String text) {
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean isTerritory(String text) {
		return false;
	}
	
	@SuppressWarnings("unused")
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
