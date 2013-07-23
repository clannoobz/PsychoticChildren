package pc.main.threads;

import org.lwjgl.input.Mouse;

import pc.main.Game;
import pc.main.entity.PlayerShip;
import pc.main.states.Play;

public class ShipMovement extends Thread{
	public void run(){
		try{
			while(true){
				if(!Play.paused){
					PlayerShip.x = Mouse.getX()-PlayerShip.width/2;
					PlayerShip.y = 600-Mouse.getY()-PlayerShip.height/2;
				}
				Thread.sleep(5);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
