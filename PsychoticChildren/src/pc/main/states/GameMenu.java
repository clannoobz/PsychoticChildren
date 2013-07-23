package pc.main.states;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pc.main.Game;

public class GameMenu extends BasicGameState{

	int mouseX = 0, mouseY = 0;
	boolean starthover = false, exithover = false;
	
	public GameMenu(int state){	

	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)		throws SlickException {
		g.setColor(Color.white);
		g.drawString("Psychotic Children", 400-18*4, 50);
		Rectangle start = new Rectangle(Game.WIDTH/8,Game.HEIGHT/4,Game.WIDTH*3/4,Game.HEIGHT/6);
		Rectangle exit = new Rectangle(Game.WIDTH/8,Game.HEIGHT*7/12,Game.WIDTH*3/4,Game.HEIGHT/6);
		if(starthover){
			g.setColor(Color.white);
			g.fillRect(start.x, start.y, start.width, start.height);
			g.setColor(Color.black);
		}else{
			g.setColor(Color.white);
			g.drawRect(start.x,start.y,start.width,start.height);
		}
		g.drawString("Start", 380, 192);
		if(exithover){
			g.setColor(Color.white);
			g.fillRect(exit.x, exit.y, exit.width, exit.height);
			g.setColor(Color.black);
		}else{
			g.setColor(Color.white);
			g.drawRect(exit.x,exit.y,exit.width,exit.height);
		}
		g.drawString("Exit", 380, 392);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)	throws SlickException {
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		Rectangle start = new Rectangle(100,150,600,100);
		Rectangle exit = new Rectangle(100,350,600,100);
		if(start.contains(800 - mouseX, 600 - mouseY)){
			if(!starthover)starthover = true;
			if(Mouse.isButtonDown(0)){
				Game.GAME.enterState(Game.play);
				Play.start();
				gc.setMouseGrabbed(true);
			}
		}else{
			if(starthover)starthover = false;
		}
		if(exit.contains(800 - mouseX, 600 - mouseY)){
			if(!exithover)exithover = true;
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}else{
			if(exithover)exithover = false;
		}
	}

	public int getID() {
		return 0;
	}

}
