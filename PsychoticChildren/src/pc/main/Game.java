package pc.main;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import pc.main.entity.PlayerShip;
import pc.main.states.GameMenu;
import pc.main.states.Play;
import pc.main.states.Score;
public class Game extends StateBasedGame{

	public static final String gamename = "Psychotic Children";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int score = 2;
	
	public static final int WIDTH = 800, HEIGHT = 600;
	
	
	public static Game GAME;
	public static int KILLED = 0;
	public static int SCORE = 0;
	private static int tehSCORE = 200;
	public static int LEVEL = 1;
	public static int LASTLEVEL = 1;
	
	public Game(String name) {
		super(name);
		this.addState(new GameMenu(menu));
		this.addState(new Play(play));
		this.addState(new Score(score));
	}
	
	public static void main(String[] args){
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(GAME = new Game(gamename));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(score).init(gc, this);
		gc.setTargetFrameRate(120);
	}

	public static void addScore(int i) {
		//Add to Score
		SCORE+=i;
	//Add to "tehSCORE"
		tehSCORE -= i;
		if(tehSCORE <= 0){
			tehSCORE = 200 + tehSCORE;
			PlayerShip.LIVES++;
		}
	}
	
}
