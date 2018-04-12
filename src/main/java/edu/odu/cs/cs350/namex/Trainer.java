package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Trainer {
	public static void main(String args[]) throws Exception {
	Trainer trainer = new Trainer();
	}
	
	
	LearningMachine learningMac = new LearningMachine();
	
	public Trainer() throws Exception{
		File file = new File("src/main/resources/TrainingData2.txt");
		if(file.exists())

				trainLearningMachine(file);
		else
			try {
				throw new Exception("File not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 *  @param trainingData
	 *  Receives training data in a file and parse the data and provides this 
	 *  to a WEKA learning machine.
	 * 
	 */
	public void trainLearningMachine(File trainingData) throws Exception {
		ArrayList<String> trainingLines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(trainingData))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	trainingLines.add(learningMac.tokenize(line.replace("<NER>", "").replaceAll("</NER>", "")));
		    }    
		}


		String[] lexical = {"NewLine", "CapLetter","Punctuation", "Number","AllCaps", "Capitalized", "other"};
		String[] partsOfSpeach = {"Article", "Conjunction", "Period", "Comma", "Hyphen", "other"}; 
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
	//	String[] previous = {"BP", "CP", "other"};
	//	String[] previous2 = {"BP", "CP", "other"};
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

		ArrayList<Attribute> attrInfo = new ArrayList<Attribute>();
		attrInfo.add(LexicalAtt);
		attrInfo.add(PoSAtt);
		attrInfo.add(DictAtt);
		attrInfo.add(CitiesAtt);
		attrInfo.add(CountriesAtt);
		attrInfo.add(PlacesAtt);
		attrInfo.add(DTIC1stNameAtt);
		attrInfo.add(DTICLastNameAtt);
		attrInfo.add(Common1stNameAtt);
		attrInfo.add(CommonLastNameAtt);
		attrInfo.add(HonorificAtt);
		attrInfo.add(PrefixAtt);
		attrInfo.add(SuffixAtt);
		attrInfo.add(KillAtt);
		attrInfo.add(PerAtt); // TODO we still need to shingling
							  // for this attribute to work
		int numberOfAttributes = attrInfo.size();
		int  size = trainingLines.size();
		Instances training = new Instances("TrainingData", attrInfo, size);
		// Which attribute holds the
        // class/category that we want
        // to predict?
		training.setClass(PerAtt);

		for (String sdata: trainingLines) {
	        String[] values = sdata.split(", ");
	        
	        
	        Instance instance = new DenseInstance(numberOfAttributes);
	        instance.setValue(LexicalAtt, values[0]);
	        instance.setValue(PoSAtt, values[1]); // Parts Of Speech
	        instance.setValue(DictAtt, values[2]); // Dictionary 
	        instance.setValue(CitiesAtt, values[3]); // Cities
	        instance.setValue(CountriesAtt, values[4]); // Countries
	        instance.setValue(PlacesAtt, values[5]); // Places
	        instance.setValue(DTIC1stNameAtt, values[6]); // DTIC 1st Names
	        instance.setValue(DTICLastNameAtt, values[7]); // DTIC Last Names
	        instance.setValue(Common1stNameAtt, values[8]); // Common 1st Names
	        instance.setValue(CommonLastNameAtt, values[9]); // Common Last Names
	        instance.setValue(HonorificAtt, values[10]); // Honorific 
	        instance.setValue(PrefixAtt, values[11]); // Prefix
	        instance.setValue(SuffixAtt, values[12]); // Suffix
	        instance.setValue(KillAtt, values[13]); // Kill
	        instance.setValue(PerAtt, values[14]); // PER

	        training.add(instance); // Add new instance to training data
		}
		
		final double gamma = 0.01; // initial guess
		final double C = 1.0;      // initial guess
		String[] options = {"-N", "0", "-V", "-1"};

		// Create the classifier
		SMO svm = new SMO();         // new instance of classifier
		svm.setOptions(options);
		svm.setKernel(new RBFKernel(training, 25007, gamma));

		svm.setC(C);

		// Train it
		svm.buildClassifier(training);
		//To use the trained classifier, we need to supply an Instance with all of the
		//same attributes except the predicted class.
		//We can re-use the attribute info we set up for the training data.

		Instances classificationInstances  = new Instances("toBeClassified", attrInfo, 1);

		// Build the instance to be classified
//		String[] dataToClassify = {"CapLetter", "other", "0", "0", "0", "1", "0", "1", "0", "0", "0", "0", "0"};
//		Instance toClassify = new Instance(numberOfAttributes-1);
//		toClassify.setDataset(classificationInstances);
//		toClassify.setValue(0, dataToClassify[0]);
//		toClassify.setValue(1, Double.parseDouble(dataToClassify[1]));
//		toClassify.setValue(2, Double.parseDouble(dataToClassify[2]));
//		toClassify.setValue(3, dataToClassify[3]);

		// Classify it
		//double predictedCategory = svm.classifyInstance(sample);
		//System.out.println ("Classified as " + play.value((int) predictedCategory);

	}
	
	private ArrayList<String> fastV(String[] data) {
	      ArrayList result = new ArrayList(data.length);
	      for (String s: data) {
	          result.add(s);
	      }
	      return result;
	  }
}
