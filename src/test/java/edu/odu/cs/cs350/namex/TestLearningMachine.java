package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestLearningMachine {

	@Test
	public void testTagWrap() {
		
		LearningMachine learning = new LearningMachine();
		int a=0,b=1,c=6;
		int x=1,y=2,z=3;
		String exampleString = "Thorin Oakenshield is a. character in Tolkein's book, The Hobbit.";
		String exampleString2 = "<PER>Thorin Oakenshield<PER/> is a character in <PER>Tolkein's<PER/> book, The Hobbit. ";
		String testString = "";
		testString = learning.tagWrap(exampleString,a,x);
		testString = learning.tagWrap(testString,b,y);
		testString = learning.tagWrap(testString,c,z);
		
		assertEquals(exampleString2,testString);
		
	}
	
	@Test
	public void testLearn() {
		
		LearningMachine learningMachine = new LearningMachine();
		
		List<String> learningTestLines = TestUtil.readLinesFromFile("src/main/resources/TestLearningMachineLearn.txt");
		
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
				assertEquals(learnOutput, learningMachine.learn(testBlock));
				testLearn = false;
			}
		}
		
	}
}
