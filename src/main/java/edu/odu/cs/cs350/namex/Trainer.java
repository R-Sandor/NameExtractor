package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Trainer {
	

	private LearningMachine learningMac = new LearningMachine();
	
	public static void main(String args[]) {
		Trainer trainer = new Trainer();
		trainer.processTrainingData();
	}
	
	/**
	 * Takes the data from the trainingData2.txt file and creates an
	 * J48 instance model file which it stores under resources.
	 */
	private void processTrainingData() {
		File file = new File("src/main/resources/TrainingData2.txt");
		if (file.exists()) {
			try {
				trainLearningMachine(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
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
	 * @throws Exception File not found
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
		Instances training = new Instances("TrainingData", attrInfo, trainingLines.size());
		// Which attribute holds the
        // class/category that we want
        // to predict?
		training.setClass(PerAtt);

		final double gamma = 0.01; // initial guess
		final double C = 1.0;      // initial guess
		String[] options = {"-N", "0", "-V", "-1"};
		
		// Create the classifier
		SMO svm = new SMO();         // new instance of classifier
		svm.setOptions(options);
		svm.setKernel(new RBFKernel(training, 25007, gamma));

		svm.setC(C);
		
		for (String sdata: trainingLines) {
	        String[] values = sdata.split(", ");
	        
	        int numWords = values.length / 15;
	        for (int i = 0; i < numWords; i++) {
	        	int offset = i * 15;
	        	
	        	Instance instance = new DenseInstance(numberOfAttributes);
		        instance.setValue(LexicalAtt, values[offset]);
		        instance.setValue(PoSAtt, values[offset + 1]); // Parts Of Speech
		        instance.setValue(DictAtt, values[offset + 2]); // Dictionary 
		        instance.setValue(CitiesAtt, values[offset + 3]); // Cities
		        instance.setValue(CountriesAtt, values[offset + 4]); // Countries
		        instance.setValue(PlacesAtt, values[offset + 5]); // Places
		        instance.setValue(DTIC1stNameAtt, values[offset + 6]); // DTIC 1st Names
		        instance.setValue(DTICLastNameAtt, values[offset + 7]); // DTIC Last Names
		        instance.setValue(Common1stNameAtt, values[offset + 8]); // Common 1st Names
		        instance.setValue(CommonLastNameAtt, values[offset + 9]); // Common Last Names
		        instance.setValue(HonorificAtt, values[offset + 10]); // Honorific 
		        instance.setValue(PrefixAtt, values[offset + 11]); // Prefix
		        instance.setValue(SuffixAtt, values[offset + 12]); // Suffix
		        instance.setValue(KillAtt, values[offset + 13]); // Kill
		        instance.setValue(PerAtt, values[offset + 14]); // PER
		        
		        training.add(instance); // Add new instance to training data
	        }
		}
		
		// Train it
		svm.buildClassifier(training);

		for (int i = 0; i < training.size(); i++) {
			double norm = svm.classifyInstance(training.get(i));
			System.out.println(norm);
		}
		
		serializeInstances(training, svm, "trainingData.model");
		
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
		
		//TODO Save data to file

	}
	
	/** 
	 * Serializes the instances information to be used
	 * in the future.
	 */
	private void serializeInstances(Instances instances, Classifier classifier, String modelName) {
		try {
			classifier.buildClassifier(instances);
			SerializationHelper.write("src/main/resources/" + modelName, classifier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	private ArrayList<String> fastV(String[] data) {
	      ArrayList<String> result = new ArrayList<>(data.length);
	      for (String s: data) {
	          result.add(s);
	      }
	      return result;
	  }
}
