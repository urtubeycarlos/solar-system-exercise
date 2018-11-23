package com.opennavent.solarsystem;

public class Planet implements Cloneable {
	
	public static enum Direction { CLOCKWISE, ANTI_CLOCKWISE };
	
	private String _name;
	private double _rotationAngle, _rotationSpeed, _distance;
	private Direction _rotationDirection;
	
	public Planet(String name, double rotationSpeed, double distance, Direction rotationDirection) {
		this(name, 0.0, rotationSpeed, distance, rotationDirection);
	}
	
	public Planet(String name, double initialRotationAngle, double rotationSpeed, double distance, Direction rotationDirection) {
		
		if( name.isEmpty() || name == null )
			throw new IllegalArgumentException("Planet's name can't be empty.");
		if( distance <= 0.00 )
			throw new IllegalArgumentException("Planet's distance can't be negative or zero");
		if( initialRotationAngle < 0.00 )
			throw new IllegalArgumentException("Planet's initial rotation angle can't be negative");
		if( rotationSpeed < 0.00 )
			throw new IllegalArgumentException("Planet's rotation speed can't be negative. Direction must be setting up with rotationDirection parameter");
		
		_name = name.toLowerCase();
		_rotationAngle = initialRotationAngle;
		_rotationSpeed = rotationSpeed;
		_distance = distance;
		_rotationDirection = rotationDirection == null? Direction.ANTI_CLOCKWISE:rotationDirection;
	}
	
	public String getName() {
		return _name;
	}
	
	public double getRotationAngle() {
		return _rotationAngle;
	}
	
	public double getRotationSpeed() {
		return _rotationSpeed;
	}
	
	public double getDistance() {
		return _distance;
	}
	
	public Direction getDirection() {
		return _rotationDirection;
	}
	
	public void rotate() {
		
		if( getDirection() == Direction.CLOCKWISE )
			_rotationAngle -= _rotationSpeed;
		if( getDirection() == Direction.ANTI_CLOCKWISE )
			_rotationAngle += _rotationSpeed;
		
		if( _rotationAngle >= 360 )
			_rotationAngle -= 360;
		if( _rotationAngle < 0 )
			_rotationAngle += 360;
			
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if( !(o instanceof Planet) || o == null )
			return false;
		
		Planet in = (Planet) o;
		if( in.getName().length() != this.getName().length() )
			return false;
		
		return this.getName().equals( in.getName() );
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Planet: {")
			.append( 
				String
					.format("name: \"%s\"; rotation angle: %.2f; rotation speed: %.2f; distance: %.2f; direction: %s",
					getName(), getRotationAngle(), getRotationSpeed(), getDistance(), getDirection())
			)
			.append("}");
		
		return sb.toString();
	}
	
	@Override
	public Object clone() {
		return new Planet(getName(), getRotationAngle(), getRotationSpeed(), getDistance(), getDirection());
	}

}
