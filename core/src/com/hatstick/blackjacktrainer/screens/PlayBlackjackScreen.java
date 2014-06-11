package com.hatstick.blackjacktrainer.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.hatstick.blackjacktrainer.BlackjackTrainer;
import com.hatstick.blackjacktrainer.entity.Card;
import com.hatstick.blackjacktrainer.entity.ComputerPlayer;
import com.hatstick.blackjacktrainer.entity.Dealer;
import com.hatstick.blackjacktrainer.entity.Hand;
import com.hatstick.blackjacktrainer.entity.HumanPlayer;
import com.hatstick.blackjacktrainer.entity.Player;
import com.hatstick.blackjacktrainer.entity.CardTable;
import com.hatstick.blackjacktrainer.factory.ButtonFactory;
import com.hatstick.blackjacktrainer.tween.CardAccessor;

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
	private ButtonFactory buttonFactory;
	private TextButton hitButton;
	private TextButton standButton;

	// In LibGdx, stages are used to display overlayed GUI
	private Stage playerTurnStage;
	private Stage newGameStage;

	public PlayBlackjackScreen(final BlackjackTrainer game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, BlackjackTrainer.SCREEN_WIDTH, BlackjackTrainer.SCREEN_HEIGHT);

		createGame();
		setupSprites();
		setupTweening();
	}

	private void createGame() {
		dealer = new Dealer();
		cardTable = new CardTable(dealer, cardSize);
		buttonFactory = new ButtonFactory();

		players.add(new HumanPlayer("Alex"));
		players.add(new ComputerPlayer("Andrew"));

		cardTable.sitDown(players.get(0));
		cardTable.sitDown(players.get(1));
	}

	private void setupSprites() {
		setupButtons();
		loadCards();
	}
	
	private void setupTweening() {
		Tween.registerAccessor(Card.class, new CardAccessor());
	}

	private void setupButtons() {
		setupPlayerTurnGUI();
		setupNewRoundGUI();
	}

	private void setupPlayerTurnGUI() {
		Table table = new Table();
		table.setFillParent(true);
		table.align(Align.bottom);
		playerTurnStage = new Stage();
		Gdx.input.setInputProcessor(playerTurnStage);

		standButton = buttonFactory.createButton("STAND", "stand", "stand_pressed");
		standButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				players.get(0).getHand().setStatus(Hand.STAND);
			}
		});
		hitButton = buttonFactory.createButton("HIT", "hit", "hit_pressed");
		hitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dealer.hit(players.get(0));
			}
		});

		table.add(standButton).width(BlackjackTrainer.SCREEN_WIDTH/8).height(BlackjackTrainer.SCREEN_HEIGHT/8).expandX().right();
		table.add(hitButton).width(BlackjackTrainer.SCREEN_WIDTH/8).height(BlackjackTrainer.SCREEN_HEIGHT/8).right();
		playerTurnStage.addActor(table);
	}

	private void setupNewRoundGUI() {
		Table table = new Table();
		table.setFillParent(true);
		table.align(Align.bottom);
		newGameStage = new Stage();
		Gdx.input.setInputProcessor(newGameStage);

		// Bet Button
		Table betButton = buttonFactory.createBetButton((HumanPlayer)players.get(0));
		table.add(betButton).width(BlackjackTrainer.SCREEN_WIDTH/6).height(BlackjackTrainer.SCREEN_HEIGHT/8).expandX().right();

		// Deal Button
		TextButton dealButton = buttonFactory.createButton("DEAL", "plain", "plain_pressed");
		dealButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (players.get(0).getBet() > 0) {
					startGame();
				}
			}
		});

		table.add(dealButton).width(BlackjackTrainer.SCREEN_WIDTH/6).height(BlackjackTrainer.SCREEN_HEIGHT/8).right();
		newGameStage.addActor(table);
	}

	private void loadCards() {
		atlas = new TextureAtlas(Gdx.files.internal("cards/cards.pack"));
		cardImages = new HashMap<String,Sprite>();
		cardSize.set(BlackjackTrainer.SCREEN_WIDTH/(cardTable.getNumberPlayers()+3),BlackjackTrainer.SCREEN_HEIGHT/(cardTable.getNumberPlayers()+1));

		// Load all cards into a map of name -> image
		// For now, we are simply scaling the image but we should find a
		// source for different sized images and load them based on resolution
		for( AtlasRegion region : atlas.getRegions()) {
			cardImages.put(region.name, atlas.createSprite(region.name));
			cardImages.get(region.name).setOrigin(0,0);
			cardImages.get(region.name).setScale(cardSize.x/region.getTexture().getWidth());
		}
	}

	private void startGame() {
		dealer.beginRound(players);

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

		// Draw Cards
		game.batch.begin();
		game.font.setScale(1.5f);
		game.font.draw(game.batch, "Chips: " + players.get(0).getChips(), 0, BlackjackTrainer.SCREEN_HEIGHT);
		drawCards();	
		game.batch.end();		

		/**
		 * TODO: Move game logic out of render thread
		 */
		
		if (dealer.isRoundStarted()) {
			dealer.continueRound(players);
			if (dealer.isPlayerTurn()) {
				Gdx.input.setInputProcessor(playerTurnStage);
				playerTurnStage.draw();
			}
		} else {
			Gdx.input.setInputProcessor(newGameStage);
			newGameStage.draw();
		}
		
		if (Gdx.input.isTouched()) {
			System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
		}

		// Update tweening
		BlackjackTrainer.tweenManager.update(delta);
	}

	private void drawCards() {

		// Draw Player Cards
		// Spacer used to place cards on top of each other
		int spacer;
		Sprite sprite;
		for( Player player : players) {
			spacer = 0;
			for( Card card : player.getHandArray()) {
				sprite = cardImages.get(card.getCard());
				sprite.setPosition(card.getPosition().x+spacer, card.getPosition().y);
				sprite.draw(game.batch);
				spacer += sprite.getWidth()/20; 
			}
		}

		// Draw Dealer Cards
		spacer = 0;
		for( Card card : dealer.getHandArray() ) {
			sprite = cardImages.get(card.getCard());
			sprite.setPosition(card.getPosition().x+spacer, card.getPosition().y);
			sprite.draw(game.batch);

			spacer += sprite.getWidth()/20;
		}

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
		atlas.dispose();
		playerTurnStage.dispose();
		newGameStage.dispose();
		buttonFactory.dispose();
	}
}
