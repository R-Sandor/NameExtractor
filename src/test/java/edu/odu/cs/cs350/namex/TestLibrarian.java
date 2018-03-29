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
		librarian.readCLIInput(exampleInput);
		ArrayList<String> block = librarian.getBlocks();
		assertTrue(block.contains(exampleInput));
		assertEquals(1, librarian.numOfBlocks());
		
		
	}
	
	/*
	 * Test the CLI input to the program. This should break
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
		librarian.processCLIBlocks();
		assert(openNerCount == closeNerCount);
		System.out.println(librarian.getBlocks());
		assertEquals(openNerCount, librarian.numOfBlocks());
		
		assertEquals(textBlocks.get(1), librarian.getBlocks().get(1));
		
	}
	
	
//	@Test
//	public void testProcessBlocks() {
//		PersonalNameExtractor PNE = new PersonalNameExtractor();
//		@SuppressWarnings("unused")
//		Librarian librarian = new Librarian();
//		assertEquals(0, PNE.getExtractedCLIBlocks().size());
//
//		Scanner tempText = new Scanner(System.in);  
//		System.out.println("Enter text: ");
//		String temp = tempText.toString();
//		PNE.CLIextract(temp);
//		
//		tempText.close();

		
	//}

	
	
	
}
