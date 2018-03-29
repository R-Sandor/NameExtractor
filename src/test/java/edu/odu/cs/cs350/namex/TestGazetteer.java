package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Gazetteer;

public class TestGazetteer {

	@Test
	public void testDoesApply() {
		
		Gazetteer gazetteer = new Gazetteer();
		// DTIC first, DTIC last, Common 1st, Common last
		
		// First name tests
		assertEquals("0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0", gazetteer.doesApply("krista"));
		assertEquals("0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("mari"));
		assertEquals("0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0", gazetteer.doesApply("amelie"));
		
		// Last name tests
		assertEquals("0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("schreiber"));
		assertEquals("0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("shapiro"));
		assertEquals("0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("chacon"));
		
		// Last and first name tests
		assertEquals("0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0", gazetteer.doesApply("deaton"));
		assertEquals("0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0", gazetteer.doesApply("michell"));
		assertEquals("0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0", gazetteer.doesApply("wilda"));
		
		// Dictionary words (English)
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("linguistic"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("backhand"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("ballyhooed"));
	
		// Cities
		assertEquals("0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Pinedale"));
		assertEquals("0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Sistersville"));
		assertEquals("0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Reedsville"));
		
		// Countries
		assertEquals("0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Dominica"));
		assertEquals("0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Vanuatu"));
		assertEquals("0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0", gazetteer.doesApply("Egypt"));
		
		// Honorifics
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("Dominica"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("Sergeant"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0", gazetteer.doesApply("Recruit"));
		
		// Prefixes
		assertEquals("0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("las"));
		assertEquals("0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("du"));
		assertEquals("0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0", gazetteer.doesApply("popa"));
		
		// Suffixs
		assertEquals("0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0", gazetteer.doesApply("III"));
		assertEquals("0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0", gazetteer.doesApply("IV"));
		
		// Kill words
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1", gazetteer.doesApply("navy"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1", gazetteer.doesApply("sciences"));
		assertEquals("1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1", gazetteer.doesApply("base"));
		
	}

}
