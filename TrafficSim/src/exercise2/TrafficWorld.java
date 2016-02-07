package exercise2;

import greenfoot.World;
import java.awt.Color;
import greenfoot.GreenfootImage;
import java.util.Random;

public class TrafficWorld extends World {
	public static final int WORLD_WIDTH = 1000;
	public static final int WORLD_HEIGHT = 750;
	public static final int WORLD_CELLSIZE = 1;
	public static final int ROAD_WIDTH = 50;
	public static final int NUM_EW_ROADS = 5;
	public static final int NUM_NS_ROADS = 7;
	public static int gapSizeEW = (WORLD_HEIGHT - (NUM_EW_ROADS * ROAD_WIDTH))/(NUM_EW_ROADS - 1);
	public static int gapSizeNS = (WORLD_WIDTH - (NUM_NS_ROADS * ROAD_WIDTH))/(NUM_NS_ROADS - 1);
	private static Road[] EWRoads = new Road[NUM_EW_ROADS];
	private static Road[] NSRoads = new Road[NUM_NS_ROADS];
	Random r = new Random();
	
	public TrafficWorld() {
		super(WORLD_WIDTH, WORLD_HEIGHT, WORLD_CELLSIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green);
		background.fill();
		
		drawHorizontalRoad();
		drawVerticalRoad();
		addHorLeftToRightCar();
		addHorRightToLeftCar();
		addVerTopToBotCar();
		addVerBotToTopCar();
		addIntersection();
	}
	
	private void drawHorizontalRoad() {
		for (int i=0; i < NUM_EW_ROADS; i++) {
			EWRoads[i] = new Road();
			this.addObject(EWRoads[i], WORLD_WIDTH/2, (((gapSizeEW + ROAD_WIDTH) * i) + ROAD_WIDTH/2));
		}
	}
	
	private void drawVerticalRoad() {
		for (int i=0; i < NUM_NS_ROADS; i++) {
			NSRoads[i] = new Road();
			NSRoads[i].setRotation(90);
			this.addObject(NSRoads[i], (((gapSizeNS + ROAD_WIDTH) * i) + ROAD_WIDTH/2), WORLD_WIDTH/2);
		}
	}
	
	private void addHorLeftToRightCar() {
		for (int n = 0; n < NUM_EW_ROADS; n++) {
			Car car = new Car();
			addObject(car, r.nextInt(WORLD_WIDTH), ((ROAD_WIDTH/4)*3)+((gapSizeEW + ROAD_WIDTH) * n));
		}
	}

	private void addHorRightToLeftCar() {
		for (int n = 0; n < NUM_EW_ROADS; n++) {
			Car car = new Car();
			car.setRotation(180);
			addObject(car, r.nextInt(WORLD_WIDTH), (ROAD_WIDTH/4)+((gapSizeEW + ROAD_WIDTH) * n));
		}
	}
	
	private void addVerBotToTopCar() {
		for (int n = 0; n < NUM_NS_ROADS; n++) {
			Car car = new Car();
			car.setRotation(270);
			addObject(car, ((ROAD_WIDTH/4)*3)+((gapSizeNS + ROAD_WIDTH) * n), r.nextInt(WORLD_HEIGHT));
		}
	}

	private void addVerTopToBotCar() {
		for (int n = 0; n < NUM_NS_ROADS; n++) {
			Car car = new Car();
			car.setRotation(90);
			addObject(car, (ROAD_WIDTH/4)+((gapSizeNS + ROAD_WIDTH) * n), r.nextInt(WORLD_HEIGHT));
		}
	}
	
	private void addIntersection() {
		for (int h = 0; h < NUM_EW_ROADS; h++) {
			for (int v = 0; v < NUM_NS_ROADS; v++) {
				Intersection intersection = new Intersection();
				this.addObject(intersection, NSRoads[v].getX(), EWRoads[h].getY());
				intersection.addLights();
			}
		}
	}
}
;