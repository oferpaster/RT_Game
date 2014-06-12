package gui;

import java.awt.Rectangle;
import java.util.ArrayList;

import enums.Sides;
public class Ball{
	
	private int x,y,dx,dy=-1;
	private GUIBackground g;
	private Pad p;
	
	public Ball(int x,int y,Pad p,GUIBackground g){
		this.g=g;
		this.p=p;
		this.x=x;
		this.y=y;
	}
	
	public void move(){
		
		if(celingCollision(getX(),getY())){
			dx=0;
			dy=1;
			x=x+dx;
			y=y+dy;
		}
		
		if(padCollision(getX(),getY())==Sides.LEFT_HARD){
			dx=-2;
			dy=-1;
			x=x+dx;
			y=y+dy;
		}
		
		else if(padCollision(getX(),getY())==Sides.LEFT_WEEK){
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
		}
		
		else if(padCollision(getX(),getY())==Sides.RIGHT_WEEK){
			dx=1;
			dy=-1;
			x=x+dx;
			y=y+dy;
		}
			

		else if(padCollision(getX(),getY())==Sides.RIGHT_HARD){
			dx=2;
			dy=-1;
			x=x+dx;
			y=y+dy;
		}
		
		/*else if(wallCollision(getX(), getY())==Sides.LEFT_WEEK){
			dx = -1;
			dy = -1;
			 x = x+dx;
			 y = y+dy;
		}
		
		else if(wallCollision(getX(), getY())==Sides.RIGHT_WEEK){
			dx = 1;
			dy = -1;
			x = x+dx;
			y = y+dy;
		}*/
		
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
		System.out.println(p.getX()+" "+p.getY()+" "+getX()+" "+getY());
		if(x>=p.getX() && x<p.getX()+13 && y==p.getY()+14)
			return Sides.LEFT_HARD;
		
		else if(x>=p.getX()+13 && x<p.getX()+26 && y==p.getY()+14)
			return Sides.LEFT_WEEK;
		
		else if(x>=p.getX()+26 && x<p.getX()+39 && y==p.getY()+14){
			System.out.println("success");
			return Sides.UP;
		}
		
		else if(x>=p.getX()+39 && x<=p.getX()+52 && y==p.getY()+14)
			return Sides.RIGHT_HARD;
		
		else if(x>=p.getX()+52 && x<=p.getX()+65 && y==p.getY()+14)
			return Sides.RIGHT_WEEK;
		
		return Sides.DO_NOTHING;
	}
	
	public void wallCollision(int x,int y){
		
		if(x==0 && y<=540 && y>=75)
			dx=dx*(-1);
			//return Sides.RIGHT_WEEK;
		
		else if(x==g.wpanel && y<=540 && y>=75)
			dx=dx*(-1);
			//return Sides.LEFT_WEEK;
		
		//return Sides.DO_NOTHING;
	}
	
	public void targetCollision(ArrayList<Target> targets){
		for (Target target : targets) {
			if(target.getRectangle().intersects(this.getRectangle())){
				target.setHit(true);
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
