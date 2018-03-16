package edu.odu.cs.cs350.namex;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.odu.cs.cs350.namex.features.Gazetteer;

public class TestGazetteer {

	@Test
	public void testDoesApply() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testPerformDatabaseFetching() {
		
		Gazetteer gazetter = new Gazetteer();
		
		// Making sure all list are empty to begin with.
		assertTrue(gazetter.getCommonFirstNamesDTIC().isEmpty());
		assertTrue(gazetter.getCommonFirstNamesCensus().isEmpty());
		assertTrue(gazetter.getCommonLastNamesDTIC().isEmpty());
		assertTrue(gazetter.getCommonLastNamesCensus().isEmpty());
		
		gazetter.performDatabaseFetching();
		
	}

}
