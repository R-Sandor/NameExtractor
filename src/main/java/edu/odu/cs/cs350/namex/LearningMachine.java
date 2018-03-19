package edu.odu.cs.cs350.namex;

import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.cs350.namex.features.Gazetteer;
import edu.odu.cs.cs350.namex.features.Lexical;
import edu.odu.cs.cs350.namex.features.PartsOfSpeech;

public class LearningMachine {

	public char calculateWord(String text) {
		char test = 0;
		return test;
	}
	
	public String learn(String blockText) {
		
		Lexical lexical = new Lexical();
		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
		Gazetteer gazetteer = new Gazetteer();
		List<String> splitText = lexical.separateText(blockText);
		List<String> mappedFeatures = new ArrayList<>();
		
		for (String text :splitText) {
			String currentText = lexical.doesApply(text);
			currentText = currentText + ", " + partsOfSpeech.doesApply(text);
			currentText = currentText + ", " + gazetteer.doesApply(text);
			mappedFeatures.add(currentText);
		}
		
		String allFeatures = "";
		boolean first = true;
		for (String feature : mappedFeatures) {
			if (first) {
				allFeatures = feature;
				first = false;
				continue;
			}
			allFeatures += ", " + feature;
		}
		
		return allFeatures;
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
	
	public String tagWrap(String inputedBlock, int key) {
		String outputBlock = "";
		
        String inputedBlock2 = "Thorin Oakenshield is a character in Tolkein's book, The Hobbit.";
        System.out.println(inputedBlock2);
        int beginName = 0;
        int continueName = 1;
        int singleName = 6;
        
        String words[] = inputedBlock2.split("\\s+");

        for(int i = 0; i < words.length; i++)
        {
            String word = words[i];
            if(i==beginName){
                word = "<PER>" + word;
            }
            else if(i==continueName) {
            	word = word + "<PER>";
            }
            else if(i==singleName) {
            	word = "<PER>" + word + "<PER>";
            }
            
            outputBlock += word;
            outputBlock += " ";
        }
        System.out.println(outputBlock);
		return outputBlock;
		
	}
}
