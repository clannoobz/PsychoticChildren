package pc.main.entity.powerups;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pc.main.Game;
import pc.main.entity.PlayerShip;

public class Shield {
	public int x, y;
	boolean alive = true;
	public static int width = 25, height = 25;
	public static List<Shield> shields = new CopyOnWriteArrayList<Shield>();
	public Shield(int x, int y){
		this.x = x;
		this.y = y;
		if(shields.size()<3){
			shields.add(this);
			final Rectangle r = new Rectangle();
			r.setBounds(x,y,width,height);
			final Shield s = this;
			new Thread(){
				public void run(){
					try{
						while(alive){
							Thread.sleep(10);
							if(r.intersects(PlayerShip.x,PlayerShip.y,PlayerShip.width,PlayerShip.height)){
								s.destroy();
								if(PlayerShip.SHIELDED){
									Game.addScore(20);
								}else{
								PlayerShip.SHIELDED = true;
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
						s.destroy();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	public void destroy(){
		shields.remove(this);
	}
}
