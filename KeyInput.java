package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown =new boolean[4];
	
	Game game;
	
	
	public KeyInput(Handler handler,Game game)
	{
		this.handler=handler;
		
		this.game=game;
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i=0 ; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				//key events for the PLayer1
				if(key == KeyEvent.VK_UP)   { tempObject.setVelY(-5); keyDown[0]=true;}
				if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5); keyDown[1]=true;}
				if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(5);  keyDown[2]=true;}
				if(key == KeyEvent.VK_RIGHT){ tempObject.setVelX(5);  keyDown[3]=true;}
				
				
			}
			/*
			 * this is for player 2
			 * if(tempObject.getId() == ID.Player2)
			{
				//key events for the PLayer1
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-5); 
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5); 
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(5); 
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5); 
				
				
			}*/
		}
		
		if(key == KeyEvent.VK_P) 
			{
				
				if(game.gameState==STATE.Game)
					{
						if(Game.paused )
							Game.paused=false;
						else Game.paused=true;
						
					}
						
			}
		if(key == KeyEvent.VK_ESCAPE)	System.exit(1);

		
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for(int i=0 ; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				//key events for the PLayer1
				if(key == KeyEvent.VK_UP) keyDown[0]=false;//tempObject.setVelY(0); 
				if(key == KeyEvent.VK_LEFT) keyDown[1]=false;//tempObject.setVelX(0); 
				if(key == KeyEvent.VK_DOWN) keyDown[2]=false;//tempObject.setVelY(0); 
				if(key == KeyEvent.VK_RIGHT) keyDown[3]=false;//tempObject.setVelX(0); 				
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) 	tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) 	tempObject.setVelX(0);
				
			}
			/*
			 * this is fro player 2
			 * if(tempObject.getId() == ID.Player2)
			{
				//key events for the PLayer1
				if(key == KeyEvent.VK_UP) tempObject.setVelY(0); 
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0); 
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0); 
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0); 
				
				
			}*/
		} 
		
	}
}







