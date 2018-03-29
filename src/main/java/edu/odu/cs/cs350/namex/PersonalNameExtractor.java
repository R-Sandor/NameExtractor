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
		final int nameOffsetIndex = 4;
		String newTextBlock = textBlock;
		for (int i = 2; i < allFeatureValues.length; i += nameOffsetIndex) {
			String isFirstName = allFeatureValues[i];
			String isLastName = allFeatureValues[i + 1];
			
			if (isFirstName.equals("1") || isLastName.equals("1")) {
				newTextBlock = learningMachine.tagWrap(newTextBlock, i / nameOffsetIndex, 3);
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
		@SuppressWarnings("unused")
		LearningMachine learningMac = new LearningMachine();
		/*
		 * TODO 
		 * 	This should return a string that has been processed by the learning machine.
		 */
		return text;
	}
	
	// Data that is being added by Librarian are being extracted 
	// before being stored in the extractedBlock
	public void CLIextract(String text) {
		String tempString = extract(text);
		String ner ="<NER>";
		String close = "</NER>";
		tempString = ner.concat(tempString);
		tempString = tempString.concat(close);
		extractedBlock.add(tempString);

	}
	
	public ArrayList<String>  getExtractedCLIBlocks(){
		//
		return extractedBlock;
	}


}
