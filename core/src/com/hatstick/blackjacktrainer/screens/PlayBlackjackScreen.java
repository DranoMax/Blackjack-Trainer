package com.hatstick.blackjacktrainer.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.entity.Card;
import com.hatstick.blackjacktrainer.entity.Dealer;
import com.hatstick.blackjacktrainer.entity.Player;

public class PlayBlackjackScreen implements Screen {

    final BlackjackTrainer game;

    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private Map<String,Sprite> cardImages;
    
    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer;

    public PlayBlackjackScreen(final BlackjackTrainer game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.position.set(800/2, 480/2, 0f); 
        
        dealer = new Dealer();
        players.add(new Player("Alex"));
        
        setupSprites();
        startGame();
    }
    
    private void setupSprites() {
    	loadCards();
    }
    
    private void loadCards() {
    	atlas = new TextureAtlas(Gdx.files.internal("cards/cards.pack"));
    	cardImages = new HashMap<String,Sprite>();
    	
    	// Load all cards into a map of name -> image
    	// For now, we are simply scaling the image but we should find a
    	// source for different sized images and load them based on resolution
    	for( AtlasRegion region : atlas.getRegions()) {
    		cardImages.put(region.name, atlas.createSprite(region.name));
    		cardImages.get(region.name).setSize(50, 50);
    	}
    }
    
    private void startGame() {
    	dealer.shuffle();
    	dealer.deal(players);
    	
    	for( Player player : players) {
            player.printHand();
        }
    }

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		for( Player player : players) {
			for( Card card : player.getHand()) {
				cardImages.get(card.getCard()).draw(game.batch);
			}
		}
		game.batch.end();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
