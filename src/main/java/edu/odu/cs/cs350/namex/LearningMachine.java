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
	
	public String tagWrap(String inputedBlock, int key, int type) {
		String outputBlock = "";
		
        //String inputedBlock2 = "Thorin Oakenshield is a character in Tolkein's book, The Hobbit.";
        //System.out.println(inputedBlock2);
        //int beginName = 0;
        //int continueName = 1;
        //int singleName = 6;
        
		System.out.println("KEY: " + key);
		
        String words[] = inputedBlock.split("\\s+");

        for(int i = 0; i < words.length; i++)
        {
        	String word = words[i];
        	if(i==key) {
            if(type==1){
            	System.out.println("inserting");
                word = "<PER>" + word;
            }
            else if(type==2) {
            	word = word + "<PER/>";
            }
            else if(type==3) {
            	word = "<PER>" + word + "<PER/>";
            }
        	}
            outputBlock += word;
            outputBlock += " ";
        }
        //System.out.println(outputBlock);
		return outputBlock;
		
	}
	
	public String[][] shingling(String blockText, int shingleSize) {
		Lexical lexical = new Lexical();
		List<String> splitText = lexical.separateText(blockText);
		String [][] answer = new String[splitText.size()][(2 * shingleSize) + 1];
		for(int x=0;x<splitText.size();x++) {
			int frontSpace = shingleSize - x;
			if(frontSpace < 0) frontSpace = 0;
			for(int a=0;a<frontSpace;a++) {
				answer[x][a] = "null";
			}
			for(int b = frontSpace;b<shingleSize;b++) {
				answer[x][b] = splitText.get(b - (shingleSize - x));
			}
			answer[x][shingleSize] =  splitText.get(x);
				
			int endSpace = shingleSize - (splitText.size() - (x+1));
			if(endSpace < 0) endSpace = 0;
			
			for(int y=x+1;(y - (x+1))<(shingleSize - endSpace);y++) {
				answer[x][shingleSize + (y - x)] = splitText.get(y);
			}
			
			for(int z=0;z<endSpace;z++) {
				answer[x][((shingleSize *2) + 1) - (z+1)] = "null";
			}
		}
		
		return answer;
	}
	
}
