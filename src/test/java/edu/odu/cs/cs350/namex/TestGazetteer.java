package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Gazetteer;

public class TestGazetteer {

	@Test
	public void testDoesApply() {
		
		Gazetteer gazetteer = new Gazetteer();
		
		assertEquals("1, 0", gazetteer.doesApply("krista"));
		assertEquals("1, 0", gazetteer.doesApply("mari"));
		assertEquals("1, 0", gazetteer.doesApply("amelie"));
		assertEquals("0, 1", gazetteer.doesApply("shapiro"));
		assertEquals("0, 1", gazetteer.doesApply("chacon"));
		assertEquals("0, 1", gazetteer.doesApply("schreiber"));
		assertEquals("1, 1", gazetteer.doesApply("deaton"));
		assertEquals("1, 1", gazetteer.doesApply("michell"));
		assertEquals("1, 1", gazetteer.doesApply("wilda"));
		
	}

}
