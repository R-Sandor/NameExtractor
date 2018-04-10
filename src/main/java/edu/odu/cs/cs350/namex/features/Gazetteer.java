package edu.odu.cs.cs350.namex.features;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.odu.cs.extract.wordlists.WordLists;

/*
 * Determines whether a given piece of text is a common first name or last name based on a reference list
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is a common first name or last name
 */
public class Gazetteer implements Feature {
		
	private Set<String> dictionaryWords = new HashSet<>();
	private Set<String> citiesAndStates = new HashSet<>();
	private Set<String> countries = new HashSet<>();
	private Set<String> places = new HashSet<>();
	private Set<String> dticFirstNames = new HashSet<>();
	private Set<String> dticLastNames = new HashSet<>();
	private Set<String> commonFirstNames = new HashSet<>();
	private Set<String> commonLastNames = new HashSet<>();
	private Set<String> honorifics = new HashSet<>();
	private Set<String> prefixes = new HashSet<>();
	private Set<String> suffixes = new HashSet<>();
	private Set<String> killWords = new HashSet<>();
	
	public Gazetteer() {	
		WordLists.englishDictionary().forEach(dictionaryWords::add);
		WordLists.citiesAndStatesUS().forEach(citiesAndStates::add);
		WordLists.countriesAndTerritories().forEach(countries::add);
		WordLists.places().forEach(places::add);
		WordLists.firstNames().forEach(dticFirstNames::add);
		WordLists.lastNames().forEach(dticLastNames::add);
		WordLists.commonFirstNames().forEach(commonFirstNames::add);
		WordLists.commonLastNames().forEach(commonLastNames::add);
		WordLists.honorifics().forEach(honorifics::add);
		WordLists.lastNamePrefixes().forEach(prefixes::add);
		WordLists.lastNameSuffixes().forEach(suffixes::add);
		WordLists.nonPersonalIdentifierCues().forEach(killWords::add);
	}
	
	
	@Override
	public String doesApply(String text) {
		StringBuilder binaryStringBuilder = new StringBuilder("");
		binaryStringBuilder.append(isDictionaryWord(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append("0, ");
		binaryStringBuilder.append("0, ");
		binaryStringBuilder.append("0, ");
		binaryStringBuilder.append(isDTICFirstName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isDTICLastName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isCommonFirstName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isCommonLastName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append("0, ");
		binaryStringBuilder.append(isPrefix(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append("0, ");
		binaryStringBuilder.append("0");
		return binaryStringBuilder.toString();
	}
	
	private boolean isCommonFirstName(String text) {
		return commonFirstNames.contains(text.toLowerCase());
	}
	
	private boolean isCommonLastName(String text) {
		return commonLastNames.contains(text.toLowerCase());
	}
	
	private boolean isDTICFirstName(String text) {
		return dticFirstNames.contains(text.toLowerCase());
	}
	
	private boolean isDTICLastName(String text) {
		return dticLastNames.contains(text.toLowerCase());
	}
	
	private boolean isDictionaryWord(String text) {
		return dictionaryWords.contains(text.toLowerCase());
	}
	
	private boolean isPrefix(String text) {
		return prefixes.contains(text);
	}
	
	public int isUsaCitiesAndStates(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, citiesAndStates);
	}
	
	public int isPlace(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, places);
	}
	
	public int isCountryOrTerritory(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, countries);
	}
	
	public int isHonorific(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, honorifics);
	}
	
	public int isSuffix(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, suffixes);
	}
	
	public int isKillText(List<String> blockText, int startIndex) {
		return findMatchingStrings(blockText, startIndex, killWords);
	}
	
	private int findMatchingStrings(List<String> blockText, int startIndex, Set<String> set) {
		String compareString = "";
		int incrementIndex = startIndex;
		while (incrementIndex != blockText.size()) {
			String tempString = blockText.get(incrementIndex);
			if (".,;\\\\:?!$%()/\\\"\"".contains(tempString)) {
				compareString += tempString;
			} else {
				if (incrementIndex != startIndex) {
					compareString = compareString + " " + tempString;	
				} else {
					compareString += tempString;
				}
			}
			if (set.contains(compareString)) {
				return incrementIndex;
			}
			incrementIndex++;
		}
		return -1;
	}
}
