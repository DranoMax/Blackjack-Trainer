package com.hatstick.blackjacktrainer.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.entity.Card;
import com.hatstick.blackjacktrainer.entity.Dealer;
import com.hatstick.blackjacktrainer.entity.Player;
import com.hatstick.blackjacktrainer.entity.CardTable;

public class PlayBlackjackScreen implements Screen {

    final BlackjackTrainer game;

    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private Map<String,Sprite> cardImages;
    private Vector2 cardSize = new Vector2();
    
    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer;
    private CardTable cardTable;
    
    // Buttons
    private TextButton hitButton;
    private TextButton standButton;
    private Skin buttonSkin;
    private BitmapFont font;
    
    // HitButton
    private TextButtonStyle hitButtonStyle;
    
    // StandButton
    private TextButtonStyle standButtonStyle;
    
    private TextureAtlas buttonAtlas;
    private Stage stage;

    public PlayBlackjackScreen(final BlackjackTrainer game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        camera.position.set(game.SCREEN_WIDTH/2, game.SCREEN_HEIGHT/2, 0f);      
        
        createGame();
        setupSprites();
        startGame();
    }
    
    private void createGame() {
    	dealer = new Dealer();
        cardTable = new CardTable(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        
        players.add(new Player("Alex"));
   //     players.add(new Player("Andrew"));

        cardTable.sitDown(players.get(0));
   //     table.sitDown(players.get(1));
    }
    
    private void setupSprites() {
    	setupButtons();
    	loadCards();
    }
    
    private void setupButtons() {
    	Table table = new Table();
    	table.setFillParent(true);
    	table.align(Align.bottom);
    	stage = new Stage();
    	Gdx.input.setInputProcessor(stage);
    	
    	font = new BitmapFont();
    	
    	buttonSkin = new Skin();
    	buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
    	buttonSkin.addRegions(buttonAtlas);
    	
    	// Hit Style
    	hitButtonStyle = new TextButtonStyle();
    	hitButtonStyle.font = font;
    	hitButtonStyle.up = buttonSkin.getDrawable("hit");
    	hitButtonStyle.down = buttonSkin.getDrawable("hit_pressed");
    	
    	// Create hitButton
    	hitButton = new TextButton("Hit", hitButtonStyle);	
    	hitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				  dealer.hit(players.get(0));
				  System.out.println(players.get(0).getHand().getTotal());   
			}
    	});
    	table.add(hitButton).width(game.SCREEN_WIDTH/10).height(game.SCREEN_HEIGHT/8);
 
    	
    	// Stand Style
    	standButtonStyle = new TextButtonStyle();
    	standButtonStyle.font = font;
    	standButtonStyle.up = buttonSkin.getDrawable("stand");
    	standButtonStyle.down = buttonSkin.getDrawable("stand_pressed");
    	
    	// Create hitButton
    	standButton = new TextButton("Stand", standButtonStyle);	
    	table.add(standButton).width(game.SCREEN_WIDTH/10).height(game.SCREEN_HEIGHT/8);
    	stage.addActor(table);
    }
    
    private void loadCards() {
    	atlas = new TextureAtlas(Gdx.files.internal("cards/cards.pack"));
    	cardImages = new HashMap<String,Sprite>();
    	cardSize.set(game.SCREEN_HEIGHT/(cardTable.getNumberPlayers()+1),game.SCREEN_HEIGHT/(cardTable.getNumberPlayers()+1));
    	
    	// Load all cards into a map of name -> image
    	// For now, we are simply scaling the image but we should find a
    	// source for different sized images and load them based on resolution
    	for( AtlasRegion region : atlas.getRegions()) {
    		cardImages.put(region.name, atlas.createSprite(region.name));
    		cardImages.get(region.name).setScale(cardSize.x/region.getTexture().getWidth());
    	}
    }
    
    private void startGame() {
    	dealer.shuffle();
    	dealer.deal(players);
    	
    	for( Player player : players) {
            System.out.println(player.getHand().getTotal());   
        }
    }

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,256,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		Sprite sprite = new Sprite();
		int spacer;
		for( Player player : players) {
			spacer = 0;
			for( Card card : player.getHandArray()) {				
				sprite = cardImages.get(card.getCard());
				sprite.setPosition(player.getPosition().x+spacer, player.getPosition().y);
				sprite.draw(game.batch);

				spacer += cardSize.x/10; 
			}
		}
		game.batch.end();		
		stage.draw();
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
