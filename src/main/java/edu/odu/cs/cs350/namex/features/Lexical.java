package edu.odu.cs.cs350.namex.features;

public class Lexical implements Feature {
    
	@Override
	public String doesApply(String text) {
		return "";
	}
	
	private boolean hasNumber(String text) {
		return false;
	}
	
	private boolean isCapitalized(String text) {
		return false;
	}
	
	private boolean isNewLine(String text) {
		return false;
	}
	
	public String[] separateText(String blockText) {
		return null;
	}
}