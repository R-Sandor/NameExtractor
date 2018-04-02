package edu.odu.cs.cs350.namex;

import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.cs350.namex.features.Gazetteer;
import edu.odu.cs.cs350.namex.features.Lexical;
import edu.odu.cs.cs350.namex.features.PartsOfSpeech;

public class LearningMachine {

	
	
	
	public char calculateWord(String text) {
		char test = 0;
		return test;
	}
	
	public String learn(String blockText) {
		
		Lexical lexical = new Lexical();
		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
		Gazetteer gazetteer = new Gazetteer();
		List<String> splitText = lexical.separateText(blockText);
		List<String> mappedFeatures = new ArrayList<>();
		
		for (String text :splitText) {
			String currentText = lexical.doesApply(text);
			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
			currentText = currentText + ", " + gazetteer.doesApply(text);
			mappedFeatures.add(currentText);
		}
		
		String allFeatures = "";
		boolean first = true;
		for (String feature : mappedFeatures) {
			if (first) {
				allFeatures = feature;
				first = false;
				continue;
			}
			allFeatures += ", " + feature;
		}
		
		return allFeatures;
	}
	
	public String judgeBlock(String inputedBlock) {
		String parsedBlock =parse(inputedBlock);
		//TODO use the pre-trainned input to make a judgement.
		return null;
	}
	
	
	/*
	 * @param inputedBlock
	 * Creates the tokens for each word in the line of the 
	 * of the block. 
	 */
	public String parse(String inputedBlock) {
		// TODO Return a String analysis of the block such as CAP, Other, 1, 0, 1, etc.
		Lexical lexical = new Lexical();
		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
		Gazetteer gazetteer = new Gazetteer();
		List<String> splitText = lexical.separateText(inputedBlock);
		List<String> mappedFeatures = new ArrayList<>();
		
		for (String text :splitText) {
			String currentText = lexical.doesApply(text);
			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
			currentText = currentText + ", " + gazetteer.doesApply(text);
			// Testing whether line is returning what it should
			mappedFeatures.add(currentText);
		}
		
		String allFeatures = "";
		boolean first = true;
		for (String feature : mappedFeatures) {
			if (first) {
				allFeatures = feature;
				first = false;
				continue;
			}
			allFeatures += ", " + feature;
		}
		
		return allFeatures;
		//return null;
		
	}

	public void addCharToList(char resultOfCalc) {
		
	}
	// This is simply an interface to add a character to collection of results 
	// for a given block.
	
	public void readFeatureList() {
		
	}
	
	public String tagWrap(String inputedBlock, int key, int type) {
		String outputBlock = "";
		int wordCount = 0;
		int wordB[]; // Holds letter position of the beginning of words at the index of the words
		wordB = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //initializes array for 20 words
		int wordEnd[]; // Holds letter position of the beginning of words at the index of the words
		wordEnd = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

		int endOfLine = inputedBlock.length() - 1; //sets a value for the length of the string
		boolean word = false;


		
	    for (int i = 0; i < inputedBlock.length(); i++) {
	    	if (Character.isLetter(inputedBlock.charAt(i)) && !word)
	    		wordB[wordCount+1] = inputedBlock.length()-i;
	        // if the char is a letter, word = true.
	        if (Character.isLetter(inputedBlock.charAt(i)) && i != endOfLine) {
	            word = true;
	            
	            // if char isn't a letter and there have been letters before,
	            // counter goes up.
	        } else if (!Character.isLetter(inputedBlock.charAt(i)) && word) {
	            wordCount++;
	            wordEnd[wordCount]=inputedBlock.length()-i;
	            word = false;
	            // last word of String; if it doesn't end with a non letter, it
	            // wouldn't count without this.
	        } else if (Character.isLetter(inputedBlock.charAt(i)) && i == endOfLine) {
	            wordCount++;
	        }
	    }
		
		if(type==1){
		outputBlock = new StringBuilder(inputedBlock).insert(inputedBlock.length()-wordB[key], "<PER>").toString();
		}
		if(type==2) {
		outputBlock = new StringBuilder(inputedBlock).insert(inputedBlock.length()-wordEnd[key], "<PER/>").toString();	
		}
		return outputBlock;
		
	}
	
	/*
	 * Sample Input: "No John, no.", 1
	 * Sample Output: {{"null","No","John"},{"No","John",","},{"John",",","no"},{",","no","."},{"no",".","null"}}
	 */
	public String[][] shingling(String blockText, int shingleSize) {
		Lexical lexical = new Lexical();
		List<String> splitText = lexical.separateText(blockText);
		String [][] answer = new String[splitText.size()][(2 * shingleSize) + 1];
		for(int x=0;x<splitText.size();x++) {
			int frontSpace = shingleSize - x;
			if(frontSpace < 0) frontSpace = 0;
			for(int a=0;a<frontSpace;a++) {
				answer[x][a] = "null";
			}
			for(int b = frontSpace;b<shingleSize;b++) {
				answer[x][b] = splitText.get(b - (shingleSize - x));
			}
			answer[x][shingleSize] =  splitText.get(x);
				
			int endSpace = shingleSize - (splitText.size() - (x+1));
			if(endSpace < 0) endSpace = 0;
			
			for(int y=x+1;(y - (x+1))<(shingleSize - endSpace);y++) {
				answer[x][shingleSize + (y - x)] = splitText.get(y);
			}
			
			for(int z=0;z<endSpace;z++) {
				answer[x][((shingleSize *2) + 1) - (z+1)] = "null";
			}
		}
		
		return answer;
	}
	
	/*
	 * Identifies PER tags in a shingled set, returns a String with coordinates
	 * Output Format: if a PER tag is found at shingled[6][7], the return String will contain "Start: 6,7, "
	 */
	public String findPER(String[][] shingled) {
		String answer = "";
		
		for(int x=0;x<shingled.length;x++) {
			for(int y=0;y<shingled[x].length;y++) {		
				if(shingled[x][y].equals("<PER>")) {
					answer += "Start: " + x + ", " + y + ", ";
				}
				else if(shingled[x][y].equals("<PER/>")) {
					answer += "End: " + x + ", " + y + ", ";
				}
			}
		}
		
		return answer;
	}
	
	
}
