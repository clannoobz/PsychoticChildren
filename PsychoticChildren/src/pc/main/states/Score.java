package pc.main.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pc.main.Game;

public class Score extends BasicGameState{

	public Score(int state) {
		// TODO Auto-generated constructor stub
	}
	
	public static void start(){
		Game.GAME.getContainer().setMouseGrabbed(false);
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("You score was: " + Game.SCORE, Game.WIDTH/2 - 60, Game.HEIGHT/2 - 4);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
