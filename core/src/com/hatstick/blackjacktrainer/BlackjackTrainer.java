package com.hatstick.blackjacktrainer;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatstick.blackjacktrainer.screens.MainMenuScreen;
import com.hatstick.blackjacktrainer.screens.PlayBlackjackScreen;

public class BlackjackTrainer extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    
    // Animation/Tweening
 	public static TweenManager tweenManager;
 	public static float tweenSpeed = 0.3f;
 	public static int tweenTimer = 1;
 	
 	public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        tweenManager = new TweenManager();
        this.setScreen(new PlayBlackjackScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
