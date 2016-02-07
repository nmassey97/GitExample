package exercise2;

import java.awt.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Road extends Actor {
	public static int ROAD_WIDTH = 50;
	
	public Road() {
		GreenfootImage roadImage = new GreenfootImage(TrafficWorld.WORLD_WIDTH, ROAD_WIDTH);
		roadImage.setColor(Color.gray);
		roadImage.fill();
		this.setImage(roadImage);
	}
}
