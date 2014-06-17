package gui;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
/**
 * 
 * @author GAL
 *This class response for conducting the game's time.
 */
public class Game_Time {
	Timer timer;
	private JLabel lblTime;
	public static int seconds=60,minutes;
	
	/**
	 * The constructor of the class.
	 * @param minutes minutes of every level.
	 * @param lblTime the label which the time is displayed on. 
	 */
	public Game_Time(int minutes, JLabel lblTime) {
		Game_Time.minutes=minutes;
		this.lblTime=lblTime;
		lblTime.setText("Time:"+" "+this.minutes+":"+"00");
		this.minutes--;
	    timer = new Timer();
	    timer.schedule(new RemindTask(),0, 1000);
	   System.out.println(minutes);
	}
	
	/**
	 * 
	 * @author GAL
	 *This class response to invoke a time task every 1 second and to count the level elapsed time.
	 */
	class RemindTask extends TimerTask {
		/**
		 * This thread response to update the seconds and the minutes of every level.
		 */
	    public void run() {
	      if(seconds==0){
	      seconds=60;
	      minutes--;
	      if(minutes == 0)
	    	  new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("itsover.wav").toString())).start();
	      }
	    
	      seconds--;
	      writeTime();
	    }
	    /**
	     * This method response to write the time onto the time's lable.
	     */
	    public void writeTime(){
	    	if(seconds/10==0)
		    	  lblTime.setText("Time:"+" "+minutes+":"+"0"+seconds);
		      
		     else
		    	 lblTime.setText("Time:"+" "+minutes+":"+seconds); 
	    }
	  }
}
