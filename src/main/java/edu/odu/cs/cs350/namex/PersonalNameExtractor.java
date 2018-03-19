package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	ArrayList<String> extractedBlock = new ArrayList<String>();
	public void trainLearningMachine(String text) {
		
	}
	
	public double accuracyTest(String text, String text1) {
		double test = 0;
		return test;
	}
	
	/*
	 * Will return a string with <PER> tags
	 */
	public String extract(String text) {
		return text;
	}
	
	// Data that is being added by Librarian are being extracted 
	// before being stored in the extractedBlock
	public void addToCollection(String text) {
		
		extractedBlock.add(extract(text));
	}
	
	public ArrayList<String>  getExtractedBlocks(){
		//
		return extractedBlock;
	}


}
