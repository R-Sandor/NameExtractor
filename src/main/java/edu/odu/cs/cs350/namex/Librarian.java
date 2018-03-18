package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * The Librarian class should handle the input of the 
 * program this entails both type of users (CLI, and API).
 *
 */

public class Librarian {
	
	private ArrayList<String> outBlock = new ArrayList<String>();
	
	/*
	 * Read text files from the CLI interface.
	 */
	public void readInput(File file) {
		List<String> fileLines = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(fileLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String addToLine;
		for (String line : fileLines) {
			
			if (line.contains("<NER>") && line.contains("</NER>")) {
				line= line.replace("</NER>","");
				outBlock.add(line.replace("<NER>", ""));
			}
			else if (line.contains("</NER>")){
				line= line.replace("</NER>","");
				addToLine = outBlock.get(outBlock.size()-1)+ line;
				if (!outBlock.get(outBlock.size()-1).equals(line)){
					outBlock.set(outBlock.size()-1, addToLine);
				}
			}
			else if (line.contains("<NER>"))
			{
				line = line.replace("</NER>","");
				outBlock.add(line.replace("<NER>", ""));


			}
			else
			{
				addToLine = outBlock.get(outBlock.size()-1)+ line;
				line = line.replace("</NER>", "");
				outBlock.set(outBlock.size()-1, addToLine.replace("<NER>", ""));
				

			}
		}
	}
	
	/*
	 * Read text from the API.
	 */
	public void readInput(String text){
		outBlock.add(text);
		processBlocks();
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
	public void processBlocks(){
		
		PersonalNameExtractor PNE = new PersonalNameExtractor();
		
		for(String line: outBlock ) {
			PNE.addToCollection(line);
		}
		
		outBlock.clear();
		
	}
}
