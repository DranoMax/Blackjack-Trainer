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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
	private ButtonFactory buttonFactory = new ButtonFactory();
	private TextButton hitButton;
	private TextButton standButton;
	private ImageButton betButton;

	private Stage stage;

	public PlayBlackjackScreen(final BlackjackTrainer game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);

		createGame();
		setupSprites();
		startGame();
	}

	private void createGame() {
		dealer = new Dealer();
		cardTable = new CardTable(dealer, game.SCREEN_WIDTH, game.SCREEN_HEIGHT, cardSize);

		players.add(new HumanPlayer("Alex"));
		//       players.add(new ComputerPlayer("Andrew"));

		cardTable.sitDown(players.get(0));
		//       cardTable.sitDown(players.get(1));
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
				System.out.println(players.get(0).getHand().getTotal());   
			}
		});
		//betButton = buttonFactory.createBetButton();
		Table test = buttonFactory.createBetButton();

		
		table.add(test).width(game.SCREEN_WIDTH/6).height(game.SCREEN_HEIGHT/8).expandX().right();
		table.add(hitButton).width(game.SCREEN_WIDTH/6).height(game.SCREEN_HEIGHT/8).right();
		table.add(standButton).width(game.SCREEN_WIDTH/6).height(game.SCREEN_HEIGHT/8).right();
		stage.addActor(table);

	}

	private void loadCards() {
		atlas = new TextureAtlas(Gdx.files.internal("cards/cards.pack"));
		cardImages = new HashMap<String,Sprite>();
		cardSize.set(game.SCREEN_WIDTH/(cardTable.getNumberPlayers()+3),game.SCREEN_HEIGHT/(cardTable.getNumberPlayers()+1));

		// Load all cards into a map of name -> image
		// For now, we are simply scaling the image but we should find a
		// source for different sized images and load them based on resolution
		for( AtlasRegion region : atlas.getRegions()) {
			cardImages.put(region.name, atlas.createSprite(region.name));
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
		game.font.draw(game.batch, "Chips: " + players.get(0).getChips(), 0, game.SCREEN_HEIGHT);
		drawCards();	
		game.batch.end();		

		/**
		 * TODO: Move game logic out of render thread
		 */
		dealer.continueRound(players);
		if (dealer.isPlayerTurn()) {
			stage.draw();
			Table.drawDebug(stage);
		}
	}

	private void drawCards() {

		// Draw Player Cards
		int spacer;
		for( Player player : players) {
			spacer = 0;
			for( Card card : player.getHandArray()) {	
				cardImages.get(card.getCard()).setPosition(player.getPosition().x+spacer, player.getPosition().y-cardSize.y);
				cardImages.get(card.getCard()).draw(game.batch);

				spacer += cardSize.x/10; 
			}
		}

		// Draw Dealer Cards
		spacer = 0;
		for( Card card : dealer.getHandArray() ) {
			cardImages.get(card.getCard()).setPosition(dealer.getPosition().x-cardSize.x/1.32f+spacer, dealer.getPosition().y-cardSize.y/1.09f);
			cardImages.get(card.getCard()).draw(game.batch);

			spacer += cardSize.x/10;
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
		// TODO Auto-generated method stub

	}
}
