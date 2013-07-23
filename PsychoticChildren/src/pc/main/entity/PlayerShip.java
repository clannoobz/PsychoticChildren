package pc.main.entity;

import java.awt.Frame;

import javax.swing.JOptionPane;

import pc.main.Game;
import pc.main.threads.EnemySpawner;

public class PlayerShip {
	public static double x, y;
	public static int width, height;
	public static int LIVES = 3;
	public static boolean SHIELDED = false;
	public PlayerShip(double x, double y, int w, int h){
		PlayerShip.x = x;
		PlayerShip.y = y;
		PlayerShip.width = w;
		PlayerShip.height = h;
	}
	public static void move(double dx, double dy){
		x += dx;
		y += dy;
	}
	public static void removeLife(){
		LIVES--;
		if(LIVES < 0){
			Game.GAME.enterState(2);
			EnemySpawner.alive = false;
			for(Enemy e: Enemy.enemies){
				e.destroy();
			}
			for(EnemyProjectile ep: EnemyProjectile.projectiles){
				ep.destroy();
			}
			Game.GAME.enterState(Game.score);
		}
	}
	public static void onCollision(){
		if(SHIELDED){
			SHIELDED = false;
		}else{
			removeLife();
		}
	}
}
