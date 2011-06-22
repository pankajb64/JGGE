package com.wishtrack.jgge.graphics;

//import Sprite;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
//import com.wishtrack.jgge.test.*;


/**
 * A class to handle the motion of sprites
 */

/**
 * @author Administrator
 *
 */
public  class SpriteManager  {

	private ArrayList<Sprite> sprites;
	private int screenWidth;
	private int screenHeight;
	
	public SpriteManager(int screenWidth, int screenHeight) {
		
		sprites = new ArrayList<Sprite>();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
	}
	
	
	//update sprites
    public synchronized void update(long timePassed) {

    	for(Sprite sprite: sprites){
     
    		if(sprite.getX() <0|| sprite.getX() + sprite.getWidth() > screenWidth) {

            sprite.setVelocityX(-1 * sprite.getVelocityX());
           }

        
    		if(sprite.getY() <0 || sprite.getY() + sprite.getHeight() > screenHeight) {

            sprite.setVelocityY(-1 * sprite.getVelocityY());
           }

        
    		
        }
    	
    	
    	 for(int i=0; i<sprites.size();i++) {
    		
    		Sprite sprite1 = sprites.get(i);
    		
    		for( int j = i+1; j<sprites.size();j++) {
    			
    			Sprite sprite2 = sprites.get(j);
    			
    			
    			double x1left = sprite1.getX();
    			double y1top = sprite1.getY();
    			double x1right = x1left + sprite1.getWidth();
    			double y1bottom = y1top + sprite1.getHeight();
    			
    			double x2left = sprite2.getX();
    			double y2top = sprite2.getY();
    			double x2right = x2left + sprite2.getWidth();
    			double y2bottom = y2top + sprite2.getHeight();
    			
    			if (x1left <= x2right && x2left <= x1right &&
    				    y1top <= y2bottom && y1bottom >= y2top) {
    				
    				double vsprite1x = sprite1.getVelocityX();
    				double vsprite1y = sprite1.getVelocityY();
    				double vsprite2x = sprite2.getVelocityX();
    				double vsprite2y = sprite2.getVelocityY();
    				
    				if(sprite1 instanceof AutomatedSprite && sprite2 instanceof AutomatedSprite) {
    					
    				
    				sprite1.setVelocityX(vsprite2x);
    				sprite1.setVelocityY(vsprite2y);
    				sprite2.setVelocityX(vsprite1x);
    				sprite2.setVelocityY(vsprite1y);
    				}
    				
    				else if(sprite2 instanceof UserControlledSprite && sprite1 instanceof AutomatedSprite) {
    					
    					if((screenHeight - y1bottom  < sprite2.getHeight()  || y1top < sprite2.getHeight()) && sprite2.getWidth() > sprite2.getHeight()) {
    						
    						sprite1.setVelocityY(-1 * vsprite1y);
    					}
    					

    					else if( screenWidth - x1right < sprite2.getWidth() || x1left < sprite2.getWidth() ) {
    						
    						sprite1.setVelocityX(-1 * vsprite1x);
    					}
    				}
    				

    				else if(sprite1 instanceof UserControlledSprite && sprite2 instanceof AutomatedSprite) {
    					
    					if( (screenHeight - y2bottom  < sprite1.getHeight() && screenHeight - y1top > sprite2.getHeight()) || (y2top < sprite1.getHeight() && y1bottom > sprite2.getHeight())  ) {
    						
    						sprite2.setVelocityY(-1 * vsprite2y);
    					}
    					

    					else if( screenWidth - x2right  < sprite1.getWidth() || x2left < sprite1.getWidth() ) {
    						
    						sprite2.setVelocityX(-1 * vsprite2x);
    					}
    				}
    				
    			}

    	
    				/*int s1width = sprite1.getWidth();
    				int s1height = sprite1.getHeight();
    				int s2width = sprite2.getWidth();
    				int s2height = sprite2.getHeight();
    				
    				double s1diagonal = Math.sqrt(s1width*s1width + s1height*s1height);
    				
    				double s2diagonal = Math.sqrt(s2width*s2width + s2height*s2height);
    				
    				
    				
    				double left1, left2;
    			    double right1, right2;
    			    double top1, top2;
    			    double bottom1, bottom2;

    			    left1 = sprite1.getX();
    			    left2 = sprite2.getX();
    			    right1 = sprite1.getX() + sprite1.getWidth();
    			    right2 = sprite2.getX() + sprite2.getWidth();
    			    top1 = sprite1.getY();
    			    top2 = sprite2.getY();
    			    bottom1 = sprite1.getY() + sprite1.getHeight();
    			    bottom2 = sprite2.getY() + sprite2.getHeight();

    			    if (bottom1 < top2 || top1 > bottom2 || right1 < left2 || left1 > right2 ) {}
    			    
    			    else {
    			    	
    			    	sprite1.setVelocityX(sprite2.getVelocityX());
    			    	sprite2.setVelocityX(sprite1.getVelocityX());
    			    	sprite1.setVelocityY(sprite2.getVelocityY());
    			    	sprite2.setVelocityY(sprite1.getVelocityY());
    			    }
    			    
    			   

    				
    				/*if((sprite1.getX() < sprite2.getX() && sprite2.getX() - sprite1.getX() == sprite1.getWidth() -1) ) {
    					
    					
    					if( sprite2.getY() < sprite1.getY() && sprite1.getY() - sprite2.getY() < sprite1.getHeight() ) {
    					//System.out.println("Entered in condition 1");
    						sprite1.setVelocityX(-1*sprite1.getVelocityX());
    					    sprite2.setVelocityX(-1*sprite2.getVelocityX());
    					    
    					}
    					
    					else if( sprite1.getY() < sprite2.getY() && sprite2.getY() - sprite1.getY() < sprite2.getHeight()) {
    						
    						sprite1.setVelocityX(-1*sprite1.getVelocityX());
    					    sprite2.setVelocityX(-1*sprite2.getVelocityX());
    					}
    				}
    				
    				else if(sprite2.getX() <sprite1.getX() && sprite1.getX() - sprite2.getX() == sprite2.getWidth() -1 ) {
    					
    					if( sprite2.getY() < sprite1.getY() && sprite1.getY() - sprite2.getY() < sprite1.getHeight() ) {
        					
        						sprite1.setVelocityX(-1*sprite1.getVelocityX());
        					    sprite2.setVelocityX(-1*sprite2.getVelocityX());
        					    
        					}
        					
        					else if( sprite1.getY() < sprite2.getY() && sprite2.getY() - sprite1.getY() < sprite2.getHeight()) {
        						
        						sprite1.setVelocityX(-1*sprite1.getVelocityX());
        					    sprite2.setVelocityX(-1*sprite2.getVelocityX());
        					}
    					
    				}

    				

    				if((sprite1.getY() <sprite2.getY() && sprite2.getY() - sprite1.getY() == sprite1.getHeight() -1) ) {
    					
    					
    					if( sprite2.getX() < sprite1.getX() && sprite1.getX() - sprite2.getX() < sprite1.getWidth() ) {
        					
        						sprite1.setVelocityY(-1*sprite1.getVelocityY());
        					    sprite2.setVelocityY(-1*sprite2.getVelocityY());
        					    
        					}
        					
        					else if( sprite1.getX() < sprite2.getX() && sprite2.getX() - sprite1.getX() < sprite2.getWidth()) {
        						
        						sprite1.setVelocityY(-1*sprite1.getVelocityY());
        					    sprite2.setVelocityY(-1*sprite2.getVelocityY());
        					}
    				}
    				
    				else if(sprite2.getY() <sprite1.getY() && sprite1.getY() - sprite2.getY() == sprite2.getHeight() - 1 ) {
    					
    					if( sprite2.getX() < sprite1.getX() && sprite1.getX() - sprite2.getX() < sprite1.getWidth() ) {
        					
        						sprite1.setVelocityY(-1*sprite1.getVelocityY());
        					    sprite2.setVelocityY(-1*sprite2.getVelocityY());
        					    
        					}
        					
        					else if( sprite1.getX() < sprite2.getX() && sprite2.getX() - sprite1.getX() < sprite2.getWidth()) {
        						
        						sprite1.setVelocityY(-1*sprite1.getVelocityY());
        					    sprite2.setVelocityY(-1*sprite2.getVelocityY());
        					}
    					
    				}*/
    				
    				/*if((sprite1.getY() <sprite2.getY() && sprite2.getY() - sprite1.getY() == sprite1.getHeight()) || (sprite2.getY() <sprite1.getY() && sprite1.getY() - sprite2.getY() == sprite2.getHeight()) ) {
    					
    					
    					
    					
    					sprite1.setVelocityY(-1* sprite1.getVelocityY());
    					sprite2.setVelocityY(-1* sprite2.getVelocityY());
    				}*/
    				
 
    				
    				
    				/*
    				if(sprite2.getX() - sprite1.getX() <=  sprite1.getWidth() || sprite1.getX() - sprite2.getX() <=  sprite2.getWidth()) {
    					
    					sprite1.setVelocityX(-1* sprite1.getVelocityX());
    					sprite2.setVelocityX(-1* sprite2.getVelocityX());
    				}
    				

    				if(sprite2.getY() - sprite1.getY() <=  sprite1.getHeight() || sprite1.getY() - sprite2.getY() <=  sprite2.getHeight()) {
    					
    					sprite1.setVelocityY(-1* sprite1.getVelocityY());
    					sprite2.setVelocityY(-1* sprite2.getVelocityY());
    				}
    				*/
    				/*if(Math.abs(sprite1.getX() - sprite2.getX()) <= s1diagonal) {
    					
    					
    				}*/
    			}
    		}
    		
    		
    		
    		
    	
    	
    	
    	
    	for(Sprite sprite : sprites){
    		
    		sprite.update(timePassed);
    	}
    }
    
    
    public void add(Sprite sprite) {
    	
    	sprites.add(sprite);
    }
    
    public ArrayList<Sprite> getAllSprites() {
    	
    	return sprites;
    }
	
    
    
}
