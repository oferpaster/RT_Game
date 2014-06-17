package gui;

import java.awt.event.KeyEvent;
import enums.Sides;
/**
 * 
 * @author GAL
 *This class response for the pad movement and features.
 */
public class Pad {
	
	private int x,dx,y,dy;
	private Sides status,fire;
	
	
	public Pad(){}
	
	/**
	 * The constructor of the class.
	 * @param x indicates on the ball's x coordinate.
	 * @param y indicates on the ball's y coordinate.
	 */
	public Pad(int x,int y){
		setX(x);
		setY(y);
	    setStatus(Sides.NORMAL_PAD);
	}
	
	/**
	 * This class response update the pad's coordinates.
	 */
	public void move(){
		if(x <= 810 && x > 2)
			x=x+dx;
		else if(x >= 810 && dx < 0)
			x=x+dx;
		else if (x <= 3 && dx > 0)
			x=x+dx;
		y=y+dy;
	}
	
	/**
	 * This is a setter for the x position of the pad.
	 * @param x indicate for the ball's x position.
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/**
	 * This is a setter for the y position of the pad.
	 * @param x indicate for the ball's y position.
	 */
	public void setY(int y){
		this.y=y;
	}
	/**
	 * This is a getter for the x position of the pad.
	 * @return the ball's x position.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * This is a getter for the y position of the ball.
	 * @return the ball's y position.
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * This method response for detecting which key has been pressed.
	 * @param e indicates on the upcoming event.
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode(); 	
		
		if(key==KeyEvent.VK_LEFT){
			
			if(getX()>2)
				dx=-1;
			else
				dx=0;
		}
		
			
		if(key==KeyEvent.VK_RIGHT){
			
			if(getX()<=810)
				dx=1;
			
			else
				dx=0;
		}
		if(key == KeyEvent.VK_UP){
			setFire(Sides.PAD_FIRE);
		}
			
	}
	
	/**
	 * This method response for detecting which key has been released.
	 * @param e indicates on the upcoming event.
	 */
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode(); 	
		
		if(key==KeyEvent.VK_LEFT);
			dx=0;
			
		if(key==KeyEvent.VK_RIGHT);
			dx=0;
			
		if(key==KeyEvent.VK_UP);
			dy=0;
			
		if(key==KeyEvent.VK_DOWN);
			setFire(Sides.STOP_FIRE);
	
	}
	
	/**
	 * This method return the pad's side status 
	 * @return pad's side status.
	 */
	public Sides getStatus() {
		return status;
	}
	
	/**
	 * This method set the pad's side status.
	 * @param status indicates on the pad's side status.
	 */
	public void setStatus(Sides status) {
		this.status = status;
	}
	
	/**
	 * This function return shooting fire Side.
	 * @return shooting fire.
	 */
	public Sides getFire() {
		return fire;
	}
	
	/**
	 * This method sets the fire's side.
	 * @param indicates on sets the fire side.
	 */
	public void setFire(Sides fire) {
		this.fire = fire;
	}
	
	/**
	 * This method reset the pad position.
	 */
	public void resetPad(){
		x=450;
		y=540;
	}
}
