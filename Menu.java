package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game ;
	private Handler handler; 
	private Random r =new Random();
	private HUD hud;
	
	public Menu(Game game,Handler handler,HUD hud)
	{
		this.game=game;
		this.handler=handler;
		this.hud= hud;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx=e.getX();
		int my=e.getY();
		
		if(game.gameState==STATE.Menu)
		{
			//play button
			if(mouseOver(mx,my,210,150,200 ,60))
			{
				/*game.gameState=STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler ));
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler ));// for creating the 1 enemy in random position
				*/
				game.gameState=STATE.Select;
				AudioPlayer.getSound("menu_sound").play(); 
				return;
			}
			//Help Button
			if(mouseOver(mx,my,210,250,200,60))
			{  
			game.gameState=STATE.Help;
			AudioPlayer.getSound("menu_sound").play();
			}
					
			//Quit Button
			if(mouseOver(mx,my,210,350,200,60))
			{
				
				System.exit(0);
			}
			
		}
		//Back Button of Help
		if(game.gameState==STATE.Help)
		{
			if(mouseOver(mx,my,210,350,200,60))
			{
				game.gameState=STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		
		}		
		//Retry button in GameOver
		if(game.gameState==STATE.End)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				game.gameState=STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				/*handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler ));
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler ));// for creating the 1 enemy in random position
				*/
				
				AudioPlayer.getSound("menu_sound").play();
			}
		
		}	
		
		//Selct difficulty button
		if(game.gameState==STATE.Select)
		{
			//select difficulty normal  button
			if(mouseOver(mx,my,210,150,200 ,60))
			{
				game.gameState=STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler ));
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler ));// for creating the 1 enemy in random position
				
				game.difficulty=0;
				AudioPlayer.getSound("menu_sound").play(); 
				
			}
			//Hard	 Button
			if(mouseOver(mx,my,210,250,200,60))
			{  
				game.gameState=STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler ));
				handler.clearEnemy();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy,handler ));// for creating the 1 enemy in random position
				
				game.difficulty=1;
			AudioPlayer.getSound("menu_sound").play();
			}
					
			//Back Button
			if(mouseOver(mx,my,210,350,200,60))
			{

				game.gameState=STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
			
		}
	}
	public void mouseReleased(MouseEvent e)
	{	
		
	}
	
	private Boolean mouseOver(int mx,int my,int x,int y,int width,int height)
	{
		if(	mx > x && mx < x + width )
		{
			if(my > y && my< y + height )
			{
				return true;
			}else return false;
		}else return false;
		
	}
	
	public void tick()
	{
		
	}
	public void render(Graphics g)
	{	
		if(game.gameState==STATE.Menu)
		{
		Font font =new Font("arial",1,50);
		Font font1 =new Font("arial",1,30);
		
		g.setFont(font);
		g.drawString("Wave",240 ,70);
		
		g.setColor(Color.WHITE);
		g.drawRect(210, 150, 200,64 );
		g.setFont(font1);
		g.drawString("Play",270 ,190);
		
		
		g.drawRect(210, 250, 200,64 );
		g.drawString("Help",270 ,290);
		
		
		g.drawRect(210, 350, 200,64 );
		g.drawString("Quit",270 ,390);
		}else if(game.gameState==STATE.Help)
		{
			Font font =new Font("arial",1,50);
			Font font1 =new Font("arial",1,30);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("HELP",270 ,70);
			
			g.setFont(font1);
			g.drawString("This is a help menu",150 ,150);
			
			g.drawString("Use arrow keys to protect Player from ",50 ,250);
			g.drawString("Enemies.",250 ,300);
			g.drawString(":)",300 ,330);
			
			g.drawRect(210, 350, 200,64 );
			g.drawString("Back",270 ,390);
			
		}else if(game.gameState==STATE.End)
		{
			Font font =new Font("arial",1,50);
			Font font1 =new Font("arial",1,30);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("GAME OVER",170 ,70);
			
		 	g.setFont(font1);
			g.drawString("You lost with a score of: " + (int)hud.getScore(),100 ,150);
			
			
			g.drawRect(210, 350, 200,64 );
			g.drawString("Try Again",250 ,390);
			
		}else if(game.gameState==STATE.Select)
		{
		Font font =new Font("arial",1,50);
		Font font1 =new Font("arial",1,30);
		
		g.setFont(font);
		g.drawString("SELECT DIFICULTY",100 ,70);
		
		g.setColor(Color.WHITE);
		g.drawRect(210, 150, 200,64 );
		g.setFont(font1);
		g.drawString("Normal",270 ,190);
		
		
		g.drawRect(210, 250, 200,64 );
		g.drawString("Hard",270 ,290);
		
		
		g.drawRect(210, 350, 200,64 );
		g.drawString("Back",270 ,390);
		}
		
		
		
	}
}
