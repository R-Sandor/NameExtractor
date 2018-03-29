package edu.odu.cs.cs350.namex;

import java.io.Console;

public class Main {

	// Used to interface with the CLI.
	public static void main(String args[]) throws Exception {
		Librarian librarian = new Librarian();

	
		
		if (args.length > 0)
		{
			for (int i=0; i< args.length;i++)
			{
				System.out.println(args[i]);
				librarian.readCLIInput(args[i]);	
			}
			for(String extractedLine:librarian.getBlocks())
			{
			System.out.println(extractedLine);
			}

		}
	}
}