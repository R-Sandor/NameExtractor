package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	private LearningMachine learningMac = new LearningMachine();
	private ArrayList<String> extractedBlock = new ArrayList<String>();
	/*
	 *  We incorporated WEKA in the training of the learning machine. 
	 * Refer to https://www.cs.odu.edu/~zeil/cs350/s18/Protected/nameExtractionDesignNotes/index.html
	 * After we parse input into its characteristics the following should be done:
	 *  1.Gathered all of the blocks that needed to be used to train the learning machine
	 *  	String [] parsedBlocks = { "CapLetter, other ,1,0,1,...," ,.... etc}
	 *  2.Organized the blocks
	 *  	String[] Lexical = {"CapLetter", "other", "Capitalized,..., etc"};
	 *		String [] PoS = ("Other", "period")); 
	 *		String dict [] = {0,1}
	 *		String countries [] = {0,1}
	 *		String dticFirstName [] = {0,1}
	 *		String dticLastName [] = {0,1}
	 *		String commonmLastName [] = {0,1}
	 *		String honorific [] = {0,1}
	 *		String prefix [] = {0,1}
	 *		String suffix [] = {0,1}
	 *		String kill[] = {0,1}
	 *	
	 *  3.Created Attributes for each of these
	 *  	Attribute LexicalAtt = new Attribute("lexicalAtt", fastV(Lexical)); 
	 *  	Attribute PoSAtt = new Attribute("PoSAtt", fastV(PoS));
	 *  	......
	 *  	FastVector attrInfo = new FastVector();
	 *		attrInfo.addElement(LexicalAtt);
	 *		attrInfo.addElement(PoSAtt);
	 *		...
     *		
	 *  4. Trained from the input
	 *  	Instances training = new Instances( "TrainingData", attrInfo, parsedData);
	 *  5. 
	 * 	 
	 *  
	 */
	

	
	public double accuracyTest(String text, String text1) {
		double test = 0;
		return test;
	}
	
	/**
	 * The text is analyzed to find personal names
	 * and then the text is added to a list of extracted
	 * blocks that contain NER tags.
	 * 
	 * @param text the text read in from the user
	 */
	public void CLIextract(String text) {
		String evaluatedText = learningMac.judgeBlock(text);
		String ner ="<NER>";
		String close = "</NER>";
		evaluatedText = ner.concat(evaluatedText);
		evaluatedText = evaluatedText.concat(close);
		extractedBlock.add(evaluatedText);
	}
	
	public void APIextract(String text)
	{
		extractedBlock.add(learningMac.judgeBlock(text));

	}
	
	public ArrayList<String>  getExtractedCLIBlocks(){
		//
		return extractedBlock;
	}

	
}	