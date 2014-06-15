package gui;
import java.awt.Rectangle;
import java.util.ArrayList;

import enums.Sides;

public class Ball{
	
	private int x,y,dx,dy=-1,celing_flag;
	private GUIBackground g;
	private Pad p;
	private Sides currWall=Sides.DOWN,curr_pad_position=null;
	
	public Ball(int x,int y,Pad p,GUIBackground g){
		this.g=g;
		this.g.wpanel=884;
		this.g.hpanel=562;
		this.p=p;
		setX(x);
		setY(y);
	}
	
public void move(){
		
		if(celingCollision(getX(),getY())){
			celing_flag=1;
			if(currWall==Sides.LEFT){
				dx=1;
				dy=1;
				x=x+dx;
				y=y+dy;
				currWall=Sides.DOWN;
			}
			
			else if(currWall==Sides.RIGHT){
				dx=-1;
				dy=1;
				x=x+dx;
				y=y+dy;
				currWall=Sides.DOWN;
			}
		
			
			else if(curr_pad_position==Sides.LEFT_HARD){
				dx=-2;
				dy=1;
				x=x+dx;
				y=y+dy;
			}
			
			else if(curr_pad_position==Sides.LEFT_WEEK){
				dx=-1;
				dy=1;
				x=x+dx;
				y=y+dy;
				
			}
			
			else if(curr_pad_position==Sides.RIGHT_WEEK){
				dx=1;
				dy=1;
				x=x+dx;
				y=y+dy;
			}
				

			else if(curr_pad_position==Sides.RIGHT_HARD){
				dx=2;
				dy=1;
				x=x+dx;
				y=y+dy;
				
			}
			
			else{
				dx=0;
				dy=1;
				x=x+dx;
				y=y+dy;
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
			celing_flag=0;
			curr_pad_position=Sides.LEFT_HARD;
		}
		
		else if(padCollision(getX(),getY())==Sides.LEFT_WEEK){
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			curr_pad_position=Sides.LEFT_WEEK;
		}
		
		else if(padCollision(getX(),getY())==Sides.RIGHT_WEEK){
			dx=1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			curr_pad_position=Sides.RIGHT_WEEK;
		}
			

		else if(padCollision(getX(),getY())==Sides.RIGHT_HARD){
			dx=2;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			curr_pad_position=Sides.RIGHT_HARD;
		}
		
		else if(padCollision(getX(),getY())==Sides.UP){
			dx=0;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			curr_pad_position=Sides.DOWN;
		}
		
		
		else if(wallCollision(getX(), getY())==Sides.LEFT_WEEK){
			
			if(celing_flag==1){
			dx=-1;
			dy=1;
			x=x+dx;
			y=y+dy;	
			}
			
			else {
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			}
		}
		
		else if(wallCollision(getX(), getY())==Sides.RIGHT_WEEK){
	
			if(celing_flag==1){
			dx=1;
			dy=1;
			x=x+dx;
			y=y+dy;
			}
			
			else  {
			dx=1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			}
		}
		
		else{
		x=x+dx;
		y=y+dy;
		}
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
	
	public boolean celingCollision(int x,int y){
		if(y==75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return true;
		}
		
		return false;
	}
	
	public Sides padCollision(int x,int y){
		
		if(x>=p.getX() && x<p.getX()+13 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.LEFT_HARD;
		}

		
		else if(x>=p.getX()+13 && x<p.getX()+26 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.LEFT_WEEK;
		}
		
		
		else if(x>=p.getX()+26 && x<p.getX()+39 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.UP;
		}
		
		
		else if(x>=p.getX()+39 && x<=p.getX()+52 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.RIGHT_WEEK;
		}
		
		
		else if(x>=p.getX()+52 && x<=p.getX()+65 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.RIGHT_HARD;
		}
		
		
		return Sides.DO_NOTHING;
	}
	
	public Sides wallCollision(int x,int y){
	
		if(x==0 && y<=540 && y>=75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			currWall=Sides.LEFT;
			return Sides.RIGHT_WEEK;
		}
		
		else if(x==g.wpanel && y<=540 && y>=75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			currWall=Sides.RIGHT;
			return Sides.LEFT_WEEK;
		}
		
		else
			
		
		return Sides.DO_NOTHING;
	}
	
	public Sides targetCollision(ArrayList<Target> targets){
		Target removeTarget = null;
		Target changedColorTarget;
		Sides targetStatus = Sides.NO_HIT;
		for (Target target : targets) {
			if(target.isAlive() && !target.isHit())
				if(target.getRectangle().intersects(this.getRectangle())){
					celing_flag=1;
					new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
					target.setNumberOfHit(target.getNumberOfHit() - 1);
					if(target.getNumberOfHit() == 0){
						target.setHit(true);
						target.setAlive(false);
						removeTarget = target;
						targetStatus = target.getTargetType();
					}
					else {
						removeTarget = target;
						if(target.getTargetType() == Sides.GUN_TARGET)
							changedColorTarget = target.changeTargetImage(GUIBackground.getFiretargetimagestr());
						else
							changedColorTarget = target.changeTargetImage(GUIBackground.getTargetimagestr());
						targets.remove(target);
						targets.add(changedColorTarget);
					}
					dy= dy*(-1);
					dx= dx*(-1);
					if(removeTarget != null)
						targets.remove(target);
					return targetStatus;
				}
		}
		return Sides.NO_HIT;
	}
	
	public boolean floorCollision(int y){
		
		if(y==g.hpanel)
			return true;
		
		return false;
	}
	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),10,10);
	}
	
	public void resetBall(){
		x=480;
		y=530;
		dx=0;
		dy=-1;
	}
}
