package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

	static List<String> readLinesFromFile(String fileName) {
		
		// Read in multiple lines that are to be tested by the lexical class.
		List<String> lexicalTestLines = new ArrayList<>();
		File file = new File(fileName);	 
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			bufferedReader.lines().forEach(lexicalTestLines::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lexicalTestLines;
	}	
}
