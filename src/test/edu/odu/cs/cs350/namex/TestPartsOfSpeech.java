package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.PartsOfSpeech;

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
				assertEquals(testFeatureText, pos.doesApply(text));
			}
			
			testpos = !testpos;
		}
	}
}
