package gui;

import java.awt.event.KeyEvent;
import enums.Sides;

public class Pad {
	
	private int x,dx,y,dy;
	private Sides status,fire;
	
	
	public Pad(){}
	
	public Pad(int x,int y){
		this.x=x;
	    this.y=y;
	    setStatus(Sides.NORMAL_PAD);
	}
	
	public void move(){
		if(x <= 810 && x > 2)
			x=x+dx;
		else if(x >= 810 && dx < 0)
			x=x+dx;
		else if (x <= 3 && dx > 0)
			x=x+dx;
		y=y+dy;
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
	
	public void resetPad(){
		x=450;
		y=540;
	}
}
