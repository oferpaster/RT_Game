package gui;

import java.awt.event.KeyEvent;

public class Pad {
	
	private int x,dx,y,dy;
	
	public Pad(){}
	
	public Pad(int x,int y){
		this.x=x;
	    this.y=y;
	}
	
	public void move(){
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
			dy=0;
	
	}
}
