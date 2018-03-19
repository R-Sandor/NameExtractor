package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestPersonalNameExtractor {
	
	@Test
	public void TestTrainLearningMachine() {
		
		PersonalNameExtractor PersonalNameExtractor = new PersonalNameExtractor();
		
		List<String> learningTestLines = TestUtil.readLinesFromFile(
				"src/main/resources/TestTrainLearningMachineText.txt");
		
		boolean testLearn = false;
		StringBuilder blockOfTextBuilder = new StringBuilder("");
		String blockOfText = "", learnOutput = "";
		for (int i = 0; i < learningTestLines.size(); i++) {
			
			String line = learningTestLines.get(i);
			
			// It will now append the line onto the block of text.
			if (!testLearn) {
				blockOfTextBuilder.append(line);
				if (line.contains("--END--")) {
					blockOfText = blockOfTextBuilder.toString();
					blockOfTextBuilder = new StringBuilder("");
					i++; // Skip number line.
					learnOutput = learningTestLines.get(i);
					testLearn = true;
				} else {
					blockOfTextBuilder.append("\n");
				}
			}
			
			if (testLearn) {
				String testBlock = blockOfText.substring(0, blockOfText.length() - 7);
				assertEquals(learnOutput, PersonalNameExtractor.trainLearningMachine(testBlock));
				testLearn = false;
			}
		}
		
	}
}