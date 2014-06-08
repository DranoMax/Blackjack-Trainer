package com.hatstick.blackjacktrainer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatstick.blackjacktrainer.screens.MainMenuScreen;
import com.hatstick.blackjacktrainer.screens.PlayBlackjackScreen;

public class BlackjackTrainer extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
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
