package edu.odu.cs.cs350.namex;

import java.io.File;

public class Main {

	// Used to interface with the CLI.
	public static void main(String args[]) {
		Librarian librarian = new Librarian();
		if (args.length > 0)
		{
			File file = new File(args[0]);
			if (file.exists()){
				librarian.readInput(file);
				librarian.processBlocks();
			}
		}
	}
}
