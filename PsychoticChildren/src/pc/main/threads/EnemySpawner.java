package pc.main.threads;

import java.util.Random;

import pc.main.Game;
import pc.main.entity.Enemy;
import pc.main.states.Play;

public class EnemySpawner extends Thread{
	Random rand = new Random();
	public static boolean alive = true;
	public void run(){
		try{
			Thread.sleep(3000);
			while(alive && (Game.GAME.getCurrentStateID() == Game.play)){
				//Create Enemy
				if(alive && !Play.paused){
				int type = rand.nextInt(2);
					if(700-20*Game.LEVEL > 0){
						new Enemy(rand.nextInt(700-20*Game.LEVEL) + 50, -50,0,Enemy.movement_speed[type],type);
					}else{
						new Enemy(50, -50,0,Enemy.movement_speed[type],type);
					}
				}
				Thread.sleep(1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
