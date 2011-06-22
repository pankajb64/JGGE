package com.wishtrack.jgge.graphics;

import java.awt.Image;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Animation {

    private ArrayList scenes;
    private int sceneIndex;
    private long movieTime;
    private long totalTime;

    //CONSTRUCTOR
    public Animation() {

        scenes = new ArrayList();
        sceneIndex = 0;
        start();
    }
    
    private Animation(ArrayList frames, long totalDuration) {
        this.scenes = frames;
        this.totalTime = totalDuration;
        start();
    }


    /**
    Creates a duplicate of this animation. The list of frames
    are shared between the two Animations, but each Animation
    can be animated independently.
*/
public Object clone() {
    return new Animation(scenes, totalTime);
}
    //add scene to ArrayList and set time for each scene
    public synchronized void addScene(Image i, long t) {

        totalTime += t;
        scenes.add(new OneScene(i, totalTime));
    }

    //start animation from beginning

    public synchronized void start() {

        movieTime = 0;
        sceneIndex = 0;
    }

    //change scenes
    public synchronized void update(long timePassed) {

        if(scenes.size() > 1) {

            movieTime+= timePassed;

            if(movieTime >= totalTime) {

                movieTime = 0;
                sceneIndex = 0;
            }

            while(movieTime > getScene(sceneIndex).endTime) {

                sceneIndex++;
            }
        }
    }

    //get animations current scene (aka image)
    public synchronized Image getImage() {

        if(scenes.size() == 0) {

            return null;
        }
        else {
            return getScene(sceneIndex).pic;
        }
   }

    //get scene
    private OneScene getScene(int x) {

        return (OneScene) scenes.get(x);
    }

    /////////PRIVATE INNER CLASS////////////////

    private class OneScene {

        Image pic;
        long endTime;

        public OneScene(Image pic, long endTime) {

            this.pic = pic;
            this.endTime = endTime;

        }
    }

}
