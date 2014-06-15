package gui;


import java.awt.Rectangle;
import javax.swing.ImageIcon;
import enums.Sides;

public class Target extends ImageIcon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private int x,y,direction;
	private boolean hit,moving,alive;
	private String selectedImage;
	private int numberOfHit = 1;
	private String imageStr;
	private Sides targetType;
	
	public Target(int x, int y,String imageStr) {
		super(imageStr);
		setSelectedImage(imageStr);
		setX(x);
		setY(y);
		setHit(false);
		setAlive(false);
		setMoving(false);
	}
	
	public Target(int x, int y,String imageStr,boolean fire) {
		super(imageStr);
		setSelectedImage(imageStr);
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
		if(moving){
			setSelectedImage(imageStr);
			setDirection(RIGHT);
		}
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

	public int getNumberOfHit() {
		return numberOfHit;
	}

	public void setNumberOfHit(int numberOfHit) {
		this.numberOfHit = numberOfHit;
	}
	
	public Target changeTargetImage(String image){
		Target newTarget= new Target(this.getX(),this.getY(),image);
		newTarget.setDirection(getDirection());
		newTarget.setHit(this.isHit());
		newTarget.setAlive(this.isAlive());
		newTarget.setMoving(this.isMoving());
		newTarget.setNumberOfHit(this.getNumberOfHit());
		newTarget.setTargetType(this.getTargetType());
		return newTarget;
	}

	public Sides getTargetType() {
		return targetType;
	}

	public void setTargetType(Sides targetType) {
		this.targetType = targetType;
	}

}
