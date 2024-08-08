package com.mygdx.game;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
    protected SoaringAdventure game;

    public AbstractScreen(SoaringAdventure game) {
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}