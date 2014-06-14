package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;


public class GUIBackground extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private JFrame mainframe;
	private JPanel mainpanel;
	private Timer time;
	private Pad p;
	private Ball b;
	ArrayList<Target> targets;
	private int hpanel;
	public int wpanel;
	private ImageIcon iconmagepad;
	private ImageIcon iconmagetarget;
	private int levelNumber;
	

	public GUIBackground(int x){}
	
	public GUIBackground(){
		levelNumber = 1;
		targets = new ArrayList<Target>();
		iconmagepad=new ImageIcon("src\\gui\\pad.png");
		createTarget();
		invokeInItWindow();
		p=new Pad(450,540);
		b=new Ball(480,530,p,this);
		addKeyListener(new AL());
		setFocusable(true);
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
		p.move();
		b.move();
		repaint();
	}
	
	public void paintComponent( Graphics g ){
		super.paintComponent(g);
		hpanel=mainpanel.getHeight();
		wpanel=mainpanel.getWidth();
		g.setColor( Color.BLACK );
		g.drawLine(0,75, wpanel,75);
		g.drawImage(iconmagepad.getImage(),p.getX(),p.getY(),null);
		paintTargets(g);
		g.setColor(Color.WHITE);
		g.setColor(Color.ORANGE);
		//b.targetCollision(targets);
		g.drawOval(b.getX(), b.getY(),10, 10);
		g.fillOval(b.getX(), b.getY(),10, 10);
	}
	
	
	public void createTarget() {
		Target target = new Target(10,100);
		target.setAlive(true);
		target.setHit(false);
		targets.add(target);
		switch (levelNumber) {
		case 1:
			for(int i = 1; i<8 ; i++){
				target = new Target(i*115,100);
				target.setAlive(true);
				target.setHit(false);
				targets.add(target);
			}
			target = new Target(10,150,true);
			target.setAlive(true);
			target.setHit(false);
			targets.add(target);
			break;

		default:
			break;
		}
	}
	
	public void paintTargets(Graphics g) {
		switch (levelNumber) {
		case 1:
			for (Target target : targets) {
				if(target.isAlive() && !target.isHit()){
					g.drawImage(target.getImage(),target.getX(),target.getY(),null);
					if(target.isMoving()){
						target.setX(target.getX()+target.getDirection());
						if(target.getX()+61 >= wpanel || target.getX() <= 0){
							target.setDirection(target.getDirection()*(-1));
							target.setX(target.getX()+target.getDirection()*8);
						}
					}
				}
			}
			break;

		default:
			break;
		}
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
