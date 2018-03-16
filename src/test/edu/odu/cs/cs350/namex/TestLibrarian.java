package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class TestLibrarian {
	
	
	@Test
	public void testReadApi()
	{
		Librarian librarian = new Librarian();
		String exampleInput = "Name Extraction -- Requirements Definition\nSteven J Zeil\nJan 20, 2016";
		librarian.readInput(exampleInput);
		ArrayList block = librarian.getBlocks();
		assert(block.contains(exampleInput));
		
		assertEquals(1, librarian.getBlocks());
		
		
	}
	
	/*
	 * Test the CLI input to the program. This should break
	 */
	@Test
	public void testReadCLI() {
		
		File file = new File("test1.txt");
		Librarian librarian = new Librarian();
		
		librarian.readInput(file);
		
		
	}
}
