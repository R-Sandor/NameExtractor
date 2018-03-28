package edu.odu.cs.cs350.namex.features;

/*
 * Determines whether a given piece of text is a common first name or last name based on a reference list
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is a common first name or last name
 */
public class Gazetteer implements Feature {
	
	
	@Override
	public String doesApply(String text) {
		return "";
	}
	
	private boolean isCommonFirstName(String text) {
		return false;
	}
	
	private boolean isCommonLastName(String text) {
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
}
