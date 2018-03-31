package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

//The interface for the extracting name. 

public class PersonalNameExtractor {
	LearningMachine learningMac = new LearningMachine();
	private ArrayList<String> extractedBlock = new ArrayList<String>();
	/*
	 *  We incorporated WEKA in the training of the learning machine. 
	 * Refer to https://www.cs.odu.edu/~zeil/cs350/s18/Protected/nameExtractionDesignNotes/index.html
	 * After we parse input into its characteristics the following should be done:
	 *  1.Gathered all of the blocks that needed to be used to train the learning machine
	 *  	String [] parsedBlocks = { "CapLetter, other ,1,0,1,...," ,.... etc}
	 *  2.Organized the blocks
	 *  	String[] Lexical = {"CapLetter", "other", "Capitalized,..., etc"};
	 *		String [] PoS = ("Other", "period")); 
	 *		String dict [] = {0,1}
	 *		String countries [] = {0,1}
	 *		String dticFirstName [] = {0,1}
	 *		String dticLastName [] = {0,1}
	 *		String commonmLastName [] = {0,1}
	 *		String honorific [] = {0,1}
	 *		String prefix [] = {0,1}
	 *		String suffix [] = {0,1}
	 *		String kill[] = {0,1}
	 *	
	 *  3.Created Atributes for each of these
	 *  	Attribute LexicalAtt = new Attribute("lexicalAtt", fastV(Lexical)); 
	 *  	Attribure PoSAtt = new Attribute("PoSAtt", fastV(PoS));
	 *  	......
	 *  	FastVector attrInfo = new FastVector();
	 *		attrInfo.addElement(LexicalAtt);
	 *		attrInfo.addElement(PoSAtt);
	 *		...
     *		
	 *  4. Trained from the input
	 *  	Instances training = new Instances( "TrainingData", attrInfo, parsedData);
	 *  5. 
	 * 	 
	 *  TODO 
	 *  We still need shingling to be able determine where PER tag.
	 * 
	 */
	
