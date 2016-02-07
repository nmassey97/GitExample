package exercise2;

import java.util.List;
import java.util.ArrayList;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor{
	private static final int HOR_GREEN_COUNT = 200;
	private static final int HOR_YELLOW_COUNT = 125;
	private static final int HOR_RED_COUNT = HOR_GREEN_COUNT + HOR_YELLOW_COUNT;
	private static final int VER_GREEN_COUNT = 200;
	private static final int VER_YELLOW_COUNT = 125;
	private static final int VER_RED_COUNT = VER_GREEN_COUNT + VER_YELLOW_COUNT;
	private int horLightCounter = 0;
	private int verLightCounter = 0;
	private TrafficLight.Color verticalColor;
	private TrafficLight.Color horizontalColor;
	private TrafficLight.Color color;
	TrafficLight lightN = new TrafficLight(TrafficLight.Color.GREEN);
	TrafficLight lightS = new TrafficLight(TrafficLight.Color.GREEN);
	TrafficLight lightE = new TrafficLight(TrafficLight.Color.RED);
	TrafficLight lightW = new TrafficLight(TrafficLight.Color.RED);
	
	public Intersection() {
		GreenfootImage intersection = new GreenfootImage(Road.ROAD_WIDTH, Road.ROAD_WIDTH);
		setImage(intersection);
	}
	
	public void addLights() {
		verticalColor = TrafficLight.Color.GREEN;
		horizontalColor = TrafficLight.Color.RED;
		
		lightS.setRotation(180);
		lightE.setRotation(90);
		lightW.setRotation(270);
		
		getWorld().addObject(lightN, this.getX(), this.getY() - (TrafficWorld.ROAD_WIDTH/2) - (lightN.getImage().getHeight()/2));
		getWorld().addObject(lightS, this.getX(), this.getY() + (TrafficWorld.ROAD_WIDTH/2) + (lightS.getImage().getHeight()/2));
		getWorld().addObject(lightW, this.getX() - (TrafficWorld.ROAD_WIDTH/2) - (lightW.getImage().getHeight()/2), this.getY());
		getWorld().addObject(lightE, this.getX() + (TrafficWorld.ROAD_WIDTH/2) + (lightE.getImage().getHeight()/2), this.getY());
	}

	public void act() {
		horLightCounter++;
		verLightCounter++;
		
		switch(horizontalColor) {
		
		case YELLOW:
			if (horLightCounter >= HOR_YELLOW_COUNT) {
				horizontalColor = TrafficLight.Color.RED;
				lightE.setColor(horizontalColor);
				lightW.setColor(horizontalColor);
				horLightCounter = 0;
				verticalColor = TrafficLight.Color.GREEN;
				lightN.setColor(verticalColor);
				lightS.setColor(verticalColor);
				verLightCounter = 0;
			}
			break;
		
		case GREEN:
			if (horLightCounter >= HOR_GREEN_COUNT) {
				horizontalColor = TrafficLight.Color.YELLOW;
				lightE.setColor(horizontalColor);
				lightW.setColor(horizontalColor);
				horLightCounter = 0;
			}
			break;
			
		case RED:
			if (horLightCounter >= HOR_RED_COUNT) {
				horizontalColor = TrafficLight.Color.GREEN;
				lightE.setColor(horizontalColor);
				lightW.setColor(horizontalColor);
				horLightCounter = 0;
				verticalColor = TrafficLight.Color.RED;
				lightN.setColor(verticalColor);
				lightS.setColor(verticalColor);
			}
			if (verLightCounter >= VER_GREEN_COUNT) {
				verticalColor = TrafficLight.Color.YELLOW;
				lightN.setColor(verticalColor);
				lightS.setColor(verticalColor);
				verLightCounter = 0;
			}
			break;
		}
		isApproach();
		isInside();
		isLeaving();
	}
	
	private List <IntersectionListener> prevApproaching = new ArrayList<IntersectionListener>();
	private List <IntersectionListener> prevIntersecting = new ArrayList<IntersectionListener>();
	
	public void isApproach() {
		List<IntersectionListener> approachIntersection = getObjectsInRange(75, IntersectionListener.class);
		for (IntersectionListener l : approachIntersection) {
			if (!prevApproaching.contains(l)) {
				l.appIntersection(this);
			}
		}		
	}
	
	private void isInside() {
		List<IntersectionListener> inIntersection = getIntersectingObjects(IntersectionListener.class);
		for (IntersectionListener l : inIntersection) {
			if (!prevIntersecting.contains(l)) {
				l.isInIntersection(this);
			}
			prevIntersecting = inIntersection;
		}
	}
	
	private void isLeaving() {
		List<IntersectionListener> leaveIntersection = getObjectsInRange(75, IntersectionListener.class);
		for (IntersectionListener l : prevApproaching) {
			if (!leaveIntersection.contains(l)) {
				l.leavingIntersection(this);
			}
			
		}
		prevApproaching = leaveIntersection;
	}
}
