package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		for (int i = 0; i < object.size(); i++) {
			 GameObject tempObject = object.get(i);
			 tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		try {
			for (int i = 0; i < object.size(); i++) {
				 GameObject tempObject = object.get(i);
				 tempObject.render(g);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error aa gai" );
			
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}

	public void clearEnemy() {
		for (int i = 0; i < object.size(); i++) {
			 GameObject tempObject = object.get(i);
			 
			 //this if statement clear all objects including player and add further new player object with same coordinates
			 if(tempObject.getId()!=ID.Player)
				 {
				 	object.clear();
				 	if(Game.gameState != Game.STATE.End )				 		
				 	addObject(new Player((int)tempObject.getX(),(int)tempObject.getY(),ID.Player,this));					 
				 }
		}
	}
	
}
