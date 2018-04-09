package edu.odu.cs.cs350.namex;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	LearningMachine learningMac = new LearningMachine();
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
	
	/*
	 * Will return a string with <PER> tags
	 */
	public String extract(String text) {
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