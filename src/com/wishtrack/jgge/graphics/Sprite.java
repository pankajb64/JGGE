package com.wishtrack.jgge.graphics;



import java.awt.Image;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Sprite {

    protected Animation a;
    private double x;
    private double y;
    private double vx;
    private double vy;

    //Constructor
    public Sprite(Animation a) {

        this.a = a;
    }

    //change postition
    public void update(long timePassed) {

        x += vx * timePassed;
        y += vy * timePassed;
        a.update(timePassed);

    }

    //get x
    public double getX() {

        return x;
    }

    //get y
    public double getY() {

        return y;
    }

    //set x
    public void setX(double x) {

        this.x = x;
    }

    //set y
    public void setY(double y) {

        this.y = y;
    }

    //get horizontal velocity
    public double getVelocityX() {

        return vx;
    }

    //get vertical velocity
    public double getVelocityY() {

        return vy;
    }

    //set horizontal velocity
    public void setVelocityX(double vx) {

        this.vx = vx;
    }

    //set vertical velocity
    public void setVelocityY(double vy) {

        this.vy = vy;
    }

    //get sprite / image
    public Image getImage() {

        return a.getImage();
    }

    //get Image width
    public int getWidth() {

        return a.getImage().getWidth(null);
    }

    //get image height
    public int getHeight() {

        return a.getImage().getHeight(null);
    }

    /**
    Clones this Sprite. Does not clone position or velocity
    info.
*/
public Object clone() {
    return new Sprite(a);
}

}
