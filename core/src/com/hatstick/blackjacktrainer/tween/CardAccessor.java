package com.hatstick.blackjacktrainer.tween;

import com.hatstick.blackjacktrainer.entity.Card;
import aurelienribon.tweenengine.TweenAccessor;

public class CardAccessor implements TweenAccessor<Card> {

	public static final int POSITION_X = 1;
	public static final int POSITION_Y = 2;
	public static final int POSITION_XY = 3;

	@Override
	public int getValues(Card target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			return 2;
		default: assert false; return -1;
		}
	}

	@Override
	public void setValues(Card target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_XY:
			target.setPosition(newValues[0],newValues[1]);
			break;
		default: assert false; break;
		}
	}

}
