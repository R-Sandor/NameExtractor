package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

public class Main {

	// Used to interface with the CLI.
	public static void main(String args[]) throws Exception {
		Librarian librarian = new Librarian();
		
		if (args.length > 0)
		{
			System.out.println(args[0]);
			librarian.readInput(args[0]);
			ArrayList<String> processedBlocks=librarian.processBlocks();
			for(String extractedLine:processedBlocks)
			{
				System.out.println(extractedLine);
			}
			
		}
	}
	
}
