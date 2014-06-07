package com.hatstick.blackjacktrainer.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonFactory {
	
	private Skin buttonSkin;
	private TextureAtlas buttonAtlas;
	private BitmapFont font;
	private TextButtonStyle buttonStyle;
	
	private ImageButtonStyle imageButtonStyle;
	private ImageButtonStyle plusButtonStyle;
	private ImageButtonStyle minusButtonStyle;
	
	
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
	
	public Table createBetButton() {
		
		Drawable background = buttonSkin.getDrawable("bet");
		
		Table table = new Table();
		table.setBackground(background);
		
		plusButtonStyle = new ImageButtonStyle();
		plusButtonStyle.up = buttonSkin.getDrawable("plus");
		table.add(new ImageButton(plusButtonStyle)).expandX().right().padRight(5f).padBottom(5f);
		
		minusButtonStyle = new ImageButtonStyle();
		minusButtonStyle.up = buttonSkin.getDrawable("minus");
		table.row();
		table.add(new ImageButton(minusButtonStyle)).right().padRight(5f);
  
    	return table;		
	}
}
