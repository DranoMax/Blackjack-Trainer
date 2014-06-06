package com.hatstick.blackjacktrainer.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ButtonFactory {
	
	private Skin buttonSkin;
	private TextureAtlas buttonAtlas;
	private BitmapFont font;
	private TextButtonStyle buttonStyle;
	
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
}
