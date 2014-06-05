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
import com.badlogic.gdx.math.Vector2;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.entity.Card;
import com.hatstick.blackjacktrainer.entity.Dealer;
import com.hatstick.blackjacktrainer.entity.Player;
import com.hatstick.blackjacktrainer.entity.Table;

public class PlayBlackjackScreen implements Screen {

    final BlackjackTrainer game;

    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private Map<String,Sprite> cardImages;
    private Vector2 cardSize = new Vector2();
    
    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer;
    private Table table;

    public PlayBlackjackScreen(final BlackjackTrainer game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        camera.position.set(800/2, 480/2, 0f); 
        
        createGame();
        setupSprites();
        startGame();
    }
    
    private void createGame() {
    	dealer = new Dealer();
        table = new Table(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        
        players.add(new Player("Alex"));
        players.add(new Player("Andrew"));

        table.sitDown(players.get(0));
        table.sitDown(players.get(1));
    }
    
    private void setupSprites() {
    	loadCards();
    }
    
    private void loadCards() {
    	atlas = new TextureAtlas(Gdx.files.internal("cards/cards.pack"));
    	cardImages = new HashMap<String,Sprite>();
    	cardSize.set(game.SCREEN_HEIGHT/(table.getNumberPlayers()+3),game.SCREEN_HEIGHT/(table.getNumberPlayers()+3));
    	
    	// Load all cards into a map of name -> image
    	// For now, we are simply scaling the image but we should find a
    	// source for different sized images and load them based on resolution
    	for( AtlasRegion region : atlas.getRegions()) {
    		cardImages.put(region.name, atlas.createSprite(region.name));
    		cardImages.get(region.name).setSize(cardSize.x, cardSize.y);
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
		Sprite sprite = new Sprite();
		int spacer;
		for( Player player : players) {
			spacer = 0;
			for( Card card : player.getHand()) {				
				sprite = cardImages.get(card.getCard());
				sprite.setPosition(player.getPosition().x+spacer, player.getPosition().y);
				sprite.draw(game.batch);
				/*
				 * TODO: Change 50 (the width of our cards) to a standard variable that we can draw from
				 */
				spacer += cardSize.x/3; 
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
