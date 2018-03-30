package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Gazetteer;

public class TestGazetteer {
	
	Gazetteer gazetteer;
	
	@Before
	public void init() {
		gazetteer = new Gazetteer();
	}

	@Test
	public void testDoesApply() {
		
		// Format of output
		// Dict., Cities, Countries, Places, DTIC first, DTIC last, Common 1st, Common last, Honorific, 
		// Prefix, Suffix, Kill
		
		// First name tests
		assertEquals("krista", "0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0", gazetteer.doesApply("krista"));
		assertEquals("amelie", "0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("amelie"));
		
		// Last name tests
		assertEquals("schreiber", "0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("schreiber"));
		assertEquals("shapiro", "0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("shapiro"));
		assertEquals("chacon", "0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("chacon"));
		
		// Last and first name tests
		assertEquals("deaton", "0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("deaton"));
		assertEquals("michell", "0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("michell"));
		assertEquals("wilda", "0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("wilda"));
		assertEquals("mari", "0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("mari"));
		
		// Dictionary words (English)
		assertEquals("linguistic", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("linguistic"));
		assertEquals("backhand", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("backhand"));
		assertEquals("ballyhooed", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("ballyhooed"));
		assertEquals("absinthe's", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("absinthe's"));
		
		// Prefixes
		assertEquals("las", "0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("las"));
		assertEquals("du", "0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0", gazetteer.doesApply("du"));
		assertEquals("popa", "0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0", gazetteer.doesApply("popa"));
		assertEquals("fitz-", "0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("fitz-"));
		
	}

	@Test
	public void testIsUsaCitiesAndStates() {
		
		assertEquals("Napakiak, Alaska", 2, gazetteer.isUsaCitiesAndStates(createListOfStrings("Napakiak", ",", "Alaska"), 0));
		assertEquals("Stevens Village, Alaska", 3, gazetteer.isUsaCitiesAndStates(createListOfStrings("Stevens", "Village", ",", "Alaska"), 0));
		assertEquals("Aliceville, Alabama", 2, gazetteer.isUsaCitiesAndStates(createListOfStrings("Aliceville", ",", "Alabama"), 0));
		assertEquals("The town is Blountsville, AL", 5, gazetteer.isUsaCitiesAndStates(createListOfStrings("The", "town", "is", "Blountsville", ",", "AL"), 3));
	}
	
	@Test
	public void testIsPlace() {
		
		assertEquals("pinedale", 0, gazetteer.isPlace(createListOfStrings("pinedale"), 0));
		assertEquals("sistersville", 0, gazetteer.isPlace(createListOfStrings("sistersville"), 0));
		assertEquals("reedsville", 0, gazetteer.isPlace(createListOfStrings("reedsville"), 0));
		assertEquals("Abel Reservoir", 1, gazetteer.isPlace(createListOfStrings("Abel", "Reservoir"), 0));
		assertEquals("this is a placename. Aberdeen Creek", 6, gazetteer.isPlace(createListOfStrings("this", "is", "a", "placename", ".", "Aberdeen", "Creek"), 5));
	}
	
	@Test
	public void testIsCountryOrTerritory() {
		
		assertEquals("Dominica", 0, gazetteer.isCountryOrTerritory(createListOfStrings("Dominica"), 0));
		assertEquals("Vanuatu", 0, gazetteer.isCountryOrTerritory(createListOfStrings("Vanuatu"), 0));
		assertEquals("Egypt", 0, gazetteer.isCountryOrTerritory(createListOfStrings("Egypt"), 0));
		assertEquals("Russian Federation", 1, gazetteer.isCountryOrTerritory(createListOfStrings("Russian", "Federation"), 0));
		assertEquals("Republic of the Marshall Islands", 4, gazetteer.isCountryOrTerritory(createListOfStrings("Republic", "of", "the", "Marshall", "Islands"), 0));
		assertEquals("There is a country named Turkey", 5, gazetteer.isCountryOrTerritory(createListOfStrings("There", "is", "a", "country", "named", "Turkey"), 5));
	}
	
	@Test
	public void testIsHonorific() {
		
		assertEquals("General", 0, gazetteer.isHonorific(createListOfStrings("General"), 0));
		assertEquals("Sergeant", 0, gazetteer.isHonorific(createListOfStrings("Sergeant"), 0));
		assertEquals("Recruit", 0, gazetteer.isHonorific(createListOfStrings("Recruit"), 0));
		assertEquals("Mrs.", 1, gazetteer.isHonorific(createListOfStrings("Mrs", "."), 0));
	}
	
	@Test
	public void testIsSuffix() {
		
		assertEquals("III", 0, gazetteer.isSuffix(createListOfStrings("III"), 0));
		assertEquals("IV", 0, gazetteer.isSuffix(createListOfStrings("IV"), 0));
		assertEquals("Jr.", 1, gazetteer.isSuffix(createListOfStrings("Jr", "."), 0));
	}
	
	@Test
	public void testIsKillText() {
		
		assertEquals("navy", 0, gazetteer.isKillText(createListOfStrings("navy"), 0));
		assertEquals("sciences", 0, gazetteer.isKillText(createListOfStrings("sciences"), 0));
		assertEquals("base", 0, gazetteer.isKillText(createListOfStrings("base"), 0));
		assertEquals("u.s.", 3, gazetteer.isKillText(createListOfStrings("u", ".", "s", "."), 0));
	}
	
	private List<String> createListOfStrings(String ... values) {
		List<String> list = new ArrayList<>();
		for (String value : values) {
			list.add(value);
		}
		return list;
	}
}
