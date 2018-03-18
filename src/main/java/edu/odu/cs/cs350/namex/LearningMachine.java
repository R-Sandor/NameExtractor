package edu.odu.cs.cs350.namex;

import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.cs350.namex.features.Lexical;

public class LearningMachine {

	public char calculateWord(String text) {
		char test = 0;
		return test;
	}
	
	public void learn(String blockText) {
		
		Lexical lexical = new Lexical();
		List<String> splitText = lexical.separateText(blockText);
		List<String> mappedFeatures = new ArrayList<>();
		
		// Provide a mapping between the 'splitText' and the 'mappedFeatures' lists
		for (String text :splitText) {
			mappedFeatures.add(lexical.doesApply(text));
			
		}
		
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
        int key2 = 2;
        
        String words[] = inputedBlock2.split(" ");

        for(int i = 0; i < words.length; i++)
        {
            String word = words[i];
            if(i==key2)
            {
                word = "<PER>" + word + "<PER>";
            }
            outputBlock += word;
            outputBlock += " ";
        }
        System.out.println(outputBlock);
		return outputBlock;
		// TODO Auto-generated method stub
		
	}
}
