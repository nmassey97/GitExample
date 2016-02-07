package exercise2;

public enum Direction {
	NORTH(0),
	SOUTH(180),
	EAST(270),
	WEST(90);
	
	private int direction;
	
	private Direction(int rotation) {
		this.direction = rotation;
	}
	
	int getDirection() {
		return direction;
	}
}
