package pc.main.threads;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import pc.main.entity.Projectile;
import pc.main.states.Play;

public class ProjectileFirer extends Thread{
	public void run(){
		try {
			Mouse.create();
		} catch (LWJGLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			while(true){
			if(Mouse.isButtonDown(0) && !Play.paused){
				new Projectile(Mouse.getX()-5, 600-Mouse.getY()-15, 0, -2);
				Thread.sleep(200);
			}else{
				Thread.sleep(5);
			}
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
