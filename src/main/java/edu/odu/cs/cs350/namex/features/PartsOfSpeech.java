package edu.odu.cs.cs350.namex.features;

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
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 1)) 
	        	{
	        		if(text.charAt(i+1) == ' ')
	        		{
	        			return true;
	        		}
	        		return true;
	        	}
	        }
	        

	        else if (text.charAt(i) == 'a' || text.charAt(i) == 'A')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 2)) 
	        	{
	        		if((text.charAt(i+1) == 'n' || text.charAt(i+1) == 'N') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	    

	        else if (text.charAt(i) == 't' || text.charAt(i) == 'T')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'h' || text.charAt(i+1) == 'H') &&
        				(text.charAt(i+2) == 'e' || text.charAt(i+1) == 'E') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	    }
	    
		return false;
	}
	
	private boolean isConjunction(String text) {
		for (int i=0; i<text.length(); i++)
	    {
	        if (text.charAt(i) == 'f' || text.charAt(i) == 'F')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'o' || text.charAt(i+1) == 'O') &&
        				(text.charAt(i+2) == 'r' || text.charAt(i+1) == 'R') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 'a' || text.charAt(i) == 'A')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'n' || text.charAt(i+1) == 'N') &&
        				(text.charAt(i+2) == 'd' || text.charAt(i+1) == 'D') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 'n' || text.charAt(i) == 'N')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'o' || text.charAt(i+1) == 'O') &&
        				(text.charAt(i+2) == 'r' || text.charAt(i+1) == 'R') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 'b' || text.charAt(i) == 'B')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'u' || text.charAt(i+1) == 'U') &&
        				(text.charAt(i+2) == 't' || text.charAt(i+1) == 'T') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 'o' || text.charAt(i) == 'O')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 2)) 
	        	{
	        		if((text.charAt(i+2) == 'r' || text.charAt(i+1) == 'R') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 'y' || text.charAt(i) == 'Y')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'e' || text.charAt(i+1) == 'E') &&
        				(text.charAt(i+2) == 't' || text.charAt(i+1) == 'T') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
	        	}
	        }
	        else if (text.charAt(i) == 's' || text.charAt(i) == 'S')
	        {
	        	if((i == 0 || text.charAt(i-1) == ' ') && i < (text.length() - 3)) 
	        	{
	        		if((text.charAt(i+1) == 'o' || text.charAt(i+1) == 'O') &&
        				(text.charAt(i+1) == ' '))
	        		{
	        			return true;
	        		}
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