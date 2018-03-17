package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Lexical;

public class TestLexical {

	@Test
	public void testDoesApply() {
		
		Lexical lexical = new Lexical();
		
		List<String> lexicalTestLines = readLinesFromFile("src/main/resources/TestLexicalText.txt");
		
		boolean testLexical = false;
		String text = "", testFeatureText = "";
		for (String line : lexicalTestLines) {

			if (!testLexical) text = line;
			if (testLexical) testFeatureText = line;
			
			// It will now make sure the output of the 'doesApply' method
			// matches that of the 'testFeatureText'.
			if (testLexical) {
				assertTrue(lexical.doesApply(text).equals(testFeatureText));
			}
			
			testLexical = !testLexical;
		}
	}

	@Test
	public void testSeperateByWhitespace() {
		
		Lexical lexical = new Lexical();
		
		List<String> lexicalTestLines = readLinesFromFile("src/main/resources/TestLexicalSeperate.txt");
		
		boolean testSeperation = false;
		StringBuilder blockOfTextBuilder = new StringBuilder("");
		String blockOfText = "";
		int numberOfText = 0;
		for (int i = 0; i < lexicalTestLines.size(); i++) {
			
			String line = lexicalTestLines.get(i);
			
			// It will now append the line onto the block of text.
			if (!testSeperation) {
				blockOfTextBuilder.append(line);
				if (line.contains("--END--")) {
					blockOfText = blockOfTextBuilder.toString();
					blockOfTextBuilder = new StringBuilder("");
					i++; // Skip number line.
					numberOfText = Integer.parseInt(lexicalTestLines.get(i));
					testSeperation = true;
				} else {
					blockOfTextBuilder.append("\n");
				}
			}
			
			if (testSeperation) {
				String testBlock = blockOfText.substring(0, blockOfText.length() - 7);
				String[] text = lexical.seperateText(testBlock);
				
				// Making sure it separated into the correct number of blocks.
				assertTrue(numberOfText == text.length);
				testSeperation = false;
			}
		}
	}
	
	private List<String> readLinesFromFile(String fileName) {
		
		// Read in multiple lines that are to be tested by the lexical class.
		List<String> lexicalTestLines = new ArrayList<>();
		File file = new File(fileName);	 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(lexicalTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lexicalTestLines;
	}
}
