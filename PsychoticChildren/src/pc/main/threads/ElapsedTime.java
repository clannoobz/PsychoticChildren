package pc.main.threads;

import pc.main.Game;
import pc.main.states.Play;

public class ElapsedTime extends Thread{
	public static int elapsedtime = 0;
	public void run(){
		try{
			while(true){
				Thread.sleep(1000);
				if(!Play.paused)
					elapsedtime++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
