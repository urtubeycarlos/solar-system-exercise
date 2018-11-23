package com.opennavent.solarsystem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SolarSystem implements Iterable<Planet>{
	
	private List<Planet> _planets;
	private int _daysCounter, _alignCounter;
	
	public SolarSystem() {
		_planets = new LinkedList<Planet>();
		_daysCounter = 0;
		_alignCounter = 0;
	}

	public boolean addPlanet(Planet planet) {
		checkNull(planet, "reference", "adding planet.");
		if( _planets.contains(planet) )
			remove(planet);
		return _planets.add(planet);
	}
	
	public boolean addPlanets(Iterable<Planet> planets) {
		checkNull(planets, "reference", "adding planets.");
		boolean ret = true;
		for(Planet p:planets)
			ret = ret && addPlanet(p);
		return ret;
	}
	
	public List<Planet> getPlanetsList(){
		LinkedList<Planet> ret = new LinkedList<Planet>();
		getPlanets()
			.forEach( p -> ret.add( (Planet) p.clone() ) ); 
		return ret;
	}
	
	public boolean contains(Planet planet) {
		checkNull(planet, "reference", "contains method.");
		return getPlanets().contains(planet);
	}
	
	public boolean contains(String name) {
		checkNull(name, "planet's name", "contains method.");
		checkEmpty(name, "contains method.");
		return find(name) != null;
	}
	
	public boolean remove(Planet planet) {
		checkNull(planet, "reference", "removing planet.");
		return getPlanets().remove(planet);
	}
	
	public boolean remove(String name) {
		checkNull(name, "planet's name", "removing planet.");
		checkEmpty(name, "removing planet.");
		return getPlanets().remove( find(name) );
	}
	
	public Planet find(String name) {
		checkNull(name, "planet's name", "finding planet.");
		checkEmpty(name, "finding planet.");
		for( Planet p:getPlanets() )
			if( p.getName().equals(name.toLowerCase()) )
				return (Planet) p.clone();
		return null;
	}
	
	public void incrementDay() {
		getPlanets().forEach( p -> p.rotate() );
		_daysCounter++;
		
		if( arePlanetsAligned() )
			_alignCounter++;
	}
	
	public int getDaysPassed() {
		return _daysCounter;
	}

	public boolean arePlanetsAligned() {
	
		double firstAngle = getPlanets().get(0).getRotationAngle();
		boolean acum = true;
		
		for( Planet p:getPlanets() )
			acum = acum && ( firstAngle == p.getRotationAngle() || areOpposite(firstAngle, p.getRotationAngle() ) );
		
		return acum;
	}

	public int getAlingsPassed() {
		return _alignCounter;
	}
	
	
	private List<Planet> getPlanets(){
		return _planets;
	}

	private boolean areOpposite(double angle1, double angle2) {
		return ( angle1 + 180.00 == angle2 ) || ( angle2 + 180.00 == angle1 );
	}
	
	private void checkNull(Object param, String parameterName, String action) {
		if( param == null )
			throw new IllegalArgumentException(parameterName + " can't be null for " + action);
	}
	
	private void checkEmpty(String param, String action) {
		if( param.isEmpty() )
			throw new IllegalArgumentException("planet's name can't be empty for " + action);
	}
	
	@Override
	public int hashCode() {
		return _planets.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {

		if( !(o instanceof SolarSystem) || o == null )
			return false;
		
		SolarSystem in = (SolarSystem) o;
		if( this.getPlanets().size() != in.getPlanets().size() )
			return false;
		
		return this.getPlanets().equals( in.getPlanets() );
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SolarSystem: {")
			.append( getPlanets().toString() )
			.append("}");
		return sb.toString();
	}
	
	@Override
	public Iterator<Planet> iterator() {
		return _planets.iterator();
	}

}
