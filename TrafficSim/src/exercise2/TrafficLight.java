package exercise2;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;

public class TrafficLight extends Actor{
	Random r = new Random();
	public static enum Color{
		RED, YELLOW, GREEN;
	}
	
	Color curColor;
	
	private String[] colorImages = {"images/trafficLightRed.png", "images/trafficLightYellow.png", "images/trafficLightGreen.png"};
	
	public TrafficLight(Color initColor) {
		GreenfootImage image = new GreenfootImage(colorImages[initColor.ordinal()]);
		this.act();
		setImage(image);
	}
	
	public void setColor(Color newColor) {
		setImage(colorImages[newColor.ordinal()]);
		curColor = newColor;
	}

	public Color getColor() {
		return curColor;
	}
}
