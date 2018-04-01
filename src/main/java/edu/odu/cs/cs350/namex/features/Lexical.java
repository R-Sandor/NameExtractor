package edu.odu.cs.cs350.namex.features;

import java.util.ArrayList;
import java.util.List;

/*
 * Determines whether a given piece of text is a New Line, Capital Letter, Punctuation mark, Number,
 * completely capitalized, or initially capitalized
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is one of the common parts of speech which may be near or 
 * 				    not near a personal name  
 */
public class Lexical implements Feature {
    
	@Override
	public String doesApply(String text) {
		
		if(isNewLine(text)) return "NewLine";
		if(isCapitalLetter(text)) return "CapLetter";
		if(isPunctuation(text)) return "Punctuation";
		if(hasNumber(text)) return "Number";
		if(isAllCaps(text)) return "AllCaps";
		if(isCapitalized(text)) return "Capitalized";
		return "other";
	}
	
	private boolean hasNumber(String text) {
		for(int i=text.length()-1; i>=0; i--) {
			if(Character.isDigit(text.charAt(i)))
				return true;
		}
		return false;
	}
	
	private boolean isNewLine(String text) {
		if(text.length()==1) {
		String newLine = "\n";
		return newLine.contains(String.valueOf(text.charAt(0)));
		}
		return false;
	}
	private boolean isCapitalized(String text) {  
		
		if (Character.isUpperCase(text.charAt(0))) return true;
		
		return false;
	}
	
	private boolean  isAllCaps(String text) {
	    for (int i=0; i<text.length(); i++)
	    {
	        if (Character.isLowerCase(text.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}
	
	private boolean isCapitalLetter(String text) {
		if(text.length()==1) {
	        return Character.isUpperCase(text.charAt(0)); 
		}
	return false;
	}
	
	private boolean isPunctuation(String text) {
		if(text.length()==1) {
		String punctutations = ".,;\\:?!$%()/\"";
		return punctutations.contains(String.valueOf(text.charAt(0)));
		}
		return false;
	}
	
	public List<String> separateText(String blockText) {
		
		List<String> text = new ArrayList<>();
		
		StringBuilder currentTextBuilder = new StringBuilder("");
		for (int i = 0; i < blockText.length(); i++) {
			char currentChar = blockText.charAt(i);
			if (currentChar == ' ' || currentChar == '\t') {
				if (currentTextBuilder.length() > 0) {
					text.add(currentTextBuilder.toString());
					currentTextBuilder = new StringBuilder("");
				}
				continue;
			}
			String charAsString = String.valueOf(currentChar);
			
			boolean partOfTag = false;
			if(currentChar == '/' && ((i-4) >= 0 && (i+1 < blockText.length()))) {
				partOfTag = (blockText.substring(i-4, i+2).equals("<PER/>"));
			}
			
			if ((isPunctuation(charAsString) && (!partOfTag)) || charAsString.equals("\n")) {
				if (currentTextBuilder.length() > 0) {
					text.add(currentTextBuilder.toString());
					currentTextBuilder = new StringBuilder("");
				}
				// Adding the current punctuation or \n.
				text.add(charAsString);
				continue;
			}
			currentTextBuilder.append(charAsString);
			
			// Have reached the end of the text.
			if (i == blockText.length() - 1) {
				text.add(currentTextBuilder.toString());
			}
		}
		
		
		return text;
	}
}