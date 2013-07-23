package pc.main.states;



import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pc.main.Game;
import pc.main.entity.Enemy;
import pc.main.entity.EnemyProjectile;
import pc.main.entity.PlayerShip;
import pc.main.entity.Projectile;
import pc.main.entity.powerups.Boom;
import pc.main.entity.powerups.Shield;
import pc.main.threads.BoomAnimation;
import pc.main.threads.ElapsedTime;
import pc.main.threads.EnemySpawner;
import pc.main.threads.LevelUpAnimation;
import pc.main.threads.ProjectileFirer;
import pc.main.threads.ShipMovement;

public class Play extends BasicGameState{
	
	static Image levelup_Image[] = new Image[2];
	static Image levelupbackground_Image[] = new Image[5];
	static Image projectile_Image;
	static Image paused_Image;
	static Image enemy_Image[] = new Image[2];
	static Image enemyProjectile_Image[] = new Image[2];
	static Image background_Image;
	static Image ship_Image;
	static Image shield_Image;
	static Image bomb_Image;
	static Image bombAniRev_Image[] = new Image[4];
	static Image bombAni_Image[] = new Image[4];
	static Image shipShield_Image;
	
	Input input;
	
	static Color INFO_COLOR = Color.black;
	
	public static boolean paused = false;
	public static boolean showInfo = true;
	
	static String INFO = "";
	
