package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.PartsOfSpeech;

public class TestPartsOfSpeech {

	@Test
	public void testDoesApply() {
		
		PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
		
		// Read in multiple lines that are to be tested by the parts of speech class.
		List<String> partsOfSpeechTestLines = new ArrayList<>();
		File file = new File("src/main/resources/TestPartsOfSpeechSentences.txt");	 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(partsOfSpeechTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean testBinary = false, ignoreOnce = false;
		StringBuilder blockOfTextBuilder = new StringBuilder("");
		String blockOfText = "", binaryFeatureText = "";
		for (String line : partsOfSpeechTestLines) {
			
			// It will now read in the line that contains the binary information
			// for specifying what the output should be when testing for parts of speech features.
			if (testBinary) binaryFeatureText = line;
			
			// It will now make sure the binary output of the 'doesApply' method
			// matches that of the 'binaryFeatureText'.
			if (testBinary) {
				assertTrue(partsOfSpeech.doesApply(blockOfText).equals(binaryFeatureText));
				testBinary = false; 
				ignoreOnce = true;
			}
			
			// It will now append the line onto the block of text.
			if (!testBinary && !ignoreOnce) {
				blockOfTextBuilder.append(line);
				if (line.contains("--END--")) {
					blockOfText = blockOfTextBuilder.toString();
					blockOfTextBuilder = new StringBuilder("");
					testBinary = true;
				} else {
					blockOfTextBuilder.append("\n");
				}
			}
			ignoreOnce = false;
		}
	}

}
