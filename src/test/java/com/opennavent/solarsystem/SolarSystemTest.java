package com.opennavent.solarsystem;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SolarSystemTest {

	SolarSystem solarSystem1, solarSystem2, solarSystem3;
	List<Planet> planetsList1, planetsList2;
	
	@Before
	public void setUp() throws Exception {

		solarSystem1 = new SolarSystem();
		solarSystem2 = new SolarSystem();
		solarSystem3 = new SolarSystem();
		
		planetsList1 = Arrays.asList(new Planet[] {
				new Planet("Alpha", 1.00, 500.00, Planet.Direction.CLOCKWISE),
				new Planet("Beta", 3.00, 2000.00, Planet.Direction.CLOCKWISE),
				new Planet("Gamma", 5.00, 1000.00, Planet.Direction.ANTI_CLOCKWISE)
		});
		
		planetsList2 = Arrays.asList(new Planet[] {
				new Planet("Terra", 7.00, 10000.00, Planet.Direction.CLOCKWISE),
				new Planet("Centauri", 3.78, 60.00, Planet.Direction.ANTI_CLOCKWISE)
		});
		
		solarSystem1.addPlanets(planetsList1);
		solarSystem2.addPlanets(planetsList1);
		solarSystem3.addPlanets(planetsList2);
		
	}
	
	@Test
	public void addsTest() {
		assertEquals( 3, solarSystem1.getPlanetsList().size() );
		solarSystem1.addPlanet(new Planet("Alpha", 1.00, 500.00, Planet.Direction.CLOCKWISE));
        assertEquals( 3, solarSystem1.getPlanetsList().size() );
	}
	
	@Test
	public void containsTest() {
		assertTrue( solarSystem1.contains( planetsList1.get(0) ) );
	}

	@Test
	public void findTest() {
		assertEquals( planetsList1.get(0), solarSystem1.find("Alpha") );
	}

	@Test
	public void containsByNameTest() {
		assertTrue( solarSystem1.contains( planetsList1.get(0).getName() ) );
	}
	
	@Test
	public void removeTest() {
		solarSystem1.remove( planetsList1.get(1) );
		assertEquals( 2, solarSystem1.getPlanetsList().size() );
	}
	
	@Test
	public void removeByNameTest() {
		solarSystem1.remove( planetsList1.get(1).getName() );
		assertEquals( 2, solarSystem1.getPlanetsList().size() );
	}
	
	@Test
	public void equalsTest() {
		assertEquals( solarSystem1, solarSystem2 );
		assertNotEquals( solarSystem2, solarSystem3 );
	}
	
	@Test
	public void alignTest() {
		assertTrue( solarSystem1.arePlanetsAligned() );

		int days = 90;
		for(int i=0; i<days; i++)
			solarSystem1.incrementDay();
		assertTrue( solarSystem1.arePlanetsAligned() );
		
		solarSystem1.incrementDay();
		assertFalse( solarSystem1.arePlanetsAligned() );
	}
	
}
