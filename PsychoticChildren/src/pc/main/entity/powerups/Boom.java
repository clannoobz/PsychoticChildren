package pc.main.entity.powerups;

import java.awt.Frame;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pc.main.Game;
import pc.main.entity.Enemy;
import pc.main.entity.EnemyProjectile;
import pc.main.entity.PlayerShip;
import pc.main.threads.BoomAnimation;

public class Boom {
	BoomAnimation BA = new BoomAnimation();
	public int x, y;
	boolean alive = true;
	public static int width = 25, height = 25;
	public static List<Boom> booms = new CopyOnWriteArrayList<Boom>();
	public Boom(int x, int y){
		this.x = x;
		this.y = y;
		if(booms.size()<3){
			booms.add(this);
			final Rectangle r = new Rectangle();
			r.setBounds(x,y,width,height);
			final Boom b = this;
			new Thread(){
				public void run(){
					try{
						while(alive){
							Thread.sleep(10);
							if(r.intersects(PlayerShip.x,PlayerShip.y,PlayerShip.width,PlayerShip.height)){
								BA.start();
								b.destroy();
								for(Enemy e: Enemy.enemies){
									e.destroy();
									Game.addScore(10);
								}
								for(EnemyProjectile ep: EnemyProjectile.projectiles){
									ep.destroy();
								}
								this.join();
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
			new Thread(){
				public void run(){
					try{
						Thread.sleep(7000);
						alive = false;
						b.destroy();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	public void destroy(){
		booms.remove(this);
	}
}
