package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{
	
	Random r= new Random();
	Handler handler;
	private BufferedImage player_image;
	public static BufferedImage sprite_sheet;
	
	
	public Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
		BufferedImageLoader  loader=new BufferedImageLoader();
		
		player_image = loader.loadImage("/1.png");	//loading the player Image in the game
		
		
	/*
	 * this is for random speed of the object
	 * 	velX = r.nextInt(5)+1 ; 
		velY = r.nextInt(5);
	*/
	
		
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-37);
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		collision();
	}
	
	private void collision()
	{
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject= handler.object.get(i);
			if(tempObject.getId()== ID.BasicEnemy||tempObject.getId()== ID.FastEnemy|| tempObject.getId()==ID.SmartEnemy)//here tempObject is basicEnemy
			{	
				if(getBounds().intersects(tempObject.getBounds()))
				{
					//collision code for health representation
					HUD.health-=2;
					
				}
			}
		}
		handler.addObject(new Trail(x,y,ID.Trail,Color.WHITE,32,32,0.08f,handler));
		
	}
	
	@Override
	public void render(Graphics g) {
		
	
		/*
		 * this is for player 1
		 * if(id == ID.Player)
		{
			g.setColor(Color.BLUE);
		}
		
		 * this is for player 2
		 * if(id == ID.Player2)
		{
			g.setColor(Color.RED);
		}*/
		
		//Graphics2D g2d = (Graphics2D) g;
		
		/*g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 32, 32);*/
		
		g.drawImage(player_image,(int)x,(int)y,null);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}

}
