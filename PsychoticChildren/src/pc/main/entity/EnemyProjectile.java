package pc.main.entity;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pc.main.states.Play;

public class EnemyProjectile {
	public double x, y, dx, dy;
	public static int width = 10, height = 20;
	public int type;
	public boolean alive = true;
	public static List<EnemyProjectile> projectiles = new CopyOnWriteArrayList<EnemyProjectile>();
	public EnemyProjectile(final double x,final double y, double dx, double dy, int type){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.type = type;
		final EnemyProjectile e = this;
		projectiles.add(e);
		new Thread(){
			public void run(){
				try{	
					Rectangle r = new Rectangle();
					while(e.y > 0){
						if(!Play.paused)
						e.y += e.dy;
						r.setBounds((int)e.x,(int)e.y,width,height);
						if(r.intersects(PlayerShip.x,PlayerShip.y,PlayerShip.width,PlayerShip.height)){
							onPlayerCollision();
							this.join();
						}
						if(!alive)
							this.join();
						Thread.sleep(2);
					}
					projectiles.remove(e);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void destroy(){
		alive = false;
		projectiles.remove(this);
	}
	private void onPlayerCollision(){
		PlayerShip.onCollision();
		destroy();
	}
}
