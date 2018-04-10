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
	
	
	/*
	 * This is to be handled by the Trainer.
	 */
	
//	public String learn(String blockText) {
//		
//		Lexical lexical = new Lexical();
//		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
//		Gazetteer gazetteer = new Gazetteer();
//		List<String> splitText = lexical.separateText(blockText);
//		List<String> mappedFeatures = new ArrayList<>();
//		
//		for (String text :splitText) {
//			String currentText = lexical.doesApply(text);
//			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
//			currentText = currentText + ", " + gazetteer.doesApply(text);
//			mappedFeatures.add(currentText);
//		}
//		
//		String allFeatures = "";
//		boolean first = true;
//		for (String feature : mappedFeatures) {
//			if (first) {
//				allFeatures = feature;
//				first = false;
//				continue;
//			}
//			allFeatures += ", " + feature;
//		}
//		
//		return allFeatures;
//	}
	
	public String judgeBlock(String inputedBlock) {
		String parsedBlock =tokenize(inputedBlock);
		//TODO use the pre-trained input to make a judgement.
		return null;
	}
	
	
	
	/**
	 * 
	 * @param block
	 * @return String of tokenized data about a work ex: "CapLetter, Other, 1, 0, 0, 0, ...
	 * Where each value separated by a comma is a feature of the word.
	 */
	
	public String tokenize(String block) {
		// The size of K
		int shingleSize = 3;
		// position is the position the word is in on the block 
		// Incremented as the enhanced for loop iterates.
		int pos = 0;
		
		Lexical lexical = new Lexical();
		List<String> splitText = lexical.separateText(block);
		
		String[][] shingledBlock = shingling(splitText,shingleSize);
		// TODO Return a String analysis of a given word such as " "CapLetter", Other, 1 , 0, 1, 0, 0 "... etc.
		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
		Gazetteer gazetteer = new Gazetteer();
		List<String> mappedFeatures = new ArrayList<>();
		for (String text :splitText) {
			String currentText = lexical.doesApply(text);
			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
			currentText = currentText + ", " + gazetteer.doesApply(text);
			currentText = currentText + ", " + isPER(shingledBlock, pos, shingleSize); 
			mappedFeatures.add(currentText);
			pos++;
			
		}
		// Concatenate all the features onto one String. 
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
		
	    for (int i = 0; i < inputedBlock.length(); i++) { //iterates along a string to find words
	    	if (Character.isLetter(inputedBlock.charAt(i)) && !word) //tests a character in a string to see if it is a letter
	    		wordB[wordCount+1] = inputedBlock.length()-i; //if the character is a letter then a word is beginning so the position at the index is collected
	        // if the char is a letter, word = true.
	        if (Character.isLetter(inputedBlock.charAt(i)) && i != endOfLine) { //if it isn't the end of a line then iterations continue
	            word = true;
	            
	            // if char isn't a letter and there have been letters before,
	            // counter goes up.
	        } else if (!Character.isLetter(inputedBlock.charAt(i)) && word) {
	            wordCount++;//
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
	 * TODO this needs to be rewritten to count the PER's indexes
	 * Sample Input: "No <PER>John</PER>, no.", 1
	 * Sample Output: {{"null","No","John"},{"No","John",","},{"John",",","no"},{",","no","."},{"no",".","null"}}
	 */
	public String[][] shingling(List<String> splitText, int shingleSize) {
		
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
	
	/**
	 * Determines whether a given word in a string is name.
	 * @param shingled a previously shingled block of text.
	 * @param pos is the position in the block of separated. 
	 * 	Sample: "<PER>" "Raphael" "</PER> "wrote" "this" sample". 
	 * 	Where Raphael is the second element in the separated text.
	 * @param k is the shingle size 
	 * @return a 1 for if the word is a part of name and 0 otherwise
	 *	 
	 */
	public String isPER(String[][] shingled, int pos, int k) {
		String answer = "";
		int[] perStartCordinates = new int[shingled.length];
				if(shingled[pos][k-1].equals("<PER>")) {
					return "1";
				}
				else if(shingled[pos][k-2].equals("<PER>") && !(shingled[pos][k].equals("</PER>"))) {
					return "1";
				}
				if(shingled[pos][k+1].equals("<PER/>")) {
					return "1";
				}
				
			
		
		
		return "0";
	}
	
	
	
}
