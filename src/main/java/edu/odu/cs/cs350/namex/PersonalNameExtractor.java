package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	
	private ArrayList<String> extractedBlock = new ArrayList<String>();
	
	public String trainLearningMachine(String textBlock) {
		LearningMachine learningMachine = new LearningMachine();
		String allFeatures = learningMachine.learn(textBlock);
		
		String[] allFeatureValues = allFeatures.split(", ");
		
		// TODO: Re-adjust the i offset value to reflect changes in the binary
		// format for the gazetteer later on.
		String newTextBlock = textBlock;
		for (int i = 2; i < allFeatureValues.length; i += 4) {
			String isFirstName = allFeatureValues[i];
			String isLastName = allFeatureValues[i + 1];
			
			if (isFirstName.equals("1") || isLastName.equals("1")) {
				newTextBlock = learningMachine.tagWrap(newTextBlock, i);
			}
		}
		
		return newTextBlock;
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
