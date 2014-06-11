package gui;

import java.awt.event.KeyEvent;

public class Pad {
	
	private int x,dx,y,dy;
	
	
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
		
		if(key==KeyEvent.VK_LEFT)
			dx=-1;
		
			
		if(key==KeyEvent.VK_RIGHT)
			dx=1;
			
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