	/**
	 *  @param trainingData
	 *  Receives training data in a file and parse the data and provides this 
	 *  to a WEKA learning machine.
	 */
	public void trainLearningMachine(File trainingData) throws FileNotFoundException, IOException {
		ArrayList<String> trainingLines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(trainingData))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	trainingLines.add(learningMac.parse(line.replace("<NER>", "").replaceAll("</NER>", "")));
		    }    
		}
		String[] lexical = {"NewLine", "CapitalLetter","Punctuation", "Number","AllCaps", "Capitalized"};
		String[] partsOfSpeach = {"Article", "Conjunction", "Period", "Comma", "Hyphen"}; 
		String[] dict = {"0", "1"};
		String[] cities = {"0","1"};
		String[] countries = {"0", "1"};
		String[] places = {"0", "1"};
		String[] dtic1stName = {"0", "1"};
		String[] dticLastName = {"0", "1"};
		String[] common1stName = {"0","1"};
		String[] commonLastName = {"0","1"};
		String[] honorific = {"0", "1"};
		String[] prefix = {"0", "1"};
		String[] suffix = {"0", "1"};
		String[] kill = {"0", "1"};
		String[] previous = {"BP", "CP", "other"};
		String[] previous2 = {"BP", "CP", "other"};
		String[] per = {"0", "1" };
		
		Attribute LexicalAtt = new Attribute("lexicalAtt", fastV(lexical));
		Attribute PoSAtt = new Attribute("PoSAtt", fastV(partsOfSpeach));
		Attribute DictAtt = new Attribute("DictAtt", fastV(dict));
		Attribute CitiesAtt = new Attribute("CitiesAtt", fastV(cities));
		Attribute CountriesAtt = new Attribute("CountriesAtt", fastV(countries));
		Attribute PlacesAtt= new Attribute("PlacesAtt", fastV(places));
		Attribute DTIC1stNameAtt = new Attribute("DTIC1stNameAtt", fastV(dtic1stName));
		Attribute DTICLastNameAtt = new Attribute("DTICLastNameAtt", fastV(dticLastName));
		Attribute Common1stNameAtt = new Attribute("CommonFirstNameAtt", fastV(common1stName));
		Attribute CommonLastNameAtt = new Attribute("CommonLastNameAtt", fastV(commonLastName));
		Attribute HonorificAtt = new Attribute("HonorificAtt", fastV(honorific));
		Attribute PrefixAtt = new Attribute("PrefixAtt", fastV(prefix));
		Attribute SuffixAtt = new Attribute("SuffixAtt", fastV(suffix));
		Attribute KillAtt = new Attribute("Kill", fastV(kill));
		Attribute PerAtt = new Attribute("PerAtt", fastV(per));

		FastVector attrInfo = new FastVector();
		attrInfo.add(LexicalAtt);
		attrInfo.add(PoSAtt);
		attrInfo.add(DictAtt);
		attrInfo.add(CitiesAtt);
		attrInfo.add(CountriesAtt);
		attrInfo.add(CitiesAtt);
		attrInfo.add(PlacesAtt);
		attrInfo.add(DTIC1stNameAtt);
		attrInfo.add(DTICLastNameAtt);
		attrInfo.add(Common1stNameAtt);
		attrInfo.add(CommonLastNameAtt);
		attrInfo.add(HonorificAtt);
		attrInfo.add(PrefixAtt);
		attrInfo.add(SuffixAtt);
		attrInfo.add(KillAtt);
		attrInfo.add(PerAtt);
		final int numberOfAttributes = attrInfo.size();

		Instances training = new Instances("trainingLines", attrInfo, trainingLines.size());
		// Which attribute holds the
        // class/category that we want
        // to predict?
		training.setClass(PerAtt); 
		for (String sdata: trainingLines) {
	        String[] values = sdata.split(",");
	        Instance instance = new DenseInstance(numberOfAttributes);
	        instance.setValue(0, values[0]); // Lexical
	        instance.setValue(1, Double.parseDouble(values[1])); // Parts Of Speech
	        instance.setValue(2, Double.parseDouble(values[2])); // Dictionary 
	        instance.setValue(3, values[3]); // Cities
	        instance.setValue(4, values[4]); // Countries
	        instance.setValue(5, values[5]); // Places
	        instance.setValue(6, values[6]); // DTIC 1st Names
	        instance.setValue(7, values[7]); // DTIC Last Names
	        instance.setValue(8, values[8]); // Countries
	        instance.setValue(9, values[9]); // Common 1st Names
	        instance.setValue(10, values[10]); // Common Last Names
	        instance.setValue(11, values[11]); // Honorific 
	        instance.setValue(12, values[12]); // Prefix
	        instance.setValue(13, values[13]); // Suffix
	        instance.setValue(14, values[14]); // Kill
	        instance.setValue(15, values[15]); // PER

	        training.add(instance); // Add new instance to training data

		}
	}
	
	public double accuracyTest(String text, String text1) {
		double test = 0;
		return test;
	}
	
	/*
	 * Will return a string with <PER> tags
	 */
	public String extract(String text) {
		learningMac.judgeBlock(text);
		/*
		 * TODO 
		 * 	This should return a string that has been processed by the learning machine.
		 */
		return text;
	}
	
	// Data that is being added by Librarian are being extracted 
	// before being stored in the extractedBlock
	public void CLIextract(String text) {
		String tempString = extract(text);
		String ner ="<NER>";
		String close = "</NER>";
		tempString = ner.concat(tempString);
		tempString = tempString.concat(close);
		extractedBlock.add(tempString);

	}
	
	public void APIextract(String text)
	{
		extractedBlock.add(extract(text));

	}
	
	public ArrayList<String>  getExtractedCLIBlocks(){
		//
		return extractedBlock;
	}

	
	 /**
	   * Utility to build a FastVector from an array of Strings.
	   * (This will be easier in later versions of WEKA where
	   * FastVector will be a subclass of ArrayList.)  
	   * @param data array of strings
	   * @return a FastVector cotnaining those strings
	   */
	 private FastVector fastV(String[] data) {
	      FastVector result = new FastVector(data.length);
	      for (String s: data) {
	          result.addElement(s);
	      }
	      return result;
	  }
	 
	 
	 /**
	  * @param block
	  * The Shingling function receives a block as input and 
	  * shift the words 2k+1 words to the right. Moving over 
	  * Once a decision on the word in relation to the previous 
	  * words
	  */
	 public ArrayList<String> Shingling(ArrayList<String> block){
		return null;
		 
	 }
	 

}
