package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.LearningMachine;

public class TestLearningMachine {

	@Test
	public void testTagWrap() {
		
		LearningMachine learning = new LearningMachine();
		int a=1,b=2,c=7;
		String exampleString = "Thorin Oakenshield is a character in Tolkein's book, The Hobbit.";
		String exampleString2 = "<PER>Thorin Oakenshield<PER> is a character in <PER>Tolkein<PER>'s book, The Hobbit.";
		learning.tagWrap(exampleString,a);
		learning.tagWrap(exampleString,b);
		learning.tagWrap(exampleString,c);
		
		assertEquals(exampleString,exampleString2);
		
	}
}
