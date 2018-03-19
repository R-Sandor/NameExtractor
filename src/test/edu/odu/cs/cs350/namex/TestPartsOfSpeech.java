package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.PartsOfSpeech;


/*
 * Tests the PartsofSpeech class using the File TestPartsOfSpeechText.txt
 * This file contains a series of words meant to be checked to determine whether or not they are
 * parts of speech such as Articles, Conjunctions, and certain pieces of punctuation, each of 
 * which may aid in identifying personal names.  Each line of the file has only one word or piece
 * of punctuation, with no excess white space.  Lines are taken in pairs, the first line gives the
 * input for the PartsOfSpeech class, the second to indicate what the expected output is for such
 * an input.
 * It is expected that PartsOfSpeech pass all of these tests.
 */
public class TestPartsOfSpeech {

	@Test
	public void testDoesApply() {
		
		PartsOfSpeech pos = new PartsOfSpeech();
		
		List<String> posTestLines = TestUtil.readLinesFromFile("src/main/resources/TestPartsOfSpeechText.txt");
		
		boolean testpos = false;
		String text = "", testFeatureText = "";
		for (String line : posTestLines) {
			if (!testpos) {
				if(line.equals("\\n")) {
					text = "\n";

				}
			else	text = line;
			}
			if (testpos) testFeatureText = line;
			
			// It will now make sure the output of the 'doesApply' method
			// matches that of the 'testFeatureText'.
			if (testpos) {
				System.out.println(text);
				assertEquals(testFeatureText, pos.doesApply(text));
			}
			
			testpos = !testpos;
		}
	}
	
	@SuppressWarnings("unused")
	private List<String> readLinesFromFile(String fileName) {
		
		// Read in multiple lines that are to be tested by the Parts of Speech class.
		List<String> posTestLines = new ArrayList<>();
		File file = new File(fileName);	 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(posTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return posTestLines;
	}
}
