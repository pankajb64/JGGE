/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wishtrack.jgge.core;

import com.wishtrack.jgge.graphics.Sprite;
import com.wishtrack.jgge.graphics.SpriteManager;
import com.wishtrack.jgge.input.GameAction;
import com.wishtrack.jgge.input.InputManager;
import com.wishtrack.jgge.sound.Sound;
import com.wishtrack.jgge.sound.SoundManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class GameCore extends Core {

    private ArrayList<Sprite> sprites;
    private ArrayList<GameAction> actions;
    private ArrayList<Sound> sounds;
    private InputManager i;
    private SpriteManager spm;
    private SoundManager sm;
    Image img;
    public static void main(String[] args) {
        new GameCore().run();
    }
    @Override
    public void init() {
        super.init();
        sprites = new ArrayList<Sprite>();
        actions = new ArrayList<GameAction>();
        sounds = new ArrayList<Sound>();
        i = new InputManager(s.getFullScreenWindow());
        spm = new SpriteManager(s.getWidth(),s.getHeight());
        sm = new SoundManager(getPlayBackFormat());
        initInput();
        loadSprites();
    }

    public void draw(Graphics2D g) {

        g.clearRect(0, 0, s.getWidth(), s.getHeight());
        g.drawString("Hello, World!", 0, 0);
    }

    @Override
    public void update(long timePassed) {

        
    }

    @Override
    public void stop() {
        super.stop();
        super.exit();
    }

    public void initInput() {
    /*
     * Load input from "input.xml", create GameAction instances
     * and initialise accordingly. Use InputManager
     */
    }
    public void checkInput() {
        /*
         * Check if any input were pressed and take appropriate
         * action
         */
       GameAction exit = new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY);
       i.mapToKey(exit, KeyEvent.VK_ESCAPE);

       if(exit.isPressed()) {

           stop();
           System.exit(0);
       }
           
    }
    public void loadSounds() {
        /*
         * Load sounds from sound.xml and act accordingly.
         * Use SoundManager.
         */
    }
    public void loadSprites() {
        /*
         * Load sprites from sprite.xml and acct accrodingly
         * Use SpriteManager
         */

        img = loadImage("C:\\test\\images\\player1.png");
    }

}
