package pc.main.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pc.main.states.Play;

public class Projectile {
	public double x, y;
	public static int width = 10, height = 40;
	public double dx, dy;
	public static List<Projectile> projectiles = new CopyOnWriteArrayList<Projectile>();
	public Projectile(double x, double y, double dx, double dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		final Projectile p = this;
		projectiles.add(p);
		new Thread(){
			public void run(){
				try{
					while(p.y + height > 0){
						if(!Play.paused)
							p.y += p.dy;
						Thread.sleep(2);
						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				projectiles.remove(p);
			}
		}.start();
	}
}
