package gui;
import java.awt.Rectangle;
import java.util.ArrayList;
import enums.Sides;

/**
 * 
 * @author GAL
 *This function response for the ball coordinates and features.
 */
public class Ball{
	
	private int x,y,dx,dy=-1,celing_flag;
	private GUIBackground g;
	private Pad p;
	private Sides currWall=Sides.DOWN,curr_pad_position=null;
	
	/**
	 * This is the constructor of the ball's class
	 * @param x indicate for the x coordinate of the ball.
	 * @param y	indicate for the y coordinate of the ball.
	 * @param p instance of the pad's class.
	 * @param g instance of the GUIBackground's class.
	 */
	public Ball(int x,int y,Pad p,GUIBackground g){
		this.g=g;
		this.g.wpanel=884;
		this.g.hpanel=562;
		this.p=p;
		setX(x);
		setY(y);
	}
	
	/**
	 * This method response for the movment of the ball
	 */
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
			currWall=Sides.DOWN;
			curr_pad_position=Sides.LEFT_HARD;
		}
		
		else if(padCollision(getX(),getY())==Sides.LEFT_WEEK){
			dx=-1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			currWall=Sides.DOWN;
			curr_pad_position=Sides.LEFT_WEEK;
		}
		
		else if(padCollision(getX(),getY())==Sides.RIGHT_WEEK){
			dx=1;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			currWall=Sides.DOWN;
			curr_pad_position=Sides.RIGHT_WEEK;
		}
			

		else if(padCollision(getX(),getY())==Sides.RIGHT_HARD){
			dx=2;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			currWall=Sides.DOWN;
			curr_pad_position=Sides.RIGHT_HARD;
		}
		
		else if(padCollision(getX(),getY())==Sides.UP){
			dx=0;
			dy=-1;
			x=x+dx;
			y=y+dy;
			celing_flag=0;
			currWall=Sides.DOWN;
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
	
	/**
	 * This is a setter for the x position of the ball.
	 * @param x indicate for the ball's x position.
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/**
	 * This is a setter for the x position of the ball.
	 * @param y  indicate for the ball's y position.
	 */
	public void setY(int y){
		this.y=y;
	}
	/**
	 * This is a getter for the x position of the ball.
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
	 * This method if the ball has been collide with the ceiling. 
	 * @param x indicates on the ball's x position.
	 * @param y indicates on the ball's y position.
	 * @return true if there is collision, else false.
	 */
	public boolean celingCollision(int x,int y){
		if(y==75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method if the ball has been collide with the pad. 
	 * @param x indicates on the ball's x position.
	 * @param y indicates on the ball's y position.
	 * @return which area the ball collide with the pad.
	 */
	public Sides padCollision(int x,int y){
		
		if(x>=p.getX() && x<p.getX()+13 && y==p.getY()-7){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.LEFT_HARD;
		}

		
		else if(x>=p.getX()+13 && x<p.getX()+26 && y==p.getY()-7){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.LEFT_WEEK;
		}
		
		
		else if(x>=p.getX()+26 && x<p.getX()+39 && y==p.getY()-7){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.UP;
		}
		
		
		else if(x>=p.getX()+39 && x<=p.getX()+52 && y==p.getY()-7){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.RIGHT_WEEK;
		}
		
		
		else if(x>=p.getX()+52 && x<=p.getX()+65 && y==p.getY()+14){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			return Sides.RIGHT_HARD;
		}
		
		
		return Sides.DO_NOTHING;
	}
	
	/**
	 * This method if the ball has been collide with the walls. 
	 * @param x indicates on the ball's x position.
	 * @param y indicates on the ball's y position.
	 * @return which side of the wall the ball was hitting.
	 */
	
	public Sides wallCollision(int x,int y){
	
		if(x==0 && y<=540 && y>=75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			currWall=Sides.LEFT;
			return Sides.RIGHT_WEEK;
		}
		
		else if(x==g.wpanel-12 && y<=540 && y>=75){
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("blip.wav").toString())).start();
			currWall=Sides.RIGHT;
			return Sides.LEFT_WEEK;
		}
		
		return Sides.DO_NOTHING;
	}
	
	/**
	 * This method if the ball has been collide with the targets. 
	 * @param x indicates on the ball's x position.
	 * @param y indicates on the ball's y position.
	 * @return which target the ball was hitting.
	 */
	public Sides targetCollision(ArrayList<Target> targets){
		Target removeTarget = null;
		Target changedColorTarget;
		Sides targetStatus = Sides.NO_HIT;
		for (Target target : targets) {
			if(target.isAlive() && !target.isHit())
				if(target.getRectangle().intersects(this.getRectangle())){
					celing_flag=1;
					currWall=Sides.DOWN;
					curr_pad_position=null;
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
	
	/**
	 * This method if the ball has been collide with the floor. 
	 * @param x indicates on the ball's x position.
	 * @param y indicates on the ball's y position.
	 * @return true if the ball hit the floor, else false.
	 */
	public boolean floorCollision(int y){
		
		if(y==g.hpanel){
		g.lives--;
		return true;
		}
		
		return false;
	}
	/**
	 * This method crate rectangle boundaries for the ball.
	 * @return new rectangle boundaries for the ball.
	 */
	public Rectangle getRectangle(){
		return new Rectangle(getX(),getY(),10,10);
	}
	
	/**
	 * THis method reset the ball coordinates.
	 */
	public void resetBall(){
		x=480;
		y=530;
		dx=0;
		dy=-1;
	}
}
