package gui;


import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import enums.Sides;

public class Gun extends ImageIcon {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String bolet = "src\\gui\\bolet.png";
	private static boolean enable;
	private static int fireLeft;
	private int x,y;
	private String strImage;
	private boolean alive;
	private Sides fireStatus;
	
	public Gun(int x, int y) {
		super(bolet);
		setX(x);
		setY(y);
		setStrImage(bolet);
		setAlive(true);
		setFireStatus(Sides.BOLET_LOADED);
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

	public String getStrImage() {
		return strImage;
	}

	public void setStrImage(String image) {
		this.strImage = image;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),11,28);
	}

	public static boolean isEnable() {
		return enable;
	}

	public static void setEnable(boolean enable) {
		Gun.enable = enable;
	}

	public static String getBolet() {
		return bolet;
	}

	public static int getFireLeft() {
		return fireLeft;
	}

	public static void setFireLeft(int fireLeft) {
		Gun.fireLeft = fireLeft;
	}

	public Sides getFireStatus() {
		return fireStatus;
	}

	public void setFireStatus(Sides fireStatus) {
		this.fireStatus = fireStatus;
	}
	
	public Sides targetCollision(ArrayList<Target> targets){
		Target removeTarget = null;
		Target changedColorTarget;
		Sides targetStatus = Sides.NO_HIT;
		for (Target target : targets) {
			if(target.isAlive() && !target.isHit())
				if(target.getRectangle().intersects(this.getRectangle())){
					new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
					target.setNumberOfHit(target.getNumberOfHit() - 1);
					if(target.getNumberOfHit() == 0){
						target.setHit(true);
						target.setAlive(false);
						removeTarget = target;
						targetStatus = target.getTargetType();
					}
					else{
						removeTarget = target;
						if(target.getTargetType() == Sides.GUN_TARGET)
							changedColorTarget = target.changeTargetImage(GUIBackground.getFiretargetimagestr());
						else
							changedColorTarget = target.changeTargetImage(GUIBackground.getTargetimagestr());
						targets.remove(target);
						targets.add(changedColorTarget);
					}
					this.setAlive(false);
					this.setFireStatus(Sides.BOLET_DONE);
					if(removeTarget != null)
						targets.remove(target);
					return targetStatus;
				}
		}
		return Sides.NO_HIT;
	}

}