	public Play(int state){
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}
	public static void start(){
		new PlayerShip(Game.WIDTH/2-25,Game.HEIGHT*5/6-25,50,50);
		new Thread(){
			public void run(){
				try{
					while(true){
						Game.LEVEL = (int) (Math.round(Math.log(Game.KILLED/2)/Math.log(2))+1);
						if(Game.LEVEL > Game.LASTLEVEL){
							onLevelUp();
							Game.LASTLEVEL = Game.LEVEL;
						}
						Thread.sleep(100);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
		new ShipMovement().start();
		new ElapsedTime().start();
		new EnemySpawner().start();
		new Thread(){
			public void run(){
				try{
					while(true){
						Game.LEVEL = (int) (Math.round(Math.log(Game.KILLED/2)/Math.log(2))+1);
						if(Game.LEVEL > Game.LASTLEVEL){
							onLevelUp();
							Game.LASTLEVEL = Game.LEVEL;
						}
						Thread.sleep(100);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
		try {
			initImages();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ProjectileFirer().start();
	}
	private static void initImages() throws SlickException {
		levelup_Image[0] = new Image("res/levelup.png").getScaledCopy(400,100);
		levelup_Image[1] = new Image("res/levelupinvert.png").getScaledCopy(400,100);
		levelupbackground_Image[0] = new Image("res/levelupb0.png").getScaledCopy(Game.WIDTH,Game.HEIGHT);
		levelupbackground_Image[1] = new Image("res/levelupb1.png").getScaledCopy(Game.WIDTH,Game.HEIGHT);
		levelupbackground_Image[2] = new Image("res/levelupb2.png").getScaledCopy(Game.WIDTH,Game.HEIGHT);
		levelupbackground_Image[3] = new Image("res/levelupb3.png").getScaledCopy(Game.WIDTH,Game.HEIGHT);
		levelupbackground_Image[4] = new Image("res/levelupb4.png").getScaledCopy(Game.WIDTH,Game.HEIGHT);
		projectile_Image = new Image("res/Projectile.png").getScaledCopy(Projectile.width,Projectile.height);
		paused_Image = new Image("res/paused.png").getScaledCopy(400,100);
		enemy_Image[0] = new Image("res/Enemy1.png").getScaledCopy(Enemy.width,Enemy.height);
		enemy_Image[1] = new Image("res/Enemy2.png").getScaledCopy(Enemy.width,Enemy.height);
		enemyProjectile_Image[0] = new Image("res/Enemy1Proj.png").getScaledCopy(EnemyProjectile.width, EnemyProjectile.height);
		enemyProjectile_Image[1] = new Image("res/Enemy2Proj.png").getScaledCopy(EnemyProjectile.width, EnemyProjectile.height);
		background_Image = new Image("res/background.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		ship_Image = new Image("res/SpaceShip.png").getScaledCopy(PlayerShip.width,PlayerShip.height);
	    shield_Image = new Image("res/Shield.png").getScaledCopy(Shield.width, Shield.height);
		bomb_Image = new Image("res/BombImage.png").getScaledCopy(Boom.width,Boom.height);
		bombAniRev_Image[0] = new Image("res/FadeoutBomb3.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAniRev_Image[1] = new Image("res/FadeoutBomb2.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAniRev_Image[2] = new Image("res/FadeoutBomb1.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAniRev_Image[3] = new Image("res/FadeoutBomb0.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAni_Image[0] = new Image("res/FadeoutBomb0.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAni_Image[1] = new Image("res/FadeoutBomb1.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAni_Image[2] = new Image("res/FadeoutBomb2.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		bombAni_Image[3] = new Image("res/FadeoutBomb3.png").getScaledCopy(Game.WIDTH, Game.HEIGHT);
		shipShield_Image = shield_Image.getScaledCopy(PlayerShip.width+8, PlayerShip.height+8);
		
		levelup_Image[0].setFilter(Image.FILTER_NEAREST);
		levelup_Image[1].setFilter(Image.FILTER_NEAREST);
		levelupbackground_Image[0].setFilter(Image.FILTER_NEAREST);
		levelupbackground_Image[1].setFilter(Image.FILTER_NEAREST);
		levelupbackground_Image[2].setFilter(Image.FILTER_NEAREST);
		levelupbackground_Image[3].setFilter(Image.FILTER_NEAREST);
		levelupbackground_Image[4].setFilter(Image.FILTER_NEAREST);
		projectile_Image.setFilter(Image.FILTER_NEAREST);
		paused_Image.setFilter(Image.FILTER_NEAREST);
		enemy_Image[0].setFilter(Image.FILTER_NEAREST);
		enemy_Image[1].setFilter(Image.FILTER_NEAREST);
		enemyProjectile_Image[0].setFilter(Image.FILTER_NEAREST);
		enemyProjectile_Image[1].setFilter(Image.FILTER_NEAREST);
		background_Image.setFilter(Image.FILTER_NEAREST);
		ship_Image.setFilter(Image.FILTER_NEAREST);
	    shield_Image.setFilter(Image.FILTER_NEAREST);
		bomb_Image.setFilter(Image.FILTER_NEAREST);
		bombAniRev_Image[0].setFilter(Image.FILTER_NEAREST);
		bombAniRev_Image[1].setFilter(Image.FILTER_NEAREST);
		bombAniRev_Image[2].setFilter(Image.FILTER_NEAREST);
		bombAniRev_Image[3].setFilter(Image.FILTER_NEAREST);
		bombAni_Image[0].setFilter(Image.FILTER_NEAREST);
		bombAni_Image[1].setFilter(Image.FILTER_NEAREST);
		bombAni_Image[2].setFilter(Image.FILTER_NEAREST);
		bombAni_Image[3].setFilter(Image.FILTER_NEAREST);
		shipShield_Image.setFilter(Image.FILTER_NEAREST);
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Draw Background Image
		//g.drawImage(background_Image, 0,0, WIDTH, HEIGHT,this);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		//Shield Powerups
			for(Shield s: Shield.shields){
				g.drawImage(shield_Image,s.x, s.y);
			}
		//Boom Powerups
			for(Boom b: Boom.booms){
				g.drawImage(bomb_Image,b.x, b.y);
			}
		//Draw Info String
			if(showInfo){
				g.setColor(INFO_COLOR);
				INFO = "Score: " + Game.SCORE + " Lives: " + PlayerShip.LIVES + " Enemies Killed: "+ Game.KILLED +" Level: " + Game.LEVEL + " Time Elapsed: " + ElapsedTime.elapsedtime +" seconds";
				g.drawString(INFO,5 , 584);
			}
		//Projectiles
			for(Projectile p: Projectile.projectiles){
				g.drawImage(projectile_Image,(int)p.x, (int)p.y);
			}
			for(EnemyProjectile p: EnemyProjectile.projectiles){
				g.drawImage(enemyProjectile_Image[p.type],(int)p.x, (int)p.y);
			}
		//Enemies
			for(Enemy p: Enemy.enemies){
				g.drawImage(enemy_Image[p.type],(int)p.x, (int)p.y);
		}
		
		//Ship
			g.drawImage(ship_Image,(int)PlayerShip.x, (int)PlayerShip.y); 
			if(PlayerShip.SHIELDED)
				g.drawImage(shipShield_Image,(int)PlayerShip.x-4,(int)PlayerShip.y-4);
		//BoomAniRev
			if(BoomAnimation.animating){
				g.drawImage(bombAniRev_Image[BoomAnimation.state], 0, 0);
			}
		//BoomAni
		if(BoomAnimation.animating){
			g.drawImage(bombAni_Image[BoomAnimation.state], 0, 0);
			}
		//LevelUpAni
		if(LevelUpAnimation.animating){
			
			g.drawImage(levelupbackground_Image[LevelUpAnimation.state], 0, 0);
			g.drawImage(levelup_Image[LevelUpAnimation.state%2], 200, 100);
		}
				
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		input = gc.getInput();
		if(input.isKeyPressed(input.KEY_ESCAPE)){
			if (paused){
				paused = false;
				gc.setMouseGrabbed(true);
			}else{
				paused = true;
				gc.setMouseGrabbed(false);
			}
		}
	}

	public static void onLevelUp(){
		for(Shield s: Shield.shields){
			s.destroy();
		}
		for(Enemy e: Enemy.enemies){
			e.destroy();
			Game.addScore(10);
		}
		for(EnemyProjectile ep: EnemyProjectile.projectiles){
			ep.destroy();
		}
		for(Boom b: Boom.booms){
			b.destroy();
		}
		new LevelUpAnimation().start();
	}	
	
	public static void onPause(){
		
	}
	public static void onUnpause(){
		PlayerShip.x = Mouse.getX() - PlayerShip.width/2;
		PlayerShip.y = 600 - Mouse.getY() - PlayerShip.height/2;
	}
	
	public int getID() {
		return 1;
	}

}
