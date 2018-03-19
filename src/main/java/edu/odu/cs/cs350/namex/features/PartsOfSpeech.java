package edu.odu.cs.cs350.namex.features;


/*
 * Determines whether a given piece of text is an Article, Conjunction, or specific piece of punctuation
 * Specifically looks for: 'A', 'an', 'the', 'for', 'and', 'nor', 'but', 'or', 'yet', 'so', '.', ',', and '-'
 * Letter casing is irrelevant
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is one of the common parts of speech which may be near or 
 * 				    not near a personal name  
 */
public class PartsOfSpeech implements Feature {
    
	@Override
	public String doesApply(String text) {
		
		if(isArticle(text)) return "Article";
		if(isConjunction(text)) return "Conjunction";
		if(hasPeriod(text)) return "Period";
		if(hasComma(text)) return "Comma";
		if(hasHyphen(text)) return "Hyphen";
		return "other";
	}
	
	private boolean isArticle(String text) {
		for (int i=0; i<text.length(); i++) {

	        if (text.charAt(i) == 'a' || text.charAt(i) == 'A')
	        {
	        	if((i == 0) && (text.length() == 1)) 
	        	{
	        		return true;
	        	}
	        }
	        

	        else if (text.charAt(0) == 'a' || text.charAt(0) == 'A')
	        {
	        		if(text.length() == 2 && (text.charAt(1) == 'n' || text.charAt(1) == 'N'))
	        		{
	        			return true;
	        		}
	        }
	    

	        else if (text.charAt(0) == 't' || text.charAt(0) == 'T')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'h' || text.charAt(1) == 'H') &&
    				(text.charAt(2) == 'e' || text.charAt(2) == 'E'))
        		{
        			return true;
        		}
	        }
	    }
	    
		return false;
	}
	
	private boolean isConjunction(String text) {
		for (int i=0; i<text.length(); i++)
	    {
			if (text.charAt(0) == 'f' || text.charAt(0) == 'F')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'o' || text.charAt(1) == 'O') &&
    				(text.charAt(2) == 'r' || text.charAt(2) == 'R'))
        		{
        			return true;
        		}
	        }
			else if (text.charAt(0) == 'a' || text.charAt(0) == 'A')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'n' || text.charAt(1) == 'N') &&
    				(text.charAt(2) == 'd' || text.charAt(2) == 'D'))
        		{
        			return true;
        		}
	        }
			else if (text.charAt(0) == 'n' || text.charAt(0) == 'N')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'o' || text.charAt(1) == 'O') &&
    				(text.charAt(2) == 'r' || text.charAt(2) == 'R'))
        		{
        			return true;
        		}
	        }
			else if (text.charAt(0) == 'b' || text.charAt(0) == 'B')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'u' || text.charAt(1) == 'U') &&
    				(text.charAt(2) == 't' || text.charAt(2) == 'T'))
        		{
        			return true;
        		}
	        }
			else if (text.charAt(0) == 'o' || text.charAt(0) == 'O')
	        {
        		if(text.length() == 2 && (text.charAt(1) == 'r' || text.charAt(1) == 'R'))
        		{
        			return true;
        		}
	        }
	        else if (text.charAt(0) == 'y' || text.charAt(0) == 'Y')
	        {
        		if(text.length() == 3 && (text.charAt(1) == 'e' || text.charAt(1) == 'E') &&
    				(text.charAt(2) == 't' || text.charAt(2) == 'T'))
        		{
        			return true;
        		}
	        }
	        else if (text.charAt(i) == 's' || text.charAt(i) == 'S')
	        {
        		if(text.length() == 2 && (text.charAt(1) == 'o' || text.charAt(1) == 'O'))
        		{
        			return true;
        		}
	        }
	    }
		return false;
	}
	
	private boolean hasPeriod(String text) {
		for (int i=0; i<text.length(); i++)
	    {
	        if (text.charAt(i) == '.')
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean hasComma(String text) {
		for (int i=0; i<text.length(); i++)
	    {
	        if (text.charAt(i) == ',')
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean hasHyphen(String text) {
		for (int i=0; i<text.length(); i++)
	    {
	        if (text.charAt(i) == '-')
	        {
	            return true;
	        }
	    }
	    return false;
	}
}