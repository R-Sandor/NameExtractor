package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Gazetteer;

public class TestGazetteer {

	@Test
	public void testDoesApply() {
		
		// Format of output
		// Dict., Cities, Countries, Places, DTIC first, DTIC last, Common 1st, Common last, Honorific, 
		// Prefix, Suffix, Kill
		
		Gazetteer gazetteer = new Gazetteer();
		
		// First name tests
		assertEquals("krista", "0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0", gazetteer.doesApply("krista"));
		assertEquals("amelie", "0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("amelie"));
		
		// Last name tests
		assertEquals("schreiber", "0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("schreiber"));
		assertEquals("shapiro", "0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("shapiro"));
		assertEquals("chacon", "0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("chacon"));
		
		// Last and first name tests
		assertEquals("deaton", "0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("deaton"));
		assertEquals("michell", "0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("michell"));
		assertEquals("wilda", "0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("wilda"));
		assertEquals("mari", "0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("mari"));
		
		// Dictionary words (English)
		assertEquals("linguistic", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("linguistic"));
		assertEquals("backhand", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("backhand"));
		assertEquals("ballyhooed", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("ballyhooed"));
	
		// Cities
		
		// Countries
		assertEquals("Dominica", "1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Dominica"));
		assertEquals("Vanuatu", "1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Vanuatu"));
		assertEquals("Egypt", "1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Egypt"));
		
		// Placenames
		assertEquals("pinedale", "0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("pinedale"));
		assertEquals("sistersville", "0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("sistersville"));
		assertEquals("reedsville", "0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("reedsville"));
		
		// Honorifics
		assertEquals("General", "0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("General"));
		assertEquals("Sergeant", "0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("Sergeant"));
		assertEquals("Recruit", "0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("Recruit"));
		
		// Prefixes
		assertEquals("las", "0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("las"));
		assertEquals("du", "0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0", gazetteer.doesApply("du"));
		assertEquals("popa", "0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0", gazetteer.doesApply("popa"));
		
		// Suffixs
		assertEquals("III", "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0", gazetteer.doesApply("III"));
		assertEquals("IV", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0", gazetteer.doesApply("IV"));
		
		// Kill words
		assertEquals("navy", "1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1", gazetteer.doesApply("navy"));
		assertEquals("sciences", "1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1", gazetteer.doesApply("sciences"));
		assertEquals("base", "1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1", gazetteer.doesApply("base"));
		
	}

}
