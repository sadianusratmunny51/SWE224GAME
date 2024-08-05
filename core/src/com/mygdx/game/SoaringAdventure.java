package com.mygdx.game;

import com.badlogic.gdx.Game;

public class SoaringAdventure extends Game {
	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));
		//setScreen(new Level2Screen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
