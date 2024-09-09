package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.InputAdapter;

public class HelpScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Texture background;
    private Texture ok;
    private Rectangle okBounds;
    private boolean transitioning;

    public HelpScreen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        background = new Texture("helpBackground.png");
        ok = new Texture("ok.png");

        okBounds = new Rectangle((Gdx.graphics.getWidth() - 300) / 2, Gdx.graphics.getHeight() / 2 - 300, 150, 70);
        transitioning = false;


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 touchPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if (okBounds.contains(touchPos) && !transitioning) {
                    transitioning = true;
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(ok, okBounds.x, okBounds.y, okBounds.width, okBounds.height);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ok.dispose();
        batch.dispose();
    }
}
