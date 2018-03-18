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

public class TestLibrarian {
	
	
	@Test
	public void testReadApi(){
		Librarian librarian = new Librarian();
		String exampleInput = "Name Extraction -- Requirements Definition\nSteven J Zeil\nJan 20, 2016";
		librarian.readInput(exampleInput);
		ArrayList<String> block = librarian.getBlocks();
		System.out.println(block);
		assertTrue(block.contains(exampleInput));
		assertTrue(true);
		assertEquals(1, librarian.numOfBlocks());
		
		
	}
	
	/*
	 * Test the CLI input to the program. This should break
	 */
	@Test
	public void testReadCLI() {
		int openNerCount = 0;
		int nerCloseCount = 0;
		List<String> textBlocks = new ArrayList<>();
		List<String> nerTestLines = new ArrayList<>();

		File file = new File("src/main/resources/TestNER.txt");
		Librarian librarian = new Librarian();
		librarian.readInput(file);
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(nerTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : nerTestLines) {
			
			if (line.contains("<NER>")) {
				openNerCount++;
				textBlocks.add(line);
			}
			if (line.contains("</NER>"))
				nerCloseCount++;
		}
		System.out.println(librarian.getBlocks());	
		assertEquals(openNerCount, librarian.numOfBlocks());
		
		assertEquals(textBlocks.get(1).replace("<NER>", "").replace("</NER>", ""), librarian.getBlocks().get(1));
		
		
		
		
	}

	
	
	
}
