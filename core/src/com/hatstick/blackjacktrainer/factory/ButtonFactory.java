package com.hatstick.blackjacktrainer.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.hatstick.blackjacktrainer.entity.Hand;
import com.hatstick.blackjacktrainer.entity.HumanPlayer;
import com.hatstick.blackjacktrainer.entity.Player;

public class ButtonFactory {
	
	private Skin buttonSkin;
	private TextureAtlas buttonAtlas;
	private BitmapFont font;
	private TextButtonStyle buttonStyle;
	
	private ImageButtonStyle plusButtonStyle;
	private ImageButtonStyle minusButtonStyle;
	
	private Label currentBet;
	
	
	public ButtonFactory() {
		buttonSkin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		buttonSkin.addRegions(buttonAtlas);
		font = new BitmapFont();
	}
	
	public TextButton createButton(String text, String released, String pressed) {
		
    	// Button Style
    	buttonStyle = new TextButtonStyle();
    	buttonStyle.font = font;
    	buttonStyle.up = buttonSkin.getDrawable(released);
    	buttonStyle.down = buttonSkin.getDrawable(pressed);
    	
    	return new TextButton(text, buttonStyle);		
	}
	
	/**
	 * Requires HumanPlayer so that we can attach appropriate listeners
	 * @param Humanplayer
	 * @return
	 */
	public Table createBetButton(final HumanPlayer player) {
		
		Drawable background = buttonSkin.getDrawable("bet");
		
		Table table = new Table();
		table.setBackground(background);
		
		Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		currentBet = new Label("dkl", uiSkin);
		currentBet.setText("0");
		table.add(currentBet).expandX().left().padLeft(15);
		
		// PLUS Button
		plusButtonStyle = new ImageButtonStyle();
		plusButtonStyle.up = buttonSkin.getDrawable("plus");
		
		ImageButton plus = new ImageButton(plusButtonStyle);
		plus.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				player.increaseBet(10);
				currentBet.setText(String.valueOf(player.getBet()));
			}
		});
		table.add(plus).right().padRight(5f).padBottom(5f);
		
		// MINUS Button
		minusButtonStyle = new ImageButtonStyle();
		minusButtonStyle.up = buttonSkin.getDrawable("minus");
		
		ImageButton minus = new ImageButton(minusButtonStyle);
		minus.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				player.decreaseBet(10);
				currentBet.setText(String.valueOf(player.getBet()));
			}
		});
		
		table.row();
		table.add();
		table.add(minus).expandX().right().padRight(5f);
		//table.debug();
    	return table;		
	}
	
	public void dispose() {
		buttonSkin.dispose();
		buttonAtlas.dispose();
		font.dispose();
	}
	
}
