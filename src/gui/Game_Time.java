package gui;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Game_Time {
	private Timer timer;
	private JLabel lblTime;
	public static int seconds=60,minutes;
	
	public Game_Time(int minutes, JLabel lblTime) {
		Game_Time.minutes=minutes;
		this.lblTime=lblTime;
	    timer = new Timer();
	    timer.schedule(new RemindTask(),0, 1000);
	    
	}

	class RemindTask extends TimerTask {
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
	    
	    public void writeTime(){
	    	if(seconds/10==0)
		    	  lblTime.setText("Time:"+" "+minutes+":"+"0"+seconds);
		      
		     else
		    	 lblTime.setText("Time:"+" "+minutes+":"+seconds); 
	    }
	  }
}
