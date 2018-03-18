package edu.odu.cs.cs350.namex;

import java.util.List;

import edu.odu.cs.cs350.namex.features.Lexical;

public class LearningMachine {

	
	public char calculateWord(String text) {
		char test = 0;
		return test;
	}
	public void learn(String blockText) {
		Lexical lexical = new Lexical();
		List<String> splitText = lexical.separateText(blockText);
		for (String s : splitText) {
			//mappedStrings.put(s," ");
		}
		
	}
	
	public char judgeBlock(char inputedBlock) {
		return inputedBlock; 
	}
	
	public void addCharToList(char resultOfCalc) {
		
	}
	// This is simply an interface to add a character to collection of results 
	// for a given block.
	
	public void readFeatureList() {
		
	}
	
	public void tagWrap(String exampleString, int a) {
		// TODO Auto-generated method stub
		
	}
}
