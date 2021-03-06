package edu.odu.cs.cs350.namex;

import java.util.Scanner;

public class Main {

	// Used to interface with the CLI.
	public static void main(String args[]) throws Exception {
		Librarian librarian = new Librarian();
		String line;
		Scanner scan = new Scanner(System.in);
		line = scan.nextLine();
		if (line.contains("<NER>"))
		{
			while(scan.hasNextLine())
			{
				librarian.readCLIInput(line);
				if (scan.hasNextLine())
					line = scan.nextLine();
				else
				{
					break;
				}
				if ("".equals(line))
				{
					scan.close();
					break;
				}
			}
			librarian.processCLIBlocks();
			for(String output:librarian.getBlocks())
				System.out.println(output);
		}
		else
		{
			while(!("".equals(line) || line.equals(null)))
			{
				librarian.readAPIInput(line);
				if(scan.hasNextLine())
					line = scan.nextLine();
				else 
					break;
				if ("".equals(line))
				{
					scan.close();
					break;
				}
			}
			librarian.processAPIBlocks();
			for(String output:librarian.getBlocks())
				System.out.println(output);
		}
		
	}
}