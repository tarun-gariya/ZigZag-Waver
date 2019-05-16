package com.tutorial.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	public int scoreKeep=0;
	private Game game;
	private Random r=new Random();
	
	public Spawn(Handler handler,HUD hud,Game game)
	{
		this.handler=handler;
		this.hud=hud;
		this.game=game;
	}
	
	public void tick()
	{
		scoreKeep++;
		if(scoreKeep>=1000)
		{
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			
			if(game.difficulty==0)
			{
				 if(hud.getLevel()==2)
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler));
					else if(hud.getLevel()==3)
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler));
					else if(hud.getLevel()==4)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==5)
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.SmartEnemy,handler));
					else if(hud.getLevel()==6)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==7)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==10) {
						handler.clearEnemy();
						handler.addObject(new EnemyBoss((Game.WIDTH/2)-48,-120 ,ID.EnemyBoss,handler));
					}
			}else if(game.difficulty==1)
			{
				 if(hud.getLevel()==2)
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler));
					else if(hud.getLevel()==3)
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler));
					else if(hud.getLevel()==4)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==5)
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.SmartEnemy,handler));
					else if(hud.getLevel()==6)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==7)
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy,handler));
					else if(hud.getLevel()==10) {
						handler.clearEnemy();
						handler.addObject(new EnemyBoss((Game.WIDTH/2)-48,-120 ,ID.EnemyBoss,handler));
					}
			}
			    
		}
	}
}
