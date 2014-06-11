package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class GUIBackground extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame mainframe;
	private JPanel mainpanel;
	private Timer time;
	private Pad p;
	private Graphics g;
	private int hpanel,wpanel;
	private int count=0;
	public GUIBackground(){
		p=new Pad(450,500);
		invokeInItWindow();
		time=new Timer(5,this);
		time.start();
	}
	
	private void invokeInItWindow(){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
					InItWindow();
					}
				});
	}
	
	private void  InItWindow(){
		mainframe=new JFrame("GAME");
		mainframe.setPreferredSize(new Dimension(900,600));
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = mainframe.getContentPane();
		cp.setLayout(new BorderLayout());
		mainpanel=new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.X_AXIS));
		mainpanel.add(this);
		mainpanel.setBackground(Color.WHITE);
		mainpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		mainframe.getContentPane().add(mainpanel,BorderLayout.CENTER);
		mainframe.pack();
		mainframe.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paintComponent( Graphics g ){
		hpanel=mainpanel.getHeight();
		wpanel=mainpanel.getWidth();
		
		
		g.setColor( Color.BLACK );
		g.drawLine(0,75, wpanel,75);
		g.setColor(Color.red);
		g.drawRect(p.getX(),p.getY(), 100,30);
		g.setColor(Color.red);
		g.fillRect(p.getX(),p.getY(),100,30);
		
	     
	}
	
	private class AL extends KeyAdapter{
		
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);
			
		}
	}
	
	
}
