package com.opennavent.solarsystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlanetTest {

	Planet planet1, planet2, planet3;
	
	@Before
	public void setUp() throws Exception {
		planet1 = new Planet("Marte", 330.00, 15.00, 80.00, Planet.Direction.ANTI_CLOCKWISE);
		planet2 = new Planet("Mercurio", 4.00, 50.00, Planet.Direction.CLOCKWISE);
        planet3 = new Planet("Mercurio", 4.00, 50.00, Planet.Direction.CLOCKWISE);
	}

	@Test
	public void anticlockwiseRotationTest() {
		planet1.rotate();	planet1.rotate();	planet1.rotate();
		assertEquals(15.00, planet1.getRotationAngle(), 1e-15);
	}
	
	@Test
	public void clockwiseRotationTest() {
		planet2.rotate();
		assertEquals(356.00, planet2.getRotationAngle(), 1e-15);
	}

	@Test
    public void equalsTest(){
	    assertNotEquals(planet1, planet2);
        assertEquals(planet2, planet3);
    }
}
