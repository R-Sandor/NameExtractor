package edu.odu.cs.cs350.namex;


import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

/**
 * 
 *
 * The Librarian class should handle the input of the 
 * program this entails both type of users (CLI, and API).
 *
 */

public class Librarian {
	
	private ArrayList<String> outBlock = new ArrayList<String>();
	private PersonalNameExtractor PNE = new PersonalNameExtractor();

	
	/* 
	 * TODO The librarian needs to load the training data when a librarian object is created.
	 * 
	 */
	public Librarian(){
		// This is the part where WEKA reads in the model. 
		try {
			Classifier cls = (Classifier) SerializationHelper.read("src/main/resources/trainingData.model");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(file.exists())
//			try {
//				PNE.trainLearningMachine(file);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		else
//			try {
//				throw new Exception("File not found");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	}
	
	/**
	 * Splits the lines of a file up by NER tags.
	 * 
	 * @param line of a file.
	 */
	public void readCLIInput(String line) {
		String addToLine;
		if (line.contains("<NER>") && line.contains("</NER>")) {
			line= line.replace("</NER>","");
			outBlock.add(line.replace("<NER>", ""));
		}
		else if (line.contains("</NER>")){
			line= line.replace("</NER>","");
			addToLine = outBlock.get(outBlock.size()-1)+ line;
			// If last line in list is not the same as the current line
			// set the last index to the concatenation of the two lines.
			if (!outBlock.get(outBlock.size()-1).equals(line) && !(outBlock.isEmpty())){
				outBlock.set(outBlock.size()-1, addToLine);
				}
			}
			else if (line.contains("<NER>"))
			{
				// Might as well guard against one liners with <NER>s
				line = line.replace("</NER>","");
				outBlock.add(line.replace("<NER>", ""));
			}
			else
			{
				if(outBlock.size()>0){
					addToLine = outBlock.get(outBlock.size()-1)+ line;
					line = line.replace("</NER>", "");
					outBlock.set(outBlock.size()-1, addToLine.replace("<NER>", ""));
				}
				else{
					line = line.replace("</NER>", "");
					outBlock.add(line.replace("<NER>", ""));
				}
			}
		}
	
	public void readAPIInput(String text)
	{
		outBlock.add(text);
	}
	
	public ArrayList<String> getBlocks(){
		return outBlock;
	}
	
	
	public int numOfBlocks(){
		return outBlock.size();
	}
	
	/**
	 * The processing of the blocks is calling each block 
	 * to the PNE system.
	 */
	public void processCLIBlocks() {
		
		
		for(String line: outBlock ) {
		   PNE.CLIextract(line);
		}
		outBlock = PNE.getExtractedCLIBlocks();
	}
	
	public void processAPIBlocks(){
		for(String line: outBlock ) {
			PNE.APIextract(line);
		}
		outBlock = PNE.getExtractedCLIBlocks();
	}

}
