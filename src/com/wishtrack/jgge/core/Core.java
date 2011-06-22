package com.wishtrack.jgge.core;

import java.awt.*;

import javax.swing.*;

import com.wishtrack.jgge.graphics.*;
import javax.sound.sampled.AudioFormat;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public abstract class Core {

    private static final AudioFormat PLAYBACK_FORMAT =
        new AudioFormat(44100, 16, 1, true, false);
    
    private static  DisplayMode[] modes = {

        new DisplayMode(1920,1080,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1920,1080,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1920,1080,16,DisplayMode.REFRESH_RATE_UNKNOWN),          	 
        new DisplayMode(1366,768,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1366,768,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1366,768,16,DisplayMode.REFRESH_RATE_UNKNOWN),      
        new DisplayMode(1024,768,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1024,768,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1024,768,16,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1280,720,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1280,720,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1280,720,16,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(800,600,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(800,600,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(800,600,16,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(640,480,32,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(640,480,24,DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(640,480,16,DisplayMode.REFRESH_RATE_UNKNOWN),
    };

     private boolean running;
     private boolean exit;
     protected ScreenManager s;

     //stop method
     public void stop() {

         running = false;
     }
     
     public boolean isGameRunning() {
    	 
    	 return running;
     }
     public void exit() {
    	 if(running) 
    		 running = false;
    	 exit = true;
     }

     //call init and gameloop
     public void run() {

         try {

             init();
             gameLoop();
          }finally{
              s.restoreScreen();
          }
     }

     //set to full screen
     public void init() {

         s = new ScreenManager();
         DisplayMode dm = s.findFirstCompatibleMode(modes);
         s.setFullScreen(dm);

         Window w = s.getFullScreenWindow();
         w.setFont(new Font("Arial", Font.PLAIN,24));
         
         w.setBackground(Color.MAGENTA);
         w.setForeground(Color.DARK_GRAY);
         running = true;

     }

     //main gameLoop
     public void gameLoop() {

         long startTime = System.currentTimeMillis();
         long cumTime = startTime;

         while(running) {

              long timePassed = System.currentTimeMillis() - cumTime;
              cumTime+= timePassed;

              update(timePassed);

              Graphics2D g = s.getGraphics();
              
              draw(g);
              g.dispose();
              s.update();
              
              /*try {

                  Thread.sleep(20);
              }catch(Exception ex) { ex.printStackTrace();}*/
         }
         
         if(!running && !exit) {
        	 exit = false;
         }
         
         while(! exit){
        	 
        	 draw(s.getGraphics());
         }
         
         

     }

     public Image loadImage (String path) {
     	
     	return new ImageIcon(path).getImage();
     }
     //update animation
     public void update(long timePassed) {
     }

     //draws to screen
     public abstract void draw(Graphics2D g);

     public static AudioFormat getPlayBackFormat() {
         return PLAYBACK_FORMAT;
     }

     
}
