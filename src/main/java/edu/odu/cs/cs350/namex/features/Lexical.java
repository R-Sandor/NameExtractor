package edu.odu.cs.cs350.namex.features;

public class Lexical implements Feature {
    
	@Override
	public String doesApply(String word) {
		return "";
	}
	
	private boolean hasNumber(String word) {
		return false;
	}
	
	private boolean isCapitalized(String word) {
		return false;
	}
	
	private boolean isNewLine(String word) {
		return false;
	}
	
	private boolean isWhitespace(String word) {
		return false;
	}
}
