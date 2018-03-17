package edu.odu.cs.cs350.namex.features;

public class PartsOfSpeech implements Feature {
    
	@Override
	public String doesApply(String text) {
		return "";
	}
	
	private boolean isArticle(String text) {
		return false;
	}
	
	private boolean isConjunction(String text) {
		return false;
	}
	
	private boolean hasPunctuation(String text) {
		return false;
	}
}

