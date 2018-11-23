package com.opennavent.solarsystem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String... args) {
	
		List<Integer> intArgs = new LinkedList<Integer>();
		
		Arrays.asList(args).stream()
			.mapToInt(arg -> {
				try {
					int n = Integer.parseInt(arg);
					if( n <= 0 )
						throw new Exception();
					return n;
				} catch (Exception ex) {
					throw new IllegalArgumentException("Input must be positive integer. Invalid input: " + arg);
				}
			})
			.forEach(intArgs::add);
		
		
		SolarSystem ss = new SolarSystem();
		ss.addPlanets( Arrays.asList(new Planet[] {
				new Planet("Alpha", 1.00, 500.00, Planet.Direction.CLOCKWISE),
				new Planet("Beta", 3.00, 2000.00, Planet.Direction.CLOCKWISE),
				new Planet("Gamma", 5.00, 1000.00, Planet.Direction.ANTI_CLOCKWISE)
		}));
		
		
		intArgs.forEach( arg -> {
			
			for(int i=0; i<arg; i++)
				ss.incrementDay();
			
			System.out.println( 
					String.format( "Days passed: %d. \nAlings passed: %d.", ss.getDaysPassed(), ss.getAlingsPassed()
			));
			
		});
		
		
	}
}
