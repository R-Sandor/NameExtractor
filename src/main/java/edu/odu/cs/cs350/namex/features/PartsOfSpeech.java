package edu.odu.cs.cs350.namex.features;

public class PartsOfSpeech implements Feature {
    
	@Override
	public String doesApply(String word) {
		return "";
	}
	
	private boolean isArticle(String word) {
		return false;
	}
	
	private boolean isConjunction(String word) {
		return false;
	}
	
	private boolean hasPunctuation(String word) {
		return false;
	}
}
