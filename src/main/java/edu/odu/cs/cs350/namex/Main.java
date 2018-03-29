package edu.odu.cs.cs350.namex;

public class Main {

	// Used to interface with the CLI.
	public static void main(String args[]) throws Exception {
		Librarian librarian = new Librarian();
		
		if (args.length > 0)
		{
		//	while ()
			librarian.readCLIInput(args[0]);
			for(String extractedLine:librarian.getBlocks())
			{
				System.out.println(extractedLine);
			}
			
		}
	}
	
}
