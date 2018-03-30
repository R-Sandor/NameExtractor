package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java.util.Scanner;
 
public class TestLibrarian {
	
	
	@Test
	public void testReadApi() throws Exception{
		Librarian librarian = new Librarian();
		String exampleInput = "Name Extraction -- Requirements Definition\nSteven J Zeil\nJan 20, 2016";
		librarian.readAPIInput(exampleInput);
		ArrayList<String> block = librarian.getBlocks();
		assertTrue(block.contains(exampleInput));
		assertEquals(1, librarian.numOfBlocks());
		
		
	}
	
	/*
	 * Test the CLI input to the program. This should break blocks up by <NER> ... </NER> Tags
	 */
	@Test
	public void testReadCLIInput() throws Exception {
		int openNerCount = 0;
		int closeNerCount =0;
		List<String> textBlocks = new ArrayList<>();
		// Lines gathered from using the actual Librarian class.
		List<String> nerTestLines = new ArrayList<>();

		File file = new File("src/main/resources/TestNER.txt");
		Librarian librarian = new Librarian();

		/*
		 * This reads in the input and contains the number of blocks the test is using.
		 * The test will then assertEqual that the number of blocks found 
		 * and the amount return from the librarian are the same.
		 * Meaning that the Librarian processed blocks based on <NER> tags. 
		 */
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(nerTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : nerTestLines) {
			librarian.readCLIInput(line);

			if (line.contains("<NER>")) {
				openNerCount++;
				textBlocks.add(line);
			}
			if (line.contains("</NER>")) {
				closeNerCount++;
			}
		}
		// This should be true or an exception needs to be thrown
		// as it would be a faulty test.
		assert(openNerCount == closeNerCount);
		assertEquals(openNerCount, librarian.numOfBlocks());

		librarian.processCLIBlocks();
		assertEquals(openNerCount, librarian.numOfBlocks());
		
		assertEquals(textBlocks.get(1), librarian.getBlocks().get(1));
		
	}
	
	
	@Test
	public void TestOutProcessing() {
		int openNerCount = 0;
		int closeNerCount =0;
		List<String> textBlocks = new ArrayList<>();
		// Lines gathered from using the actual Librarian class.
		List<String> nerTestLines = new ArrayList<>();

		File file = new File("src/main/resources/TestNER.txt");
		Librarian librarian = new Librarian();

		/*
		 * This reads in the input and contains the number of blocks the test is using.
		 * The test will then assertEqual that the number of blocks found 
		 * and the amount return from the librarian are the same.
		 * Meaning that the Librarian processed blocks based on <NER> tags. 
		 */
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(nerTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : nerTestLines) {
			librarian.readCLIInput(line);

			if (line.contains("<NER>")) {
				openNerCount++;
				textBlocks.add(line);
			}
			if (line.contains("</NER>")) {
				closeNerCount++;
			}
		}
		// This should be true or an exception needs to be thrown
		// as it would be a faulty test.
		assert(openNerCount == closeNerCount);
		assertEquals(openNerCount, librarian.numOfBlocks());
		int orginalNumberOfBlocksIn = librarian.numOfBlocks();
		librarian.processCLIBlocks();
		assertEquals(openNerCount, librarian.numOfBlocks());
		// Test if there are still the same number of blocks after processing Through PNE.
		assertEquals(orginalNumberOfBlocksIn, librarian.getBlocks().size());
		for (String block: librarian.getBlocks())
		{
			assertTrue(block.contains("<NER>"));
			assertTrue(block.contains("</NER>"));

		}
	}

	
	
	
}
