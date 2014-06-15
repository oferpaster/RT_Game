package gui;

import java.awt.event.KeyEvent;
import enums.Sides;

public class Pad {
	
	private int x,dx,y,dy;
	private Sides status,fire;
	
	
	public Pad(){}
	
	public Pad(int x,int y){
		setX(x);
		setY(y);
	    setStatus(Sides.NORMAL_PAD);
	}
	
	public void move(){
		x=x+dx;
		y=y+dy;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode(); 	
		
		if(key==KeyEvent.VK_LEFT){
			
			if(getX()>=5)
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

	public Sides getStatus() {
		return status;
	}

	public void setStatus(Sides status) {
		this.status = status;
	}

	public Sides getFire() {
		return fire;
	}

	public void setFire(Sides fire) {
		this.fire = fire;
	}
}
