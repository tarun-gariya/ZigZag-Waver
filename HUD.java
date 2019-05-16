package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float health = 100;
	private float greenValue=255;
	private float score=0;
	private float level=1;
	
	
	public void tick()
	{
		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue,0,255);
		greenValue=health*2;
		
		score++;//this is for increasing the score as time goes
	}
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15,15,200,32);
		g.setColor(new Color(75,(int)greenValue,0));
		g.fillRect(15,15,(int)health*2,32);
		g.setColor(Color.WHITE);
		g.drawRect(15,15,(int)health*2,32);
		
		g.drawString("Score: "+score,15,64 );
		g.drawString("Level: "+level,15,80 );
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getLevel() {
		return level;
	}
	public void setLevel(float level) {
		this.level = level;
	}
		
	
}
