package exercise2;

import java.util.Random;
import exercise2.TrafficLight.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	public static enum State {OUTSIDE, APPROACHING, INSIDE};
	public int speed;
	public static final int MAX_SPD = 5;
	Random r = new Random();
	private Color color;
	TrafficLight t;
	Intersection i;
	State state = State.OUTSIDE;
	//blue = 0;
	//red = 1;
	//purple = 2;
	//yellow = 3;
	int randCarColor = r.nextInt(4);

	public Car() {
		String carColor = "";
		if (randCarColor == 0)
			carColor = "images\\topCarBlue.png";
		else if (randCarColor == 1)
			carColor = "images\\topCarRed.png";
		else if (randCarColor == 2)
			carColor = "images\\topCarPurple.png";
		else if (randCarColor == 3)
			carColor = "images\\topCarYellow.png";
		GreenfootImage carImage = new GreenfootImage(carColor);
		this.setImage(carImage);
	}

	public void act() {

		switch (state) {
		case OUTSIDE:
			speedUp();
			break;

		case APPROACHING:
			if (this.getRotation() == 0) {
				t = i.lightE;
			} if (this.getRotation() == 90) {
				t = i.lightS;
			} if (this.getRotation() == 180) {
				t = i.lightW;
			} if (this.getRotation() == 270) {
				t = i.lightN;
			}
			if (this.getRotation() == Direction.NORTH.getDirection() || this.getRotation() == Direction.SOUTH.getDirection()) {
				if (i != null) {
					if (t.getColor() == TrafficLight.Color.GREEN)
						speedUp();
					else
						slowDown();
					}
			} if (this.getRotation() == Direction.EAST.getDirection() || this.getRotation() == Direction.WEST.getDirection()) {
				if (i != null) {
					if (t.getColor() == TrafficLight.Color.GREEN)
						speedUp();
					else
						slowDown();
					}
			}
			break;

		case INSIDE:
			if (this.getRotation() == 0) {
				t = i.lightE;
			} if (this.getRotation() == 90) {
				t = i.lightS;
			} if (this.getRotation() == 180) {
				t = i.lightW;
			} if (this.getRotation() == 270) {
				t = i.lightN;
			}
			if (this.getRotation() == Direction.NORTH.getDirection() || this.getRotation() == Direction.SOUTH.getDirection()) {
				if (i != null) {
					if (t.getColor() == TrafficLight.Color.RED)
						slowDown();
					else
						speedUp();
				}
			} if (this.getRotation() == Direction.EAST.getDirection() || this.getRotation() == Direction.WEST.getDirection()) {
				if (i != null) {
					if (t.getColor() == TrafficLight.Color.RED)
						slowDown();
					else
						speedUp();
				}
			}
			break;
		}

		move(speed);

		if (isAtEdge()) {
			if (getX() >= TrafficWorld.WORLD_WIDTH - 1)
				setLocation(0, getY());
			else if (getX() <= 1)
				setLocation(1000, getY());
			else if (getY() >= TrafficWorld.WORLD_HEIGHT - 1)
				setLocation(getX(), 0);
			else if (getY() <= 1)
				setLocation(getX(), 750);
		}
	}

	@Override
	public void appIntersection(Intersection i) {
		this.i = i;
		state = State.APPROACHING;		
	}

	@Override
	public void isInIntersection(Intersection i) {
		this.i = i;
		state = State.INSIDE;
	}

	@Override
	public void leavingIntersection(Intersection i) {
		this.i = i;
		state = State.OUTSIDE;
	}
	
	public void slowDown() {
		if (speed > 0) 
			speed--;
	}
	
	public void speedUp() {
		if (speed < MAX_SPD)
			speed++;
	}
}
