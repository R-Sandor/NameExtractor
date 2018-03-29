package edu.odu.cs.cs350.namex.features;

import edu.odu.cs.extract.wordlists.WordLists;

/*
 * Determines whether a given piece of text is a common first name or last name based on a reference list
 * @param text: a single String of text containing a single word or piece of punctuation without any white space
 * @return String: indicates whether the given text is a common first name or last name
 */
public class Gazetteer implements Feature {
	
	
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
		return contains(WordLists.commonFirstNames(), text);
	}
	
	private boolean isCommonLastName(String text) {
		return contains(WordLists.commonLastNames(), text);
	}
	
	private boolean isDTICFirstName(String text) {
		return contains(WordLists.firstNames(), text);
	}
	
	private boolean isDTICLastName(String text) {
		return contains(WordLists.lastNames(), text);
	}
	
	private boolean isDictionaryWord(String text) {
		return contains(WordLists.englishDictionary(), text);
	}
	
	private boolean isUsaCitiesAndStates(String text) {
		return contains(WordLists.citiesAndStatesUS(), text);
	}
	
	private boolean isPlace(String text) {
		return contains(WordLists.places(), text);
	}
	
	private boolean isCountry(String text) {
		return contains(WordLists.countriesAndTerritories(), text);
	}
	
	private boolean isHonorific(String text) {
		return contains(WordLists.honorifics(), text);
	}
	
	private boolean isPrefix(String text) {
		return contains(WordLists.lastNamePrefixes(), text);
	}
	
	private boolean isSuffix(String text) {
		return contains(WordLists.lastNameSuffixes(), text);
	}
	
	private boolean isKillText(String text) {
		return contains(WordLists.nonPersonalIdentifierCues(), text);
	}
	
	private boolean contains(Iterable<String> list, String text) {
		for (String word : list) {
			if (word.equals(text)) return true;
		}
		return false;
	}
}
