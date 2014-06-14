package gui;
import java.awt.Rectangle;
import java.util.ArrayList;

import enums.Sides;

public class Ball{
	
	private int x,y,dx,dy=-1,celinf_flag,pad_flag;
	private GUIBackground g;
	private Pad p;
	private Sides currWall=Sides.DOWN,prevWall;
	
	public Ball(int x,int y,Pad p,GUIBackground g){
		this.g=g;
		this.g.wpanel=884;
		this.p=p;
		this.x=x;
		this.y=y;
	}
	
	public void move(){
		
		if(celingCollision(getX(),getY())){
		
			if(currWall==Sides.LEFT){
				dx=1;
				dy=1;
				x=x+dx;
				y=y+dy;
				celinf_flag=1;
				pad_flag=0;
				currWall=Sides.DOWN;
			}
			
			else if(currWall==Sides.RIGHT){
				dx=-1;
				dy=1;
				x=x+dx;
				y=y+dy;
				celinf_flag=1;
				pad_flag=0;
				currWall=Sides.DOWN;
			}
			
			else{
				dx=0;
				dy=1;
				x=x+dx;
				y=y+dy;
				currWall=Sides.DOWN;
			}
		}
		
		else if(getX()>g.wpanel){
			x=g.wpanel;
		}
		
		else if(getX()<0)
			x=0;
			
		
		if(padCollision(getX(),getY())==Sides.LEFT_HARD){
			dx=-2;
			dy=-1;
			x=x+dx;
			y=y+dy;
			pad_flag=1;
			celinf_flag=0;
		}
		
		else if(padCollision(getX(),getY())==Sides.LEFT_WEEK){
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			pad_flag=1;
			celinf_flag=0;
		}
		
		else if(padCollision(getX(),getY())==Sides.RIGHT_WEEK){
			dx=1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			pad_flag=1;
			celinf_flag=0;
		}
			

		else if(padCollision(getX(),getY())==Sides.RIGHT_HARD){
			dx=2;
			dy=-1;
			x=x+dx;
			y=y+dy;
			pad_flag=1;
			celinf_flag=0;
		}
		
		else if(padCollision(getX(),getY())==Sides.UP){
			dx=0;
			dy*=-1;
			x=x+dx;
			y=y+dy;
			pad_flag=1;
			celinf_flag=0;
		}
		else if(wallCollision(getX(), getY())==Sides.LEFT_WEEK){
		
			//System.out.println(prevWall+" "+currWall+" "+1);
			System.out.println("celinf_flag"+" "+1);
			dx=-1;
			dy=1;
			x=x+dx;
			y=y+dy;	
			}
			
			else{
			System.out.println("pad_flag"+" "+1);
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			}
		}
			else if(wallCollision(getX(), getY())==Sides.RIGHT_WEEK){
				//System.out.println(prevWall+" "+currWall+" "+2);
					
				if(prevWall==Sides.DOWN){
				dx=1;
				dy=1;
				x=x+dx;
				y=y+dy;
				}
				
				else{
				dx = 1;
				dy = -1;
				x = x+dx;
				y = y+dy;
				}
			}
			
			else{
				x=x+dx;
				y=y+dy;
			}
	}
		
		
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean celingCollision(int x,int y){
		if(y==75 || x==0 || x==g.wpanel)
			return true;
		
		return false;
	}
	
	public Sides padCollision(int x,int y){
		
		if(x>=p.getX() && x<p.getX()+13 && y==p.getY()+14)
			return Sides.LEFT_HARD;

		
		else if(x>=p.getX()+13 && x<p.getX()+26 && y==p.getY()+14)
			return Sides.LEFT_WEEK;
		
		
		else if(x>=p.getX()+26 && x<p.getX()+39 && y==p.getY()+14)
			return Sides.UP;
		
		
		else if(x>=p.getX()+39 && x<=p.getX()+52 && y==p.getY()+14)
			return Sides.RIGHT_WEEK;
		
		
		else if(x>=p.getX()+52 && x<=p.getX()+65 && y==p.getY()+14)
			return Sides.RIGHT_HARD;
		
		
		return Sides.DO_NOTHING;
	}
	
	public Sides wallCollision(int x,int y){
	
		if(x==0 && y<=540 && y>=75){
			prevWall=currWall;
			currWall=Sides.LEFT;
			return Sides.RIGHT_WEEK;
		}
		
		else if(x==g.wpanel && y<=540 && y>=75){
			prevWall=currWall;
			currWall=Sides.RIGHT;
			return Sides.LEFT_WEEK;
		}
		
		return Sides.DO_NOTHING;
	}
	
	public void targetCollision(ArrayList<Target> targets){
		for (Target target : targets) {
			if(target.isAlive() && !target.isHit())
				if(target.getRectangle().intersects(this.getRectangle())){
					target.setHit(true);
					target.setAlive(false);
					dy= dy*(-1);
					dx= dx*(-1);
					break;
				}
		}
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),10,10);
	}
}
