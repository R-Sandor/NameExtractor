package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	
	private ArrayList<String> extractedBlock = new ArrayList<String>();
	/*
	 * TODO We need incorporate WEKA in the training of the learning machine. 
	 * Refer to https://www.cs.odu.edu/~zeil/cs350/s18/Protected/nameExtractionDesignNotes/index.html
	 * After we parse input into its characteristics the following should be done:
	 *  1.Gather all of the blocks that need to used to train the learning machine
	 *  	String [] parsedBlocks = { "CapLetter, other ,1,0,1,...," ,.... etc}
	 *  2.Organize the blocks
	 *  	String[] Lexical = {"CapLetter", "other", "Capitalized,..., etc"};
			String [] PoS = ("Other", "period")); 
			String dict [] = {0,1}
			String countries [] = {0,1}
			String dticFirstName [] = {0,1}
			String dticLastName [] = {0,1}
			String commonmLastName [] = {0,1}
			String honorific [] = {0,1}
			String prefix [] = {0,1}
			String suffix [] = {0,1}
			String kill[] = {0,1}
		
	 *  3.Create Atributes for each of these
	 *  	Attribute LexicalAtt = new Attribute("lexicalAtt", fastV(Lexical)); 
	 *  	Attribure PoSAtt = new Attribute("PoSAtt", fastV(PoS));
	 *  	......
	 *  	FastVector attrInfo = new FastVector();
			attrInfo.addElement(LexicalAtt);
			attrInfo.addElement(PoSAtt);
			...
			
	 *  4. Train from the input
	 *  	Instances training = new Instances( "TrainingData", attrInfo, parsedData);
	 *  5. M
	 */
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
		LearningMachine learningMac = new LearningMachine();
		learningMac.judgeBlock(text);
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
	
	public void APIextract(String text)
	{
		extractedBlock.add(extract(text));

	}
	
	public ArrayList<String>  getExtractedCLIBlocks(){
		//
		return extractedBlock;
	}


}
