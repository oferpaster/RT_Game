package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;


public class GUIBackground extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame mainframe;
	private JPanel mainpanel;
	private Timer time;
	private Pad p;
	private Ball b;
	private int hpanel;
	public int wpanel;
	private ImageIcon iconmagepad;

	public GUIBackground(int x){}
	
	public GUIBackground(){
		iconmagepad=new ImageIcon("C:\\Users\\GAL\\git\\RT_Game2\\src\\gui\\pad.png");
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
		g.setColor(Color.WHITE);
		g.setColor(Color.ORANGE);
		g.drawOval(b.getX(), b.getY(),10, 10);
		g.fillOval(b.getX(), b.getY(),10, 10);
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
