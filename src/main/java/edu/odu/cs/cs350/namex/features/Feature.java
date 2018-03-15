package edu.odu.cs.cs350.namex.features;

public interface Feature {

	/**
	 * Returns a string which indicates if particular assortments
	 * of features applies to the word passed to the method.
	 * 
	 * @param word the word that is being tested against features.
	 * @return the string containing information about features that
	 *         apply to the word passed.
	 */
	String doesApply(String word);
}
