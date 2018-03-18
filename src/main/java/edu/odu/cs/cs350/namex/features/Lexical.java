package edu.odu.cs.cs350.namex.features;

public class Lexical implements Feature {
    
	@Override
	public String doesApply(String text) {
		isPuncutation(text);//needs more
		return "";
	}
	
	private boolean hasNumber(String text) {
		return false;
	}
	
	private boolean isCapitalized(String text) {        
		    for(int i=text.length()-1; i>=0; i--) {
		        if(Character.isUpperCase(text.charAt(i))) {
		            return true;
		        }
		    }
		return false;
	}
	
	private boolean isNewLine(String text) {
		return false;
	}
	
	private boolean isAllCaps(String text) {
		return false;
	}
	
	private boolean isCapitalLetter(String text) {
	    for(int i=text.length()-1; i>=0; i--) {
	        if(Character.isUpperCase(text.charAt(i))) {
	            return true;
	        }
	    }
	return false;
}
	
	private boolean isPuncutation(String text) {
		if(text.length()==1) {
		String punctutations = ".,;:?!$%'\"" ;
		return punctutations.contains(String.valueOf(text.charAt(0)));
		}
		return false;
	}
	
	public String[] separateText(String blockText) {
		return null;
	}
}