package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Lexical;

import static org.junit.Assert.assertTrue;

public class TestLexical {

	@Test
	public void testDoesApply() {
		Lexical lexical = new Lexical();
		
		// Read in multiple lines that are to be tested by the lexical class.
		List<String> lexicalTestLines = new ArrayList<>();
		File file = new File("src/main/resources/TestSentences.txt");	 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(lexicalTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean testBinary = false, ignoreOnce = false;
		StringBuilder blockOfTextBuilder = new StringBuilder("");
		String blockOfText = "", binaryFeatureText = "";
		for (String line : lexicalTestLines) {
			
			// It will now read in the line that contains the binary information
			// for specifying what the output should be when testing for lexical features.
			if (testBinary) binaryFeatureText = line;
			
			// It will now make sure the binary output of the 'doesApply' method
			// matches that of the 'binaryFeatureText'.
			if (testBinary) {
				assertTrue(lexical.doesApply(blockOfText).equals(binaryFeatureText));
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
