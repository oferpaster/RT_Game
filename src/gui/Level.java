package gui;

public class Level {

	private static int currentLevel;
	private int levelNumber,numberOfTargets,haveMovingTarget;
	private int gunBonus,gameSpeed,targetsToWin,hitToBreakTarget;
	private boolean play,won,lost;
	private static boolean gameOver = false;
	
	public Level(int levelNumber) {
		setLevelNumber(levelNumber);
		setPlay(false);
		setHaveMovingTarget(0);
		setHitToBreakTarget(1);
		setWon(false);
		setGameOver(false);
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public int getNumberOfTargets() {
		return numberOfTargets;
	}

	public void setNumberOfTargets(int numberOfTargets) {
		setTargetsToWin(numberOfTargets);
		this.numberOfTargets = numberOfTargets;
	}

	public int getGunBonus() {
		return gunBonus;
	}

	public void setGunBonus(int gunBonus) {
		this.gunBonus = gunBonus;
	}

	public int getTargetsToWin() {
		return targetsToWin;
	}

	public void setTargetsToWin(int targetsToWin) {
		this.targetsToWin = targetsToWin;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(int currentLevel) {
		Level.currentLevel = currentLevel;
	}

	public int getHaveMovingTarget() {
		return haveMovingTarget;
	}

	public void setHaveMovingTarget(int haveMovingTarget) {
		this.haveMovingTarget = haveMovingTarget;
	}

	public int getHitToBreakTarget() {
		return hitToBreakTarget;
	}

	public void setHitToBreakTarget(int hitToBreakTarget) {
		this.hitToBreakTarget = hitToBreakTarget;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		if(won)
			new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("good.wav").toString())).start();
		this.won = won;
	}
	
	public void lost(){
		new Thread(new MediaPlayer(this.getClass().getClassLoader().getResource("itsover.wav").toString())).start();
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
		setGameOver(lost);
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		Level.gameOver = gameOver;
	}

}
