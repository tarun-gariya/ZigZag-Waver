package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Random;

public class Game extends Canvas implements Runnable {
		
	public static final int WIDTH=640,HEIGHT=WIDTH/12*9;
	private  Thread thread;
	private Boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static boolean paused =false; 
	public  int difficulty=0;
	
	//0 is for nrmal 
	//1 is for hard
	
	public enum STATE 
	{
		Menu,
		Help,
		Select,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu; 
	
	//public static BufferedImage sprite_sheet;
	
	public Game()
	{	
	
		handler= new Handler();
		hud = new HUD();
		menu = new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();     // adding music in the game in a continious loop
		
		new Window(WIDTH,HEIGHT,"WAVE",this);
		
		//BufferedImageLoader  loader=new BufferedImageLoader();
		
		//sprite_sheet = loader.loadImage("/1.png");	//loading the image in the game
		
		spawner=new Spawn(handler, hud,this);
		
		r = new Random(); 
		
		
		//this loop is for adding object in random position with random movement
		/*for (int i = 0; i < 50; i++) {
			//handler.addObject(new Player(0,0,ID.Player ));
			handler.addObject(new Player(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.Player ));
				
		}*/
		if(gameState==STATE.Game)
		{

			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler ));
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH-50),r.nextInt(HEIGHT-50),ID.BasicEnemy,handler ));// for creating the 1 enemy in random position

		}
		else
		{
			for (int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuPartical,handler ));
				
			}
		}
		/*if(r.nextInt(WIDTH)>38) 
				temp=r.nextInt(WIDTH)-32;
		else 
				temp=r.nextInt(WIDTH);
		*/
		
		//handler.addObject(new EnemyBoss(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.EnemyBoss,handler));
		//handler.addObject(new EnemyBoss((Game.WIDTH/2)-48,-120 ,ID.EnemyBoss,handler));
		
		//handler.addObject(new Player(WIDTH/2+64,HEIGHT/2-32,ID.Player2 )); this is for player 2
		/*
		 * this is for multiple enemy in the room with random postition
		 * for(int i=0;i<20;i++)
		{
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.BasicEnemy ));
		}*/
	}
	public synchronized void start()
	{
		thread =new Thread(this);
		thread.start(); 
		running =true;
	}
	
	public synchronized void stop()
	{
		try {
			thread.join();
			running=false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			long now =System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta>= 1)
			{
				tick();
				delta--;
			}
			if(running)
			{	
				render();
			}
			frames++;
			
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				//System.out.println("FPS: " + frames);
				frames=0;
			}
		}
		stop();
		
	}
	
	private void tick()
	{
		
		if(gameState==STATE.Game)
		{
			if(!paused)
			{
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if(HUD.health<=0)
				{
					HUD.health=100;
					gameState=STATE.End;
					spawner.scoreKeep=0;
					handler.clearEnemy();
					for (int i = 0; i < 20; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuPartical,handler ));
						
					} 
				}
			}
			
			
		}else if(gameState==STATE.Menu || gameState==STATE.End || gameState==STATE.Select)
		{
			menu.tick();
			handler.tick();
			
		}
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		} 
		
		 
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		handler.render(g);
		
		if(paused)
		{	
			/*Font font =new Font("arial",1,30);
			g.setFont(font);*/
			g.setColor(Color.WHITE);
			
			g.drawString("Paused", WIDTH/2,HEIGHT/2);
		}
		
		if(gameState==STATE.Game)
		{
			hud.render(g);
		}else if(gameState==STATE.Menu || gameState==STATE.Help || gameState==STATE.End || gameState==STATE.Select)
		{
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var,float min,float max)
	{	
		
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;		
	}
	
	public static void main(String[] args) {
		
		new Game();
		
	}

	

}
