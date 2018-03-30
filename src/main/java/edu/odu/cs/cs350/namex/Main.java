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
			while(!("".equals(line)))
			{
				System.out.println(line);
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
			while(!("".equals(line)))
			{
				System.out.println(line);
				librarian.readAPIInput(line);
					line = scan.nextLine();
			
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
		//System.out.println(args[0]);

//		
//		if (args.length > 0)
//		{
//			for (int i=0; i< args.length;i++)
//			{
//				librarian.readCLIInput(args[i]);	
//			}
//			for(String extractedLine:librarian.getBlocks())
//			{
//			System.out.println(extractedLine);
//			}
//
//		}
	}
}