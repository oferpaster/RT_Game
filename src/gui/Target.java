package gui;


import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Target extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String imageStr = "src\\gui\\targes.png";
	private int x;
	private int y;
	private boolean hit;
	private boolean alive;
	private boolean moving;
	
	public Target(int x, int y) {
		super(imageStr);
		setX(x);
		setY(y);
		setHit(false);
		setAlive(false);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getImageStr() {
		return imageStr;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),61,26);
	}

}
