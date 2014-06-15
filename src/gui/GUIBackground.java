package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

import enums.Sides;


public class GUIBackground extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final String targetImageStr = "src\\gui\\targes.png";
	private static final String hardTargetImageStr = "src\\gui\\targesHard.png";
	private static final String fireTargetImageStr = "src\\gui\\targesFire.png";
	private JFrame mainframe;
	private JPanel mainpanel;
	private Timer time;
	private Pad p;
	private Ball b;
	private ArrayList<Target> targets;
	public int wpanel,hpanel;
	private ImageIcon iconmagepad;
	private ImageIcon iconmageFirePad;
	private ArrayList<Gun> guns;
	private Level[] levels;
	private JLabel wonLabel;
	private Sides gameStat;
	private JLabel lblTime;
	private JLabel lblLevel; 
	

	public GUIBackground(int x){}
	
	public GUIBackground(){
		targets = new ArrayList<Target>();
		guns = new ArrayList<Gun>();
		iconmagepad=new ImageIcon("src\\gui\\pad.png");
		iconmageFirePad=new ImageIcon("src\\gui\\FirePad.png");
		invokeInItWindow();
		initilaize();
		initLevels();
		createTarget();
		p=new Pad(450,540);
		b=new Ball(480,530,p,this);
		addKeyListener(new AL());
		setFocusable(true);
		time=new Timer(5,this);
		setLayout(null);
		
		wonLabel = new JLabel("You won level " + Level.getCurrentLevel());
		wonLabel.setForeground(UIManager.getColor("ComboBox.selectionBackground"));
		wonLabel.setFont(new Font("Tahoma", Font.BOLD, 67));
		wonLabel.setBounds(168, 225, 542, 105);
		wonLabel.setVisible(false);
		add(wonLabel);
		time.start();
		new Game_Time(3,lblTime);
	
	}
	
	public void initilaize(){
setLayout(null);
		
		lblLevel = new JLabel("Level: 1");
		lblLevel.setForeground(Color.CYAN);
		lblLevel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBounds(37, 46, 106, 31);
		add(lblLevel);
		
		lblLives = new JLabel("Lives:\r\n");
		lblLives.setHorizontalAlignment(SwingConstants.LEFT);
		lblLives.setForeground(Color.RED);
		lblLives.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLives.setBounds(258, 46, 106, 31);
		add(lblLives);
		
		lblShoots = new JLabel("Shoots:");
		lblShoots.setHorizontalAlignment(SwingConstants.LEFT);
		lblShoots.setForeground(Color.PINK);
		lblShoots.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblShoots.setBounds(469, 46, 106, 31);
		add(lblShoots);
		
		lblTime = new JLabel("Time:\r\n\r\n");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setForeground(Color.GREEN);
		lblTime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTime.setBounds(689, 46, 106, 31);
		add(lblTime);
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
		gameStat = Sides.GAME_NORMAL;
	}
	
	private void initLevels(){
		levels = new Level[3];
		levels[0] = new Level(1);
		levels[1] = new Level(2);
		levels[2] = new Level(3);
		Level.setCurrentLevel(1);
		
		//levels[0].setGunBonus(1);
		levels[0].setNumberOfTargets(1);
		levels[0].setPlay(true);
		//levels[0].setHaveMovingTarget(1);
		levels[0].setHitToBreakTarget(1);
		
		levels[1].setGunBonus(2);
		levels[1].setNumberOfTargets(10);
		levels[1].setHitToBreakTarget(2);
		
		levels[2].setGunBonus(6);
		levels[2].setNumberOfTargets(11);
		levels[2].setHitToBreakTarget(2);
		levels[2].setHaveMovingTarget(1);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		p.move();
		b.move();
		repaint();
	}
	
	public void paintComponent( Graphics g ){
		super.paintComponent(g);
		Sides ballCollisionResult;
		if(gameStat == Sides.GAME_WON_LEVEL){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			wonLabel.setVisible(false);
			Level.setCurrentLevel(Level.getCurrentLevel() + 1);
			levels[Level.getCurrentLevel()-1].setPlay(true);
			targets.clear();
			createTarget();
			gameStat = Sides.GAME_NORMAL;
		}
		hpanel=mainpanel.getHeight();
		wpanel=mainpanel.getWidth();
		g.setColor( Color.BLACK );
		g.drawLine(0,75, wpanel,75);
		if(b.floorCollision(b.getY())){
			b.setX(480);
			b.setY(530);
			p.setX(450);
			p.setY(540);
		}
		if(p.getStatus() == Sides.NORMAL_PAD)
			g.drawImage(iconmagepad.getImage(),p.getX(),p.getY(),null);
		else
			g.drawImage(iconmageFirePad.getImage(),p.getX(),p.getY(),null);
		paintTargets(g);
		g.setColor(Color.WHITE);
		g.setColor(Color.ORANGE);
		ballCollisionResult = b.targetCollision(targets);
		g.drawOval(b.getX(), b.getY(),10, 10);
		g.fillOval(b.getX(), b.getY(),10, 10);
		if(ballCollisionResult != Sides.NO_HIT){
			levels[Level.getCurrentLevel()-1].setTargetsToWin(levels[Level.getCurrentLevel()-1].getTargetsToWin() - 1);
			if(levels[Level.getCurrentLevel()-1].getTargetsToWin() == 0 && Level.getCurrentLevel() != 3){
				levels[Level.getCurrentLevel()-1].setPlay(false);
				levels[Level.getCurrentLevel()-1].setWon(true);
				if(Level.getCurrentLevel() != 3)
					wonLabel.setText("You won level " + Level.getCurrentLevel());
				else
					wonLabel.setText("You Won The Game!");
				wonLabel.setVisible(true);
				gameStat = Sides.GAME_WON_LEVEL;
				b.resetBall();
				p.resetPad();
			}
			else if(levels[Level.getCurrentLevel()-1].getTargetsToWin() == 0 && Level.getCurrentLevel() == 3){
				levels[Level.getCurrentLevel()-1].setPlay(false);
				levels[Level.getCurrentLevel()-1].setWon(true);
			}
		}
		if(ballCollisionResult == Sides.GUN_TARGET){
			p.setStatus(Sides.FIRE_PAD);
			Gun.setEnable(true);
			Gun.setFireLeft(5);
			for(int i = 0; i < Gun.getFireLeft() ; i++){
				Gun gun = new Gun(p.getX()+32,p.getY());
				guns.add(gun);
			}
		}
		ballCollisionResult = Sides.NO_HIT;
		if(Gun.isEnable()){
			paintBolet(g);

		}
	}
	
	
	private void paintBolet(Graphics g) {
		Gun gunHit = null;
		for (Gun gun : guns) {
			if(gun.getFireStatus()==Sides.BOLET_FIRED){
				if(gun.targetCollision(targets) != Sides.NO_HIT)
					gunHit = gun;
			}
			if(p.getFire() == Sides.PAD_FIRE){
				if(gun.getFireStatus() == Sides.BOLET_LOADED){
					p.setFire(Sides.STOP_FIRE);
					gun.setX(p.getX()+32);
					gun.setY(p.getY());
					gun.setFireStatus(Sides.BOLET_FIRED);
					Gun.setFireLeft(Gun.getFireLeft()-1);
					if(Gun.getFireLeft() == 0)
						p.setStatus(Sides.NORMAL_PAD);
				}
			}
			if(gun.getFireStatus() == Sides.BOLET_FIRED){
				gun.setY(gun.getY()-1);
				if(b.celingCollision(gun.getX(), gun.getY())){
					gun.setAlive(false);
					gun.setFireStatus(Sides.BOLET_DONE);
					if(Gun.getFireLeft() == 0)
						Gun.setEnable(false);
				}
				else if(gun.isAlive())
					g.drawImage(gun.getImage(),gun.getX(),gun.getY(),null);
			}
		}
		if(gunHit != null)
			guns.remove(gunHit);
	}

	public void createTarget() {
		int i = Level.getCurrentLevel()-1;
		int x = 10;
		int numHitTarget = levels[i].getHitToBreakTarget();
		Target target;
		
		
		for(int j = 0 ; j < (levels[i].getNumberOfTargets() - levels[i].getHaveMovingTarget()) ; j++){
			switch (i) {
			case 0:
				x = j*130;
				break;
			case 1:
				x = j*101;
				break;
			case 2:
				x = j*90;
				break;

			default:
				break;
			}
			
			if (j == 0){//for first target
				if(j == levels[i].getGunBonus()-1){
					if(numHitTarget > 1)
						target = new Target(10,100,hardTargetImageStr);
					else
						target = new Target(10,100,fireTargetImageStr);
					target.setTargetType(Sides.GUN_TARGET);
				}
				else{	
					if(numHitTarget > 1)
						target = new Target(10,100,hardTargetImageStr);
					else
						target = new Target(10,100,targetImageStr);
					target.setTargetType(Sides.NORMAL_TARGET);
				}
			}
			
			else if(j == (levels[i].getGunBonus()-1)){
				if(numHitTarget > 1)
					target = new Target(x,100,hardTargetImageStr);
				else
					target = new Target(x,100,fireTargetImageStr);
				target.setTargetType(Sides.GUN_TARGET);
			}
			else {
				if(numHitTarget > 1)
					target = new Target(x,100,hardTargetImageStr);
				else
					target = new Target(x,100,targetImageStr);
				target.setTargetType(Sides.NORMAL_TARGET);
			}
				target.setAlive(true);
				target.setHit(false);
				target.setNumberOfHit(numHitTarget);
				targets.add(target);
				
			}
		if(levels[i].getHaveMovingTarget() == 1){//For moving target
			if(numHitTarget > 1)
				target = new Target(10,150,hardTargetImageStr);
			else
				target = new Target(10,150,targetImageStr);
			target.setMoving(true);
			target.setTargetType(Sides.MOVING_TARGET);
			target.setAlive(true);
			target.setHit(false);
			targets.add(target);
		}
	}
	
	
	public void paintTargets(Graphics g) {
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
	}
	
	private class AL extends KeyAdapter{
		
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);
			
		}
	}
	public static String getTargetimagestr() {
		return targetImageStr;
	}

	public static String getHardtargetimagestr() {
		return hardTargetImageStr;
	}

	public static String getFiretargetimagestr() {
		return fireTargetImageStr;
	}
}
