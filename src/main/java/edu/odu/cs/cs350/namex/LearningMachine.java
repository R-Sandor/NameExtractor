package edu.odu.cs.cs350.namex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

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
	
	/**
	 * Makes a judgment about the inputed block of text and
	 * places PER tags around the places where personal names
	 * are found.
	 * 
	 * @param inputedBlock the block of text read from the user.
	 * @return the processed block of text containing PER tags.
	 */
	public String judgeBlock(String inputedBlock) {
		String parsedBlock = tokenize(inputedBlock);
		
		Lexical myLex = new Lexical();
		
		List <String> words = myLex.separateText(inputedBlock);
		
		String[] values = parsedBlock.split(", ");
        int numWords = values.length / 15;
        List<Integer> collectedIndexes = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
        	int offset = i * 15;
        	
        	if (values[offset + 8].equals("1") || values[offset + 9].equals("1")) {
        		if (words.get(i).length() >= 4 && values[offset + 2].equals("0")) {
        			collectedIndexes.add(i);
        		}
        	}
        }
        
        
        String sentence = inputedBlock;
        int numItrs = 0;
        for (int index : collectedIndexes) {
        	sentence = tagWrap(sentence, index + (2 * numItrs), 1);
        	numItrs++;
        }
        
		return sentence;
	}
	
	
	
	/**
	 * Tokenizes a block of data by using parts of speech, gazetteer,
	 * and lexical features.
	 * 
	 * @param block an inputed block to be tokenized
	 * 
	 * @return allfeatures of tokenized data about a word. example: "CapLetter, Other, 1, 0, 0, 0, ...
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
		ArrayList<String> mappedFeatures = new ArrayList<>();
		for (String text :splitText) {
			String currentText = lexical.doesApply(text);
			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
			currentText = currentText + ", " + gazetteer.doesApply(text);
			
			currentText = currentText + ", " + isPER(shingledBlock, pos);
			mappedFeatures.add(currentText);
			pos++;
			
		}
		
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 3, (blockText, i) -> gazetteer.isUsaCitiesAndStates(splitText, i));
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 5, (blockText, i) -> gazetteer.isPlace(splitText, i));
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 4, (blockText, i) -> gazetteer.isCountryOrTerritory(splitText, i));
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 10, (blockText, i) -> gazetteer.isHonorific(splitText, i));
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 12, (blockText, i) -> gazetteer.isSuffix(splitText, i));
		applyLongGazetteerFeature(splitText, gazetteer, mappedFeatures, 13, (blockText, i) -> gazetteer.isKillText(splitText, i));
		
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

	private void applyLongGazetteerFeature(List<String> splitText, Gazetteer gazetteer, ArrayList<String> mappedFeatures, 
			int replacementIndex, BiFunction<List<String>, Integer, Integer> isType) {
		for (int i = 0; i < splitText.size(); i++) {
			int coveredWords = isType.apply(splitText, i);
			// No matches found
			if (coveredWords == -1) {
				continue;
			}
			
			for (int j = i; j <= coveredWords; j++) {	
				String current = mappedFeatures.get(j);
				String[] pieces = current.split(", ");
				StringBuilder newTokenBuilder = new StringBuilder("");
				for (int l = 0; l < pieces.length; l++) {
					if (l == replacementIndex) pieces[l] = "1";
					newTokenBuilder.append(pieces[l] + ((l != pieces.length - 1) ? ", " : ""));
				}
				mappedFeatures.set(j, newTokenBuilder.toString());
			}
		}
	}


	public void addCharToList(char resultOfCalc) {
		
	}
	
	// This is simply an interface to add a character to collection of results 
	// for a given block.
	public void readFeatureList() {
		
	}
		//tagWrap adjusted to run with simple strings and not ignore punctuation
	public String tagWrap(String inputedBlock, int key, int length) {
		String result = "";//initiate string for holding result for later
		
		Lexical myLex = new Lexical();
		List<String> myWords = myLex.separateText(inputedBlock);
		
		myWords.add(key, "<PER>"); //If it is the beginning of a word add <PER>
		myWords.add(key + 1 + length, "<PER/>"); //If it is the end of the word add <PER/>
		
		for(int y=0;y<myWords.size();y++) { 
			result += myWords.get(y); 
			String punctutations = ".,;\\:'?!$%()/\""; //Punctuation to check against separated text
			String spaceAfter = ".,;:?!\"";
			
			Boolean checker =  (myWords.get(y).length() == 1) && (y < (myWords.size() - 1));
			if(checker)
				checker = (punctutations.contains(String.valueOf(myWords.get(y).charAt(0)))) && !(spaceAfter.contains(String.valueOf(myWords.get(y).charAt(0))));
			checker = checker || myWords.get(y).equals("<PER>"); //Makes sure placed tags are not counted as words
			if(y < (myWords.size() - 1))
				checker = checker || myWords.get(y+1).equals("<PER/>") || (punctutations.contains(String.valueOf(myWords.get(y+1).charAt(0))));//Makes sure placed tags are not counted as words
			
			checker = checker || (y == myWords.size() - 1);
			
			if(!checker)
				result += " "; //Adds spaces between words
			
		}
		
		return result;
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
	 * Determines whether a given word in a string is part of a name.
	 * @param shingled a previously shingled block of text.
	 * @param pos is the position in the block of separated.
	 * @param k is the shingle size 
	 * @return a 1 for if the word is a part of name and 0 otherwise
	 */
	public String isPER(String[][] shingled, int pos) {
		int k = (shingled[0].length / 2);
		boolean inPER = false;
		for(int i=0;i<shingled.length;i++) {
			if(shingled[i][k].equals("<PER>")) inPER = true;
			else if(shingled[i][k].equals("</PER>")) inPER = false;
			
			if(i == pos && inPER)
				return "1";
		}
		return "0";
	}
}

