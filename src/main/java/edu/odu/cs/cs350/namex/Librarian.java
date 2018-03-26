package edu.odu.cs.cs350.namex;


import java.util.ArrayList;

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

	public Librarian(){
		PNE.trainLearningMachine("TEXT/FILES WILL GO HERE");
	}
	
	/*
	 * Read text files from the CLI one line at a time.
	 * Note this used to take files. This 
	 */
	public void readInput(String line ) throws Exception {
		String addToLine;
		System.out.println("So far so good.");
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
				if (outBlock.isEmpty())
				{
					throw new Exception("Inputed string must be marked in <NER>... </NER> tags");
				}
				else
				{
					addToLine = outBlock.get(outBlock.size()-1)+ line;
					line = line.replace("</NER>", "");
					outBlock.set(outBlock.size()-1, addToLine.replace("<NER>", ""));
				}
				

			}
		}
	
	
	
	public ArrayList<String> getBlocks(){
		return outBlock;
	}
	
	
	public int numOfBlocks(){
		return outBlock.size();
	}
	
	/*
	 * The processing of the blocks is calling each block 
	 * to the PNE system.
	 */
	public ArrayList<String> processBlocks(){
		
		
		for(String line: outBlock ) {
		 PNE.addToCollection(line);
		}
		return PNE.getExtractedBlocks();
	}
	

}
