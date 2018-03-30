package edu.odu.cs.cs350.namex.features;

import java.util.HashSet;
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
	// We might need to create a list of phrases for this one.
	private Set<String> organizations = new HashSet<>();
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
		binaryStringBuilder.append(isUsaCitiesAndStates(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isCountry(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isPlace(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isDTICFirstName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isDTICLastName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isCommonFirstName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isCommonLastName(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isHonorific(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isPrefix(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isSuffix(text) ? "1" : "0").append(", ");
		binaryStringBuilder.append(isKillText(text) ? "1" : "0");
		return binaryStringBuilder.toString();
	}
	
	private boolean isCommonFirstName(String text) {
		return commonFirstNames.contains(text);
	}
	
	private boolean isCommonLastName(String text) {
		return commonLastNames.contains(text);
	}
	
	private boolean isDTICFirstName(String text) {
		return dticFirstNames.contains(text);
	}
	
	private boolean isDTICLastName(String text) {
		return dticLastNames.contains(text);
	}
	
	private boolean isDictionaryWord(String text) {
		return dictionaryWords.contains(text);
	}
	
	private boolean isUsaCitiesAndStates(String text) {
		return citiesAndStates.contains(text);
	}
	
	private boolean isPlace(String text) {
		return places.contains(text);
	}
	
	private boolean isCountry(String text) {
		return countries.contains(text);
	}
	
	private boolean isHonorific(String text) {
		return honorifics.contains(text);
	}
	
	private boolean isPrefix(String text) {
		return prefixes.contains(text);
	}
	
	private boolean isSuffix(String text) {
		return suffixes.contains(text);
	}
	
	private boolean isKillText(String text) {
		if (killWords.contains(text)|| organizations.contains(text)) {
			return true;
		}
		return false;
		
	}
	
	@SuppressWarnings("unused")
	private boolean isOrganization(String text) {
		return organizations.contains(text);
	}
}
