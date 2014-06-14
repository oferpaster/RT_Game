package gui;


import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Target extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String imageStr = "src\\gui\\targes.png";
	private static final String fireImageStr = "src\\gui\\targesFire.png";
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private int x;
	private int y;
	private boolean hit;
	private boolean alive;
	private boolean moving;
	private String selectedImage;
	private int direction;
	private boolean giftTarget;
	
	public Target(int x, int y) {
		super(imageStr);
		setSelectedImage(imageStr);
		setX(x);
		setY(y);
		setHit(false);
		setAlive(false);
		setMoving(false);
	}
	
	public Target(int x, int y,boolean moving) {
		super(fireImageStr);
		setSelectedImage(fireImageStr);
		setMoving(moving);
		setX(x);
		setY(y);
		setHit(false);
		setAlive(false);
		setDirection(RIGHT);
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
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
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		if(moving)
			setSelectedImage(fireImageStr);
		this.moving = moving;
	}
	
	public String getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}

	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),61,26);
	}

}
