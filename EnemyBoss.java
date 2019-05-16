package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private int timmer=80;
	private int timmer2=50;
	private Random r = new Random();
	
	public EnemyBoss(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=0;
		velY=2;
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(timmer<=0)
		{
			velY=0;
		}
		else {
			timmer--;
		}
		
		if(timmer<=0) timmer2--;
		if( timmer2 <=0)
		{	
			if(velX==0) velX=2;
			if(velX>0)
			{
			velX+=0.005f;
			}
			if(velX<0)
			{
			velX-=0.005f;
			}
			Game.clamp(velX, -7, 7);
			int spawn = r.nextInt(10);
			if(spawn==0) handler.addObject(new EnemyBossBullet((int)x+48,(int)y+48,ID.BasicEnemy,handler));
		}
		/*
		if(y<=0 || y>=Game.HEIGHT-32)	
			velY*=-1;*/
		if(x<=0 || x>=Game.WIDTH-96)	
			velX*=-1;
		
		//handler.addObject(new Trail(x,y,ID.Trail,Color.red,96,96,0.02f,handler)); creating trail of enemy
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect((int) x,(int)y,96,96 );
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

}
